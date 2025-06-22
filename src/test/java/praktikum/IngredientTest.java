package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;

@RunWith(Parameterized.class)
public class IngredientTest {

    private Ingredient ingredientObject;
    private IngredientType type;
    private String name;
    private float price;

    public IngredientTest(IngredientType type, String name, float price){
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] data(){
        return new Object[][]{
                {IngredientType.SAUCE,"Самец", 12f},
                {IngredientType.FILLING,"Самка", 1.001f}
        };
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        ingredientObject = new Ingredient(type, name, price);
    }

    @Test
    public void getPriceShouldReturnPriceTest() {
        Assert.assertEquals(price, ingredientObject.getPrice(), 0.0001f);
    }

    @Test
    public void getNameShouldReturnNameTest() {
        Assert.assertEquals(name, ingredientObject.getName());
    }

    @Test
    public void getTypeShouldReturnTypeTest() {
        Assert.assertEquals(type, ingredientObject.getType());
    }
}