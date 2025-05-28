# 📰 Journalist & Media Manager - Spring Boot REST API

A secure, production-ready Java Spring Boot RESTful API designed to manage data for **Journalists** and the **Media** they work for.

This backend system is intended to showcase proficiency in building robust, scalable APIs using Spring Boot and MySQL — great for portfolios and real-world applications.

---

## 🚀 Features

- JWT-based Authentication (Login/Register)
- CRUD operations for Journalists and Media
- Pagination support on listing endpoints
- Role-based access control
- MySQL database integration
- Production-level architecture with a planned refresh token system

---

## 📡 API Endpoints

### 🔐 Authentication (Public)
| Method | Endpoint               | Description       |
|--------|------------------------|-------------------|
| POST   | `api/v1/auth/login`    | User login (returns JWT) |
| POST   | `api/v1/auth/register` | User registration        |


### 🧑 Journalists (Secured)
| Method | Endpoint                 | Description              |
|--------|---------------------------|--------------------------|
| GET    | `api/v1/journalists`      | Get paginated list of journalists (`page`, `size=25`) |
| GET    | `api/v1/journalists/{id}` | Get journalist by ID     |
| POST   | `api/v1/journalists`      | Create new journalist    |
| PATCH  | `api/v1/journalists/{id}` | Update journalist        |
| DELETE | `api/v1/journalists/{id}` | Delete journalist        |

### 📰 Media (Secured)
| Method | Endpoint             | Description              |
|--------|----------------------|--------------------------|
| GET    | `api/v1/media`       | Get paginated list of media (`page`, `size=25`) |
| GET    | `api/v1/media/{id}`  | Get media by ID          |
| POST   | `api/v1/media`       | Create new media entry   |
| PATCH  | `api/v1/media/{id}`  | Update media entry       |
| DELETE | `api/v1/media/{id}`  | Delete media entry       |

---

## 🛡️ Security & JWT

All non-auth routes require a **valid Bearer Token** (JWT). A token is issued upon login and contains:

```json
{
  "userDetails": {
    "id": 16,
    "username": "username",
    "email": "johndoe@email.com",
    "roleDto": {
      "id": 4,
      "roleName": "ROLE_ADMIN"
    }
  },
  "sub": "16",
  "iat": 1748425652,
  "exp": 1748512052
}
```

## 🧰 Tech Stack
- Java 17
- Spring Boot 3
- Maven
- Spring Security
- JWT (JSON Web Tokens)
- MySQL

## 🏁 Getting Started
### Prerequisites
- Java 17
- Maven 3.x
- MySQL server

### Setup
```bash
# Clone this repository
git clone https://github.com/yourusername/journalist-media-manager-springboot.git
cd journalist-media-manager-springboot

# Create MySQL DB and configure application.properties

# Build the project
./mvnw clean install

# Run the app
./mvnw spring-boot:run
```

## ⚙️ Environment Configuration
### Configure the following in your application.properties or application.yml:
```properties
spring.application.name=journalist-media-manager-springboot
server.servlet.contextPath=/api/v1
server.port=8080

# Datasource
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_user
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.sql.init.platform=mysql

# JWT
jwt.secret=your_jwt_secret
jwt.expiration=86400000

# Security
security.maxAllowFailLogins=${SECURITY_MAX_ALLOW_FAIL_LOGINS:3}
security.loginLockTimeInMinutes=${SECURITY_LOGIN_LOCK_TIME:5}
```
## 📌 TODO
- Implement JWT Refresh Token
