package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    private final IngredientType expectedType = IngredientType.FILLING;
    private final String expectedName = "Котлетка";
    private final float expectedPrice = 250;
    private final Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);

    @Test
    @DisplayName("Проверка геттера цены ингредиента")
    public void getPriceSuccess() {
        float actualPrice = ingredient.getPrice();
        assertEquals(expectedPrice, actualPrice, "getPrice() возвращает некорректную цену");
    }

    @Test
    @DisplayName("Проверка геттера названия ингредиента")
    public void getNameSuccess() {
        String actualName = ingredient.getName();
        assertEquals(expectedName, actualName, "getName() возвращает некорректное название");
    }

    @Test
    @DisplayName("Проверка геттера типа ингредиента")
    public void getTypeSuccess() {
        IngredientType actualType = ingredient.getType();
        assertEquals(expectedType, actualType, "getType() возвращает некорректный тип");
    }

}
