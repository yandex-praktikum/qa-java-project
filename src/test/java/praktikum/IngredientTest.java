package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void shouldReturnCorrectType() {
        Ingredient ingredient =
                new Ingredient(IngredientType.SAUCE,
                        "hot sauce",
                        100);

        assertEquals(IngredientType.SAUCE,
                ingredient.getType());
    }

    @Test
    public void shouldReturnCorrectName() {
        Ingredient ingredient =
                new Ingredient(IngredientType.SAUCE,
                        "hot sauce",
                        100);

        assertEquals("hot sauce",
                ingredient.getName());
    }

    @Test
    public void shouldReturnCorrectPrice() {
        Ingredient ingredient =
                new Ingredient(IngredientType.SAUCE,
                        "hot sauce",
                        100);

        assertEquals(100,
                ingredient.getPrice(),
                0);
    }
}