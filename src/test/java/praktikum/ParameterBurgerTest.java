package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class ParameterBurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient;
    private float expectedPrice;
    private String expectedReceipt;

    public ParameterBurgerTest(String bunName, float bunPrice, IngredientType type, String nameIngredient, float priceIngredient, float expectedPrice, String expectedReceipt) {
        this.bun = new Bun(bunName, bunPrice);
        this.ingredient = new Ingredient(type, nameIngredient, priceIngredient);
        this.expectedPrice = expectedPrice;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2} {3} {4} {5}")
    public static Object[][] getTest() {
        return new Object[][]{
                {"black bun", 100, SAUCE, "hot sauce", 100, 300,
                        "(==== black bun ====)%n= sauce hot sauce =%n(==== black bun ====)%n%nPrice: 300,000000%n"},
                {"white bun", 200.25f, FILLING, "sausage", 300, 700.50f,
                        "(==== white bun ====)%n= filling sausage =%n(==== white bun ====)%n%nPrice: 700,500000%n"},
                {"red bun", 300.25f, FILLING, "dinosaur", 200.25f, 800.75f,
                        "(==== red bun ====)%n= filling dinosaur =%n(==== red bun ====)%n%nPrice: 800,750000%n"},
        };
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        float actualPrice = burger.getPrice();
        Assert.assertEquals("Неверно посчитана стоимость",expectedPrice, actualPrice, 0.01f);
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String actualReceipt = burger.getReceipt();

        Assert.assertEquals("Неверный рецепт",String.format(expectedReceipt), actualReceipt);
    }
}
