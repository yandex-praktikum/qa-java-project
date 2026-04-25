package Diplom_1;


import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;

public class IngredientTest {

    private IngredientType typeIngredient = FILLING;
    private String nameIngredient = "hot sauce";
    private float priceIngredient = 100f;

    @Test
    public void getPriceIngredient() {
        Ingredient ingredient = new Ingredient(typeIngredient, nameIngredient, priceIngredient);
        assertEquals(priceIngredient, ingredient.getPrice(), 0.00001);
    }

    @Test
    public void getNameIngredient() {
        Ingredient ingredient = new Ingredient(typeIngredient, nameIngredient, priceIngredient);
        assertEquals(nameIngredient, ingredient.getName());
    }

    @Test
    public void getTypeIngredient() {
        Ingredient ingredient = new Ingredient(typeIngredient, nameIngredient, priceIngredient);
        assertEquals(typeIngredient, ingredient.getType());
    }
}