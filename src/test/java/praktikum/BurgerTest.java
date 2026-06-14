package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    @Before
    public void setUp() {
        burger = new Burger();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);

        assertNotNull(burger.getBun());
        assertEquals(mockBun, burger.getBun());
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);

        assertEquals(1, burger.getIngredients().size());
        assertEquals(mockIngredient1, burger.getIngredients().get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        assertEquals(3, burger.getIngredients().size());

        burger.removeIngredient(1);

        assertEquals(2, burger.getIngredients().size());
        assertEquals(mockIngredient1, burger.getIngredients().get(0));
        assertEquals(mockIngredient3, burger.getIngredients().get(1));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(0, 2);

        assertEquals(3, burger.getIngredients().size());
        assertEquals(mockIngredient2, burger.getIngredients().get(0));
        assertEquals(mockIngredient3, burger.getIngredients().get(1));
        assertEquals(mockIngredient1, burger.getIngredients().get(2));
    }

    @Test
    public void testGetPrice() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        when(mockIngredient2.getPrice()).thenReturn(75.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 100.0f * 2 + 50.0f + 75.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    public void testGetReceipt() {
        Bun realBun = new Bun("black bun", 100.0f);
        Ingredient realIngredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 100.0f);
        Ingredient realIngredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 100.0f);

        Burger testBurger = new Burger();
        testBurger.setBuns(realBun);
        testBurger.addIngredient(realIngredient1);
        testBurger.addIngredient(realIngredient2);

        String receipt = testBurger.getReceipt();

        assertTrue(receipt.contains("(==== black bun ====)"));
        assertTrue(receipt.contains("= sauce hot sauce ="));
        assertTrue(receipt.contains("= filling cutlet ="));
    }

    @Test
    public void testGetReceiptWithOneIngredient() {
        Bun realBun = new Bun("white bun", 200.0f);
        Ingredient realIngredient = new Ingredient(IngredientType.SAUCE, "chili sauce", 300.0f);

        Burger testBurger = new Burger();
        testBurger.setBuns(realBun);
        testBurger.addIngredient(realIngredient);

        String receipt = testBurger.getReceipt();

        assertTrue(receipt.contains("(==== white bun ====)"));
        assertTrue(receipt.contains("= sauce chili sauce ="));
    }

    @Test
    public void testGetPriceWithTwoIngredients() {
        Bun realBun = new Bun("bun1", 200.0f);
        Burger burger1 = new Burger();
        burger1.setBuns(realBun);
        burger1.addIngredient(new Ingredient(IngredientType.SAUCE, "sauce1", 50.0f));
        burger1.addIngredient(new Ingredient(IngredientType.FILLING, "filling1", 75.0f));

        assertEquals(525.0f, burger1.getPrice(), 0.001);
    }

    @Test
    public void testGetPriceWithThreeIngredients() {
        Bun realBun = new Bun("bun2", 150.0f);
        Burger burger2 = new Burger();
        burger2.setBuns(realBun);
        burger2.addIngredient(new Ingredient(IngredientType.SAUCE, "sauce2", 30.0f));
        burger2.addIngredient(new Ingredient(IngredientType.FILLING, "filling2", 40.0f));
        burger2.addIngredient(new Ingredient(IngredientType.SAUCE, "sauce3", 20.0f));

        assertEquals(390.0f, burger2.getPrice(), 0.001);
    }

    @Test
    public void testGetPriceWithoutIngredients() {
        Bun realBun = new Bun("bun3", 100.0f);
        Burger burger3 = new Burger();
        burger3.setBuns(realBun);

        assertEquals(200.0f, burger3.getPrice(), 0.001);
    }

    @Test
    public void testGetPriceWithZeroPriceBun() {
        Bun realBun = new Bun("bun4", 0.0f);
        Burger burger4 = new Burger();
        burger4.setBuns(realBun);
        burger4.addIngredient(new Ingredient(IngredientType.SAUCE, "sauce4", 10.0f));
        burger4.addIngredient(new Ingredient(IngredientType.FILLING, "filling4", 20.0f));

        assertEquals(30.0f, burger4.getPrice(), 0.001);
    }
}