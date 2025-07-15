package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.assertj.core.api.SoftAssertions;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static praktikum.IngredientType.*;

@RunWith(Enclosed.class)
public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient fillingIngredient;
    private Ingredient sauceIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
        bun = Mockito.mock(Bun.class);
        fillingIngredient = Mockito.mock(Ingredient.class);
        sauceIngredient = Mockito.mock(Ingredient.class);

        Mockito.when(bun.getName()).thenReturn("white bun");
        Mockito.when(bun.getPrice()).thenReturn(200f);

        Mockito.when(fillingIngredient.getType()).thenReturn(FILLING);
        Mockito.when(fillingIngredient.getName()).thenReturn("cutlet");
        Mockito.when(fillingIngredient.getPrice()).thenReturn(100f);

        Mockito.when(sauceIngredient.getType()).thenReturn(SAUCE);
        Mockito.when(sauceIngredient.getName()).thenReturn("hot sauce");
        Mockito.when(sauceIngredient.getPrice()).thenReturn(50f);
    }

    @Test
    public void testSetBunsSetsBun() {
        burger.setBuns(bun);
        assertNotNull(burger.bun);
    }

    @Test
    public void testSetBunsSetsCorrectBun() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        burger.addIngredient(fillingIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        burger.addIngredient(fillingIngredient);
        assertEquals(fillingIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientDecreasesSize() {
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(sauceIngredient);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemovesCorrectIngredient() {
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(sauceIngredient);
        burger.removeIngredient(0);
        assertEquals(sauceIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientChangesOrder() {
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(sauceIngredient);
        burger.moveIngredient(0, 1);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(sauceIngredient);
        softly.assertThat(burger.ingredients.get(1)).isEqualTo(fillingIngredient);
        softly.assertAll();
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(sauceIngredient);

        float expectedPrice = bun.getPrice() * 2 + fillingIngredient.getPrice() + sauceIngredient.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0.0f);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(fillingIngredient);
        burger.addIngredient(sauceIngredient);

        String expectedReceipt = String.format("(==== %s ====)%n", bun.getName()) +
                String.format("= %s %s =%n", fillingIngredient.getType().toString().toLowerCase(), fillingIngredient.getName()) +
                String.format("= %s %s =%n", sauceIngredient.getType().toString().toLowerCase(), sauceIngredient.getName()) +
                String.format("(==== %s ====)%n", bun.getName()) +
                String.format("%nPrice: %f%n", burger.getPrice());

        assertEquals(expectedReceipt, burger.getReceipt());
    }

    @RunWith(Parameterized.class)
    public static class ParameterizedReceiptTest {
        @Parameterized.Parameter
        public IngredientType ingredientType;

        @Parameterized.Parameters(name = "{index}: Test with ingredientType={0}")
        public static List<IngredientType> data() {
            return Arrays.asList(FILLING, SAUCE);
        }

        private Burger burger;
        private Bun bun;
        private Ingredient ingredient;

        @Before
        public void setUp() {
            burger = new Burger();
            bun = Mockito.mock(Bun.class);
            ingredient = Mockito.mock(Ingredient.class);

            Mockito.when(bun.getName()).thenReturn("white bun");
            Mockito.when(bun.getPrice()).thenReturn(200f);
            Mockito.when(ingredient.getType()).thenReturn(ingredientType);
            Mockito.when(ingredient.getName()).thenReturn("test ingredient");
            Mockito.when(ingredient.getPrice()).thenReturn(100f);
        }

        @Test
        public void testGetReceiptContainsCorrectIngredientType() {
            burger.setBuns(bun);
            burger.addIngredient(ingredient);

            String expectedType = ingredientType.toString().toLowerCase();
            assertTrue(burger.getReceipt().contains(expectedType));
        }
    }
}
