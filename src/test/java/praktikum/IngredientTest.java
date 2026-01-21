package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;

    public IngredientTest(IngredientType ingredientType, String ingredientName, float ingredientPrice) {
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {IngredientType.SAUCE, "hot sauce", 100.0f},
                {IngredientType.FILLING, "cutlet", 200.0f},
                {IngredientType.SAUCE, "chili sauce", 150.0f},
                {IngredientType.FILLING, "dinosaur", 250.0f}
        });
    }

    @Test
    public void ingredientConstructorSetsTypeCorrectly() {
        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);

        assertEquals(ingredientType, ingredient.getType());
    }

    @Test
    public void ingredientConstructorSetsNameCorrectly() {
        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);

        assertEquals(ingredientName, ingredient.getName());
    }

    @Test
    public void ingredientConstructorSetsPriceCorrectly() {
        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);

        assertEquals(ingredientPrice, ingredient.getPrice(), 0.001);
    }

    @Test
    public void ingredientGetTypeReturnsCorrectType() {
        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);

        IngredientType type = ingredient.getType();

        assertEquals(ingredientType, type);
    }

    @Test
    public void ingredientGetNameReturnsCorrectName() {
        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);

        String name = ingredient.getName();

        assertEquals(ingredientName, name);
    }

    @Test
    public void ingredientGetPriceReturnsCorrectPrice() {
        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);

        float price = ingredient.getPrice();

        assertEquals(ingredientPrice, price, 0.001);
    }
}