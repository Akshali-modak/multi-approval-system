# Multi-User Approval System

## 🚀 Features
- User authentication (Signup & Login)
- Task creation with multi-user approval process
- Email notifications
- Real-time WebSockets for task status updates
- Commenting functionality

---

## 🛠️ Tech Stack
- **Backend:** Java, Spring Boot, PostgreSQL
- **Real-time:** WebSockets
- **Database:** PostgreSQL
- **API Tool:** Postman or Swagger

---

## 🌐 API Endpoints

### 1. User Authentication
- **Signup**  
`POST /api/auth/signup`
```json
{
    "name": "Sandeep kumar",
    "email": "kumar@example.com",
    "password": "password123"
}
