package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {

    private final Bun bunMock;
    private final Ingredient[] ingredientMock;
    private final String expectedReceipt;

    public BurgerReceiptTest(Bun bunMock, Ingredient[] ingredientMock, String expectedReceipt) {
        this.bunMock = bunMock;
        this.ingredientMock = ingredientMock;
        this.expectedReceipt = expectedReceipt;

    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2}")
    public static Object[][] getTestData() {
        Bun bunMock1 = mock(Bun.class);
        Mockito.when(bunMock1.getPrice()).thenReturn(100f);
        Mockito.when(bunMock1.getName()).thenReturn("black bun");

        Ingredient ingredientMock1 =mock(Ingredient.class);
        Mockito.when(ingredientMock1.getPrice()).thenReturn(100f);
        Mockito.when(ingredientMock1.getName()).thenReturn("cutlet");
        Mockito.when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);

        Ingredient ingredientMock2 =mock(Ingredient.class);
        Mockito.when(ingredientMock2.getPrice()).thenReturn(300f);
        Mockito.when(ingredientMock2.getName()).thenReturn("chili sauce");
        Mockito.when(ingredientMock2.getType()).thenReturn(IngredientType.SAUCE);

        return new Object[][]{
                {bunMock1, new Ingredient[]{},
                        "(==== black bun ====)\n" +
                                "(==== black bun ====)\n"+
                                "\nPrice: 200,000000\n"
                },
                {bunMock1, new Ingredient[]{ingredientMock1},
                        "(==== black bun ====)\n" +
                                "= filling cutlet =\n" +
                                "(==== black bun ====)\n" +
                                "\nPrice: 300,000000\n"
                },
                {bunMock1, new Ingredient[]{ingredientMock1, ingredientMock2},
                        "(==== black bun ====)\n" +
                                "= filling cutlet =\n" +
                                "= sauce chili sauce =\n" +
                                "(==== black bun ====)\n" +
                                "\nPrice: 600,000000\n"
                }

        };

    }

    @Test
    public void getReceiptTest() {
        Burger burger = new Burger();
        burger.setBuns(bunMock);

        for (Ingredient ingredient:ingredientMock) {
            burger.addIngredient(ingredient);
        }

        String actualReceipt = burger.getReceipt();

        Assert.assertEquals("Чеки не совпадают",expectedReceipt, actualReceipt);



    }

}






