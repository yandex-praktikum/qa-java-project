package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {

    private Burger burger;
    private final String description;
    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final String expectedReceipt;

    public BurgerReceiptTest(String description, Bun bun, List<Ingredient> ingredients, String expectedReceipt) {
        this.description = description;
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Object[][] getTestData() {

        Bun blackBun = mock(Bun.class);
        when(blackBun.getName()).thenReturn("black bun");
        when(blackBun.getPrice()).thenReturn(100f);

        Bun whiteBun = mock(Bun.class);
        when(whiteBun.getName()).thenReturn("white bun");
        when(whiteBun.getPrice()).thenReturn(200f);

        Bun redBun = mock(Bun.class);
        when(redBun.getName()).thenReturn("red bun");
        when(redBun.getPrice()).thenReturn(300f);


        Ingredient hotSauce = mock(Ingredient.class);
        when(hotSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(hotSauce.getName()).thenReturn("hot sauce");
        when(hotSauce.getPrice()).thenReturn(50f);

        Ingredient sourCream = mock(Ingredient.class);
        when(sourCream.getType()).thenReturn(IngredientType.SAUCE);
        when(sourCream.getName()).thenReturn("sour cream");
        when(sourCream.getPrice()).thenReturn(20f);

        Ingredient cutlet = mock(Ingredient.class);
        when(cutlet.getType()).thenReturn(IngredientType.FILLING);
        when(cutlet.getName()).thenReturn("cutlet");
        when(cutlet.getPrice()).thenReturn(100f);

        return new Object[][]{
                {
                        "black bun",
                        blackBun,
                        List.of(),
                        String.format("(==== black bun ====)%n(==== black bun ====)%n%nPrice: 200,000000%n")
                },
                {
                        "white bun + hot sauce",
                        whiteBun,
                        List.of(hotSauce),
                        String.format("(==== white bun ====)%n= sauce hot sauce =%n(==== white bun ====)%n%nPrice: 450,000000%n")
                },
                {
                        "red bun + sour cream + cutlet",
                        redBun,
                        Arrays.asList(sourCream, cutlet),
                        String.format("(==== red bun ====)%n= sauce sour cream =%n= filling cutlet =%n(==== red bun ====)%n%nPrice: 720,000000%n")
                }
        };
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void getReceiptReturnsCorrectFormat() {
        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
