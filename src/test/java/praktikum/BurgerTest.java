package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockSauce;

    @Mock
    private Ingredient mockFilling;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBunsSetsBun() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        burger.addIngredient(mockSauce);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        burger.addIngredient(mockSauce);
        assertEquals(mockSauce, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientDecreasesSize() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientRemovesCorrectOne() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.removeIngredient(0);
        assertEquals(mockFilling, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientChangesOrderFirstElement() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.moveIngredient(0, 1);
        assertEquals(mockFilling, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientChangesOrderSecondElement() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.moveIngredient(0, 1);
        assertEquals(mockSauce, burger.ingredients.get(1));
    }

    @Test
    public void testGetPriceWithBunAndIngredients() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockSauce.getPrice()).thenReturn(50f);
        when(mockFilling.getPrice()).thenReturn(200f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        assertEquals(450f, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetPriceOnlyBun() {
        when(mockBun.getPrice()).thenReturn(150f);
        burger.setBuns(mockBun);
        assertEquals(300f, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceiptContainsBunName() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockSauce.getName()).thenReturn("hot sauce");
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauce.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== black bun ====)"));
    }

    @Test
    public void testGetReceiptContainsIngredient() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockSauce.getName()).thenReturn("hot sauce");
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauce.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("= sauce hot sauce ="));
    }

    @Test
    public void testGetReceiptContainsPrice() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockSauce.getName()).thenReturn("hot sauce");
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockSauce.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price:"));
    }
}