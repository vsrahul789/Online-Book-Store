### 📚 **Online Book Store Application**
#### A RESTful CRUD API built with Spring Boot, Spring Data JPA, and MySQL.

---

## 📌 **Project Overview**
The **Online Book Store Application** is a simple RESTful API that allows users to **manage a collection of books**. It provides functionalities to:
- Add new books 📚
- Retrieve book details 🔍
- Update book information ✏️
- Delete books ❌

Built with:
- **Spring Boot** (REST API)
- **Spring Data JPA** (Persistence Layer)
- **MySQL** (Database)
- **Spring Boot Exception Handling**

---

## 📁 **Project Structure**
```
OnlineBookstoreApp/
│── src/main/java/com/bookstoreapp/OnlineBookstoreApp/    # Main package
│   │── controller/                         # REST Controllers
│   │── service/                            # Business Logic Layer
│   │── repository/                         # Data Persistence Layer
│   │── model/                             # JPA Models/Entities
│   │── exception/                          # Global Exception Handling
│── src/main/resources/
│   │── application.properties              # Database Configuration
│── pom.xml                                 # Dependencies (Spring Boot, JPA, MySQL)
│── README.md                               # Project Documentation
```

---

## 🛠 **Installation & Setup**

### **Step 1: Configure the Database**
Open `src/main/resources/application.properties` and update the MySQL database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/assi_09_springboot
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
🚨 **Make sure MySQL is running and `bookstore_db` database exists!**

### **Step 2: Build and Run the Application**
```sh
mvn clean install
mvn spring-boot:run
```
The application starts at:  
**📍 http://localhost:8080**

---

## 🚀 **API Endpoints**
| Method | Endpoint | Description |
|--------|---------|-------------|
| **POST** | `/books` | Add a new book |
| **GET** | `/books` | Retrieve all books |
| **GET** | `/books/{id}` | Retrieve a book by ID |
| **PUT** | `/books/{id}` | Update book details |
| **DELETE** | `/books/{id}` | Delete a book |

---

## ⚠️ **Error Handling**
The API uses **Global Exception Handling** (`@ControllerAdvice`) to return meaningful error responses.

| Scenario | Response |
|----------|---------|
| **Book Not Found (GET/PUT/DELETE)** | `{ "error": "Book not found" }` (404) |
| **Invalid JSON Input (POST/PUT)** | `{ "error": "Invalid request data" }` (400) |
| **Database Connection Issue** | `{ "error": "Internal Server Error" }` (500) |

---

## 🛠 **Troubleshooting**
### **1.`java.sql.SQLException: Access denied for user`**
- Check `spring.datasource.username` and `spring.datasource.password` in `application.properties`.
- Verify that MySQL is running and `assi_09_springboot` exists.

### **2.`org.hibernate.exception.SQLGrammarException: Table 'bookstore_db.books' doesn't exist`**
- Run the app once with `spring.jpa.hibernate.ddl-auto=create` to create the tables.


