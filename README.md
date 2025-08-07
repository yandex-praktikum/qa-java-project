# Дипломное задание №1 Юнит-тесты

## Цель задания
1. Покрыть тестами класс Burger
2. Использовать моки, стабы и параметризацию
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
│               ├── BurgerPriceTest.java    # Параметризированные тесты для getPrice
│               ├── BurgerReceiptTest.java  # Параметризированные тесты для getReceipt
│               └── BurgerTest.java         # Основные unit-тесты с Mockito
├── pom.xml
└── README.md
</pre>

## Список тестов

### Тесты BurgerTest
1. setBunsSetsBunCorrectly - Убеждается, что метод setBuns корректно сохраняет переданную булочку в поле bun класса Burger.
2. addIngredientAddsOneIngredient - Проверяет, что после вызова addIngredient размер списка ingredients увеличивается на 1.
3. addIngredientAddsCorrectIngredient - Убеждается, что добавленный ингредиент действительно попадает в список ingredients.
4. removeIngredientRemovesFromList - Проверяет, что после удаления ингредиента список ingredients становится пустым.
5. removeIngredientThrowsWhenIndexInvalid - Убеждается, что при попытке удалить ингредиент из пустого списка выбрасывается IndexOutOfBoundsException.
6. moveIngredientThrowsWhenListEmpty - Проверяет, что метод moveIngredient в классе Burger корректно выбрасывает исключение при попытке переместить несуществующий ингредиент из пустого списка.
7. moveIngredientMovesToNewPosition - Проверяет, что ингредиент действительно перемещается с позиции 0 на позицию 1 в списке.

### Параметризированные тесты BurgerPriceTest для getPrice
Проверяет комбинацию булочки и ингредиентов:
1. Корректность расчёта цены бургера без ингредиентов.
2. Учёт цены одного ингредиента в общей стоимости.
3. Корректность расчёта с дорогой булочкой и начинкой.
4. Суммирование цен нескольких ингредиентов.

### Параметризированные тесты BurgerReceiptTest для getReceipt  
Проверяет формат чека для разных комбинаций булочек и ингредиентов:
1. Бургер только с булочкой (black bun, без ингредиентов).
2. Бургер с булочкой и одним соусом (white bun + hot sauce).
3. Бургер с булочкой и несколькими ингредиентами (red bun + sour cream + cutlet).

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