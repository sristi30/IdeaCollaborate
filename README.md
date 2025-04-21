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

## Features
- **Employee Login:** Employees can log in using their employee ID.
- **Idea Submission:** Employees can submit their ideas for collaboration.
- **Voting on Ideas:** Employees can vote on existing ideas, except their own idea.
- **Collaboration on Ideas:** Employees can express interest in collaborating on an idea.
- **View Collaborators:** Employees can see a list of other employees who have expressed interest in collaborating on an idea.

## Tech Stack
- **Backend:** Spring Boot (Java)
- **Database:** PostgreSQL
- **Authentication:** JWT (JSON Web Tokens)
- **Containerization:** Docker

## Setup

### Docker Setup

1. **Ensure Docker and Docker Compose are installed**.

2. **Build and start the Docker containers**:
   ```bash
   docker-compose up --build
   ```

   This command will:
    - Build the application JAR file using the Maven build process.
    - Start the PostgreSQL database container and the Spring Boot application container.

3. **Access the application** at:
   ```bash
   http://localhost:8080
   ```

4. **Login with your employee ID** using the `/auth/login` endpoint to receive a JWT token, using a REST client, like Postman.

### Local Setup (Without Docker)

1. **Install dependencies**:
   Ensure you have **Java** and **Maven** installed on your local machine.

2. **Run PostgreSQL locally**:
   Make sure PostgreSQL is running locally on port `5432` with a database named `idea_db`.

3. **Configure `application.properties`**:
   Set the correct database connection in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/idea_db
   spring.datasource.username=postgres
   spring.datasource.password=<your-password>
   ```

4. **Build and run the application**:
   ```bash
   mvn clean package
   java -jar target/idea-collaborate-backend-1.0.0.jar
   ```

5. **Access the application** at:
   ```bash
   http://localhost:8080
   ```

6. **Login with your employee ID** using the `/auth/login` endpoint to receive a JWT token.

## API Endpoints

- **POST /auth/login**: Login with employee ID to get a JWT token.
- **POST /ideas**: Submit a new idea.
- **GET /ideas**: Get all ideas, optionally sorted by vote count or other criteria.
- **POST /ideas/{ideaId}/vote**: Vote on an existing idea.
- **POST /ideas/{ideaId}/collaborate**: Add yourself as a collaborator on an idea.
- **GET /ideas/{ideaId}/collaborators**: Get the list of collaborators on a given idea.

## Running the Application

Once the application is running, you can interact with the API via **Postman** or **cURL**.

### Example Signup Request
- **URL:** `POST http://localhost:8080/auth/signup`
- **Headers:** Content-Type: application/json
- **Body (JSON):**
  ```json
  {
    "employeeId": "456",
    "name": "Peter Smith",
    "email": "peter.smith@example.com",
    "password": "password456"
  }
  ```
- **Response:**
  ```json
  {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5ODciLCJpYXQiOjE3NDUyMTI1NDIsImV4cCI6MTc0NTIxNjE0Mn0.NT8hEy8iBtyNMSxl20D0CgB-Zh5hxQBlMp0eyMBupMvsI93ujXluQNBLuWb1knbI2S9S8xjqzQb5mT1PUx7AiQ"
  }
  ```

### Example Login Request
- **URL:** `POST http://localhost:8080/auth/login`
- Headers: Content-Type: application/json
- **Body (JSON):**
  ```json
  {
    "employeeId": "456",
    "password": "password456"
  }
  ```
- **Response:**
  ```json
  {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI5ODciLCJpYXQiOjE3NDUyMTI1NDIsImV4cCI6MTc0NTIxNjE0Mn0.NT8hEy8iBtyNMSxl20D0CgB-Zh5hxQBlMp0eyMBupMvsI93ujXluQNBLuWb1knbI2S9S8xjqzQb5mT1PUx7AiQ"
  }
  ```

### Example Idea Submission Request
- **URL:** `POST http://localhost:8080/ideas`
- **Headers:**
    - Authorization: `Bearer <your-jwt-token>`
    - Content-Type: application/json
- **Body (JSON):**
  ```json
  {
  "title": "My First Innovative Idea",
  "description": "A platform to share ideas across teams.",
  "tags": ["collaboration", "innovation"]
  }
  ```
  Make sure you have the tags already inserted in the tag table before trying to insert the idea

### Example View all Ideas
- **URL:** `GET http://localhost:8080/ideas`
- **Headers:**
    - Authorization: `Bearer <your-jwt-token>`
 
The ideas will be sorted by vote count by default. In order to sort by creation date, pass the sortBy parameter:
- **URL:** `GET http://localhost:8080/ideas?sortBy=creationDate`
- **Headers:**
    - Authorization: `Bearer <your-jwt-token>`
### Example Vote on Idea Request
- **URL:** `POST http://localhost:8080/ideas/{ideaId}/vote`
- **Headers:**
    - Authorization: `Bearer <your-jwt-token>`

### Example Collaborate on Idea Request
- **URL:** `POST http://localhost:8080/ideas/{ideaId}/collaborate`
- **Headers:**
    - Authorization: `Bearer <your-jwt-token>`
 
### Example Get all Collaborators Request
- **URL:** `GET http://localhost:8080/ideas/{ideaId}/collaborators`
- **Headers:**
    - Authorization: `Bearer <your-jwt-token>`


