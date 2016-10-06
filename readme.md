## User Authentication Web Service

This application can be used for publishing the web service endpoint for user authorization. 
Client sends login and password, service tries to get user with passed login from the storage (/UserAuthWebService/storage/users.xml), 
compares passwords and returns result to the client. 

## Installation
Application requires JRE 8.
To start application run UserAuthWebService.bat from command line.

## API Reference

Main class of the program is UserAuthWebService/src/main/UserAuthWebService.java
Client implementation is com.userauth.ws.client.UserAuthServiceClient.

All commands can be called from cmd window after UserAuthWebService.bat has been run.  
To call endpoint use command `authorize user_login user_password`  
To stop web service use command `stop`
