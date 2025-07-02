import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import praktikum.Ingredient;

import static praktikum.IngredientType.SAUCE;

public class IngredientTest {

    Ingredient ingredient = new Ingredient(SAUCE, "Сметанка", 150);

    @Test
    @DisplayName("Проверка типа")
    public void getTypeTest() {
        Assertions.assertEquals(SAUCE, ingredient.getType());

    }    @Test
    @DisplayName("Проверка названия")
    public void getNameTest() {
        Assertions.assertEquals("Сметанка", ingredient.getName());

    }    @Test
    @DisplayName("Проверка цены")
    public void getPriceTest() {
        Assertions.assertEquals(150, ingredient.getPrice());

    }
}
