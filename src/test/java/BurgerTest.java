import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import org.assertj.core.api.SoftAssertions;


import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private final static float PRICE_BUN = 2.78f;
    private final static float PRICE_INGREDIENT = 2.78f;

    private final static String NAME_BUN = "black bun";
    private final static String NAME_INGREDIENT = "hot sauce";

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSetNullBunResultsNullInBun() {

        Burger burger = new Burger();
        burger.setBuns(null);
        assertNull(burger.bun);
    }
    @Test
    public void testSetBunResultsBun() {
        Bun bun = new Bun(NAME_BUN, PRICE_BUN);

        Burger burger = new Burger();
        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }
    @Test
    public void testAddOneIngredientResultsInListSize1() {
       Ingredient ingredient = new Ingredient(IngredientType.SAUCE, NAME_INGREDIENT, PRICE_INGREDIENT);

        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
    }
    @Test
    public void testAddNullIngredientResultsInListSize1() {

        Burger burger = new Burger();
        burger.addIngredient(null);
        assertEquals(1, burger.ingredients.size());
    }


    @Test
    public void testRemoveOneExistingIngredientResultsInListSize0() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, NAME_INGREDIENT, PRICE_INGREDIENT);
        burger.addIngredient(ingredient);
        Ingredient expectedRemoved = burger.ingredients.get(0);

        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testRemoveOneExistingIngredientResultsInListNotContainsRemovedIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, NAME_INGREDIENT, PRICE_INGREDIENT);
        burger.addIngredient(ingredient);
        Ingredient expectedRemoved = burger.ingredients.get(0);

        burger.removeIngredient(0);

        assertFalse(burger.ingredients.contains(expectedRemoved));
    }
    @Test
    public void testRemoveNotExistingIngredientThrowsException() {
        Burger burger = new Burger();
        assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(0));
    }


    @Test
    public void testMoveExistingIndexToNotExistingIndexThrowsException() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, NAME_INGREDIENT, PRICE_INGREDIENT);
        burger.addIngredient(ingredient);

        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(0,1));
    }
    @Test
    public void testMoveNotExistingIndexToExistingIndexThrowsException() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, NAME_INGREDIENT, 2.78f);
        burger.addIngredient(ingredient);

        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(1,0));
    }
    @Test
    public void testGetPriceWhenBurgerIsEmptyThrowsException() {
        Burger burger = new Burger();

        assertThrows(NullPointerException.class, () -> burger.getPrice());
    }

    @Test
    public void testGetPriceWhenNoBunAndHaveIngredientsThrowsException() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);

        assertThrows(NullPointerException.class, () -> burger.getPrice());
    }
    @Test
    public void testGetPriceWhenBunAndNoIngredientsReturnsPriceOfTwoBuns() {
        Burger burger = new Burger();

        Mockito.when(bun.getPrice()).thenReturn(PRICE_BUN);
        burger.setBuns(bun);

        float expectedPrice = PRICE_BUN*2;
        float actualPrice = burger.getPrice();

        assertEquals(expectedPrice, actualPrice, 0.0001f);
    }
    @Test
    public void testGetReceiptWhenNoBunAndNoIngredientThrowsNullPointerException() {
        Burger burger = new Burger();
        assertThrows(NullPointerException.class, () -> burger.getReceipt());
    }

    @Test
    public void testGetReceiptWhenHasBunAndNoIngredientsWithSoftAssertions() {
        Burger burger = new Burger();
        Mockito.when(bun.getName()).thenReturn(NAME_BUN);
        Mockito.when(bun.getPrice()).thenReturn(PRICE_BUN);
        burger.setBuns(bun);

        String receipt = burger.getReceipt();
        SoftAssertions.assertSoftly(softAssertions -> {

            softAssertions.assertThat(receipt)
                    .contains(NAME_BUN)
                    .doesNotContain("= sauce")
                    .doesNotContain("= filling")
                    .contains(String.format("Price: %f", PRICE_BUN*2));

            softAssertions.assertAll();
        });

    }


}
