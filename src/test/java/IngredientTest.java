import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[] data(){
        return new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100},
                {IngredientType.SAUCE, "sour cream", 50},
                {IngredientType.SAUCE, "chili sauce", 300},
                {IngredientType.FILLING, "cutlet", 100},
                {IngredientType.SAUCE,"",0},
                {IngredientType.SAUCE,"",-100},
        };
    }

    @Test
    public void getPriceTest(){
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("Упс. Что-то пошло не так.", type, ingredient.getType());
    }
    @Test
    public void getNameTest(){
        Ingredient ingredient = new Ingredient(type,name,price);
        String actualName = ingredient.getName();
        assertEquals("Упс. Что-то пошло не так.", name, actualName);
    }
    @Test
    public void getTypeTest(){
        Ingredient ingredient = new Ingredient(type, name,0.001F);
        assertEquals("Упс. Что-то пошло не так.", type, ingredient.getType());
    }
}
