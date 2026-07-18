package praktikum;

import org.junit.jupiter.api.Test;

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

    @Test
    public void getTypeShouldReturnIngredientType() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "meat", 35f);
        IngredientType actual = ingredient.getType();
        assertEquals(
                IngredientType.FILLING,
                actual,
                "Тип ингредиента не соответствует ожидаемому");
    }

}
