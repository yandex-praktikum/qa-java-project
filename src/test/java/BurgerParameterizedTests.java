import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static data.DataModel.*;

@RunWith(Parameterized.class)
public class BurgerParameterizedTests {
    private Burger burger;
    private final Bun bun;
    private final Ingredient firstIngredient;
    private final Ingredient secondIngredient;
    private final float expectedTotalPrice;

    public BurgerParameterizedTests(Bun bun, Ingredient firstIngredient, Ingredient secondIngredient, float expectedTotalPrice) {
        this.bun = bun;
        this.firstIngredient = firstIngredient;
        this.secondIngredient = secondIngredient;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Parameterized.Parameters(name = "Общая стоимость: {3}")
    public static Collection<Object[]> getTestData() {
        return Arrays.asList(new Object[][]{
                {
                        new Bun("black bun", PRICE_OF_BUN_1),
                        new Ingredient(IngredientType.SAUCE, "hot sauce", PRICE_OF_SAUCE_1),
                        new Ingredient(IngredientType.FILLING, "cutlet", PRICE_OF_FILLY_1),
                        300
                },
                {
                        new Bun("white bun", PRICE_OF_BUN_2),
                        new Ingredient(IngredientType.SAUCE, "sour cream", PRICE_OF_SAUCE_2),
                        new Ingredient(IngredientType.FILLING, "dinosaur", PRICE_OF_FILLY_2),
                        600
                },
                {
                        new Bun("red bun", PRICE_OF_BUN_3),
                        new Ingredient(IngredientType.SAUCE, "chili sauce", PRICE_OF_SAUCE_3),
                        new Ingredient(IngredientType.FILLING, "sausage", PRICE_OF_FILLY_3),
                        900
                }
        });
    }

    @Test
    public void checkGetPriceCorrectTotalPrice() {
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        float actualPrice = burger.getPrice();
        Assert.assertEquals("Рассчитанная сумма заказа: " + bun.getPrice() + "+" + firstIngredient.getPrice() + "+" + secondIngredient.getPrice(),
                expectedTotalPrice, actualPrice, DELTA);
    }

    @Test
    public void checkGetReceiptCorrectFormat() {
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        String expectedReceipt = String.format("(==== %s ====)%n", bun.getName())
                + String.format("= %s %s =%n", firstIngredient.getType().toString().toLowerCase(), firstIngredient.getName())
                + String.format("= %s %s =%n", secondIngredient.getType().toString().toLowerCase(), secondIngredient.getName())
                + String.format("(==== %s ====)%n", bun.getName())
                + String.format("%nPrice: %f%n", expectedTotalPrice);
        String actualReceipt = burger.getReceipt();

        Assert.assertEquals("\nОжидаемый результат: " + expectedReceipt + "\n" + "Фактический результат: " + actualReceipt,
                expectedReceipt, actualReceipt);
    }
}
