package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientParameterizedTest {
    
    private IngredientType type;
    private String name;
    private float price;

    public IngredientParameterizedTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "Ketchup", 5.0f},
                {IngredientType.SAUCE, "Mustard", 3.5f},
                {IngredientType.SAUCE, "Mayo", 4.0f},
                {IngredientType.FILLING, "Cheese", 10.0f},
                {IngredientType.FILLING, "Chicken", 12.75f},
                {IngredientType.FILLING, "Lettuce", 2.5f},
                {IngredientType.FILLING, "", 0.0f}
        });
    }

    @Test
    public void ingredientShouldBeCreatedWithCorrectParameters() {
        Ingredient ingredient = new Ingredient(type, name, price);
        
        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice(), 0.001f);
    }
}