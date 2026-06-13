# InterviewAce API Documentation

## Base URL
```
http://localhost:8080/api
```

## Authentication
All authenticated endpoints require JWT token in header:
```
Authorization: Bearer <JWT_TOKEN>
```

---

## 1. Authentication APIs

### 1.1 Register User
**Endpoint:** `POST /auth/register`  
**Auth Required:** No

**Request Body:**
```json
{
  "fullName": "John Doe",
  "email": "john@example.com",
  "mobileNumber": "9876543210",
  "password": "Password@123",
  "confirmPassword": "Password@123"
}
```

**Response:** `201 Created`
```json
{
  "message": "User registered successfully",
  "user": {
    "userId": 1,
    "fullName": "John Doe",
    "email": "john@example.com",
    "mobileNumber": "9876543210",
    "role": "ROLE_STUDENT",
    "profileCompletion": 0,
    "isActive": true,
    "createdAt": "2024-01-20T10:30:00"
  }
}
```

### 1.2 Login with Email
**Endpoint:** `POST /auth/login-email`  
**Auth Required:** No

**Request Body:**
```json
{
  "email": "john@example.com",
  "password": "Password@123"
}
```

**Response:** `200 OK`
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "user": {...},
  "message": "Login successful"
}
```

### 1.3 Login with Mobile
**Endpoint:** `POST /auth/login-mobile`  
**Auth Required:** No

**Request Body:**
```json
{
  "mobileNumber": "9876543210",
  "password": "Password@123"
}
```

**Response:** `200 OK` (same as login-email)

### 1.4 Change Password
**Endpoint:** `POST /auth/change-password`  
**Auth Required:** Yes

**Request Body:**
```json
{
  "oldPassword": "Password@123",
  "newPassword": "NewPassword@123",
  "confirmPassword": "NewPassword@123"
}
```

**Response:** `200 OK`
```json
{
  "message": "Password changed successfully"
}
```

### 1.5 Forgot Password - Request OTP
**Endpoint:** `POST /auth/forgot-password/request-otp`  
**Auth Required:** No

**Request Body:**
```json
{
  "mobileNumber": "9876543210"
}
```

**Response:** `200 OK`
```json
{
  "message": "OTP sent successfully",
  "resetId": 1
}
```

### 1.6 Forgot Password - Verify OTP
**Endpoint:** `POST /auth/forgot-password/verify-otp`  
**Auth Required:** No

**Request Body:**
```json
{
  "resetId": 1,
  "otp": "123456"
}
```

**Response:** `200 OK`
```json
{
  "message": "OTP verified",
  "token": "eyJhbGciOiJIUzUxMiJ9..."
}
```

### 1.7 Set New Password
**Endpoint:** `POST /auth/forgot-password/set-password`  
**Auth Required:** No

**Request Body:**
```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9...",
  "newPassword": "NewPassword@123",
  "confirmPassword": "NewPassword@123"
}
```

**Response:** `200 OK`
```json
{
  "message": "Password reset successful"
}
```

---

## 2. Profile APIs

### 2.1 Get Profile
**Endpoint:** `GET /profile`  
**Auth Required:** Yes

**Response:** `200 OK`
```json
{
  "profileId": 1,
  "user": {...},
  "bio": "Java Developer",
  "location": "New York",
  "yearsOfExperience": 2,
  "linkedinUrl": "https://linkedin.com/in/johndoe",
  "resumeUrl": "uploads/resume_1.pdf",
  "createdAt": "2024-01-20T10:30:00",
  "updatedAt": "2024-01-20T10:30:00"
}
```

### 2.2 Update Profile
**Endpoint:** `PUT /profile/personal-details`  
**Auth Required:** Yes

**Request Body:**
```json
{
  "bio": "Senior Java Developer",
  "location": "San Francisco",
  "yearsOfExperience": 3,
  "linkedinUrl": "https://linkedin.com/in/johndoe"
}
```

**Response:** `200 OK`
```json
{
  "message": "Profile updated",
  "profile": {...}
}
```

### 2.3 Add Skill
**Endpoint:** `POST /profile/skills`  
**Auth Required:** Yes

**Request Body:**
```json
{
  "skillName": "Java",
  "proficiencyLevel": "Advanced"
}
```

**Response:** `201 Created`
```json
{
  "skillId": 1,
  "message": "Skill added"
}
```

### 2.4 Get All Skills
**Endpoint:** `GET /profile/skills`  
**Auth Required:** Yes

**Response:** `200 OK`
```json
[
  {
    "skillId": 1,
    "skillName": "Java",
    "proficiencyLevel": "Advanced",
    "createdAt": "2024-01-20T10:30:00"
  },
  ...
]
```

### 2.5 Add Education
**Endpoint:** `POST /profile/education`  
**Auth Required:** Yes

**Request Body:**
```json
{
  "instituteName": "XYZ University",
  "degree": "B.Tech",
  "fieldOfStudy": "Computer Science",
  "startYear": 2018,
  "endYear": 2022,
  "gradeCgpa": 7.5
}
```

**Response:** `201 Created`
```json
{
  "educationId": 1,
  "message": "Education added"
}
```

### 2.6 Get All Education
**Endpoint:** `GET /profile/education`  
**Auth Required:** Yes

**Response:** `200 OK` (array of education objects)

### 2.7 Get Profile Completion Percentage
**Endpoint:** `GET /profile/completion-percentage`  
**Auth Required:** Yes

**Response:** `200 OK`
```json
{
  "completionPercentage": 75
}
```

---

## 3. Test APIs (Student)

### 3.1 Get Available Tests
**Endpoint:** `GET /student/tests?category=Java&page=0&size=10`  
**Auth Required:** Yes

**Response:** `200 OK`
```json
{
  "content": [
    {
      "testId": 1,
      "testName": "Java Basics",
      "category": "Java",
      "totalQuestions": 20,
      "durationMinutes": 60,
      "difficultyLevel": "MEDIUM",
      "isActive": true,
      "createdAt": "2024-01-20T10:30:00"
    },
    ...
  ],
  "totalElements": 50,
  "totalPages": 5,
  "currentPage": 0
}
```

### 3.2 Start Test
**Endpoint:** `POST /student/test-attempts/start/{testId}`  
**Auth Required:** Yes

**Response:** `201 Created`
```json
{
  "attemptId": 1,
  "startTime": "2024-01-20T10:30:00",
  "message": "Test started"
}
```

### 3.3 Get Test Questions
**Endpoint:** `GET /student/test-attempts/{attemptId}/questions`  
**Auth Required:** Yes

**Response:** `200 OK`
```json
{
  "testName": "Java Basics",
  "totalQuestions": 20,
  "durationMinutes": 60,
  "questions": [
    {
      "questionId": 1,
      "category": "JAVA",
      "difficulty": "EASY",
      "questionText": "What is JVM?",
      "optionA": "Java Virtual Machine",
      "optionB": "Java Version Manager",
      "optionC": "Java Visual Model",
      "optionD": "None",
      "explanation": "JVM stands for Java Virtual Machine"
    },
    ...
  ]
}
```

### 3.4 Submit Answer
**Endpoint:** `POST /student/test-attempts/{attemptId}/answer`  
**Auth Required:** Yes

**Request Body:**
```json
{
  "questionId": 1,
  "userAnswer": "A",
  "isMarkedForReview": false
}
```

**Response:** `200 OK`
```json
{
  "message": "Answer submitted"
}
```

### 3.5 Submit Test
**Endpoint:** `POST /student/test-attempts/{attemptId}/submit`  
**Auth Required:** Yes

**Response:** `200 OK`
```json
{
  "message": "Test submitted",
  "totalScore": 150,
  "correctAnswers": 15,
  "wrongAnswers": 5,
  "accuracy": 75.0
}
```

### 3.6 Get Test Result
**Endpoint:** `GET /student/test-attempts/{attemptId}/result`  
**Auth Required:** Yes

**Response:** `200 OK`
```json
{
  "attemptId": 1,
  "testName": "Java Basics",
  "totalScore": 150,
  "correctAnswers": 15,
  "wrongAnswers": 5,
  "unanswered": 0,
  "accuracy": 75.0,
  "timeTakenSeconds": 1800,
  "categoryWisePerformance": [
    {
      "category": "JAVA",
      "score": 10,
      "total": 10
    }
  ]
}
```

---

## 4. Interview Questions APIs

### 4.1 Get All Interview Questions
**Endpoint:** `GET /interview-questions?page=0&size=10`  
**Auth Required:** Yes

**Response:** `200 OK`
```json
{
  "content": [
    {
      "interviewQuestionId": 1,
      "category": "JAVA",
      "questionText": "What is polymorphism?",
      "answerText": "Polymorphism is the ability of an object to take multiple forms..."
    },
    ...
  ],
  "totalElements": 100,
  "totalPages": 10
}
```

### 4.2 Search Questions
**Endpoint:** `GET /interview-questions?search=polymorphism&category=JAVA&page=0&size=10`  
**Auth Required:** Yes

### 4.3 Bookmark Question
**Endpoint:** `POST /interview-questions/{questionId}/bookmark`  
**Auth Required:** Yes

**Response:** `201 Created`
```json
{
  "bookmarkId": 1,
  "message": "Question bookmarked"
}
```

### 4.4 Get Bookmarked Questions
**Endpoint:** `GET /student/bookmarked-questions?page=0&size=10`  
**Auth Required:** Yes

### 4.5 Mark as Learned
**Endpoint:** `PUT /interview-questions/{questionId}/mark-learned`  
**Auth Required:** Yes

**Request Body:**
```json
{
  "isLearned": true
}
```

---

## 5. Job Tracker APIs

### 5.1 Add Job Application
**Endpoint:** `POST /student/job-applications`  
**Auth Required:** Yes

**Request Body:**
```json
{
  "companyName": "Tech Company",
  "position": "Java Developer",
  "location": "New York",
  "appliedDate": "2024-01-20T00:00:00",
  "status": "Applied",
  "notes": "Applied via LinkedIn"
}
```

**Response:** `201 Created`
```json
{
  "jobId": 1,
  "message": "Job application added"
}
```

### 5.2 Get Job Applications
**Endpoint:** `GET /student/job-applications?status=Applied&page=0&size=10`  
**Auth Required:** Yes

---

## 6. Leaderboard APIs

### 6.1 Get Top Scorers
**Endpoint:** `GET /leaderboard/top-scorers?limit=10`  
**Auth Required:** Yes

**Response:** `200 OK`
```json
[
  {
    "rank": 1,
    "userId": 5,
    "fullName": "Jane Doe",
    "averageScore": 92.5,
    "testsAttempted": 10,
    "bestScore": 100
  },
  ...
]
```

### 6.2 Get User Rank
**Endpoint:** `GET /leaderboard/user-rank`  
**Auth Required:** Yes

---

## Error Responses

### Validation Error
```json
{
  "message": "Invalid email format",
  "status": 400
}
```

### Unauthorized
```json
{
  "message": "Unauthorized",
  "status": 401
}
```

### Not Found
```json
{
  "message": "User not found",
  "status": 404
}
```

### Server Error
```json
{
  "message": "Internal server error",
  "status": 500
}
```

---

## HTTP Status Codes

| Code | Meaning |
|------|---------|
| 200 | OK - Request successful |
| 201 | Created - Resource created |
| 400 | Bad Request - Invalid input |
| 401 | Unauthorized - Auth required |
| 403 | Forbidden - Access denied |
| 404 | Not Found - Resource not found |
| 500 | Server Error |

---

## Testing with cURL

```bash
# Register
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"fullName":"Test","email":"test@example.com"...}'

# Login and get token
TOKEN=$(curl -s -X POST http://localhost:8080/api/auth/login-email \
  -H "Content-Type: application/json" \
  -d '{"email":"test@example.com","password":"Password@123"}' | jq -r '.token')

# Use token for authenticated requests
curl -X GET http://localhost:8080/api/profile \
  -H "Authorization: Bearer $TOKEN"
```

---

**API Documentation Complete** ✅

For more details, refer to individual API implementations in the controller classes.
