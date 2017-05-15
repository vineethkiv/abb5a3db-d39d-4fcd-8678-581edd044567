# abb5a3db-d39d-4fcd-8678-581edd044567

# Project contents
1. AccountInquiryInterface
2. AccountInquiryService
3. AccountServiceClient

# AccountInquiryInterface 
Service entry point.
Can be shared as service contract to consuming developers.

# AccountInquiryService
Service implementation for fetching account details. <br>
Contains 2 operations <br>
  a) getAccountIdList - get available account id list <br>
     sample URL : http://localhost:9081/getAccountIdList <br>
  b) getAccountDetailsById - accept account id as parameter <br>
     sample URL : http://localhost:9081/getAccountById/12345 <br>
Authentication implemented using JSON Web Token authentication method.
Token expiry time set for 1 minute

# AccountServiceClient
Simple JAVA class to invoke account service from command line.

# How to Run
Minimum requirement: JAVA 8
1. Check out the project
2. cd AccountInquiryInterface
3. mvn clean install
4. cd AccountInquiryService
5. mvn clean install
6. mvn spring-boot:run
7. cd AccountServiceClient\src
8. javac ClientAccountDisplay.java
9. java -cp . ClientAccountDisplay -ip localhost -port 9081 -accountID 12345


