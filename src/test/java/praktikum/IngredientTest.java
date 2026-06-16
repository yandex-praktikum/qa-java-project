package praktikum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTest {

    private IngredientType type;
    private String name;
    private float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {SAUCE, "Кетчуп", 10.0f},
                {FILLING, "Котлета", 20.0f}
        });
    }

    @Test
    public void getTypeReturn() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("ОР - вернул тип enum", type, ingredient.getType());
    }

    @Test
    public void getNameReturn() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("ОР - вернул имя", name, ingredient.getName());
    }

    @Test
    public void getPriceReturn() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals("ОР - вернул цену", price, ingredient.getPrice(), 0.01);
    }
}
