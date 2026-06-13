# InterviewAce - Smart Interview Preparation & Career Tracking Platform

A comprehensive full-stack application built with **Spring Boot** (Backend) and **React JS** (Frontend) to help fresher/intermediate developers prepare for interviews, track their progress, and manage their job applications.

## 📋 Project Overview

**InterviewAce** is an all-in-one platform featuring:
- **Authentication & Security** - User registration, login, JWT tokens, BCrypt encryption
- **Profile Management** - Personal details, skills, education, resume upload
- **MCQ Test System** - Create and attempt tests with timer, mark for review
- **Interview Question Bank** - Search, filter, bookmark, and mark questions as learned
- **Job Tracking** - Track job applications with status updates
- **Mock Interview Tracker** - Schedule and track mock interviews
- **Leaderboard** - Compete with other users based on test scores
- **Dashboard** - View statistics and recent activities
- **Achievement System** - Earn badges for milestones
- **Notifications** - Stay updated with platform activities

## 🔧 Technology Stack

### Backend
- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Security** with JWT
- **Spring Data JPA**
- **MySQL** Database
- **Maven** Build Tool

### Frontend
- **React 18.2**
- **React Router DOM** for navigation
- **Axios** for API calls
- **JavaScript (ES6+)**
- **CSS3**

## 📁 Project Structure

```
InterviewAce/
├── backend/
│   ├── src/main/
│   │   ├── java/com/interviewace/
│   │   │   ├── controller/     (REST API endpoints)
│   │   │   ├── service/        (Business logic)
│   │   │   ├── repository/     (Database access)
│   │   │   ├── entity/         (JPA entities)
│   │   │   ├── dto/            (Data transfer objects)
│   │   │   ├── config/         (Configuration classes)
│   │   │   ├── security/       (JWT security)
│   │   │   ├── exception/      (Exception handling)
│   │   │   ├── util/           (Utility classes)
│   │   │   └── enums/          (Enum classes)
│   │   └── resources/
│   │       └── application.properties
│   ├── pom.xml
│   └── DATABASE_SETUP.sql
│
└── frontend/
    ├── src/
    │   ├── components/         (React components)
    │   ├── pages/             (Page components)
    │   ├── services/          (API services)
    │   ├── hooks/             (Custom hooks)
    │   ├── context/           (React context)
    │   ├── utils/             (Utility functions)
    │   ├── assets/            (Images, styles)
    │   ├── App.jsx
    │   └── index.js
    ├── public/
    ├── package.json
    └── .env.example
```

## 🚀 Setup Instructions

### Prerequisites
- **Java 17** or higher
- **Node.js 14+** and npm
- **MySQL 8.0** or higher
- **Git**

### Backend Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd InterviewAce/backend
   ```

2. **Create MySQL Database**
   ```bash
   mysql -u root -p < DATABASE_SETUP.sql
   ```

3. **Configure Database Connection**
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/interview_ace
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

4. **Build and Run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   
   Backend will start on `http://localhost:8080`

### Frontend Setup

1. **Navigate to frontend directory**
   ```bash
   cd ../frontend
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Create .env file**
   ```bash
   cp .env.example .env
   ```

4. **Start development server**
   ```bash
   npm start
   ```
   
   Frontend will open on `http://localhost:3000`

## 🔌 API Documentation

### Authentication APIs
- **POST** `/api/auth/register` - Register new user
- **POST** `/api/auth/login-email` - Login with email
- **POST** `/api/auth/login-mobile` - Login with mobile
- **POST** `/api/auth/forgot-password/request-otp` - Request password reset OTP
- **POST** `/api/auth/forgot-password/verify-otp` - Verify OTP
- **POST** `/api/auth/forgot-password/set-password` - Set new password
- **POST** `/api/auth/change-password` - Change password (authenticated)
- **POST** `/api/auth/logout` - Logout (authenticated)

### Profile APIs
- **GET** `/api/profile` - Get user profile
- **PUT** `/api/profile/personal-details` - Update personal details
- **POST** `/api/profile/skills` - Add skill
- **GET** `/api/profile/skills` - Get all skills
- **POST** `/api/profile/education` - Add education
- **GET** `/api/profile/education` - Get all education
- **GET** `/api/profile/completion-percentage` - Get profile completion

