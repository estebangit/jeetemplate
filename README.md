JEETemplate: Deployment of an EAR Containing a JSF/rest WAR and EJB/JPA JAR
====================================================================
Author: Esteban Barajas  
Level: Intermediate  
Technologies: EJB, JPA, rest, EAR  
Summary: The `JEETemplate` demonstrates how to deploy an EAR archive that contains a JEE application.  
Target Product: JBoss EAP  
Source: <https://github.com/estebangit/jeetemplate/>  
Based on <https://github.com/jboss-developer/jboss-eap-quickstarts/tree/7.0.x/ejb-in-ear>


What is it?
-----------

The `JEETemplate` demonstrates the deployment of an EAR artifact to Red Hat JBoss Enterprise Application Platform. The EAR contains: *JSF* WAR and an *EJB* JAR.

The example is composed of five Maven projects, each with a shared parent. The projects are as follows:

1. `ejb`: This project contains the EJB code and can be built independently to produce the JAR archive.

2. `web`: This project contains the JSF/rest pages and the managed bean.

3. `ear`: This project builds the EAR artifact and pulls in the EJB and Web artifacts.

4. `public`: This project contains beans or interfaces.

5. `test`: This project contains integration tests.


The root `pom.xml` builds each of the subprojects in the above order and deploys the EAR archive to the server.

The example follows the common "Hello World" pattern. These are the steps that occur:

1. A JSF page asks the user for their name.
2. On clicking _Greet_, the name is sent to a managed bean named `Greeter`.
3. On setting the name, the `Greeter` invokes the `GreeterEJB`, which was injected to the managed bean. Notice the field annotated with `@EJB`.
4. The response from invoking the `HelloEJB` is stored in a field (message) of the managed bean.
5. The managed bean is annotated as `@SessionScoped`, so the same managed bean instance is used for the entire session. This ensures that the message is available when the page reloads and is displayed to the user.

System requirements
-------------------

The application this project produces is designed to be run on Red Hat JBoss Enterprise Application Platform 7 or later. 

All you need to build this project is Java 8.0 (Java SDK 1.8) or later and Maven 3.1.1 or later. See [Configure Maven for JBoss EAP 7](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_MAVEN_JBOSS_EAP7.md#configure-maven-to-build-and-deploy-the-quickstarts) to make sure you are configured correctly for testing the quickstarts.


Use of EAP7_HOME
---------------

In the following instructions, replace `EAP7_HOME` with the actual path to your JBoss EAP installation. The installation path is described in detail here: [Use of EAP7_HOME and JBOSS_HOME Variables](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/USE_OF_EAP7_HOME.md#use-of-eap_home-and-jboss_home-variables).

        sudo gedit /etc/bash.bashrc

add at the end of the file:

        export EAP7_HOME=/usr/local/share/jboss
        
To load the changes, execute the following statement.

        source /etc/bash.bashrc
        
        

Start Oracle XE
-------------------------

        sudo service oracle-xe start
        

Stop Oracle XE
-------------------------

        sudo service oracle-xe stop
        

Run SQL Developer from the terminal
-------------------------

        sudo /opt/sqldeveloper/sqldeveloper.sh
        

Start the JBoss EAP Server
-------------------------

1. Open a command prompt and navigate to the root of the JBoss EAP directory.
2. The following shows the command line to start the server:

        For Linux:   $EAP7_HOME/bin/standalone.sh
        For Windows: EAP7_HOME\bin\standalone.bat


Build and Deploy the application
-------------------------

1. Make sure you have started the JBoss EAP server as described above.
2. Open a command prompt and navigate to the root directory of this application.
3. Type this command to build and deploy the archive:

        mvn clean install wildfly:deploy

4. This will deploy `ear/target/JEETemplate.ear` to the running instance of the server.


 
Configure Oracle in JBoss
---------------------
Sources: 

<https://access.redhat.com/documentation/en-us/red_hat_jboss_enterprise_application_platform/7.0/html-single/configuration_guide/#datasource_management>

<http://stackoverflow.com/questions/41461091/unable-to-create-oracle-data-source-in-jboss-eap-7-0-server>

Using:

    $EAP7_HOME/bin/jboss-cli.sh


1. Add the Oracle JDBC driver as a core module.
    
       module add --name=com.oracle --resources=ojdbc6.jar --dependencies=javax.api,javax.transaction.api


2. Register the Oracle JDBC driver

       /subsystem=datasources/jdbc-driver=oracle:add(driver-name=oracle,driver-module-name=com.oracle,driver-xa-datasource-class-name=oracle.jdbc.xa.client.OracleXADataSource)

3. Add Oracle datasource to JBoss

        data-source add --name=OracleDS --jndi-name=java:jboss/OracleDS --driver-name=oracle --connection-url=jdbc:oracle:thin:@localhost:1521:XE --user-name=myDBUser --password=myDBPassword --validate-on-match=true --background-validation=false --valid-connection-checker-class-name=org.jboss.jca.adapters.jdbc.extensions.oracle.OracleValidConnectionChecker --exception-sorter-class-name=org.jboss.jca.adapters.jdbc.extensions.oracle.OracleExceptionSorter --stale-connection-checker-class-name=org.jboss.jca.adapters.jdbc.extensions.oracle.OracleStaleConnectionChecker


The config file will be under :

/usr/local/share/jboss/standalone/configuration/standalone.xml


Access the application 
---------------------

The application will be running at the following URL <http://localhost:8090/JEETemplate>.

Enter a name in the input field and click the _Greet_ button to see the response.

For rest, <http://localhost:8090/JEETemplate/rest/hello>

Undeploy the Archive
--------------------

1. Make sure you have started the JBoss EAP server as described above.
2. Open a command prompt and navigate to the root directory of this application.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy


Run the JEETemplate in Red Hat JBoss Developer Studio or Eclipse
-------------------------------------

You can also start the server and deploy the quickstarts or run the Arquillian tests from Eclipse using JBoss tools. For general information about how to import a quickstart, add a JBoss EAP server, and build and deploy a quickstart, see [Use JBoss Developer Studio or Eclipse to Run the Quickstarts](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/USE_JBDS.md#use-jboss-developer-studio-or-eclipse-to-run-the-quickstarts) 

For this quickstart, follow the special instructions to build [Quickstarts Containing an EAR](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/USE_JBDS.md#quickstarts-containing-an-ear)

1. Right-click on the `JEETemplate-ear` subproject, and choose `Run As` --> `Run on Server`.
2. Choose the server and click `Finish`. 
3. This starts the server, deploys the application, and opens a browser window that accesses the running application at <http://localhost:8090/JEETemplate>.


Debug the Application
---------------------

If you want to debug the source code of any library in the project, run the following command to pull the source into your local repository. The IDE should then detect it.

        mvn dependency:sources


PowerShell
-----------


<https://github.com/PowerShell/PowerShell/blob/master/docs/installation/linux.md#ubuntu-1604>
