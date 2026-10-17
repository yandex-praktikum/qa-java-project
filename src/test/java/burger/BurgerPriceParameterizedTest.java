package praktikum.tests.burger;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    private final String scenario;
    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final float expectedPrice;

    public BurgerPriceParameterizedTest(String scenario, Bun bun, List<Ingredient> ingredients, float expectedPrice) {
        this.scenario = scenario;
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[] {
                        "без ингредиентов — булка x2",
                        new Bun("Classic", 50.0f),
                        List.of(),
                        50.0f * 2
                },
                new Object[] {
                        "один ингредиент — булка x2 + начинка",
                        new Bun("Premium", 60.0f),
                        List.of(new Ingredient(IngredientType.FILLING, "Cheese", 30.0f)),
                        60.0f * 2 + 30.0f
                },
                new Object[] {
                        "несколько ингредиентов — начинка и соус складываются",
                        new Bun("Deluxe", 70.0f),
                        Arrays.asList(
                                new Ingredient(IngredientType.FILLING, "Beef", 90.0f),
                                new Ingredient(IngredientType.SAUCE, "Garlic", 20.0f)
                        ),
                        70.0f * 2 + 90.0f + 20.0f
                },
                new Object[] {
                        "дробные цены — без потери копеек",
                        new Bun("Small", 25.5f),
                        Arrays.asList(
                                new Ingredient(IngredientType.FILLING, "Lettuce", 10.5f),
                                new Ingredient(IngredientType.FILLING, "Tomato", 8.5f)
                        ),
                        25.5f * 2 + 10.5f + 8.5f
                },
                new Object[] {
                        "один и тот же ингредиент добавлен дважды — считается дважды",
                        new Bun("Repeat", 40.0f),
                        Arrays.asList(
                                new Ingredient(IngredientType.SAUCE, "Ketchup", 15.0f),
                                new Ingredient(IngredientType.SAUCE, "Ketchup", 15.0f) // тот же состав, новый объект
                        ),
                        40.0f * 2 + 15.0f + 15.0f
                }
        );
    }

    @Test
    public void getPriceShouldCalculateBunTwicePlusIngredientsTest() {

        Burger burger = new Burger();
        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);

        Assert.assertEquals(scenario + ": цена должна быть булка x2 + сумма ингредиентов",
                expectedPrice, burger.getPrice(), 0.001f);
    }
}
