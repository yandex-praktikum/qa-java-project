package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerGetReceiptTest {

    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final String expectedReceipt;

    public BurgerGetReceiptTest(
            Bun bun,
            List<Ingredient> ingredients,
            String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters(name = "Проверка чека: {2}")
    public static Object[][] testData() {
        Bun blackBun = mock(Bun.class);
        when(blackBun.getName()).thenReturn("black bun");
        when(blackBun.getPrice()).thenReturn(100f);

        Ingredient sourCream = mock(Ingredient.class);
        when(sourCream.getType()).thenReturn(IngredientType.SAUCE);
        when(sourCream.getName()).thenReturn("sour cream");
        when(sourCream.getPrice()).thenReturn(200f);

        Ingredient cutlet = mock(Ingredient.class);
        when(cutlet.getType()).thenReturn(IngredientType.FILLING);
        when(cutlet.getName()).thenReturn("cutlet");
        when(cutlet.getPrice()).thenReturn(100f);

        Ingredient dinosaur = mock(Ingredient.class);
        when(dinosaur.getType()).thenReturn(IngredientType.FILLING);
        when(dinosaur.getName()).thenReturn("dinosaur");
        when(dinosaur.getPrice()).thenReturn(200f);

        Bun whiteBun = mock(Bun.class);
        when(whiteBun.getName()).thenReturn("white bun");
        when(whiteBun.getPrice()).thenReturn(1f);

        Ingredient whiteCutlet = mock(Ingredient.class);
        when(whiteCutlet.getType()).thenReturn(IngredientType.FILLING);
        when(whiteCutlet.getName()).thenReturn("cutlet");
        when(whiteCutlet.getPrice()).thenReturn(0.5f);

        Ingredient chiliSauce = mock(Ingredient.class);
        when(chiliSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(chiliSauce.getName()).thenReturn("chili sauce");
        when(chiliSauce.getPrice()).thenReturn(4f);

        Ingredient sausage = mock(Ingredient.class);
        when(sausage.getType()).thenReturn(IngredientType.FILLING);
        when(sausage.getName()).thenReturn("sausage");
        when(sausage.getPrice()).thenReturn(1f);

        Bun redBun = mock(Bun.class);
        when(redBun.getName()).thenReturn("red bun");
        when(redBun.getPrice()).thenReturn(1f);

        Ingredient hotSauce = mock(Ingredient.class);
        when(hotSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(hotSauce.getName()).thenReturn("hot sauce");
        when(hotSauce.getPrice()).thenReturn(5.5f);

        Ingredient redSausage = mock(Ingredient.class);
        when(redSausage.getType()).thenReturn(IngredientType.FILLING);
        when(redSausage.getName()).thenReturn("sausage");
        when(redSausage.getPrice()).thenReturn(14.5f);

        Ingredient redDinosaur = mock(Ingredient.class);
        when(redDinosaur.getType()).thenReturn(IngredientType.FILLING);
        when(redDinosaur.getName()).thenReturn("dinosaur");
        when(redDinosaur.getPrice()).thenReturn(200f);

        return new Object[][]{
                {
                        blackBun,
                        List.of(sourCream, cutlet, dinosaur),
                        String.format(
                                Locale.getDefault(),
                                "(==== black bun ====)%n" +
                                        "= sauce sour cream =%n" +
                                        "= filling cutlet =%n" +
                                        "= filling dinosaur =%n" +
                                        "(==== black bun ====)%n%n" +
                                        "Price: %f%n",
                                700.0f
                        )
                },
                {
                        whiteBun,
                        List.of(whiteCutlet, chiliSauce, sausage),
                        String.format(
                                Locale.getDefault(),
                                "(==== white bun ====)%n" +
                                        "= filling cutlet =%n" +
                                        "= sauce chili sauce =%n" +
                                        "= filling sausage =%n" +
                                        "(==== white bun ====)%n%n" +
                                        "Price: %f%n",
                                7.5f
                        )
                },
                {
                        redBun,
                        List.of(hotSauce, redSausage, redDinosaur),
                        String.format(
                                Locale.getDefault(),
                                "(==== red bun ====)%n" +
                                        "= sauce hot sauce =%n" +
                                        "= filling sausage =%n" +
                                        "= filling dinosaur =%n" +
                                        "(==== red bun ====)%n%n" +
                                        "Price: %f%n",
                                222.0f
                        )
                }
        };
    }

    @Test
    public void testGetReceipt() {
        Burger burger = new Burger();

        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);

        String actualReceipt = burger.getReceipt();

        Assert.assertEquals(expectedReceipt, actualReceipt);
    }
}