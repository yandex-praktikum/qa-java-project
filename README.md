\# 🍔 Stellar Burgers – Юнит-тестирование класса Burger



!\[Java](https://img.shields.io/badge/Java-11-blue)

!\[JUnit](https://img.shields.io/badge/JUnit-4.13.2-green)

!\[Mockito](https://img.shields.io/badge/Mockito-4.11.0-red)

!\[JaCoCo](https://img.shields.io/badge/JaCoCo-0.8.8-brightgreen)

!\[Coverage](https://img.shields.io/badge/coverage-100%25-success)



\## 📌 Описание проекта



Данный проект является частью дипломной работы по автоматизации тестирования веб-приложения \*\*Stellar Burgers\*\*. В рамках \*\*первого задания\*\* выполнено полное покрытие юнит-тестами класса `Burger` — ключевой модели для формирования заказа в бургерной.



Цель: достичь \*\*100% покрытия кода\*\* с применением моков, стабов и параметризации.



\## 🧱 Структура проекта



qa-java-project-main/

├── src/

│ ├── main/

│ │ └── java/praktikum/

│ │ ├── Bun.java

│ │ ├── Burger.java

│ │ ├── Database.java

│ │ ├── Ingredient.java

│ │ ├── IngredientType.java

│ │ └── Praktikum.java

│ └── test/

│ └── java/praktikum/

│ └── BurgerTest.java

├── pom.xml

└── README.md





\## ✅ Что протестировано



В классе `BurgerTest` проверены все публичные методы класса `Burger`:



| Метод | Описание тестов |

|-------|-----------------|

| `setBuns(Bun bun)` | Проверка корректного сохранения объекта булочки |

| `addIngredient(Ingredient)` | Добавление одного и нескольких ингредиентов |

| `removeIngredient(int index)` | Удаление по индексу, реакция на некорректный индекс |

| `moveIngredient(int from, int to)` | Перемещение ингредиентов в списке, обработка неверных индексов |

| `getPrice()` | Расчёт итоговой стоимости с параметризацией (разные комбинации цен) |

| `getReceipt()` | Формирование чека с ингредиентами и без |



\### Предварительные требования

\- Java 11

\- Maven 3.6+



\### Запустить тест

mvn clean test

