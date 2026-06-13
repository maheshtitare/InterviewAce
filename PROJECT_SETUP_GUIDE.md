# Complete Project Setup Guide

## Step-by-Step Installation

### Part 1: Backend Setup (Spring Boot)

#### 1.1 Prerequisites
- Install Java 17+
  ```bash
  java -version  # Should show version 17 or higher
  ```
- Install Maven
  ```bash
  mvn -version  # Should show Maven 3.6+
  ```
- Install MySQL 8.0+
  ```bash
  mysql --version  # Should show MySQL 8.0+
  ```

#### 1.2 Database Configuration
1. Open MySQL command line
   ```bash
   mysql -u root -p
   ```

2. Run the setup script
   ```bash
   source DATABASE_SETUP.sql;
   ```

3. Verify database creation
   ```bash
   USE interview_ace;
   SHOW TABLES;  # Should show all 15 tables
   ```

#### 1.3 Configure Backend
1. Edit `backend/src/main/resources/application.properties`
   ```properties
   # Update these with your MySQL credentials
   spring.datasource.url=jdbc:mysql://localhost:3306/interview_ace
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   
   # JWT Configuration
   jwt.secret=YourSecretKeyHere
   jwt.expiration=86400000  # 24 hours in milliseconds
   ```

#### 1.4 Build Backend
1. Navigate to backend directory
   ```bash
   cd backend
   ```

2. Clean and build
   ```bash
   mvn clean install
   ```

3. Run the application
   ```bash
   mvn spring-boot:run
   ```

4. Verify startup
   - Look for: `Started InterviewAceApplication in X seconds`
   - Backend will run on: `http://localhost:8080`

### Part 2: Frontend Setup (React)

#### 2.1 Prerequisites
- Install Node.js 14+ and npm
  ```bash
  node --version
  npm --version
  ```

#### 2.2 Install Dependencies
1. Navigate to frontend directory
   ```bash
   cd frontend
   ```

2. Install npm packages
   ```bash
   npm install
   ```

3. Verify installation
   ```bash
   npm list react  # Should show installed react version
   ```

#### 2.3 Configure Frontend
1. Create `.env` file
   ```bash
   cp .env.example .env
   ```

2. Edit `.env` file
   ```
   REACT_APP_API_URL=http://localhost:8080/api
   REACT_APP_ENV=development
   ```

#### 2.4 Run Frontend
1. Start development server
   ```bash
   npm start
   ```

2. Wait for compilation
   - Look for: `Compiled successfully!`
   - Frontend will open automatically on: `http://localhost:3000`

### Part 3: Testing the Application

#### 3.1 Register a New User
1. Click "Register" on login page
2. Fill in the form:
   - Full Name: John Doe
   - Email: john@example.com
   - Mobile: 9876543210
   - Password: Password@123
3. Click Register
4. You'll be redirected to login page

#### 3.2 Login
1. Use your registered credentials
2. Choose login method (Email or Mobile)
3. Enter password
4. Click Login
5. You'll see the dashboard

#### 3.3 Complete Profile
1. Click "Profile" in navigation
2. Add personal details
3. Add skills and education
4. Upload resume
5. View completion percentage

#### 3.4 Take a Test
1. Click "Tests" in navigation
2. Choose a test category
3. Click "Start Test"
4. Answer questions
5. Use timer and navigation
6. Submit test
7. View results

### Part 4: Project Structure Overview

#### Backend Files
```
backend/
├── src/main/java/com/interviewace/
│   ├── entity/              # JPA entities (database models)
│   ├── repository/          # Data access layer
│   ├── service/             # Business logic
│   ├── controller/          # REST API endpoints
│   ├── dto/                 # Data transfer objects
│   ├── config/              # Spring configuration
│   ├── security/            # JWT & Security
│   ├── exception/           # Custom exceptions
│   ├── util/                # Utility classes
│   └── enums/               # Enum types
├── pom.xml                  # Maven dependencies
└── src/main/resources/
    └── application.properties  # Configuration

Total: 20+ Java classes
```

#### Frontend Files
```
frontend/
├── src/
│   ├── components/          # React components
│   │   ├── auth/            # Login, Register
│   │   ├── profile/         # Profile management
│   │   ├── test/            # Test taking
│   │   ├── interview/       # Interview questions
│   │   ├── admin/           # Admin features
│   │   ├── shared/          # Shared components
│   │   └── dashboard/       # Dashboard
│   ├── pages/               # Page components
│   ├── services/            # API service calls
│   ├── hooks/               # Custom hooks
│   ├── context/             # React context
│   ├── utils/               # Utilities
│   └── assets/              # Images, styles
├── public/                  # Static files
├── package.json             # Dependencies
└── .env.example             # Environment variables

Total: 30+ React files
```

### Part 5: Troubleshooting

| Issue | Solution |
|-------|----------|
| Port 8080 already in use | Change server.port in application.properties |
| MySQL connection failed | Check credentials and ensure MySQL is running |
| Frontend can't connect to backend | Verify CORS config and API URL in .env |
| npm install fails | Delete node_modules and package-lock.json, then reinstall |
| Java version error | Install Java 17: `java -version` should show 17+ |
| Token expired | Clear localStorage and login again |
| CORS errors | Ensure backend CORS config has `http://localhost:3000` |

### Part 6: Key Endpoints to Test

#### Authentication
```bash
# Register
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "fullName": "Test User",
    "email": "test@example.com",
    "mobileNumber": "9876543210",
    "password": "Password@123",
    "confirmPassword": "Password@123"
  }'

# Login
curl -X POST http://localhost:8080/api/auth/login-email \
  -H "Content-Type: application/json" \
  -d '{
    "email": "test@example.com",
    "password": "Password@123"
  }'
```

#### With Authentication
```bash
# Get Profile (use token from login)
curl -X GET http://localhost:8080/api/profile \
  -H "Authorization: Bearer YOUR_TOKEN"

# Get Tests
curl -X GET http://localhost:8080/api/student/tests \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### Part 7: Database Verification

```sql
-- Check all tables
USE interview_ace;
SHOW TABLES;

-- Check user count
SELECT COUNT(*) FROM users;

-- Check specific user
SELECT * FROM users WHERE email='test@example.com';

-- View user roles
SELECT user_id, full_name, role FROM users;
```

### Part 8: Performance Tips

1. **Backend**: 
   - Uses connection pooling
   - Lazy loading for relationships
   - Proper indexing on foreign keys

2. **Frontend**:
   - React functional components
   - Axios interceptors for auth
   - Local state management

3. **Database**:
   - Indexes on frequently queried columns
   - Proper foreign key relationships
   - Normalized schema

---

**Setup Complete!** 🎉

You're now ready to:
- Register and login users
- Create and manage tests
- Track job applications
- Prepare for interviews
- Track achievements

Start exploring the application and refer to the API documentation for all available endpoints.
