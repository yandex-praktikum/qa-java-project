Проект содержит модульные (юнит) тесты для проверки бизнес-логики приложения **"Stellar Burgers"**.
Основной упор сделан на тестирование класса `Burger`: добавление ингредиентов, расчет стоимости и печать чека.

---

## Цель проекта

Выполнение задания по автоматизации тестирования:
- Покрыть юнит-тестами класс `Burger` с использованием моков и параметризации
- Достичь 100% покрытия кода
- Использовать JUnit 4, Mockito и JaCoCo

---

## 🧪 Структура тестов
### Тестовые классы для Burger:

1. **`BurgerTest.java`** — Основные тесты с использованием Mockito:
    - Установка булочки (`setBuns`)
    - Добавление и удаление ингредиентов (`addIngredient`, `removeIngredient`)
    - Перемещение ингредиентов (`moveIngredient`)
    - Формирование текста чека (`getReceipt`)

2. **`BurgerPriceParameterizedTest.java`** — Параметризованный тест:
    - Расчет полной стоимости бургера (`getPrice`)
    - Тестирование различных комбинаций цен

3. **`BurgerRemoveParameterizedTest.java`** — Дополнительный параметризованный тест:
    - Удаление ингредиентов по разным индексам

---
## 🧪 Запуск тестов
bash
# Все тесты
mvn clean test
# Конкретный тестовый класс
mvn test -Dtest=BurgerTest
mvn test -Dtest=BurgerPriceParameterizedTest
# С детальным выводом
mvn test -Dtest=BurgerTest -DfailIfNoTests=false
📊 Отчеты
bash
# Генерация отчета JaCoCo
mvn clean test jacoco:report
# Открытие отчета (Windows)
start target/site/jacoco/index.html
# Проверка покрытия
mvn jacoco:check

---

## 📊 Результаты покрытия кода

### ✅ Класс Burger: 100% покрытие

![Диплом1.PNG](%D0%94%D0%B8%D0%BF%D0%BB%D0%BE%D0%BC1.PNG)

---
## 📁 Структура проекта

### Корень проекта
| Файл/Папка | Назначение |
|------------|------------|
| `src/` | Исходный код и тесты |
| `pom.xml` | Конфигурация Maven с зависимостями |
| `README.md` | Документация проекта |
| `coverage-burger.png` | Скриншот 100% покрытия кода |
| `.gitignore` | Исключения для Git |

### Исходный код (`src/main/java/praktikum/`)
| Файл | Описание |
|------|----------|
| `Burger.java` | **Основной тестируемый класс** - логика бургера |
| `Bun.java` | Модель булочки (name, price) |
| `Ingredient.java` | Модель ингредиента (type, name, price) |
| `IngredientType.java` | Enum: SAUCE, FILLING |
| `Database.java` | База данных доступных ингредиентов |
| `Praktikum.java` | Основной класс приложения |

### Тесты (`src/test/java/praktikum/`)
| Файл | Тестов | Тип | Описание |
|------|--------|-----|----------|
| `BurgerTest.java` | 19     | Моки | Основные тесты с Mockito |
| `BurgerPriceParameterizedTest.java` | 4      | Параметризованный | Тест расчета цены с разными данными |
| `BurgerRemoveParameterizedTest.java` | 7      | Параметризованный | Тест удаления по разным индексам |

### Отчеты (`target/site/jacoco/`)
| Файл | Назначение |
|------|------------|
| `index.html` | **HTML отчет о покрытии кода** |
| `jacoco-resources/` | Ресурсы для отображения отчета |
| `jacoco.xml` | XML версия отчета |

---
## 👤 Автор
### Tokoro77
### 📁 github.com/Tokoro77/Diplom_1
### 🌿 Ветка: develop1