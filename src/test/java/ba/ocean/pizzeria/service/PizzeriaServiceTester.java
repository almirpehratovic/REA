package ba.ocean.pizzeria.service;

import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ba.ocean.jrea.domain.core.Agent;
import ba.ocean.jrea.domain.core.Event;
import ba.ocean.jrea.domain.core.Resource;
import ba.ocean.pizzeria.behaviour.IdentificationPattern;
import ba.ocean.pizzeria.domain.Cash;
import ba.ocean.pizzeria.domain.CashReceipt;
import ba.ocean.pizzeria.domain.Customer;
import ba.ocean.pizzeria.domain.Pizza;
import ba.ocean.pizzeria.domain.Pizzeria;
import ba.ocean.pizzeria.domain.Sale;

public class PizzeriaServiceTester {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:META-INF/spring/pizzeria-app-context.xml");
		ctx.refresh();
		
		PizzeriaService service = ctx.getBean("pizzeriaService",PizzeriaService.class);
		
		PizzeriaServiceTester tester = new PizzeriaServiceTester();
		//tester.parseXml();
		
		Pizza p = new Pizza();
		p.setName("funghi");
		
		Object o = p;
		try {
			Method method = o.getClass().getMethod("getName",null);
			System.out.println(method.invoke(o, null));
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private void parseXml() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><config>" +
				"<static length=\"2\">PIZZA-</static><date>yyMM-ddS</date><static>-R</static>" +
				//"<a>10</a>" +
				"</config>";
		System.out.println(xml);
		
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			SAXParser parser = spf.newSAXParser();
			XmlParserHandler handler = new XmlParserHandler();
			//parser.parse(xml, handler);
			parser.parse(new InputSource(new StringReader(xml)),handler);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private class XmlParserHandler extends DefaultHandler{
		
		private List<String> list = new ArrayList<String>();
		private String text;
		private int textLength = -1;

		@Override
		public void startElement(String namespaceURI, String localName, String qName,Attributes attributes) throws SAXException {
			text = qName+":";
			for (int i=0; i<attributes.getLength(); i++) {
				if (attributes.getQName(i).equals("length")) {
					textLength = Integer.parseInt(attributes.getValue(i));
					break;
				}
			}
		}
		
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			String pom = new String(ch,start,length);
			if (textLength != -1)
				text += pom.substring(0,textLength);
			else
				text += pom;
		}
		
		@Override
		public void endElement(String namespaceURI, String localName, String qName)
				throws SAXException {
			System.out.println(text);
			text="";
			textLength = -1;
		}
		
	}

}
