# SpringBoot_Java_Project
This project is developed using the Spring Boot framework to build a Java application with various key Spring features such as MVC, CRUD operations, AOP, Security, and Hibernate. Additionally, it includes functionality to generate Excel sheets and PDF files from the database.

 
## Features ##
 Spring Boot MVC: Implements the Model-View-Controller pattern for structuring web applications.
 
 Spring Boot CRUD: Supports Create, Read, Update, and Delete operations on entities in the application.
 
 Spring AOP (Aspect-Oriented Programming): Used for cross-cutting concerns like logging, transactions, etc.
 
 Spring Security: Secures the application by handling authentication and authorization.
 
 Spring Hibernate: Provides ORM (Object-Relational Mapping) support for interacting with databases.
 
 Excel and PDF Generation: Retrieves data from the database and generates Excel sheets and PDF reports.
 

## Technologies Used
Java 17

Spring Boot 3.1.4


Spring MVC


Spring Data JPA (Hibernate)


Spring Security


Spring AOP


Thymeleaf (library for front-end)


MySQL for the database


Apache POI for Excel generation

 
openpdf for PDF generation(group id: com.github.librepdf)


Maven for dependency management


Git for version control

  
  
## Running the Application. 

First, create data in MySQL database for the testing (using SQL scripts provided)  
Once the application is running, you can perform the following:

Access the API:
Visit http://localhost:8080/ to access the application endpoints.
login using user below user using password: fun123 
    user name: john, mary, susan. (These users have different access roles which demonstrate spring security) 

Click on the 'Employees Details' page on home page to view all employees where you can add, update delete employes(CRUD operations). 
Moreever you can generate Excel and PDF reports.    
   
    
