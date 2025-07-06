package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTest {

    @Test
    public void constructorShouldSetTypeNameAndPrice() {
        IngredientType expectedType = IngredientType.SAUCE;
        String expectedName = "Ketchup";
        float expectedPrice = 5.0f;
        
        Ingredient ingredient = new Ingredient(expectedType, expectedName, expectedPrice);
        
        assertEquals(expectedType, ingredient.getType());
        assertEquals(expectedName, ingredient.getName());
        assertEquals(expectedPrice, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void getTypeShouldReturnCorrectType() {
        IngredientType expectedType = IngredientType.FILLING;
        Ingredient ingredient = new Ingredient(expectedType, "Cheese", 10.0f);
        
        assertEquals(expectedType, ingredient.getType());
    }

    @Test
    public void getNameShouldReturnCorrectName() {
        String expectedName = "Mustard";
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, expectedName, 3.5f);
        
        assertEquals(expectedName, ingredient.getName());
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        float expectedPrice = 12.75f;
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Chicken", expectedPrice);
        
        assertEquals(expectedPrice, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void constructorShouldHandleEmptyName() {
        String emptyName = "";
        IngredientType type = IngredientType.SAUCE;
        float price = 2.0f;
        
        Ingredient ingredient = new Ingredient(type, emptyName, price);
        
        assertEquals(type, ingredient.getType());
        assertEquals(emptyName, ingredient.getName());
        assertEquals(price, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void constructorShouldHandleZeroPrice() {
        IngredientType type = IngredientType.FILLING;
        String name = "Free Lettuce";
        float zeroPrice = 0.0f;
        
        Ingredient ingredient = new Ingredient(type, name, zeroPrice);
        
        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(zeroPrice, ingredient.getPrice(), 0.001f);
    }

}