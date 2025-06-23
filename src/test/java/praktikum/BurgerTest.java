package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class BurgerTest {

    @Mock
    private Bun mockBun;
    
    @Mock
    private Ingredient mockIngredient1;
    
    @Mock
    private Ingredient mockIngredient2;

    private Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    @Test
    public void testSetBun() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientReducesSize() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        
        burger.removeIngredient(0);
        
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientChangesOrder() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        
        burger.moveIngredient(0, 1);
        
        assertEquals(mockIngredient1, burger.ingredients.get(1));
    }

    @Test
    public void testCalculateCorrectPrice() {
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        when(mockIngredient2.getPrice()).thenReturn(70.0f);
        
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        
        float expectedPrice = 100.0f * 2 + 50.0f + 70.0f; 
        assertEquals(expectedPrice, burger.getPrice(), 0.0f);
    }

    @Test
    public void testAddMultipleIngredientsIncreasesSize() {
        Ingredient ingredient1 = new Ingredient(IngredientType.FILLING, "bun", 100.0f);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING, "cheese", 50.0f);
        Ingredient ingredient3 = new Ingredient(IngredientType.SAUCE, "sauce", 25.0f);
        
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);
        
        assertEquals(3, burger.ingredients.size());
    }

    @Test
    public void testAddedIngredientIsContainedInList() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cheese", 50.0f);
        
        burger.addIngredient(ingredient);
        
        assertTrue(burger.ingredients.contains(ingredient));
    }

    @Test
    public void testGetReceiptWithBunAndIngredients() {
        when(mockBun.getName()).thenReturn("Test Bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getName()).thenReturn("Test Sauce");
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getPrice()).thenReturn(50.0f);
        
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Test Bun"));
    }

    @Test
    public void testGetReceiptContainsPrice() {
        when(mockBun.getName()).thenReturn("Test Bun");
        when(mockBun.getPrice()).thenReturn(100.0f);
        
        burger.setBuns(mockBun);
        
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price: 200"));
    }

    @Test
    public void testGetPriceWithoutIngredients() {
        when(mockBun.getPrice()).thenReturn(150.0f);
        
        burger.setBuns(mockBun);
        
        assertEquals(300.0f, burger.getPrice(), 0.0f);
    }
}