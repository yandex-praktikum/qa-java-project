package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun mockBun;
    
    @Mock
    private Ingredient mockIngredient1;
    
    @Mock
    private Ingredient mockIngredient2;
    
    @Mock
    private Ingredient mockIngredient3;

    private Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(mockBun);
        
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredientToList() {
        burger.addIngredient(mockIngredient1);
        
        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(mockIngredient1));
    }

    @Test
    public void addMultipleIngredientsShouldAddAllIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        
        assertEquals(3, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(mockIngredient1));
        assertTrue(burger.ingredients.contains(mockIngredient2));
        assertTrue(burger.ingredients.contains(mockIngredient3));
    }

    @Test
    public void removeIngredientShouldRemoveIngredientByIndex() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        
        burger.removeIngredient(1);
        
        assertEquals(2, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(mockIngredient1));
        assertFalse(burger.ingredients.contains(mockIngredient2));
        assertTrue(burger.ingredients.contains(mockIngredient3));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToNewPosition() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        
        burger.moveIngredient(0, 2);
        
        assertEquals(3, burger.ingredients.size());
        assertEquals(mockIngredient2, burger.ingredients.get(0));
        assertEquals(mockIngredient3, burger.ingredients.get(1));
        assertEquals(mockIngredient1, burger.ingredients.get(2));
    }

    @Test
    public void getPriceShouldCalculateCorrectPrice() {
        when(mockBun.getPrice()).thenReturn(10.0f);
        when(mockIngredient1.getPrice()).thenReturn(5.0f);
        when(mockIngredient2.getPrice()).thenReturn(3.0f);
        
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        
        float expectedPrice = 10.0f * 2 + 5.0f + 3.0f;
        
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
    }

    @Test
    public void getPriceShouldReturnBunPriceTimesTwo() {
        when(mockBun.getPrice()).thenReturn(15.0f);
        
        burger.setBuns(mockBun);
        
        float expectedPrice = 15.0f * 2;
        
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void getReceiptShouldReturnFormattedReceipt() {
        when(mockBun.getName()).thenReturn("Sesame Bun");
        when(mockBun.getPrice()).thenReturn(10.0f);
        when(mockIngredient1.getName()).thenReturn("Cheese");
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient1.getPrice()).thenReturn(5.0f);
        when(mockIngredient2.getName()).thenReturn("Ketchup");
        when(mockIngredient2.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient2.getPrice()).thenReturn(3.0f);
        
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        
        String receipt = burger.getReceipt();
        
        assertTrue(receipt.contains("Sesame Bun"));
        assertTrue(receipt.contains("filling Cheese"));
        assertTrue(receipt.contains("sauce Ketchup"));
        assertTrue(receipt.contains("Price: 28,000000"));
        
        verify(mockBun, atLeast(2)).getName();
        verify(mockIngredient1, times(1)).getName();
        verify(mockIngredient1, times(1)).getType();
        verify(mockIngredient2, times(1)).getName();
        verify(mockIngredient2, times(1)).getType();
    }

    @Test
    public void getReceiptShouldHandleEmptyIngredientsList() {
        when(mockBun.getName()).thenReturn("Classic Bun");
        when(mockBun.getPrice()).thenReturn(8.0f);
        
        burger.setBuns(mockBun);
        
        String receipt = burger.getReceipt();
        
        assertTrue(receipt.contains("Classic Bun"));
        assertTrue(receipt.contains("Price: 16,000000"));
        assertFalse(receipt.contains("filling"));
        assertFalse(receipt.contains("sauce"));
        
        verify(mockBun, atLeast(2)).getName();
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void moveIngredientShouldHandleMovingToBeginning() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        
        burger.moveIngredient(2, 0);
        
        assertEquals(3, burger.ingredients.size());
        assertEquals(mockIngredient3, burger.ingredients.get(0));
        assertEquals(mockIngredient1, burger.ingredients.get(1));
        assertEquals(mockIngredient2, burger.ingredients.get(2));
    }

    @Test
    public void removeIngredientShouldHandleRemovingFirstIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        
        burger.removeIngredient(0);
        
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredient2, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldHandleRemovingLastIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        
        burger.removeIngredient(1);
        
        assertEquals(1, burger.ingredients.size());
        assertEquals(mockIngredient1, burger.ingredients.get(0));
    }
}