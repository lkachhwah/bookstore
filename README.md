#Book Store Application

**Description:** This is project is POC for book store application.

**Prerequisite:**
1. Java version 11 required.
2. Maven/Git to be installed.
3. Mongo DB up and running.

**Technology Used:**
1. Java 11 coding language
2. Mongo DB for persisting DB.
3. Git for version control.
4. Maven for dependency management.
5. Spring boot for development framework.
6. swagger for documentation.

**Steps to build project**
1. Take a clone of project code from : https://github.com/lkachhwah/bookstore and please make note we need to use branch "main"
2. Open terminal and go to project directory where project is cloned.
3. Execute command "mvn --version" and make sure maven installed and working.
4. Update project mongo db url in below two files ,set value of property "spring.data.mongodb.uri: `mongodb://<user>:<passwd>@<host>:<port>/<dbname>`":
   src/test/resources/application.properties
   src/main/resources/application.properties
5. Execute command "mvn clean install" and check logs and observe build is successful.
6. Go to target folder .
7. Execute the command "java -jar bookstore-0.0.1-SNAPSHOT.jar" and observe below logs in terminal console.

      2022-01-11 00:17:52.359  INFO 15905 --- [  restartedMain] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8081 (http) with context path ''
      2022-01-11 00:17:52.677  INFO 15905 --- [  restartedMain] c.e.bookstore.BookstoreApplication       : Started BookstoreApplication in 3.596 seconds (JVM running for 4.085)
8. Open browser and hit endpoint: "http://<hostname>:8081/swagger-ui.html", browse through the documentation about different model details used for request and controller.
9. Basic authentication is enable for all api .Please refer below details.

|  username   | tuser |
|-----|-------|
|   password  | tpwd  |

10. For all api sample request refer the postman collection : getir.postman_collection.json, Import this collection in postman .

### **Execute API in below order to get E2E order process:**

1.Create customer using postman collection "addCustomer".

2.Add Book using postman collection "addBook".

3.To make order use postman collection "addOrder" and also set proper customer ID(from point1 response) and book Id(from point2 response) in order request body.

4.To get stats details use postman collection "statsmontlyOrder".

5.To update book quantity use postman collection "updateBookQuantity" ,set proper book Id(from point2 response).


**Tech Depth**
1. To handle last book order by multiple customer at same time - to overcome this case is used Optimistic locking in Book model.
2. For configurable message purpose for different code we can use  message.properties and update message as per requirement.
3. Spring security used for user authentication purpose and details is maintained in collection "users". We can also add new users in this db and make sure password is Bcrypted while storing in DB.
4. Swagger is used for documentation.

**DB Different Collections  Details**

| Collection Name | Discription                                 |
|-----------------|---------------------------------------------|
| users           | store user credetial for authentication     |
| book            | To Store book details                       |
| orderDetails    | To store order details                      |
| customer        | To store Customer details                   |
| customSequence  | To generate unique for different collection |
