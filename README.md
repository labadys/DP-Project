Library Management System

A modern, robust library management system built with Spring Boot featuring REST API, JWT authentication, and comprehensive database management.

✨ Features
Full CRUD Operations - Complete management for books, authors, genres, and publishers
RESTful API - Clean, intuitive API endpoints with proper HTTP status codes
JWT Authentication & Authorization - Secure access control with Spring Security
Database Migrations - Version-controlled schema management with Liquibase
API Documentation - Interactive Swagger/OpenAPI documentation
Data Validation - Bean Validation with Hibernate Validator
Object Mapping - Efficient DTO mapping with MapStruct
Code Coverage - JaCoCo integration for test coverage reports
Email Support - Spring Boot Mail integration for notifications

🛠️ Technology Stack
Backend
Java 17 - Modern Java LTS version
Spring Boot 3.5.4 - Rapid application development framework
Spring Data JPA - Database access and ORM
Spring Security - Authentication and authorization
Spring Validation - Data validation framework
Spring Mail - Email sending capabilities
Database
H2 Database - In-memory database for development
Liquibase - Database migration and versioning tool
JPA/Hibernate - Object-relational mapping
API & Documentation
SpringDoc OpenAPI 2.8.0 - Swagger API documentation
RESTful Web Services - Clean API design
Development Tools
Lombok - Reduced boilerplate code
MapStruct 1.5.5 - Efficient object mapping
JaCoCo 0.8.11 - Code coverage reporting
JUnit 5 - Unit testing framework
TestNG - Additional testing capabilities
Build Tools
Maven - Dependency management and build automation
Spring Boot Maven Plugin - Simplified Spring Boot application packaging

🚀 Getting Started
Prerequisites
Java 17 or higher
Maven 3.9.11 or higher
(Optional) Docker for containerization
Installation & Setup
Clone the repository
bash
git clone https://github.com/labadys/DP-Project.git
cd DP-Project
Build the project
bash
mvn clean install
Run the application
bash
mvn spring-boot:run
Access the application
Application: http://localhost:8080
Swagger UI: http://localhost:8080/swagger-ui.html
H2 Console: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (leave empty)
Docker Support
The application includes Docker support for easy containerization:
bash
# Build Docker image
docker build -t library-management .
# Run container
docker run -p 8080:8080 library-management
📚 API Documentation
Once the application is running, explore the complete API documentation:
Swagger UI: http://localhost:8080/swagger-ui.html
OpenAPI JSON: http://localhost:8080/v3/api-docs
The API includes endpoints for:
Book management (CRUD operations)
Author management
Genre management
Publisher management
User authentication and registration
Search and filtering capabilities

🧪 Testing
The project includes comprehensive testing with code coverage:

bash
# Run tests with coverage report
mvn test

# Generate detailed coverage report
mvn jacoco:report
Test Coverage: Minimum 80% line coverage enforced
Testing Frameworks: JUnit 5, TestNG, Spring Test
Security Testing: Spring Security Test integration

📦 Project Structure
text
src/
├── main/
│   ├── java/com/example/library/
│   │   ├── controller/     # REST controllers
│   │   ├── service/        # Business logic layer
│   │   ├── repository/     # Data access layer
│   │   ├── entity/         # JPA entities
│   │   ├── dto/           # Data Transfer Objects
│   │   ├── mapper/        # MapStruct mappers
│   │   └── config/        # Configuration classes
│   └── resources/
│       ├── db/changelog/  # Liquibase migrations
│       └── application.yml # Configuration
└── test/                  # Test classes
⚙️ Configuration
Key configuration options in application.yml:

yaml
spring:
datasource:
url: jdbc:h2:mem:testdb
username: sa
password:
jpa:
hibernate:
ddl-auto: validate
show-sql: true
h2:
console:
enabled: true
liquibase:
change-log: classpath:db/changelog/db.changelog-master.yaml
🔧 Development
Code Quality
Lombok for reduced boilerplate code

MapStruct for efficient object mapping
Bean Validation for input validation
Consistent code style and naming conventions

Database Management
Liquibase for database version control
H2 for development and testing
JPA for object-relational mapping

Build and Deployment
bash
# Clean build
mvn clean package

# Run with specific profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Create executable JAR
mvn package spring-boot:repackage
📊 Code Coverage
The project uses JaCoCo to enforce code quality standards:
Minimum 80% line coverage requirement
Coverage reports generated in target/site/jacoco/
Build fails if coverage requirements are not met

🤝 Contributing
Fork the repository
Create a feature branch (git checkout -b feature/amazing-feature)
Commit your changes (git commit -m 'Add amazing feature')
Push to the branch (git push origin feature/amazing-feature)
Open a Pull Request

📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

🆘 Support
For support and questions:
Check the API documentation at /swagger-ui.html
Review the project issues on GitHub
Contact the development team
