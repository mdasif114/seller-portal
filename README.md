## SBT General
This project can be started either directly from command line or by IDE.

1) Below are the ways to launch application
    ## Maven command
        * Execute "mvn clean spring-boot:run" command from command line

    ## IDE excution
        * execute SellerPortalApplication.java file to launch the application

2) By default, application will be started on 8080 port. If you want to start on different port, configure "server.port=<port-value> in application.properties file.
    Ex: server.port=8080

3) Access URL, http://localhost:8080 from browser to launch the application.

3) To access H2 database, access URL by appending "h2-console" at the end of running web application
    Ex: http://localhost:8080/h2-console/

