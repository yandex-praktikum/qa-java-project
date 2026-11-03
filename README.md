# Diplom_1 — юнит-тесты для Stellar Burgers

Задание 1 второй части дипломного проекта «Автоматизатор тестирования на Java» (Яндекс Практикум): юнит-тесты программы, которая помогает собрать и заказать бургер в Stellar Burgers (класс `Burger`).

## Стек

- Java 11, Maven
- JUnit 4.13.2
- Mockito 4.11.0
- JaCoCo 0.8.12

## Покрытые сценарии (17 тестов)

Класс `Burger` покрыт полностью — все 6 методов (`setBuns`, `addIngredient`, `removeIngredient`, `moveIngredient`, `getPrice`, `getReceipt`), строки и ветки, **покрытие 100%**.

| Класс тестов | Сценарии |
|---|---|
| `BurgerTest` | 8 тестов: цена с одной булкой, добавление / удаление / перестановка ингредиентов, граничный `removeIngredient` (`IndexOutOfBoundsException`), цена с ингредиентами, чек целиком — на моках и стабах `Bun`/`Ingredient` |
| `BurgerPriceParameterizedTest` | 5 наборов параметров цены (`@RunWith(Parameterized.class)`) |
| `BurgerReceiptParameterizedTest` | 4 набора: пустой бургер, начинка `FILLING`, соус `SAUCE`, оба типа — чек собирается и сверяется целиком |

Приёмы: моки и стабы (`@RunWith(MockitoJUnitRunner.class)`, `@Mock`, `when(...).thenReturn(...)`), параметризация, `@Test(expected = ...)`. Ожидания чеков собираются тем же `String.format` с `%n`/`%f`, что и в коде, — тесты не зависят от ОС и локали.

## Структура проекта

```
src/main/java/praktikum/   — код: Burger, Bun, Ingredient, IngredientType, Database, Praktikum
src/test/java/praktikum/   — тесты: BurgerTest, BurgerPriceParameterizedTest, BurgerReceiptParameterizedTest
```

## Запуск тестов

```bash
mvn clean test
```

## Отчёт о покрытии

После прогона отчёт лежит в `target/site/jacoco/index.html` (пакет `praktikum`): `Burger` — 100% строк и веток, `Bun`/`Ingredient`/`IngredientType` — 100%. Снимок отчёта закоммичен в репозиторий (для `target/site/jacoco/` сделано исключение из `.gitignore`). Классы `Database` и `Praktikum` тестами не покрываются — по заданию 100% требуется только для `Burger`.
