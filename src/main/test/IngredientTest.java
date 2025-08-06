import org.junit.jupiter.api.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    public void testIngredientWithSAUCE(){
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Самый вкусный бургер", 100.00f);
        assertEquals(IngredientType.SAUCE, ingredient.getType());
        assertEquals("Самый вкусный бургер", ingredient.getName());
        assertEquals(100.00f, ingredient.getPrice());
    }

    @Test
    public void testIngredientWithFILLING(){
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Нет, вот это самый вкусный бургер", 200.00f);
        assertEquals(IngredientType.FILLING, ingredient.getType());
        assertEquals("Нет, вот это самый вкусный бургер", ingredient.getName());
        assertEquals(200.00f, ingredient.getPrice());
    }

}