### Test APIs
- **GET** `/api/student/tests` - Get available tests
- **POST** `/api/student/test-attempts/start/{testId}` - Start test
- **GET** `/api/student/test-attempts/{attemptId}/questions` - Get test questions
- **POST** `/api/student/test-attempts/{attemptId}/answer` - Submit answer
- **POST** `/api/student/test-attempts/{attemptId}/submit` - Submit test
- **GET** `/api/student/test-attempts/{attemptId}/result` - Get test result

### Admin APIs
- **POST** `/api/admin/questions` - Create question
- **GET** `/api/admin/questions` - Get all questions
- **PUT** `/api/admin/questions/{questionId}` - Update question
- **DELETE** `/api/admin/questions/{questionId}` - Delete question

### Other APIs
- Interview questions, Job tracking, Mock interviews, Leaderboard, Dashboard, Achievements, Notifications

## 📊 Database Schema

The application uses 15 tables:
1. **users** - User accounts
2. **profiles** - User profiles
3. **skills** - User skills
4. **education** - Education records
5. **password_reset** - Password reset OTP
6. **questions** - MCQ questions
7. **tests** - Test collections
8. **test_questions** - Questions in tests
9. **test_attempts** - User test attempts
10. **test_answers** - User answers
11. **interview_questions** - Interview Q&A
12. **bookmarked_questions** - Bookmarked questions
13. **job_applications** - Job applications
14. **mock_interviews** - Mock interview records
15. **achievements** - User achievements
16. **notifications** - User notifications

## 🔐 Security Features

- **BCrypt Password Encryption** - Passwords encrypted with BCrypt
- **JWT Authentication** - Stateless token-based authentication
- **CORS Configuration** - Cross-origin resource sharing enabled
- **Role-based Access Control** - Admin and Student roles
- **Input Validation** - All inputs validated
- **SQL Injection Protection** - Using prepared statements

## 🎯 Key Features Implemented

✅ Complete user authentication system  
✅ Profile management with completion percentage  
✅ MCQ test creation and execution  
✅ Real-time test timer and navigation  
✅ Answer review and mark for review functionality  
✅ Detailed test result analysis  
✅ Interview question bank with search/filter  
✅ Bookmark and learning tracking  
✅ Job application tracker  
✅ Mock interview scheduler  
✅ Leaderboard rankings  
✅ Dashboard with statistics  
✅ Achievement system  
✅ Notification system  

## 🧪 Testing the Application

### Default Admin Credentials (After Setup)
- Email: `admin@interviewace.com`
- Password: `Admin@123`

### Default Student Credentials
- Email: `student@interviewace.com`
- Password: `Student@123`

## 📝 Code Quality Standards

- ✅ Simple and readable code
- ✅ Meaningful variable and function names
- ✅ Minimal comments (self-explanatory code)
- ✅ No advanced enterprise patterns
- ✅ Beginner-friendly code
- ✅ Standard folder structure
- ✅ REST API best practices
- ✅ Security best practices

## 🐛 Common Issues & Solutions

### Issue: Database connection failed
**Solution:** Ensure MySQL is running and credentials in `application.properties` are correct

### Issue: CORS errors in frontend
**Solution:** Check that backend CORS config includes `http://localhost:3000`

### Issue: JWT token expired
**Solution:** Token expires in 24 hours. User needs to login again

### Issue: Port already in use
**Solution:** Change port in `application.properties` or kill the process using the port

## 📚 Learning Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev)
- [JWT Authentication](https://jwt.io)
- [REST API Best Practices](https://restfulapi.net)

## 🤝 Contributing

This project is designed for learning. Feel free to:
- Extend features
- Improve UI/UX
- Add more question categories
- Implement advanced analytics
- Add real-time features

## 📄 License

This project is open source for educational purposes.

## 📞 Support

For issues and questions, please refer to the code comments and documentation provided in each file.

---

**Built with ❤️ for Interview Preparation**
