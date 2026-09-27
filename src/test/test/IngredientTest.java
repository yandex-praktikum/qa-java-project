package test;

import org.junit.jupiter.api.*;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты класса Ingredient")
class IngredientTest {

    private static final String NAME = "sauce";
    private static final float PRICE = 50f;

    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        ingredient = new Ingredient(IngredientType.SAUCE, NAME, PRICE);
    }

    @Test
    @DisplayName("Конструктор сохраняет type")
    void constructorShouldSetType() {
        assertEquals(IngredientType.SAUCE, ingredient.type);
    }

    @Test
    @DisplayName("Конструктор сохраняет name")
    void constructorShouldSetName() {
        assertEquals(NAME, ingredient.name);
    }

    @Test
    @DisplayName("Конструктор сохраняет price")
    void constructorShouldSetPrice() {
        assertEquals(PRICE, ingredient.price, 0.0001f);
    }

    @Test
    @DisplayName("Конструктор с FILLING сохраняет тип")
    void constructorShouldAcceptFilling() {
        Ingredient filling = new Ingredient(IngredientType.FILLING, "kotleta", 80f);

        assertEquals(IngredientType.FILLING, filling.type);
        assertEquals("kotleta", filling.name);
        assertEquals(80f, filling.price, 0.0001f);
    }

    @Test
    @DisplayName("Конструктор принимает null в качестве name")
    void constructorShouldAcceptNullName() {
        Ingredient nullName = new Ingredient(IngredientType.SAUCE, null, 10f);

        assertNull(nullName.name);
        assertEquals(IngredientType.SAUCE, nullName.type);
    }

    @Test
    @DisplayName("Конструктор принимает null в качестве type")
    void constructorShouldAcceptNullType() {
        Ingredient nullType = new Ingredient(null, "something", 10f);

        assertNull(nullType.type);
    }

    @Test
    @DisplayName("Конструктор принимает нулевую цену")
    void constructorShouldAcceptZeroPrice() {
        Ingredient zero = new Ingredient(IngredientType.SAUCE, "free", 0f);

        assertEquals(0f, zero.price, 0.0001f);
    }

    @Test
    @DisplayName("Конструктор принимает отрицательную цену")
    void constructorShouldAcceptNegativePrice() {
        Ingredient negative = new Ingredient(IngredientType.SAUCE, "weird", -5f);

        assertEquals(-5f, negative.price, 0.0001f);
    }
    @Test
    @DisplayName("getName() возвращает имя, переданное в конструктор")
    void getNameShouldReturnName() {
        assertEquals(NAME, ingredient.getName());
    }

    @Test
    @DisplayName("getName() возвращает null, если имя null")
    void getNameShouldReturnNull() {
        Ingredient nullName = new Ingredient(IngredientType.SAUCE, null, 10f);

        assertNull(nullName.getName());
    }

    @Test
    @DisplayName("getName() возвращает пустую строку, если имя пустое")
    void getNameShouldReturnEmptyString() {
        Ingredient empty = new Ingredient(IngredientType.SAUCE, "", 10f);

        assertEquals("", empty.getName());
    }
    @Test
    @DisplayName("getPrice() возвращает цену, переданную в конструктор")
    void getPriceShouldReturnPrice() {
        assertEquals(PRICE, ingredient.getPrice(), 0.0001f);
    }

    @Test
    @DisplayName("getPrice() возвращает 0 для нулевой цены")
    void getPriceShouldReturnZero() {
        Ingredient zero = new Ingredient(IngredientType.SAUCE, "free", 0f);

        assertEquals(0f, zero.getPrice(), 0.0001f);
    }

    @Test
    @DisplayName("getPrice() возвращает отрицательное значение, если так задано")
    void getPriceShouldReturnNegative() {
        Ingredient negative = new Ingredient(IngredientType.SAUCE, "weird", -5f);

        assertEquals(-5f, negative.getPrice(), 0.0001f);
    }@Test
    @DisplayName("Поле name можно изменить напрямую")
    void nameFieldIsMutable() {
        ingredient.name = "new name";

        assertEquals("new name", ingredient.getName());
    }

    @Test
    @DisplayName("Поле price можно изменить напрямую")
    void priceFieldIsMutable() {
        ingredient.price = 999f;

        assertEquals(999f, ingredient.getPrice(), 0.0001f);
    }

    @Test
    @DisplayName("Поле type можно изменить напрямую")
    void typeFieldIsMutable() {
        ingredient.type = IngredientType.FILLING;

        assertEquals(IngredientType.FILLING, ingredient.getType());
    }
}
