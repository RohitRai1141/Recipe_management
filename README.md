
### JSON Data Format (`recipes.json`)

```json
{
"1": {
"title": "Recipe Name",
"cuisine": "Cuisine Type",
"rating": 4.5,
"prep_time": 15,
"cook_time": 30,
"total_time": 45,
"description": "Recipe description...",
"serves": 4,
"nutrients": {
"calories": 350,
"protein": 25,
"carbohydrates": 15,
"fat": 20,
"fiber": 3,
"sugar": 8,
"sodium": 800,
"cholesterol": 75,
"saturated_fat": 8
}
}
}

```plaintext

## Setup Instructions

### Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+
- Node.js 16+ (for frontend development)

### Backend Setup
\`\`\`bash
# 1. Clone repository
git clone <repository-url>
cd recipe-management

# 2. Create MySQL database
mysql -u root -p
CREATE DATABASE recipe_management;

# 3. Configure database credentials
# Edit src/main/resources/application.properties

# 4. Run Spring Boot application
cd backend
mvn spring-boot:run

# 5. Load sample data
curl -X POST http://localhost:8080/api/data/load-recipes
```

### Frontend Setup

```bash

# 1. Install dependencies

npm install

# 2. Start development server

npm run dev

# 3. Open browser

# Navigate to [http://localhost:5173](http://localhost:5173)

```plaintext

## Usage Guide

### Loading Recipe Data
1. **Automatic Loading**: Place JSON file in `src/main/resources/recipes.json`
2. **API Loading**: `POST /api/data/load-recipes`
3. **Custom File**: `POST /api/data/load-recipes?fileName=custom.json`

### Frontend Features
- **Search**: Type in search bar to filter by recipe title
- **Filter**: Select cuisine from dropdown
- **Pagination**: Navigate through recipe pages
- **Recipe Details**: Click any recipe to view full details
- **Responsive**: Works on desktop, tablet, and mobile

### API Usage Examples
\`\`\`bash
# Get all recipes
curl http://localhost:8080/api/recipes

# Search recipes
curl "http://localhost:8080/api/recipes?search=chicken"

# Filter by cuisine
curl "http://localhost:8080/api/recipes?cuisine=Indian"

# Get specific recipe
curl http://localhost:8080/api/recipes/1

# Create new recipe
curl -X POST http://localhost:8080/api/recipes \
  -H "Content-Type: application/json" \
  -d '{"title":"New Recipe","cuisine":"Italian"}'
```

## Troubleshooting

### Common Issues

#### 1. "Failed to fetch" Error

- **Cause**: Backend not running or CORS issues
- **Solution**:
```bash

# Check if backend is running

curl [http://localhost:8080/api/recipes](http://localhost:8080/api/recipes)

# Verify CORS configuration in application.properties

```




#### 2. HTTP 500 Internal Server Error

- **Cause**: Database connection or pagination issues
- **Solution**:
```bash

# Check MySQL is running

mysql -u root -p -e "SHOW DATABASES;"

# Verify table exists

mysql -u root -p recipe_management -e "DESCRIBE recipes;"
```




#### 3. "No recipes found"

- **Cause**: Empty database or frontend not connecting
- **Solution**:
```bash

# Load sample data

curl -X POST [http://localhost:8080/api/data/load-recipes](http://localhost:8080/api/data/load-recipes)

# Check data exists

curl [http://localhost:8080/api/recipes](http://localhost:8080/api/recipes)
```




#### 4. Pagination Issues

- **Cause**: Mismatch between frontend (0-based) and backend (1-based)
- **Solution**: Frontend automatically converts pagination


### Development Tips

- **Backend Logs**: Check Spring Boot console for detailed error messages
- **Frontend Debugging**: Use browser developer tools console
- **Database Issues**: Verify MySQL connection and table structure
- **CORS Problems**: Ensure frontend port is in allowed origins


## Project Structure

```plaintext
recipe-management/
├── backend/
│   ├── src/main/java/com/recipe/
│   │   ├── config/
│   │   │   └── JacksonConfig.java
│   │   ├── controller/
│   │   │   ├── RecipeController.java
│   │   │   └── DataLoaderController.java
│   │   ├── dto/
│   │   │   ├── RecipeResponseDTO.java
│   │   │   └── PagedResponseDTO.java
│   │   ├── model/
│   │   │   └── Recipe.java
│   │   ├── repository/
│   │   │   └── RecipeRepository.java
│   │   ├── service/
│   │   │   ├── RecipeService.java
│   │   │   └── RecipeDataLoaderService.java
│   │   └── RecipeManagementApplication.java
│   ├── src/main/resources/
│   │   ├── application.properties
│   │   └── recipes.json
│   └── pom.xml
├── frontend/
│   ├── index.html
│   ├── style.css
│   ├── script.js
│   └── package.json
└── README.md
```

## Contributing

1. Fork the repository
2. Create feature branch (`git checkout -b feature/new-feature`)
3. Commit changes (`git commit -am 'Add new feature'`)
4. Push to branch (`git push origin feature/new-feature`)
5. Create Pull Request


## License

This project is licensed under the MIT License.

```plaintext



</CodeProject>

I've created comprehensive documentation for your entire recipe management system. The README covers the complete architecture, all backend and frontend components, API endpoints, database schema, setup instructions, and troubleshooting guide. This documentation will help anyone understand and work with your Spring Boot backend and vanilla JavaScript frontend system.



<AssistantMessageContentPart partEncoded="eyJ0eXBlIjoidGFzay1kaWFnbm9zdGljcy12MSIsImNyZWF0ZWRBdCI6MTc1NTc3NjYwNjA3MiwiZmluaXNoZWRBdCI6bnVsbCwiaWQiOiJLeTI0dnlhTHdXdE5mM3lrIiwibGFzdFBhcnRTZW50QXQiOjE3NTU3NzY2MDYwNzIsInBhcnRzIjpbeyJ0eXBlIjoiZmV0Y2hpbmctZGlhZ25vc3RpY3MifV19" />



<AssistantMessageContentPart partEncoded="eyJ0eXBlIjoidGFzay1kaWFnbm9zdGljcy12MSIsImNyZWF0ZWRBdCI6MTc1NTc3NjYwNjA3MiwiZmluaXNoZWRBdCI6bnVsbCwiaWQiOiJLeTI0dnlhTHdXdE5mM3lrIiwibGFzdFBhcnRTZW50QXQiOjE3NTU3NzY2MTg2ODMsInBhcnRzIjpbeyJ0eXBlIjoiZmV0Y2hpbmctZGlhZ25vc3RpY3MifSx7InR5cGUiOiJkaWFnbm9zdGljcy1wYXNzZWQifV19" />



<AssistantMessageContentPart partEncoded="eyJ0eXBlIjoidGFzay1kaWFnbm9zdGljcy12MSIsImNyZWF0ZWRBdCI6MTc1NTc3NjYwNjA3MiwiZmluaXNoZWRBdCI6MTc1NTc3NjYxODcwOCwiaWQiOiJLeTI0dnlhTHdXdE5mM3lrIiwibGFzdFBhcnRTZW50QXQiOjE3NTU3NzY2MTg2ODMsInBhcnRzIjpbeyJ0eXBlIjoiZmV0Y2hpbmctZGlhZ25vc3RpY3MifSx7InR5cGUiOiJkaWFnbm9zdGljcy1wYXNzZWQifV19" />

```
