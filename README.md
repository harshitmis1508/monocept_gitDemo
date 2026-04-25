# 🎓 Student Course Registration & Fee Management System

A **Java JDBC-based console application** that manages student registrations, course enrollments, and fee tracking using a **layered architecture** (UI → Service → DAO → Database).

---

## 🚀 Features

### 👨‍🎓 Student Management

* Add new student with validation
* Update student details
* Delete student (with transaction support)

### 📚 Course Registration

* Register student for a course
* Prevent duplicate registrations
* Validate course from predefined list
* Case-insensitive course handling (e.g., `java = Java`)

### 💰 Fee Management

* Update course fees
* Ensure fees are always positive

### 🔍 Data Retrieval

* View all students with course details (JOIN)
* Search student by ID

### 📊 Reports

* High paying students report
* Course-wise student count

---

## 🧠 Key Concepts Used

* JDBC (Java Database Connectivity)
* PreparedStatement (SQL Injection Prevention)
* Transaction Management (Commit & Rollback)
* Layered Architecture
* Input Validation & Error Handling
* SQL Joins & Aggregations

---

## 🏗️ Project Structure

```
com.project.app
┣ model
┃ ┣ Student.java
┃ ┗ Registration.java
┣ dao
┃ ┣ StudentDAO.java
┃ ┣ RegistrationDAO.java
┃ ┗ CourseDAO.java
┣ service
┃ ┗ StudentService.java
┣ util
┃ ┣ DBUtil.java
┃ ┗ InputUtil.java
┗ app
 ┗ MainApp.java
```

---

## 🗄️ Database Setup

### 1. Create Database

```sql
CREATE DATABASE student_management;
USE student_management;
```

### 2. Create Tables

```sql
CREATE TABLE student (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT CHECK (age > 0),
    branch VARCHAR(50)
);

CREATE TABLE registration (
    reg_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_name VARCHAR(50),
    fees_paid DOUBLE CHECK (fees_paid > 0),
    FOREIGN KEY (student_id) REFERENCES student(id)
);

CREATE TABLE courses (
    course_name VARCHAR(50) PRIMARY KEY
);
```

### 3. Insert Predefined Courses

```sql
INSERT INTO courses VALUES
('Java'),
('Python'),
('C++'),
('DBMS'),
('React');
```

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```
git clone <your-repo-link>
```

### 2. Add MySQL Connector

* Download **mysql-connector-j**
* Add to Eclipse:

  ```
  Right Click Project → Build Path → Add External JAR
  ```

### 3. Configure DB Connection

Update in `DBUtil.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/student_management";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

---

## ▶️ How to Run

1. Start MySQL Server
2. Run `MainApp.java`
3. Use menu-driven interface

---

## 🧪 Sample Flow

```
1 Add Student
2 Register Course
3 View All
...
```

Example:

```
ID: 1
Name: Harshit
Course: Java
Fee: 5000
```

---

## 🔐 Validations Implemented

* Duplicate student ID ❌
* Duplicate course registration ❌
* Invalid course ❌
* Negative fee ❌
* Empty input ❌
* Student not found ❌

---

## 🔥 Transactions Used

### ✔ Register Course

* Ensures atomic insertion
* Rolls back on failure

### ✔ Delete Student

* Deletes registrations + student
* Rolls back if any step fails

---

## 💡 Highlights

* Clean layered architecture
* Robust input validation with retry mechanism
* Case-insensitive course matching
* Safe and secure DB operations
* Interview-ready implementation

---

## 🛠️ Tech Stack

* Java (Core)
* JDBC
* MySQL
* Eclipse IDE

---

## 📌 Future Improvements

* Convert to Spring Boot REST API
* Add GUI (JavaFX / Web)
* Logging & Exception Handling
* Authentication System

---

## 🙋‍♂️ Author

**Harshit Mishra**
Final Year CSE Student
Aspiring Software Engineer 🚀

---

## ⭐ If you like this project

Give it a ⭐ on GitHub and share it!
