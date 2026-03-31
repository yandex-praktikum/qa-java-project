package praktikum;

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

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient;

    // Параметры для тестов с понятными названиями
    private final String bunName;
    private final float bunPrice;
    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;

    public BurgerParameterizedTest(String bunName, float bunPrice,
                                   IngredientType ingredientType,
                                   String ingredientName,
                                   float ingredientPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters(name = "Булочка: {0}, Ингредиент: {3}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"black bun", 100.0f, IngredientType.SAUCE, "hot sauce", 50.0f},
                {"white bun", 200.0f, IngredientType.FILLING, "cutlet", 150.0f},
                {"red bun", 300.0f, IngredientType.SAUCE, "sour cream", 75.0f},
                {"special bun", 250.0f, IngredientType.FILLING, "dinosaur", 200.0f}
        });
    }

    @Before
    public void setUp() {
        burger = new Burger();

        bun = MockFactory.createBunMock(bunName, bunPrice);
        ingredient = MockFactory.createIngredientMock(ingredientType, ingredientName, ingredientPrice);
    }

    @Test
    public void shouldCalculatePriceWithBunAndIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        float actualPrice = burger.getPrice();
        float expectedPrice = (bunPrice * 2) + ingredientPrice;

        assertEquals("Цена должна быть правильной", expectedPrice, actualPrice, 0.001f);
    }

    @Test
    public void shouldGenerateReceiptWithBunAndIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();
        String expectedIngredientLine = String.format("= %s %s =%n",
                ingredientType.toString().toLowerCase(), ingredientName);
        String expectedReceipt = String.format("(==== %s ====)%n%s(==== %s ====)%n%nPrice: %f%n",
                bunName, expectedIngredientLine, bunName, (bunPrice * 2) + ingredientPrice);

        assertEquals("Чек должен быть правильным", expectedReceipt, receipt);
    }
}
