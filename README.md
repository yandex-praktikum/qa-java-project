# 🍔 Stellar Burgers — Unit Tests

Автоматические юнит-тесты для учебного проекта **Stellar Burgers**  
(Задание 1 диплома Яндекс.Практикума)

---

## 🚀 Технологии

| Технология | Версия |
|-----------|--------|
| Java      | 11     |
| Maven     | 3.x    |
| JUnit     | 4.13.2 |
| Mockito   | 5.x    |
| Hamcrest  | 2.2    |
| JaCoCo    | 0.8.11 |

---

## 🧱 Структура проекта

```
QA-java-diplom-1
│
├── src
│   ├── main/java/praktikum
│   │   ├── Bun.java
│   │   ├── Burger.java
│   │   ├── Database.java
│   │   ├── Ingredient.java
│   │   ├── IngredientType.java
│   │   └── Praktikum.java
│   │
│   └── test/java/praktikum
│       ├── BurgerTest.java
│       ├── BunParameterizedTest.java
│       └── IngredientParameterizedTest.java
│
├── .gitignore
├── pom.xml
└── README.md
```

---

## 🧪 Покрытие тестами

### ✔ Burger (моки)
- Установка булки
- Добавление ингредиентов
- Удаление ингредиента
- Перемещение ингредиента
- Полный расчёт цены
- Полное сравнение строки чека

### ✔ Bun (параметризация)
- Разные имена и цены булок
- Положительные, нулевые, отрицательные значения

### ✔ Ingredient (параметризация)
- Проверка типа
- Проверка имени
- Проверка цены

---

## 📊 Покрытие JaCoCo

Генерация:

```bash
mvn clean test
```

Открыть отчёт:

```
reports/jacoco/index.html
```

Покрытие `Burger`, `Bun`, `Ingredient`, `IngredientType` — **100%**.

---

## ▶️ Запуск тестов

```bash
mvn clean test
```

---

