package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static praktikum.IngredientType.*;

class IngredientTest {
    private Ingredient ingredient;
    private IngredientType testType = FILLING;
    private String testName = "black bun";
    private float testPrice = 100.0f;


    @BeforeEach
    public void setUp() {
        ingredient = new Ingredient(testType, testName, testPrice);
    }

    @Test
    @DisplayName("Проверка метода getPrice() возвращает правильную цену")
    public void testGetPriceReturnsCorrectPrice() {
        float actualPrice = ingredient.getPrice();
        assertEquals(testPrice, actualPrice, "Цена ингредиента не совпадает");
    }

    @Test
    @DisplayName("Проверка метода getName() возвращает правильное название")
    public void testGetNameReturnsCorrectName() {
        String actualName = ingredient.getName();
        assertEquals(testName, actualName, "Название ингредиента не совпадает");
    }

    @Test
    @DisplayName("Проверка метода getType() возвращает правильный тип ингредиента")
    public void testGetTypeReturnsCorrectType() {
        IngredientType actualType = ingredient.getType();
        assertEquals(testType, actualType,"Тип ингредиента не совпадает");
    }
}