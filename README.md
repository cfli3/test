# test

Demo Application
1.	Install Java SDK v1.8 or higher
2.	Install maven https://maven.apache.org/install.html) or use within IDE Maven plugin
3.	git clone repo: https://github.com/cfli3/test
4.	Build using maven: mvn clean install
5.	Run the following command from demo root directory to start SpringBoot web application: 

    - java -jar target/demo-0.0.1-SNAPSHOT.jar -Dspring.profiles.active=development

6.	H2 database console URL: 	http://localhost:8080/h2-console
    - Connection (User: “sa” with blank password)
    - SQL query: 	SELECT * FROM USER;

7.	POST User URL: 	http://localhost:8080/user

      JSON request body: 
      - {
        "userId": 1,
        "loginName": "UserLogin1",
        "firstName": "UserFname",
        "lastName": "UserLname",
        "email": "user1@email.com",
        "address": "User1 address"
       }
      
8.	GET User URL: 	http://localhost:8080/user/{userId}


