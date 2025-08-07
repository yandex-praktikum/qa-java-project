# Дипломное задание №1 Юнит-тесты
## Цель задания
1. Покрыть тестами класс Burger
2. Используй моки, стабы и параметризацию
3. Покрытие кода класса Burger должно быть 100%



##  Технологии

| Компонент | Версия  | Описание                                       |
|-----------|---------|------------------------------------------------|
| Java      | 11      | Основной язык                                  |
| JUnit     | 4.13.2  | Фреймворк для тестов                           |
| JaCoCo    | 0.8.8   | Анализ покрытия кода с генерацией HTML-отчётов |
| Mockito   | 3.12.4  | Мокирование зависимостей                       |
| Maven     | 3.9.0   | Система сборки                                 |


## Структура проекта
<pre>
QA-java-diplom-1/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── praktikum/
│   │           ├── Bun.java
│   │           ├── Burger.java
│   │           ├── Database.java
│   │           ├── Ingredient.java
│   │           ├── IngredientType.java
│   │           └── Praktikum.java
│   └── test/
│       └── java/
│           └── praktikum/
│               ├── BurgerTest.java         # Основные unit-тесты с Mockito
│               └── BurgerReceiptTest.java  # Параметризованные тесты чека
├── pom.xml
└── README.md
</pre>

## Запуск тестов
### Предварительные требования
1. Установите:
    - JDK 11
    - Maven 3.9+
    - JaCoCo 0.8.8
### Клонирование и подготовка
```bash
git clone https://github.com/sinigqa/QA-java-diplom-1.git
cd QA-java-diplom-1    # Переход в папку проекта
mvn clean test         # Запуск всех тестов
mvn jacoco:report      # Генерация отчета (target/site/jacoco/index.html)