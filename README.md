# Meme Management System API

A robust Spring Boot backend application designed to manage a meme ecosystem. This API allows users to manage meme templates, track trending topics, and create memes by associating them with specific users, templates, and current trends.

## 🚀 Features

- **User Management**: Full CRUD operations for application users.
- **Template Library**: Manage various meme templates with categories and keywords.
- **Trend Tracking**: Monitor trending topics across different sources (e.g., Reddit, Twitter) with popularity scores.
- **Meme Engine**: Create and manage memes that link users, templates, and multiple trending topics together.
- **Relational Integrity**: Built-in support for entity relationships (One-to-Many, Many-to-Many).

## 🛠 Tech Stack

- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA** (Hibernate)
- **H2 Database / MySQL** (Configurable via `application.properties`)
- **Maven** (Dependency Management)

## 📋 Prerequisites

- JDK 17 or higher installed.
- Maven installed (or use the provided `./mvnw` wrapper).
- An IDE (IntelliJ IDEA, Eclipse, or VS Code).

## ⚙️ Setup and Installation

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd demo
   ```

2. **Configure the database**:
   Edit `src/main/resources/application.properties` to set your database preferences. For testing without persistence:
   ```properties
   spring.jpa.hibernate.ddl-auto=create-drop
   ```

3. **Build and Run**:
   Using the Maven wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
   The server will start at `http://localhost:8080`.

## 📖 API Documentation

### Users
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/users/create` | Create a new user |
| GET | `/users/all` | Retrieve all users |
| GET | `/users/{id}` | Get user by ID |
| PUT | `/users/{id}/update` | Update user details |
| DELETE | `/users/{id}/delete` | Delete a user |

**Create User Payload:**
```json
{
  "name": "Alice Example",
  "email": "alice@example.com",
  "password": "password123"
}
```

### Templates
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/templates/create` | Create a meme template |
| GET | `/templates/all` | Retrieve all templates |
| PUT | `/templates/{id}/update`| Update a template |

### Trending
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/trending/create` | Add a trending topic |
| GET | `/trending/all` | List all trends |

### Memes
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| POST | `/memes/create` | Create a new meme |
| GET | `/memes/all` | List all memes |
| PUT | `/memes/{id}/update` | Update meme metadata |

**Creating a Meme (with relationships):**
To create a meme, ensure the User, Template, and Trends already exist. Use their IDs to link them:
```json
{
  "imgUrl": "https://example.com/memes/1.png",
  "caption": "When code compiles on first try",
  "genre": "programming",
  "user": { "userId": 1 },
  "template": { "templateId": 1 },
  "trends": [ { "trendingId": 1 }, { "trendingId": 2 } ]
}
```

## 🧪 Testing with Postman

1. Start the application.
2. Import the provided `POSTMAN_MOCK_DATA.md` logic into your Postman collections.
3. **Recommended Test Flow**:
    - Create a **User**.
    - Create a **Template**.
    - Create a **Trending** topic.
    - Create a **Meme** using the IDs generated from the previous steps.
    - Verify with a `GET /memes/all` call.

## 🏗 Project Structure

```text
src/main/java/com/example/demo/
 ├── controller/    # REST API Endpoints
 ├── model/         # JPA Entities (User, Template, Trending, Meme)
 ├── repository/    # Spring Data JPA Repositories
 └── service/       # Business Logic Layer
```

## 📝 Notes

- **No DTOs**: This project currently uses Entities directly for API communication for simplicity.
- **Location Headers**: On successful `POST` requests, the `Location` header in the response contains the URL of the newly created resource.

## ⚖️ License

This project is licensed under the MIT License.
```
