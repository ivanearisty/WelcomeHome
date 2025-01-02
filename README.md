# WelcomeHome

WelcomeHome is a web application implemented in Java using the Spring Boot framework. It serves as a backend system for managing users, items, orders, and donations with role-based access control. The application interacts directly with the database using JDBC and prepared statements, ensuring security and efficiency.

## Features

### Core Functionalities
1. **Login & User Session Handling**
   - Users can log in using their credentials.
   - Passwords are securely stored with cryptographic hashing (SHA-256 with salt).
   - User sessions are managed with JWT (JSON Web Tokens).

2. **Find Single Item**
   - Retrieve all locations where pieces of a specific item are stored.

3. **Find Order Items**
   - Retrieve all items in a specific order, including the locations of their pieces.

4. **Accept Donation**
   - Only staff members can accept donations.
   - Ensure donors are registered and validate the locations of donated items.
   - Record details of donated items and their pieces in the database.

5. **Start an Order**
   - Only staff members can start an order.
   - Assign an order ID and associate it with a client.

6. **Add to Current Order**
   - Browse available items by category and add them to an ongoing order.
   - Mark added items as ordered.

### Additional Features
- Role-based access and permissions for staff, volunteers, donors, and clients.
- Comprehensive exception handling for database and application errors.
- Security mechanisms to prevent SQL Injection and XSS attacks.

## Technologies Used
- **Backend**: Java, Spring Boot, Spring Security
- **Database**: JDBC for direct SQL interaction
- **Authentication**: JSON Web Tokens (JWT)
- **Security**: BCryptPasswordEncoder for password hashing, role-based access control
- **Build Tool**: Maven

## Security
- All SQL interactions use prepared statements to prevent SQL Injection.
- Sensitive data, such as passwords, is securely hashed before storage.
- Cross-Site Scripting (XSS) attacks are mitigated through input validation.

## Application Structure

### Configuration
- **SecurityConfig**: Defines the security settings, including role-based access permissions and JWT authentication filters.
- **TokenProvider**: Manages JWT creation, validation, and parsing.

### Controllers
- **Controller**: Handles all HTTP requests for user management, item retrieval, donation processing, and order creation.
- **ControllerAdvice**: Centralized exception handling for the application.

### Services
- **UserServiceImpl**: Manages user operations, including creation, retrieval, and role assignment.

### Repositories
- **UserRepositoryImpl**: Handles direct SQL interactions for user management.
- **RowMapperUser**: Maps database results to Java objects.

### DTOs
- **UserDTO**: Represents user data for communication between layers.
- **PieceRequest**: Represents the details of an individual piece during item creation or donation.

## How to Run

### Prerequisites
- Java 17 or higher
- Maven
- A database server (e.g., MySQL)

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/ivanearisty/WelcomeHome.git
   ```
2. Configure the database connection in `application.properties`.
3. Build the application:
   ```bash
   mvn clean install
   ```
4. Run the application:
   ```bash
   mvn spring-boot:run
   ```
5. Access the API at `http://localhost:8080`.

---

## Endpoints

| Endpoint               | Method | Role Required       | Description                                    |
|------------------------|--------|---------------------|------------------------------------------------|
| `/user/login`          | POST   | Public              | Log in a user and retrieve tokens.            |
| `/user/register`       | POST   | Admin               | Register a new user.                          |
| `/item/{id}`           | GET    | READ:ITEM           | Get all pieces of an item.                    |
| `/order/get/{orderId}` | GET    | READ:ORDER          | Retrieve items and pieces of an order.        |
| `/donation`            | POST   | CREATE:ITEM         | Accept a donation from a donor.               |
| `/order/create`        | POST   | CREATE:ORDER        | Create a new order.                           |
| `/order/addItem`       | POST   | UPDATE:ORDER        | Add an item to an order.                      |

For a full list of endpoints and their details, refer to the `Controller.java` file.
