package ba.ocean.pizzeria.behaviour;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Component;

import ba.ocean.jrea.domain.core.DecrementEvent;
import ba.ocean.jrea.domain.core.Event;
import ba.ocean.jrea.domain.core.Resource;
import ba.ocean.pizzeria.domain.Pizza;

@Component
public class IdentificationPattern /*extends DynamicMethodMatcherPointcut implements MethodBeforeAdvice */{
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
	
	
	/*public List<String> getPatternClasses() {
		return patternClasses;
	}

	public void setPatternClasses(List<String> patternClasses) {
		this.patternClasses = patternClasses;
	}

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		String newId = "";
		if (target instanceof Resource){
			SimpleDateFormat format = new SimpleDateFormat("yyMM-ddS");
			newId = target.getClass().getSimpleName().substring(0,1) + "-" + 
					format.format(new Date()) + "-R";
		} else {
			SimpleDateFormat format = new SimpleDateFormat("yyMM-ddS");
			newId = target.getClass().getSimpleName().substring(0,1) + "-" + 
					format.format(new Date()) + "-X";
		}		
		args[0] = newId;
	}
	
	
	@Override
	public boolean matches(Method method, Class<?> cls, Object[] args) {
		if (patternClasses.contains(cls.getName()) && 
				method.getName().equals("setName")){
			return true;
		}
		return false;
	}
	
	*/
	

	
}
