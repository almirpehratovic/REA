package ba.ocean.pizzeria.behaviour;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import ba.ocean.jrea.domain.core.Event;
import ba.ocean.jrea.domain.core.Resource;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * Identification pattern is the aspect that can give an identity to specified objects or REA entities.
 * This implementation provide auto-numbering or setup based on date patterns. If autoNumber is false,
 * then users of application can configure this pattern in UI. Instead of database, all configurations
 * are kept in setupFile.  
 */

@Component
public class IdentificationPattern {
	private boolean autoNumber;
	private org.springframework.core.io.Resource setupFile;
	
	public boolean isAutoNumber() {
		return autoNumber;
	}

	public void setAutoNumber(boolean autoNumber) {
		this.autoNumber = autoNumber;
	}
	
	
	public org.springframework.core.io.Resource getSetupFile() {
		return setupFile;
	}

	public void setSetupFile(org.springframework.core.io.Resource setupFile) {
		this.setupFile = setupFile;
	}
	
	/**
	 * This is typical Spring advice. The goal is to intercept saving of various REA entities
	 * and change them before going to database. Method assumes that every REA entity has 'name'
	 * property. Configuration for aop intercepting can be found in aop-context.xml file.
	 */
	public Object modifyArgument(ProceedingJoinPoint joinpoint) throws Throwable{
		System.out.println("*** ID PATTERN " + joinpoint.getArgs()[0].getClass().getSimpleName());
		
		
		if (joinpoint.getArgs()[0] instanceof Event){
			Event event = (Event)joinpoint.getArgs()[0];
			Properties props = new Properties();
			try {
				props.load(getSetupFile().getInputStream());
				SimpleDateFormat format = new SimpleDateFormat(props.getProperty("cashReceipt.setup.pattern"));
				String newId = (Integer.parseInt(props.getProperty("cashReceipt.setup.lastId")) + 1) + "";
				if (!autoNumber) {
					newId = props.getProperty("cashReceipt.setup.prefix") + "-" + 
							format.format(new Date()) + "-" + props.getProperty("cashReceipt.setup.suffix");
				} else {
					props.setProperty("cashReceipt.setup.lastId", newId);
					File file = getSetupFile().getFile();
					props.store(new FileOutputStream(file),"setup za identifikacijski pattern");
				}
				event.setName(newId);
				Object ret = joinpoint.proceed(new Object[]{event});
				return ret;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		} else if (joinpoint.getArgs()[0] instanceof Resource){
			Resource resource = (Resource)joinpoint.getArgs()[0];
			
			Properties props = new Properties();
			try {
				props.load(getSetupFile().getInputStream());
				SimpleDateFormat format = new SimpleDateFormat(props.getProperty("pizza.setup.pattern"));
				String newId = (Integer.parseInt(props.getProperty("pizza.setup.lastId")) + 1) + "";
				if (!autoNumber) {
					newId = props.getProperty("pizza.setup.prefix") + "-" + 
							format.format(new Date()) + "-" + props.getProperty("pizza.setup.suffix");
				} else {
					props.setProperty("pizza.setup.lastId", newId);
					File file = getSetupFile().getFile();
					props.store(new FileOutputStream(file),"setup za identifikacijski pattern");
				}
				resource.setName(newId);
				Object ret = joinpoint.proceed(new Object[]{resource});
				return ret;
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		Object ret = joinpoint.proceed();
		return ret;
	}
}
