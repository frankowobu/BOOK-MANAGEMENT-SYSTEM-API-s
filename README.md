# BOOK-MANAGEMENT-SYSTEM-API-s
Book Management System
This is a Spring Boot application Backend API Service for a Book Management System.

Requirements
-Java 1.8 

Getting Started
git clone https://github.com/frankowobu/BOOK-MANAGEMENT-SYSTEM-API-s.git

Open your code editor e.g intelliJ or VisualStudio code

Navigate to the project directory:
cd libraryApplication

BUILD THE APPLICATION
mvn clean install

STARTUP THE APPLICATION
mvn spring-boot:run

To connect to the H2 database
paste this url on your browser http://localhost:8081/h2

OPEN YOUR POSTMAN TO TEST THE API

                            # AUTHOR Endpoints
Create Author API
localhost:8081/author Method: POST
This API create a new author, it requires providing the firstName and lastName as the request body.
Authenticate: login as the admin; username=admin password=admin-pass

Get an Author 
localhost:8081/author/{authorId} Method: GET
This API get an author, it requires providing a authorId as the path variable
Authenticate: login as the admin; username=admin password=admin-pass || the user; username=user password=user-pass

Update Author API
localhost:8081/author/update/{authorId} Method: PUT
This API update an already exited author, it requires providing a firstName, and lastName in the request body, and the authorId as the path variable
Authenticate: login as the admin; username=admin password=admin-pass

Delete Author API
localhost:8081/author/delete/{authorId} Method: DELETE
This API delete author, it requires providing a authorId as the path variable
Authenticate: login as the admin; username=admin password=admin-pass

Get all Author API List
localhost:8081/author Method: GET
This API get all authors from the db

                            # BOOK Endpoints
Create Book API
localhost:8081/book/author/{authorId} Method: POST
This API create a new book, it requires providing the title,pages,summary as the request body and authorId as the path variable.
Authenticate: login as the admin; username=admin password=admin-pass

Update Book API
localhost:8081/book/update/author/{authorId} Method: PUT
This API update an already exited book, it requires providing the title,pages,summary as the request body and authorId as the path variable.
Authenticate: login as the admin; username=admin password=admin-pass

Delete Book API
localhost:8081/book/delete/author/{authorId} Method: DELETE
This API delete book, it requires providing an authorId as the path variable
Authenticate: login as the admin; username=admin password=admin-pass

Get all Book API List
localhost:8081/book Method: GET
This API get all books from the db
Authenticate: login as the admin; username=admin password=admin-pass || the user; username=user password=user-pass

Get all Book API List based on an author
localhost:8081/book/author{authorId} Method: GET
This API get all books from the db, it requires providing an authorId as the path variable
Authenticate: login as the admin; username=admin password=admin-pass || the user; username=user password=user-pass






