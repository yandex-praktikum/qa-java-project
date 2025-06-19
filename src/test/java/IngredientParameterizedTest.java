import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

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

    @Parameterized.Parameters(name = "{index} : type = {0}")
    public static Object[][] data() {
        return new Object[][] {
                {IngredientType.FILLING, "Cheese", 40.0f},
                {IngredientType.SAUCE, "Cesar", 50.0f}
        };
    }

    @Test
    public void getTypeParameterizedTest(){
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Ожидаемый и фактический тип ингредиента не совпадают",type, ingredient.getType());
    }

}
