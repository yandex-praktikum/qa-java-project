import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParametrizedTest {
    private Burger burger;
    private Ingredient sauce;
    private Ingredient filling;
    private final String name;
    private final float price;

    public BurgerParametrizedTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Before
    public void createNewInstance() {
        sauce = new Ingredient(IngredientType.SAUCE, "chili", 20.0f);
        filling = new Ingredient(IngredientType.FILLING, "cheese", 15.5f);
        burger = new Burger();
    }

    @Parameterized.Parameters(name = "{index} : price = {1}")
    public static Object[][] getBunData() {
        return new Object[][] {
                {"original-free", 0.0f},
                {"original", 200.0f}
        };
    }

    @Test
    public void getPrice() {
        Bun bun = new Bun(name, price);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        float expected = bun.price * 2 + sauce.price + filling.price;
        float actual = burger.getPrice();

        assertEquals("Неверные значения цены бургера", expected, actual, 0);
    }
}