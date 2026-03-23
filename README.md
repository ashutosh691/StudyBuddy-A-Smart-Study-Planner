# 📚 Intelligent Exam Preparation Planner

## 🚀 Overview

The Intelligent Exam Preparation Planner is a web-based application designed to help students generate optimized study plans based on multiple subjects, exam dates, and difficulty levels.

The system intelligently prioritizes subjects and distributes topics across available days, ensuring efficient preparation.

---

## 🎯 Objectives

* Automate study plan generation
* Prioritize subjects based on exam dates
* Handle multiple subjects dynamically
* Provide user-specific plan storage
* Improve productivity and time management

---

## 🏗️ Tech Stack

### 💻 Backend

* Java (Servlets)
* JDBC

### 🌐 Frontend

* HTML
* CSS
* JavaScript (for dynamic forms)

### 🗄️ Database

* MySQL

### ⚙️ Server

* Apache Tomcat

---

## 🧠 Key Features (Phase 2)

### 🔐 Authentication System

* User Signup (with duplicate username validation)
* User Login (session-based authentication)
* Logout functionality

---

### 📥 Dynamic Input System

* User selects number of subjects
* Dynamic form generation using JavaScript
* Input includes:

  * Subject Name
  * Topics (comma-separated)
  * Difficulty Level
  * Exam Date

---

### 🧠 Intelligent Scheduling Logic

* Subjects sorted based on nearest exam date
* Topics distributed across available days
* Difficulty considered during planning
* Ensures urgent subjects are prioritized

---

### 📊 Study Plan Generation

* Day-wise structured plan
* Multiple subjects handled simultaneously
* Clean and readable output

---

### 💾 Database Integration

* Normalized schema design:

#### Tables:

* `users` → stores user credentials
* `study_plan` → stores plan metadata
* `plan_details` → stores day-wise schedule

---

### 🔗 Relationships

* Each plan is linked to a user using `user_id`
* Each plan contains multiple day-wise entries

---

### 💡 Conditional Saving

* Plan is NOT saved automatically
* User must click **"Save Plan"**
* Improves control and user experience

---

### 📂 View Saved Plans

* Users can view previously saved plans
* Data filtered based on logged-in user
* Structured display using JSP + JSTL

---

### 🎨 UI/UX Improvements

* Clean dark-themed interface
* Card-based layout for plans
* Popup alerts for invalid login/signup
* Responsive and user-friendly design

---

## 🔄 System Workflow

Signup → Login → Input Subjects → Generate Plan → Save Plan → View Plans → Logout

---

## 🗂️ Project Structure

```
ExamPrepPlanner
│
├── src/main/java/com/examprepplanner
│   ├── dao
│   │   └── PlanDAO.java
│   │
│   ├── database
│   │   └── DBConnection.java
│   │
│   ├── logic
│   │   └── Scheduler.java
│   │
│   ├── model
│   │   └── Subject.java
│   │
│   └── servlet
│       ├── LoginServlet.java
│       ├── SignupServlet.java
│       ├── LogoutServlet.java
│       ├── PlanServlet.java
│       ├── SavePlanServlet.java
│       └── ViewPlansServlet.java
│
├── src/main/webapp
│   ├── index.html
│   ├── login.html
│   ├── signup.html
│   ├── result.jsp
│   └── viewPlans.jsp
│
└── WEB-INF
```

---

## 🧪 Testing & Validation

* Tested with multiple subjects and varying difficulty levels
* Verified correct prioritization based on exam dates
* Validated database storage and retrieval
* Handled edge cases:

  * Same exam dates
  * Very few days left
  * Multiple subjects with many topics

---

## 🧠 Design Principles

* MVC architecture (Servlet → Logic → JSP)
* Separation of concerns
* Normalized database design
* Session-based user management

---

## ⚠️ Limitations (Current Phase)

* No edit/delete functionality for plans
* No visualization (charts/graphs)
* Static difficulty handling (can be improved)

---

## 🚀 Future Enhancements (Phase 3)

* Calendar-based scheduling view
* Plan editing and deletion
* Progress tracking system
* Smart AI-based scheduling improvements
* Visualization dashboards (charts/graphs)

---

## 🧠 Key Learning Outcomes

* Java Servlets and JSP integration
* JDBC and database normalization
* Session management
* Dynamic UI using JavaScript
* Full-stack application development

---

## 👨‍💻 Author

Ashutosh Upreti

---

## 📌 Conclusion

The project successfully demonstrates a full-stack web application that intelligently generates and manages study plans. It provides a strong foundation for further enhancements in Phase 3.

---
