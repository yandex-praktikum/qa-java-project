package praktikum.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerGetReceiptTest {
    private final String bunName;
    private final float bunPrice;
    private final String ingredient1Name;
    private final String ingredient1Type;
    private final float ingredient1Price;
    private final String ingredient2Name;
    private final String ingredient2Type;
    private final float ingredient2Price;

    private Burger burger;

    public BurgerGetReceiptTest(String bunName, float bunPrice, String ingredient1Name, String ingredient1Type, float ingredient1Price, String ingredient2Name, String ingredient2Type, float ingredient2Price) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredient1Name = ingredient1Name;
        this.ingredient1Type = ingredient1Type;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Name = ingredient2Name;
        this.ingredient2Type = ingredient2Type;
        this.ingredient2Price = ingredient2Price;
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        Bun mockBun = Mockito.mock(Bun.class);
        when(mockBun.getName()).thenReturn(bunName);
        when(mockBun.getPrice()).thenReturn(bunPrice);

        burger = new Burger();
        burger.setBuns(mockBun);

        Ingredient mockIngredient1 = Mockito.mock(Ingredient.class);
        Ingredient mockIngredient2 = Mockito.mock(Ingredient.class);

        IngredientType mockIngredientType1 = Mockito.mock(IngredientType.class);
        IngredientType mockIngredientType2 = Mockito.mock(IngredientType.class);

        if (ingredient1Name != null) {
            when(mockIngredient1.getName()).thenReturn(ingredient1Name);
            when(mockIngredientType1.toString()).thenReturn(ingredient1Type);
            when(mockIngredient1.getType()).thenReturn(mockIngredientType1);
            when(mockIngredient1.getPrice()).thenReturn(ingredient1Price);
            burger.addIngredient(mockIngredient1);
        }

        if (ingredient2Name != null) {
            when(mockIngredient2.getName()).thenReturn(ingredient2Name);
            when(mockIngredientType2.toString()).thenReturn(ingredient2Type);
            when(mockIngredient2.getType()).thenReturn(mockIngredientType2);
            when(mockIngredient2.getPrice()).thenReturn(ingredient2Price);
            burger.addIngredient(mockIngredient2);
        }
    }

    // Тестовые данные
    @Parameterized.Parameters(name = "{index}: bun={0}, bunPrice={1}, ingredient1={2}({3}), ingredient1Price={4}, ingredient2={5}({6}), ingredient2Price={7}")
    public static Object[][] data() {
        return new Object[][]{
                {"brioche bun", 2.5f, "cutlet", "filling", 2.0f, null, null, 0f},
                {"sesame bun", 1.5f, "ketchup", "sauce", 1.0f, "cutlet", "filling", 3.0f,},
                {"plain bun", 1.0f, null, null, 0f, null, null, 0f}
        };
    }

    // Тест для метода getReceipt()
    @Test
    public void getReceiptTest() {
        StringBuilder expectedReceipt = new StringBuilder(String.format("(==== %s ====)%n", bunName));
        if (ingredient1Name != null) {
            expectedReceipt.append(String.format("= %s %s =%n", ingredient1Type.toLowerCase(), ingredient1Name));
        }
        if (ingredient2Name != null) {
            expectedReceipt.append(String.format("= %s %s =%n", ingredient2Type.toLowerCase(), ingredient2Name));
        }

        expectedReceipt.append(String.format("(==== %s ====)%n", bunName));
        expectedReceipt.append(String.format("%nPrice: %f%n", bunPrice * 2 + ingredient1Price + ingredient2Price));

        String actualReceipt = burger.getReceipt();
        assertEquals(expectedReceipt.toString(), actualReceipt);
    }
}
