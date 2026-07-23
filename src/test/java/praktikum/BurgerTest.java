package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBunCorrectly() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void addIngredientShouldIncreaseSize() {
        burger.addIngredient(mockIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddCorrectIngredient() {
        burger.addIngredient(mockIngredient);
        assertEquals(mockIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveIngredientCorrectly() {
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldRemoveCorrectIngredient() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.removeIngredient(0);

        assertEquals(secondIngredient, burger.ingredients.get(0));
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void moveIngredientShouldMoveIngredientCorrectly() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(1, 0);

        assertEquals(secondIngredient, burger.ingredients.get(0));
        assertEquals(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void getPriceShouldReturnCorrectSum() {
        when(mockBun.getPrice()).thenReturn(1.5f);
        when(mockIngredient.getPrice()).thenReturn(2.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        float expectedPrice = 1.5f * 2 + 2.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void getPriceShouldIncludeTwoBuns() {
        when(mockBun.getPrice()).thenReturn(2.0f);

        burger.setBuns(mockBun);

        float expectedPrice = 4.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void getReceiptShouldContainBunName() {
        when(mockBun.getName()).thenReturn("Краторная булка");
        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== Краторная булка ====)"));
    }

    @Test
    public void getReceiptShouldContainIngredientInfo() {
        when(mockBun.getName()).thenReturn("Краторная булка");
        when(mockBun.getPrice()).thenReturn(1.0f);
        when(mockIngredient.getName()).thenReturn("Соус");
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient.getPrice()).thenReturn(1.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("= sauce Соус ="));
    }

    @Test
    public void getReceiptShouldContainPrice() {
        Bun realBun = new Bun("Краторная булка", 1.5f);
        Ingredient realIngredient = new Ingredient(IngredientType.SAUCE, "Соус", 2.0f);

        burger.setBuns(realBun);
        burger.addIngredient(realIngredient);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price:"));
        assertTrue(receipt.contains("5")); // 1.5 + 1.5 + 2.0 = 5.0
    }
}