# Resources Events Agents (REA) example
## Preface
[REA](http://en.wikipedia.org/wiki/Resources,_events,_agents_%28accounting_model%29) (Resource, Events, Agents) is popular model for teaching and designing accounting information systems. The model is originally proposed by William E. McCarthy in 1982. Although number of books with this subject are written in the past, there are no many examples and concrete implementations that can be found on the internet. Thus, the main reason for writing this sample application is learning REA and trying to practically apply theory behind the model.

The book [Model-Driven Design Using Business Patterns](http://www.springer.com/us/book/9783540301547) by [Pavel Hruby](http://phruby.com/) explaines REA accounting model in great details and introduces very interesting business patterns based on real-life problems and situations. Examples in the book are following a small pizzeria dealing with exchange and conversion processes on day-to-day basis. This project is Java Spring implementation of simple exchange process and Identification pattern, as described in the book.

This document will describe how to import REA example into Spring STS IDE, how to run it and change few configuration options in application. 

## Getting Started

Source code in this repository is Java Spring implementation of simple REA exchange process and Identification pattern (based on aspect-oriented programming). Project uses in-memory database (H2) so it is very simple to try this example on any machine that can run Java and [Spring STS tool](https://spring.io/tools/sts) (which is actually a modified Eclipse tool).

If you wish to try this example, please download source code from repository clicking on [link](https://github.com/almirpehratovic/REA/archive/master.zip). Extract the project somewhere on disk and turn on Spring STS. Inside Spring STS import project by choosing File - Import - Maven - Existing Maven Project and navigating to extracted folder. Spring STS will import project and download all java dependencies configured in pom.xml file.

This project is using various Java anntotations that are not compatible with older java versions so we have to change compatibility to Java 1.6. In project properties (right click on project name) in Java Compiler section choose Compiler compliance level as 1.6, and in Java Build Path section on Libraries tab remove JRE 1.5 library and add your installed Java sdk version greater than 1.5. Spring STS will rebuild the project.

Finally, to run the example, right click on the project and choose Run as - Maven build. As a goal type tomcat7:run. First time Spring STS will download tomcat7 web server and at the end run the app. To try application, in favorite web browser type http://localhost:8080/rea/pizzeria and that's it. 

## Trying and changing the application
Username and password are user/user. Links on the left side of application enable entering new pizza and sale, and configuring identification setup (Id rules). Forms and interfaces are very simple and easy to use.

Java code is divided into java packages. Main REA classes are contained in ba.ocean.jrea.domain.core package, and domain objects that are extending REA entities (Pizza, Cash etc) are contained in ba.ocean.pizzeria.domain package.

Identification pattern is enabled by default. Configuration for this pattern can be found in file src/main/resources/META-INF/spring/aop-context.xml

```html
<bean id="identificationPattern" class="ba.ocean.pizzeria.behaviour.IdentificationPattern">
	<property name="autoNumber" value="false"/>
 	<property name="setupFile" value="classpath:META-INF/spring/idSetup.properties"/>
</bean>
	 
<aop:config>
	<aop:pointcut expression="execution(* *save(ba.ocean.pizzeria.domain.Pizza))" id="savePizza"/>
 	<aop:pointcut expression="execution(* *save(ba.ocean.pizzeria.domain.CashReceipt))" id="saveCashReceipt"/>
 	<aop:aspect ref="identificationPattern">
 		<aop:around method="modifyArgument" pointcut-ref="savePizza"/>
 		<aop:around method="modifyArgument" pointcut-ref="saveCashReceipt"/>
 	</aop:aspect>
</aop:config>
```

Commenting the line with propriate aop:around tag would disable Identification pattern on Pizza and/or CashReceipt objects. For any change of this or any other file it is necessary to stop the app and re-run it after the change.

Persistence in project is configured with JPA and Hibernate combination. By default, application is configured to run on in-memory H2 database, but changing the database is pretty easy. For example, projects contains database script for creating tables and initial records on postgreSQL database. After creating database structure all that is necessary is to change few configurations:

* In src/main/resources/META-INF/spring/jdbc-postgresql.properties file add connection properties:
```html
jdbc.driverClassName=org.postgresql.Driver
jdbc.url=jdbc:postgresql://132.147.201.39:5432/pizzeria
jdbc.username=postgres
jdbc.password=postgres
jdbc.properties=ssl=true;sslfactory=org.postgresql.ssl.NonValidatingFactory;
```

* In src/main/resources/META-INF/spring/datasource-jpa.xml and src/main/resources/META-INF/spring/security-context.xml files comment-out dataSource section and comment-in embedded database section:
```html
<context:property-placeholder location="classpath:META-INF/spring/jdbc-postgresql.properties"/>

   <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
    	<property name="driverClassName">
    		<value>${jdbc.driverClassName}</value>
    	</property>
    	<property name="url">
    		<value>${jdbc.url}</value>
    	</property>
    	<property name="username">
    		<value>${jdbc.username}</value>
    	</property>
    	<property name="password">
    		<value>${jdbc.password}</value>
    	</property>
    	<property name="connectionProperties">
    		<value>${jdbc.properties}</value>
    	</property>
   </bean>
     
   <!-- 
    <jdbc:embedded-database id="dataSource" type="H2">
	 	<jdbc:script location="classpath:META-INF/spring/pizzeria-h2.sql"/>
	 </jdbc:embedded-database>
   -->
```

* In same file change appropriate database dialect:
```html
<prop key="hibernate.dialect">org.hibernate.dialect.PostgreSQLDialect</prop>  
<!-- <prop key="hibernate.dialect">org.hibernate.dialect.H2Dialect</prop> -->
```

