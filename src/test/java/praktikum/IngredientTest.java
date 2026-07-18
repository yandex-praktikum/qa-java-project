package praktikum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    public void  getPriceShouldReturnIngredientPrice(){
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "meat", 35);
        float actual = ingredient.getPrice();
        assertEquals(
                35,
                actual,
                "Цена ингредиента не соответствует ожидаемому");
    }
}
