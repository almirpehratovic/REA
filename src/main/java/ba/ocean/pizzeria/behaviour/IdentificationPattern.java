package ba.ocean.pizzeria.behaviour;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ba.ocean.jrea.domain.core.Agent;
import ba.ocean.jrea.domain.core.Event;
import ba.ocean.jrea.domain.core.Resource;
import ba.ocean.pizzeria.service.PizzeriaService;

/**
 * @author Almir Pehratovic
 * @version 0.1
 * @since 05-2015
 * 
 *        Identification pattern is the aspect that can give an identity to
 *        specified objects or REA entities. This implementation provide
 *        auto-numbering or setup based on date patterns. If autoNumber is
 *        false, then users of application can configure this pattern in UI.
 *        Identification setups are stored in database. Every setup has xml
 *        structure with these tags: static, date, local. For example
 *        <static>P-</static><field length="2"
 *        case="upper">name</field><date>yyMM-ddS</date><static>-R</static>
 */

@Component
public class IdentificationPattern {
	@Autowired
	private PizzeriaService pizzeriaService;

	private boolean autoNumber;

	public boolean isAutoNumber() {
		return autoNumber;
	}

	public void setAutoNumber(boolean autoNumber) {
		this.autoNumber = autoNumber;
	}

	/**
	 * This is typical Spring advice. The goal is to intercept saving of various
	 * REA entities and change them before going to database. Method assumes
	 * that every REA entity has 'name' property. Configuration for aop
	 * intercepting can be found in aop-context.xml file.
	 */
	public Object modifyArgument(ProceedingJoinPoint joinpoint)
			throws Throwable {
		System.out.println("*** ID PATTERN "
				+ joinpoint.getArgs()[0].getClass().getSimpleName());

		if (joinpoint.getArgs()[0] instanceof Event) {
			Event event = (Event) joinpoint.getArgs()[0];
			List<IdentificationSetup> setups = pizzeriaService
					.findIdentificationSetupsByEntity(event.getClass()
							.getName());
			if (setups.size() > 0) {
				IdentificationSetup setup = setups.get(0);
				if (!autoNumber) {
					event.setName(parseSetup(event, setup, pizzeriaService));
				} else {
					setup.setLastId(setup.getLastId() + 1);
					event.setName("" + setup.getLastId());
					pizzeriaService.save(setup);
				}
			}
		} else if (joinpoint.getArgs()[0] instanceof Resource) {
			Resource resource = (Resource) joinpoint.getArgs()[0];
			List<IdentificationSetup> setups = pizzeriaService
					.findIdentificationSetupsByEntity(resource.getClass()
							.getName());
			if (setups.size() > 0) {
				IdentificationSetup setup = setups.get(0);
				if (!autoNumber) {
					resource.setName(parseSetup(resource, setup, pizzeriaService));
				} else {
					setup.setLastId(setup.getLastId() + 1);
					resource.setName("" + setup.getLastId());
					pizzeriaService.save(setup);
				}
			}
		} else if (joinpoint.getArgs()[0] instanceof Agent) {
			Agent agent = (Agent) joinpoint.getArgs()[0];
			List<IdentificationSetup> setups = pizzeriaService
					.findIdentificationSetupsByEntity(agent.getClass()
							.getName());
			if (setups.size() > 0) {
				IdentificationSetup setup = setups.get(0);
				if (!autoNumber) {
					agent.setName(parseSetup(agent, setup, pizzeriaService));
				} else {
					setup.setLastId(setup.getLastId() + 1);
					agent.setName("" + setup.getLastId());
					pizzeriaService.save(setup);
				}
			}
		}

		Object ret = joinpoint.proceed();
		return ret;
	}

	private String parseSetup(Object target, IdentificationSetup setup, PizzeriaService service) {
		if (setup.getPattern() == null) {
			return "";
		} else {
			String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><config>"
					+ setup.getPattern() + "</config>";
			SAXParserFactory spf = SAXParserFactory.newInstance();
			try {
				SAXParser parser = spf.newSAXParser();
				XmlParserHandler handler = new XmlParserHandler(target,setup);
				parser.parse(new InputSource(new StringReader(xml)), handler);
				if (handler.isAutoNumbered()) {
					service.save(handler.getSetup());
				}
				return handler.getText();
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
	}

	private class XmlParserHandler extends DefaultHandler {

		private Object obj;
		private IdentificationSetup setup;
		
		private StringBuffer text = new StringBuffer();
		private String tag;
		private String pattern;
		private String compiledText;
		private String attCase;
		private int attLength = -1;
		private boolean autoNumbered = false;

		public XmlParserHandler(Object obj,IdentificationSetup setup) {
			this.obj = obj;
			this.setup = setup;
		}

		@Override
		public void startElement(String namespaceURI, String localName,
				String qName, Attributes attributes) throws SAXException {
			tag = qName.toLowerCase();
			for (int i = 0; i < attributes.getLength(); i++) {
				if (attributes.getQName(i).equals("length")) {
					attLength = Integer.parseInt(attributes.getValue(i));
				} else if (attributes.getQName(i).equals("case")) {
					attCase = attributes.getValue(i);
				}
			}
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			String pattern = new String(ch, start, length);

			if (tag.equals("static")) {
				compiledText = pattern;
			} else if (tag.equals("date")) {
				System.out.println("*** pattern = " + pattern);
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				compiledText = format.format(new Date());
			} else if (tag.equals("field")) {
				try {
					String methodName = "get"
							+ pattern.substring(0, 1).toUpperCase()
							+ pattern.substring(1);
					Method method = obj.getClass().getMethod(methodName, null);
					compiledText = (String) method.invoke(obj, null);
				} catch (Exception e) {
					throw new SAXException(e);
				}
			} else if (tag.equals("id")) {
				setup.setLastId(setup.getLastId() + 1);
				compiledText = ""+setup.getLastId();
				autoNumbered=true;
			}

			if (attLength != -1) {
				try {
					compiledText = compiledText.substring(0, attLength);
				} catch (StringIndexOutOfBoundsException e) {
					
				}
			}

			if (attCase != null) {
				if (attCase.equals("upper")) {
					compiledText = compiledText.toUpperCase();
				} else if (attCase.equals("lower")) {
					compiledText = compiledText.toLowerCase();
				}
			}

		}

		@Override
		public void endElement(String namespaceURI, String localName,
				String qName) throws SAXException {
			text.append(compiledText);
			tag = "";
			pattern = "";
			attLength = -1;
			attCase = "";
			compiledText = "";
		}
		
		public boolean isAutoNumbered() {
			return autoNumbered;
		}
		
		public IdentificationSetup getSetup() {
			return setup;
		}

		public String getText() {
			return text.toString();
		}
	}
}
