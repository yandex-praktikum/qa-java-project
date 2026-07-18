package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    public void getPriceShouldReturnIngredientPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ketchup", 35f);
        float actual = ingredient.getPrice();
        assertEquals(
                35f,
                actual,
                "Цена ингредиента не соответствует ожидаемой");
    }

    @Test
    public void getNameShouldReturnIngredientName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "meat", 35f);
        String actual = ingredient.getName();
        assertEquals(
                "meat",
                actual,
                "Имя ингредиента не соответствует ожидаемому");
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    public void getTypeShouldReturnIngredientType(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "ingredient", 35f);
        IngredientType actual = ingredient.getType();
        assertEquals(
                type,
                actual,
                "Тип ингредиента не соответствует ожидаемому");

    }

}
