# Idea Collaboration Application

This is a collaborative platform where employees can share, vote, and collaborate on ideas. The application allows employees to log in using their employee ID, submit ideas, vote on ideas, and collaborate on ideas with other employees. This project is built with **Spring Boot** for the backend, **PostgreSQL** for data storage, and **JWT** for authentication.

## Table of Contents
1. [Features](#features)
2. [Tech Stack](#tech-stack)
3. [Setup](#setup)
    1. [Docker Setup](#docker-setup)
    2. [Local Setup](#local-setup)
4. [API Endpoints](#api-endpoints)
5. [Running the Application](#running-the-application)
6. [Testing the Application](#testing-the-application)
7. [License](#license)

## Features
- **Employee Login:** Employees can log in using their employee ID.
- **Idea Submission:** Employees can submit their ideas for collaboration.
- **Voting on Ideas:** Employees can vote on existing ideas to prioritize them.
- **Collaboration on Ideas:** Employees can collaborate on ideas by adding their input.

## Tech Stack
- **Backend:** Spring Boot (Java)
- **Database:** PostgreSQL
- **Authentication:** JWT (JSON Web Tokens)
- **Containerization:** Docker
- **API Documentation:** Swagger

## Setup

### Docker Setup

1. **Clone the repository**:
   ```bash
   git clone <your-repository-url>
   cd <your-repository-folder>
   ```

2. **Ensure Docker and Docker Compose are installed**. If not, download and install Docker from the [official website](https://www.docker.com/).

3. **Build and start the Docker containers**:
   ```bash
   docker-compose up --build
   ```

   This command will:
    - Build the application JAR file using the Maven build process.
    - Start the PostgreSQL database container and the Spring Boot application container.

4. **Access the application** at:
   ```bash
   http://localhost:8080
   ```

5. **Login with your employee ID** using the `/auth/login` endpoint to receive a JWT token.

### Local Setup (Without Docker)

1. **Clone the repository**:
   ```bash
   git clone <your-repository-url>
   cd <your-repository-folder>
   ```

2. **Install dependencies**:
   Ensure you have **Java** and **Maven** installed on your local machine.

3. **Run PostgreSQL locally**:
   Make sure PostgreSQL is running locally on port `5432` with a database named `idea_db`.

4. **Configure `application.properties`**:
   Set the correct database connection in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/idea_db
   spring.datasource.username=postgres
   spring.datasource.password=<your-password>
   ```

5. **Build and run the application**:
   ```bash
   mvn clean package
   java -jar target/idea-collaborate-backend-1.0.0.jar
   ```

6. **Access the application** at:
   ```bash
   http://localhost:8080
   ```

7. **Login with your employee ID** using the `/auth/login` endpoint to receive a JWT token.

## API Endpoints

- **POST /auth/login**: Login with employee ID to get a JWT token.
- **POST /ideas**: Submit a new idea.
- **GET /ideas**: Get all ideas, optionally sorted by vote count or other criteria.
- **POST /ideas/{ideaId}/vote**: Vote on an existing idea.
- **POST /ideas/{ideaId}/collaborate**: Add yourself as a collaborator on an idea.
- **GET /ideas/{ideaId}/collaborators**: Get the list of collaborators on a given idea.

## Running the Application

Once the application is running, you can interact with the API via **Postman** or **cURL**.

### Example Login Request (Postman or cURL)
- **URL:** `POST http://localhost:8080/auth/login`
- **Body (JSON):**
  ```json
  {
    "employeeId": "12345"
  }
  ```
- **Response:**
  ```json
  {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5ODciLCJpYXQiOjE3NDUyMTI1NDIsImV4cCI6MTc0NTIxNjE0Mn0.NT8hEy8iBtyNMSxl20D0CgB-Zh5hxQBlMp0eyMBupMvsI93ujXluQNBLuWb1knbI2S9S8xjqzQb5mT1PUx7AiQ"
  }
  ```

### Example Idea Submission Request (Postman or cURL)
- **URL:** `POST http://localhost:8080/ideas`
- **Headers:**
    - Authorization: `Bearer <your-jwt-token>`
- **Body (JSON):**
  ```json
  {
    "title": "New Idea",
    "description": "This is a new idea for collaboration."
  }
  ```

### Example Vote on Idea Request (Postman or cURL)
- **URL:** `POST http://localhost:8080/ideas/{ideaId}/vote`
- **Headers:**
    - Authorization: `Bearer <your-jwt-token>`

## Testing the Application

You can test the API by making requests using Postman or any HTTP client. Ensure that you include the `Authorization` header with the JWT token obtained after logging in.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

### Additional Notes:
- **JWT Authentication**: Ensure you include the `Authorization` header with `Bearer <your-jwt-token>` for endpoints that require authentication.
- **Docker**: With Docker Compose, the application and database run in separate containers but can communicate with each other over a private Docker network.

Feel free to tweak the contents based on your preferences and project specifics!