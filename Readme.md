#  This is Task Manager Application

## To run the Task Manager application:

# `Requirements`
- Java 17
- Maven
- PostgreSQL
- Git Bash
- Postman
- IDE (Eclipse, IntelliJ, or VS Code)
- 
# Steps to Run the Application Using IntelliJ:
- Clone the repository:

- Clone the project repository to your local machine using Git.
- Open in an IDE:

- Open the project in your preferred IDE (IntelliJ IDEA is recommended).
- Locate the Main Class:

- Navigate to the TaskManagerApplication.java file located at:
`task-manager\src\main\java\com\infosys\taskmanager\TaskManagerApplication.java`
- 
# Run the Application:

- Right-click on the main method in TaskManagerApplication.java and select Run to start the application.
# Access API Documentation:

- Open a browser and go to http://localhost:8080/swagger-ui/index.html to access the API documentation.
# Test the API:
- You can test the API using Swagger UI (accessed in step 5), Postman, or run the included api-test.sh script:
- Change the task ID in the script (api-test.sh) to match an available task ID from the database.
- Run the script in Git Bash:  `./api-test.sh`
- View the output in `api_test_output.txt`


# Steps to Run the Application Using the JAR:
- Build the JAR file:
- If you haven't already built the JAR file, run the following Maven command to build the application:

`mvn clean install`
-This will generate the JAR file task-manager-0.0.1-SNAPSHOT.jar in the target directory.
- Run the JAR file:

- Navigate to the target directory where the task-manager-0.0.1-SNAPSHOT.jar file is located.
- Run the JAR file using the following command:
`java -jar task-manager-0.0.1-SNAPSHOT.jar`

- Access the Application:
- Once the JAR is running, you can access the API documentation at:
`http://localhost:8080/swagger-ui/index.html`
- Test the API:
- Similar to the IntelliJ method, test the API using Swagger UI, Postman, or the api-test.sh script (as explained in the IntelliJ section).