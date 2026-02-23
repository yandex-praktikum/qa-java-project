package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerFullCoverageTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient sauceMock;

    @Mock
    private Ingredient fillingMock;

    @Before
    public void setUp() {
        burger = new Burger();
        when(bunMock.getName()).thenReturn("Test Bun");
        when(bunMock.getPrice()).thenReturn(100.0f);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(sauceMock);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(sauceMock);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bunMock);
        when(sauceMock.getPrice()).thenReturn(50.0f);
        burger.addIngredient(sauceMock);
        assertEquals(250.0f, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceiptContainsBunName() {
        burger.setBuns(bunMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Test Bun"));
    }

    @Test
    public void testGetReceiptContainsPrice() {
        burger.setBuns(bunMock);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price: 200.000000"));
    }
}