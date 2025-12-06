package ru.yandex.practicum.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerGetReceiptParameterizedTest {

    private Burger burger;
    private final String caseName;
    private final String expectedReceipt;
    private final List<Ingredient> ingredients;
    private final String bunName;
    private final float bunPrice;

    public BurgerGetReceiptParameterizedTest(String caseName,
                                             String expectedReceipt,
                                             List<Ingredient> ingredients,
                                             String bunName,
                                             float bunPrice) {
        this.caseName = caseName;
        this.expectedReceipt = expectedReceipt;
        this.ingredients = ingredients;
        this.bunName = bunName;
        this.bunPrice = bunPrice;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        // CASE 1: только булки (без ингредиентов)
        String bun1 = "PremiumBun";
        float bun1Price = 20.0f;
        float total1 = bun1Price * 2;
        String expected1 = String.format("(==== %s ====)%n" +
                        "(==== %s ====)%n" +
                        "%n" +
                        "Price: %f%n",
                bun1, bun1, total1);

        // CASE 2: булки + один соус
        String bun2 = "StreetBun";
        float bun2Price = 25.0f;
        Ingredient ing2 = createIngredient(IngredientType.SAUCE, "hotSauce", 5.0f);
        float total2 = bun2Price * 2 + 5.0f;
        String expected2 = String.format("(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%n" +
                        "Price: %f%n",
                bun2,
                IngredientType.SAUCE.toString().toLowerCase(), "hotSauce",
                bun2,
                total2);

        // CASE 3: булки + два ингредиента (начинка + соус)
        String bun3 = "ClassicBun";
        float bun3Price = 15.5f;
        Ingredient ing3a = createIngredient(IngredientType.FILLING, "megaPatty", 30.0f);
        Ingredient ing3b = createIngredient(IngredientType.SAUCE, "sweetDip", 2.5f);
        float total3 = bun3Price * 2 + 30.0f + 2.5f;
        String expected3 = String.format("(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%n" +
                        "Price: %f%n",
                bun3,
                IngredientType.FILLING.toString().toLowerCase(), "megaPatty",
                IngredientType.SAUCE.toString().toLowerCase(), "sweetDip",
                bun3,
                total3);

        return Arrays.asList(new Object[][]{
                {"Только булки (нет ингредиентов)", expected1, List.of(), bun1, bun1Price},
                {"Булка + один соус", expected2, List.of(ing2), bun2, bun2Price},
                {"Булка + начинка + соус", expected3, List.of(ing3a, ing3b), bun3, bun3Price}
        });
    }

    private static Ingredient createIngredient(IngredientType type, String name, float price) {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Mockito.when(ingredient.getType()).thenReturn(type);
        Mockito.when(ingredient.getName()).thenReturn(name);
        Mockito.when(ingredient.getPrice()).thenReturn(price);
        return ingredient;
    }

    @Before
    public void setUp() {
        Bun bunMock = Mockito.mock(Bun.class);
        Mockito.when(bunMock.getName()).thenReturn(bunName);
        Mockito.when(bunMock.getPrice()).thenReturn(bunPrice);

        burger = new Burger();
        burger.setBuns(bunMock);

        for (Ingredient ing : ingredients) {
            burger.addIngredient(ing);
        }
    }

    @Test
    public void getReceipt_ShouldMatchExpected_ForCase() {
        String actual = burger.getReceipt();
        assertEquals("Case: " + caseName, expectedReceipt, actual);
    }
}