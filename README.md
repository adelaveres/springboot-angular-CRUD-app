# springboot-angular-CRUD
Spring Boot - Angular - Hibernate - Maven - CRUD application


Installation steps:

1. install java- JDK 1.7 or above
	https://java.com/en/download/
	
2. download and install the Eclipse IDE for Java EE Developers
	https://www.eclipse.org/downloads/packages/eclipse-ide-java-ee-developers/neon3
	
3. install maven- download the binary distribution:
	https://maven.apache.org/download.cgi
	
	3.1. unzip the archive:
	
		Unix-based operating systems (Linux, Solaris and Mac OS X)
			tar zxvf apache-maven-3.x.y.tar.gz
		Windows
			unzip apache-maven-3.x.y.zip

	3.2. A directory called "apache-maven-3.x.y" will be created.

	3.3. Add the bin directory to your PATH, eg:
	
		Unix-based operating systems (Linux, Solaris and Mac OS X)
		  export PATH=/usr/local/apache-maven-3.x.y/bin:$PATH
		Windows
		  set PATH="c:\program files\apache-maven-3.x.y\bin";%PATH%

	3.4. Make sure JAVA_HOME is set to the location of your JDK

	3.5. Run "mvn --version" to verify that it is correctly installed.
	
4. M2E plugin for Eclipse
	install the latest M2Eclipse release by using the following update site from within Eclipse:
	http://download.eclipse.org/technology/m2e/releases
	
Accessing application:
● Employee View: 

	--username: employee
	--password: empl
	
● Admin View:

	--username: admin
	--password: adm

Use cases screenshots:

Login:
 ![alt tag](screenshots/Login.jpg)

Login-employee:
 ![alt tag](screenshots/Login-employee.jpg)

Login-admin:
![alt tag](screenshots/Login-admin.jpg)
 
	Loged in as employee:

Employee page:
 ![alt tag](screenshots/Employee-view1.jpg)
 ![alt tag](screenshots/Employee-view2.jpg)

Adding Client-CNP exists:
 ![alt tag](screenshots/Employee-addClient-CNPexists.jpg)

Adding Client (same details, correct CNP):
![alt tag](screenshots/Employee-addClient.jpg)
 
Delete Client (Teodora Barbulescu):
-	before
![alt tag](screenshots/Employee-beforeDelet-TeodoraBarbulescu.jpg)
 
-	after
![alt tag](screenshots/Employee-afterDelete-TeodoraBarbulescu.jpg)
 

Update Client (Andreescu Silviu):
 ![alt tag](screenshots/Employee-updateClient1.jpg)

Reset Form:
 ![alt tag](screenshots/Employee-resetForm.jpg)

Select Client Accounts (Hans Chr. Andersen):
 ![alt tag](screenshots/Employee-selectAccounts-HansChrAnd.jpg)

Deposit in account 
-	initially:
![alt tag](screenshots/Employee-depositInAccount-1-HansChrAnd.jpg)

-	after Refresh:
![alt tag](screenshots/Employee-depositInAccount-2-afterRefresh-HansChrAnd.jpg) 

Withdraw from Account:
-	initially:
![alt tag](screenshots/Employee-withdrawFromAccount-1-beforeRefresh-HansChrAnd.jpg)

-	after Refresh:
![alt tag](screenshots/Employee-withdrawFromAccount-2-afterRefresh-HansChrAnd.jpg) 

Transfer between accounts:
![alt tag](screenshots/Employee-transfer-1-HansChrAnd.jpg)

after Refresh:
 ![alt tag](screenshots/Employee-transfer-2-HansChrAnd.jpg)

Add Client Account:
![alt tag](screenshots/Employee-addAccount-1-HansChrAnd.jpg)
![alt tag](screenshots/Employee-addAccount-2-HansChrAnd.jpg)

after Refresh:
 ![alt tag](screenshots/Employee-addAccount-3-HansChrAnd.jpg)

Delete Client Account:
 ![alt tag](screenshots/Employee-deleteAccount-1-HansChrAnd.jpg)

after Refresh:
![alt tag](screenshots/Employee-deleteAccount-2-HansChrAnd.jpg)
 

	Logged in as admin:

Admin page:
 ![alt tag](screenshots/Admin-view1.jpg)
 ![alt tag](screenshots/Admin-view2.jpg)

Add Employee:
 ![alt tag](screenshots/Admin-addEmpl-1.jpg)
 ![alt tag](screenshots/Admin-addEmpl-2.jpg)
 
Add Employee with existing CNP:
 ![alt tag](screenshots/Admin-addEmpl-3-identicalCNP.jpg)
 
Delete Employee (Maria Nicoara):
-	before:
![alt tag](screenshots/Admin-beforeDelete.jpg)

-	after:
![alt tag](screenshots/Admin-afterDelete.jpg)

Update Employee:
![alt tag](screenshots/Admin-updateEmpl-1.jpg) 
![alt tag](screenshots/Admin-updateEmpl-2.jpg) 
