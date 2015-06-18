package ba.ocean.pizzeria.behaviour;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ba.ocean.jrea.domain.core.Agent;
import ba.ocean.jrea.domain.core.Event;
import ba.ocean.jrea.domain.core.Resource;
import ba.ocean.pizzeria.service.PizzeriaService;

/**
 * @author 		Almir Pehratovic
 * @version  	0.1
 * @since		05-2015
 * 
 * Identification pattern is the aspect that can give an identity to specified objects or REA entities.
 * This implementation provide auto-numbering or setup based on date patterns. If autoNumber is false,
 * then users of application can configure this pattern in UI. Identification setups are stored in database
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
	 * This is typical Spring advice. The goal is to intercept saving of various REA entities
	 * and change them before going to database. Method assumes that every REA entity has 'name'
	 * property. Configuration for aop intercepting can be found in aop-context.xml file.
	 */
	public Object modifyArgument(ProceedingJoinPoint joinpoint) throws Throwable{
		System.out.println("*** ID PATTERN " + joinpoint.getArgs()[0].getClass().getSimpleName());
		
		if (joinpoint.getArgs()[0] instanceof Event){
			Event event = (Event)joinpoint.getArgs()[0];
			List<IdentificationSetup> setups = pizzeriaService.findIdentificationSetupsByEntity(event.getClass().getName());
			if (setups.size()>0) {
				IdentificationSetup setup = setups.get(0);
				if (!autoNumber) {
					SimpleDateFormat format = new SimpleDateFormat(setup.getPattern());
					event.setName(setup.getPrefix()+"-"+format.format(new Date())+"-"+setup.getSuffix());
				} else {
					setup.setLastId(setup.getLastId()+1);
					event.setName(""+setup.getLastId());
					pizzeriaService.save(setup);
				}
			}
		} else if (joinpoint.getArgs()[0] instanceof Resource){
			Resource resource = (Resource)joinpoint.getArgs()[0];
			List<IdentificationSetup> setups = pizzeriaService.findIdentificationSetupsByEntity(resource.getClass().getName());
			if (setups.size()>0) {
				IdentificationSetup setup = setups.get(0);
				if (!autoNumber) {
					SimpleDateFormat format = new SimpleDateFormat(setup.getPattern());
					resource.setName(setup.getPrefix()+"-"+format.format(new Date())+"-"+setup.getSuffix());
				} else {
					setup.setLastId(setup.getLastId()+1);
					resource.setName(""+setup.getLastId());
					pizzeriaService.save(setup);
				}
			}
		} else if (joinpoint.getArgs()[0] instanceof Agent){
			Agent agent = (Agent)joinpoint.getArgs()[0];
			List<IdentificationSetup> setups = pizzeriaService.findIdentificationSetupsByEntity(agent.getClass().getName());
			if (setups.size()>0) {
				IdentificationSetup setup = setups.get(0);
				if (!autoNumber) {
					SimpleDateFormat format = new SimpleDateFormat(setup.getPattern());
					agent.setName(setup.getPrefix()+"-"+format.format(new Date())+"-"+setup.getSuffix());
				} else {
					setup.setLastId(setup.getLastId()+1);
					agent.setName(""+setup.getLastId());
					pizzeriaService.save(setup);
				}
			}
		}
		
		Object ret = joinpoint.proceed();
		return ret;
	}
}
