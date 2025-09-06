package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptParameterizedTest {

    private final String expectedReceipt;
    private final Ingredient[] ingredients;
    private Bun bun;

    public BurgerReceiptParameterizedTest(Bun bun, Ingredient[] ingredients, String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0}, {1}, {2}")
    public static Collection<Object[]> data() {
        Bun bunBlack = Mockito.mock(Bun.class);
        when(bunBlack.getName()).thenReturn("black bun");
        when(bunBlack.getPrice()).thenReturn(100f);

        Bun bunWhite = Mockito.mock(Bun.class);
        when(bunWhite.getName()).thenReturn("white bun");
        when(bunWhite.getPrice()).thenReturn(200f);

        Bun bunRed = Mockito.mock(Bun.class);
        when(bunRed.getName()).thenReturn("red bun");
        when(bunRed.getPrice()).thenReturn(300f);

        Ingredient hotSauce = Mockito.mock(Ingredient.class);
        when(hotSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(hotSauce.getName()).thenReturn("hot sauce");
        when(hotSauce.getPrice()).thenReturn(100f);

        Ingredient sourCream = Mockito.mock(Ingredient.class);
        when(sourCream.getType()).thenReturn(IngredientType.SAUCE);
        when(sourCream.getName()).thenReturn("sour cream");
        when(sourCream.getPrice()).thenReturn(200f);

        Ingredient chiliSauce = Mockito.mock(Ingredient.class);
        when(chiliSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(chiliSauce.getName()).thenReturn("chili sauce");
        when(chiliSauce.getPrice()).thenReturn(300f);

        Ingredient cutlet = Mockito.mock(Ingredient.class);
        when(cutlet.getType()).thenReturn(IngredientType.FILLING);
        when(cutlet.getName()).thenReturn("cutlet");
        when(cutlet.getPrice()).thenReturn(100f);

        Ingredient dinosaur = Mockito.mock(Ingredient.class);
        when(dinosaur.getType()).thenReturn(IngredientType.FILLING);
        when(dinosaur.getName()).thenReturn("dinosaur");
        when(dinosaur.getPrice()).thenReturn(200f);

        Ingredient sausage = Mockito.mock(Ingredient.class);
        when(sausage.getType()).thenReturn(IngredientType.FILLING);
        when(sausage.getName()).thenReturn("sausage");
        when(sausage.getPrice()).thenReturn(300f);

        return Arrays.asList(new Object[][]{
                {
                        bunBlack,
                        new Ingredient[]{},
                        "(==== black bun ====)\r\n" +
                                "(==== black bun ====)\r\n\r\n" +
                                "Price: 200,000000\r\n"
                },

                {
                        bunWhite,
                        new Ingredient[]{sourCream},
                        "(==== white bun ====)\r\n" +
                                "= sauce sour cream =\r\n" +
                                "(==== white bun ====)\r\n\r\n" +
                                "Price: 600,000000\r\n"
                },
                {
                        bunRed,
                        new Ingredient[]{dinosaur, chiliSauce},
                        "(==== red bun ====)\r\n" +
                                "= filling dinosaur =\r\n" +
                                "= sauce chili sauce =\r\n" +
                                "(==== red bun ====)\r\n\r\n" +
                                "Price: 1100,000000\r\n"
                },
                {bunBlack,
                        new Ingredient[]{hotSauce, sourCream, chiliSauce, cutlet, dinosaur, sausage},
                        "(==== black bun ====)\r\n" +
                                "= sauce hot sauce =\r\n" +
                                "= sauce sour cream =\r\n" +
                                "= sauce chili sauce =\r\n" +
                                "= filling cutlet =\r\n" +
                                "= filling dinosaur =\r\n" +
                                "= filling sausage =\r\n" +
                                "(==== black bun ====)\r\n\r\n" +
                                "Price: 1400,000000\r\n"
                }
        });
    }

    @Test
    public void getReceiptParameterizedTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        System.out.println(expectedReceipt);
        System.out.println(burger.getReceipt());
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}