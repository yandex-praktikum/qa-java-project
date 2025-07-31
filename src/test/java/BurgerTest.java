import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import org.assertj.core.api.SoftAssertions;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class BurgerTest {

    private Burger burger;
    private SoftAssertions softly;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient1;
    @Mock
    private Ingredient ingredient2;
    @Mock
    private Ingredient ingredient3;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
        softly = new SoftAssertions();

        when(bun.getName()).thenReturn("test bun");
        when(ingredient1.getName()).thenReturn("ingredient1");
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient2.getName()).thenReturn("ingredient2");
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient3.getName()).thenReturn("ingredient3");
        when(ingredient3.getType()).thenReturn(IngredientType.SAUCE);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        int initialSize = burger.ingredients.size();
        burger.addIngredient(ingredient1);
        assertEquals(initialSize + 1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        burger.addIngredient(ingredient1);
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientDecreasesSize() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        int initialSize = burger.ingredients.size();
        burger.removeIngredient(0);
        assertEquals(initialSize - 1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemovesCorrectIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(ingredient1));
    }

    @Test
    public void testMoveIngredientChangesPosition() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);
        burger.moveIngredient(0, 2);
        assertEquals(ingredient1, burger.ingredients.get(2));
    }

    @Test
    public void testMoveIngredientMaintainsOtherPositions() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);
        burger.moveIngredient(0, 2);
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(ingredient2);
        softly.assertThat(burger.ingredients.get(1)).isEqualTo(ingredient3);
        softly.assertAll();
    }

    @Test
    public void testGetReceipt() {
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient1.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        String expected = String.format(
                "(==== test bun ====)%n" +
                        "= sauce ingredient1 =%n" +
                        "(==== test bun ====)%n" +
                        "%n" +
                        "Price: 250,00%n"
        );

        assertEquals(expected, burger.getReceipt());
    }
}