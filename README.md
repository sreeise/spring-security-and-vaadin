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

If you have python 3 installed then you can use the mini build tool 
included in the project:

To build the projects docker container:

    $ python3 build_tool/run.py build
    
To run the projects docker container:

    $ python3 build_tool/run.py run

To build and run the projects docker container:

    $ python3 build_tool/run.py dev
    
To exit press CTRL-C and the containers will shutdown and be removed.

If you don't have python 3 you can use the Gradle build tool. 
The build.gradle file includes the plugin for Google's 
Jib tool for building Docker containers from Gradle:

    gradle jibDockerBuild
    
next run the docker-compose file from the projects root folder

    docker-compose up
    
You can change the name of the container in the gradle.build file:
    
    jib.to.image = ["<hub-user>/<repo-name>:<tag>"] 
