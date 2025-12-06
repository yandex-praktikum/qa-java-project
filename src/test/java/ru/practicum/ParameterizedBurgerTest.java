package ru.practicum;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParameterizedBurgerTest {
    // Параметры теста
    private float bunPrice;           // Цена одной булочки
    private int numberOfIngredients;  // Количество ингредиентов
    private float ingredientPrice;    // Цена одного ингредиента
    private float expectedPrice;      // Ожидаемая общая цена бургера

    private Burger burger;  // Тестируемый объект

    // Конструктор для параметризированного теста
        public ParameterizedBurgerTest(float bunPrice, int numberOfIngredients,
                                   float ingredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.numberOfIngredients = numberOfIngredients;
        this.ingredientPrice = ingredientPrice;
        this.expectedPrice = expectedPrice;

    }

    // Тестовые данные
    @Parameters(name = "Булочка: {0}, Ингредиенты: {1}х{2}, Ожидаемая цена: {3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {50.0f, 0, 10.0f, 100.0f},    // Только булочки: 2*50 = 100
                {50.0f, 1, 10.0f, 110.0f},    // +1 ингредиент: 100 + 10 = 110
                {50.0f, 2, 10.0f, 120.0f},    // +2 ингредиента: 100 + 20 = 120
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();  // Создаем новый бургер для каждого теста
    }

    @Test
    public void testGetPriceWithDifferentParameters() {
        // Создаем стаб булочки с заданной ценой
        Bun bunStub = createBunStub("булочка", bunPrice);

        // Устанавливаем булочку в бургер
        burger.setBuns(bunStub);

        // Добавляем указанное количество ингредиентов-стабов
        for (int i = 0; i < numberOfIngredients; i++) {
            // Создаем стаб ингредиента с заданной ценой
            Ingredient ingredientStub = createIngredientStub(
                    "Ингредиент_" + (i + 1),  // Уникальное имя для каждого ингредиента
                    IngredientType.FILLING,    // Тип ингредиента. РАССМОТРЕТЬ ВОЗМОЖНОСТЬ ПАРАМЕТРИЗАЦИИ
                    ingredientPrice            // Цена ингредиента из параметров теста
            );
            // Добавляем ингредиент в бургер
            burger.addIngredient(ingredientStub);
        }

        // Вызываем тестируемый метод
        float actualPrice = burger.getPrice();

        // Сравниваем фактический результат с ожидаемым
        assertEquals(
                String.format("Ошибка при bunPrice=%.1f, ingredients=%dx%.1f",
                        bunPrice, numberOfIngredients, ingredientPrice),
                expectedPrice,
                actualPrice,
                0.001f  // Погрешность для сравнения float
        );
    }

    // Вспомогательный метод для создания стаба булочки

    private Bun createBunStub(String name, float price) {
        return new Bun(name, price) {
        };
    }

    // Вспомогательный метод для создания стаба ингредиента
    private Ingredient createIngredientStub(String name, IngredientType type, float price) {
        return new Ingredient(type, name, price) {
            @Override
            public String getName() {
                return name;  // Всегда возвращаем заданное имя
            }

            @Override
            public float getPrice() {
                return price;  // Всегда возвращаем заданную цену
            }

            @Override
            public IngredientType getType() {
                return type;  // Всегда возвращаем заданный тип
            }
        };
    }
}
