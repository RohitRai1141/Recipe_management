# Recipe Management System

A full-stack Recipe Management application with Spring Boot backend and vanilla JavaScript frontend.

## Features

- Browse recipes with pagination
- Search and filter recipes by title, cuisine, rating, time, and calories
- View detailed recipe information with nutrition facts
- Responsive design that works on all devices
- RESTful API with proper error handling

## Technology Stack

### Backend
- Spring Boot 3.2.0
- Spring Data JPA
- MySQL Database
- Maven

### Frontend
- Vanilla HTML, CSS, JavaScript
- Responsive design
- Modern UI with smooth animations

## Setup Instructions

### Prerequisites
- Java 17 or higher
- MySQL 8.0 or higher
- Maven 3.6 or higher

### Database Setup

1. Install MySQL and create a database:
```sql
CREATE DATABASE recipe_management;
```

2. Run the SQL script to create tables and insert sample data:
```bash
mysql -u root -p recipe_management < db.sql
```

3. Update database credentials in `backend/src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Backend Setup

1. Navigate to the backend directory:
```bash
cd backend
```

2. Build and run the Spring Boot application:
```bash
mvn clean install
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`

### Frontend Setup

The frontend is already configured to run with Vite. It will automatically connect to the Spring Boot backend running on port 8080.

## API Endpoints

### Get All Recipes
```
GET /api/recipes?page=1&limit=15
```

### Search Recipes
```
GET /api/recipes/search?title=chicken&cuisine=Italian&rating=4.0&total_time=60&calories=500&page=1&limit=15
```

### Get Recipe by ID
```
GET /api/recipes/{id}
```

## Database Schema

The `recipes` table includes:
- Basic info: title, cuisine, rating, prep/cook/total time
- Description and serving information
- Detailed nutrition information (calories, carbs, protein, etc.)
- Timestamps for created/updated dates

## Usage

1. Start the MySQL database
2. Run the Spring Boot backend
3. Open the frontend in your browser
4. Use the search filters to find recipes
5. Click on any recipe row to view detailed information

## Sample Data

The database includes 20 sample recipes from various cuisines including:
- Southern Recipes
- Italian
- Asian
- Mexican
- Mediterranean
- American
- Indian
- Thai
- Korean
- French
- British
- Russian

Each recipe includes complete nutrition information and cooking times.