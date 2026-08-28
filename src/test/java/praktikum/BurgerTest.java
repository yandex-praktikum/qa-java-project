package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    // === Тесты для setBuns ===

    @Test
    public void setBunsShouldSetBunCorrectly() {
        when(mockBun.getName()).thenReturn("black bun");
        burger.setBuns(mockBun);
        assertEquals("black bun", burger.bun.getName());
    }

    // === Тесты для addIngredient ===

    @Test
    public void addIngredientShouldIncreaseListSize() {
        burger.addIngredient(mockIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddCorrectIngredient() {
        when(mockIngredient.getName()).thenReturn("cutlet");
        burger.addIngredient(mockIngredient);
        assertEquals("cutlet", burger.ingredients.get(0).getName());
    }

    // === Тесты для removeIngredient ===

    @Test
    public void removeIngredientShouldDecreaseListSize() {
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientWithInvalidIndexShouldThrowException() {
        burger.removeIngredient(0);
    }

    // === Тесты для moveIngredient ===

    @Test
    public void moveIngredientShouldChangePosition() {
        Ingredient ingredient1 = new Ingredient(IngredientType.FILLING, "cutlet", 100f);
        Ingredient ingredient2 = new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);

        assertEquals("hot sauce", burger.ingredients.get(0).getName());
        assertEquals("cutlet", burger.ingredients.get(1).getName());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientWithInvalidIndexShouldThrowException() {
        burger.moveIngredient(0, 1);
    }

    // === Тесты для getPrice ===

    @Test
    public void getPriceShouldReturnCorrectPriceWithBunAndIngredients() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient.getPrice()).thenReturn(50f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        float expectedPrice = 100f * 2 + 50f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void getPriceShouldReturnOnlyBunPriceWhenNoIngredients() {
        when(mockBun.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);

        float expectedPrice = 100f * 2;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    // === Тесты для getReceipt ===

    @Test
    public void getReceiptShouldContainBunName() {
        when(mockBun.getName()).thenReturn("black bun");
        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();
        assertEquals(true, receipt.contains("black bun"));
    }

    @Test
    public void getReceiptShouldContainIngredientName() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient.getName()).thenReturn("cutlet");

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String receipt = burger.getReceipt();
        assertEquals(true, receipt.contains("cutlet"));
    }

    @Test
    public void getReceiptShouldContainIngredientTypeLowerCase() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient.getName()).thenReturn("hot sauce");

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String receipt = burger.getReceipt();
        assertEquals(true, receipt.contains("sauce"));
    }

    @Test
    public void getReceiptShouldContainPrice() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);

        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();
        assertEquals(true, receipt.contains("Price:"));
    }
}
