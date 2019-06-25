# Spring Security Login with Vaadin UI Example

Integrates a Vaadin UI i18n login form with Spring Security. Passwords
are hashed using bycrpt and stored in a MySQL database. After logging
in, a user will be redirected to a empty dashboard that can be used
for whatever. There is also a log out page that will clear the Spring
security context for the signed in user and redirect to the login page.

The project includes a docker-compose file that will start the project
as well as a MySQL database server. You will need to install MySQL
as well as Docker if you want to run the project in a container.

License: MIT

Once you start the project you can navigate to:

    localhost:8080

An account is automatically created with the following credentials:

    username: admin@dashboard.com
    password: admin
    
## Building and Running

#### Creating the Database
First, to use a MySQL database, which is what the project is set up for,
you will either need to create the database before running or set the dll-auto 
option to CREATE in the Spring settings file which will create the database on 
the first run. After creating the database on the first run, change the dll-auto 
setting back to update.

The Spring settings file is the normal application.properties file
for Spring projects located under src/main/resources. In the
application.properties file change the ddl-auto setting to:

    spring.jpa.hibernate.ddl-auto=CREATE
  
Next, run the project through Docker (discussed below) which will create the database. 
Afterward, change this setting back to UPDATE:

    spring.jpa.hibernate.ddl-auto=UPDATE
    
If you do not want to use Docker, then you will also want to change
the spring.datasource.url, spring.datasource.username, and spring.datasource.password 
to your database url, username, and password.

If you are unfamiliar with Spring and SQL databases than you may
want to read the Spring documentation on working with SQL databases: 
https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-sql.html

#### Building and running using the included build tool 

If you have python 3 installed then you can use the mini build tool 
included in the project. The build tool should just work, however, in case
there is an issue you may want to set up virtualenv under the build_tool
folder.

To build the projects docker container:

    $ python3 build_tool/run.py build
    
To run the projects docker container:

    $ python3 build_tool/run.py run

To build and run the projects docker container:

    $ python3 build_tool/run.py dev
    
To exit press CTRL-C and the containers will shutdown and be removed.

#### Building and running with Gradle

If you don't have python 3 you can use the Gradle build tool. 
The build.gradle file includes the plugin for Google's 
Jib tool for building Docker containers from Gradle:

    gradle jibDockerBuild

next run the docker-compose file from the projects root folder

    docker-compose up
    
You can change the name of the container in the gradle.build file:
    
    jib.to.image = ["<hub-user>/<repo-name>:<tag>"]
