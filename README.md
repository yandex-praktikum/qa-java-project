# 🍔 Stellar Burgers — Unit Testing

![Java](https://img.shields.io/badge/Java-11-orange?logo=java)
![JUnit](https://img.shields.io/badge/JUnit-4.13.2-brightgreen)
![Mockito](https://img.shields.io/badge/Mockito-Mocking-green)
![JaCoCo](https://img.shields.io/badge/JaCoCo-Code%20Coverage-red)
![Maven](https://img.shields.io/badge/Maven-Build-red?logo=apachemaven)
![Unit Tests](https://img.shields.io/badge/Tests-Unit-blue)

---

## 📌 Project Overview

Automation project for testing the burger constructor logic in the educational service **Stellar Burgers**.

The main goal of this task is to implement **unit tests for the `Burger` class** and achieve **100% code coverage** using **JaCoCo**.

The project demonstrates:

- Unit testing with **JUnit 4**
- Dependency mocking with **Mockito**
- **Parameterized tests**
- Code coverage analysis with **JaCoCo**
- Maven-based project structure

---

## 🎯 Test Coverage

The following functionality of the `Burger` class is covered by unit tests:

### 🍞 Bun management
✔ Setting a bun for the burger (`setBuns`)

### 🥩 Ingredient management
✔ Adding ingredients (`addIngredient`)  
✔ Removing ingredients (`removeIngredient`)  
✔ Moving ingredients inside the burger (`moveIngredient`)

### 🧾 Receipt generation
✔ Correct receipt structure (`getReceipt`)  
✔ Correct bun name output  
✔ Correct ingredient format  
✔ Correct price output

## 🏗 Project Structure
```
src
├── main
│   └── java
│       └── praktikum
│           ├── Burger.java
│           ├── Bun.java
│           ├── Ingredient.java
│           ├── IngredientType.java
│           ├── Database.java
│           └── Praktikum.java
│
└── test
    └── java
        ├── BurgerTest.java
        ├── BurgerPriceTest.java
        └── BurgerReceiptTest.java
```

## 🧪 Tech Stack

| Tool | Purpose |
|------|---------|
| Java 11 | Core programming language |
| Maven | Dependency & build management |
| JUnit 4 | Unit testing framework |
| Mockito | Mocking dependencies |
| JaCoCo | Code coverage analysis |

---

## ▶ How to Run Tests

Run all tests:
```
mvn clean test
```
## 📊 Code Coverage Report
```
Generate coverage report:
```
## 📊Open report in browser:
```
target/site/jacoco/index.html
```
## 📈 What This Project Demonstrates

- Understanding of **unit testing principles**
- Use of **mock objects for isolation**
- Parameterized testing in **JUnit**
- Working with **Maven build lifecycle**
- Code coverage analysis with **JaCoCo**

---

## 👨‍💻 Author

**Nikita Makoveev**