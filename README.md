# 🎓 Student Management System (Java)

## 📌 Overview

The **Student Management System** is a console-based Java application designed to manage student records efficiently. It allows users to add, update, delete, and view student details along with automatic grade calculation based on performance.

This project demonstrates the use of core Java concepts and Object-Oriented Programming (OOP) principles.

---

## 🚀 Features

* ➕ Add new students
* ❌ Delete existing students
* 🔄 Update student details (name & marks)
* 📋 View all student records
* 📊 Automatic calculation of:

  * Average marks
  * Highest marks
  * Lowest marks
* 🏆 Grade assignment based on average performance

---

## 🧠 Concepts Used

This project is built using the following Java concepts:

* Object-Oriented Programming (OOP)

  * Classes & Objects
  * Interfaces
  * Encapsulation
* Java Collections (`ArrayList`)
* Exception Handling (`try-catch`)
* User Input Handling (`Scanner`)
* Basic Control Structures (loops, conditionals)

---

## 📂 Project Structure

```
studentManagementSystem/
 └── studentGradeTrack.java
```

---

## ▶️ How to Run

### 1. Compile the program

```bash
javac studentManagementSystem/studentGradeTrack.java
```

### 2. Run the program

```bash
java studentManagementSystem.studentGradeTrack
```

---

## 💡 How It Works

* The system maintains a list of students using an `ArrayList`.
* Each student has:

  * Unique ID [instade od Roll no. Because deletion a student would broke the sequential order of Roll no.]
  * Name
  * Marks (4 subjects)
  * Calculated grade
* Users interact with the system through a menu-driven interface.
* All operations (CRUD) are performed dynamically during runtime.

---

## 📸 Sample Menu

```
1. Initialize Student Database
2. Add New Student(s)
3. Delete a Student
4. Update a Student
5. View All Students
6. Exit
```

---

## 🔮 Future Improvements

* Search functionality
* Input validation improvements

---

## 👨‍💻 Author

**Rupam Pal**

---

## ⭐ Acknowledgment

This project is created for learning and practicing Java programming and OOP concepts.

---
