package tests;

import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

public class IngredientTest {

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Ingredient ingredient =
                new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);

        float price = ingredient.getPrice();

        assertEquals(50f, price, 0.001f);
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        Ingredient ingredient =
                new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);

        String name = ingredient.getName();

        assertEquals("hot sauce", name);
    }

    @Test
    public void getTypeShouldReturnCorrectType() {
        Ingredient ingredient =
                new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);

        IngredientType type = ingredient.getType();

        assertEquals(IngredientType.SAUCE, type);
    }
}