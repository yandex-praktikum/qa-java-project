package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMock;

    private Burger burger;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsShouldAssignBun() {
        burger.setBuns(bunMock);
        assertSame(bunMock, burger.bun);
    }

    @Test
    public void addIngredientShouldIncreaseSize() {
        burger.addIngredient(ingredientMock);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddCorrectIngredient() {
        burger.addIngredient(ingredientMock);
        assertSame(ingredientMock, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveFromList() {
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientShouldPutIngredientOnFirstPosition() {
        Ingredient second = mock(Ingredient.class);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(second);

        burger.moveIngredient(1, 0);

        assertSame(second, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldPutOldIngredientOnSecondPosition() {
        Ingredient second = mock(Ingredient.class);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(second);

        burger.moveIngredient(1, 0);

        assertSame(ingredientMock, burger.ingredients.get(1));
    }

    @Test
    public void getPriceWithoutIngredients() {
        when(bunMock.getPrice()).thenReturn(120f);
        burger.setBuns(bunMock);

        assertEquals(240f, burger.getPrice(), 0.01f);
    }

    @Test
    public void getReceiptWithoutIngredientsContainsBunName() {
        when(bunMock.getName()).thenReturn("Sesame");
        when(bunMock.getPrice()).thenReturn(50f);
        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Sesame"));
    }

    @Test
    public void getReceiptWithoutIngredientsContainsPrice() {
        when(bunMock.getName()).thenReturn("Sesame");
        when(bunMock.getPrice()).thenReturn(50f);
        burger.setBuns(bunMock);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Price:"));
    }

    @Test
    public void getReceiptWithIngredientContainsBunName() {
        when(bunMock.getName()).thenReturn("Rye");
        when(bunMock.getPrice()).thenReturn(40f);
        when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock.getName()).thenReturn("cheese sauce");
        when(ingredientMock.getPrice()).thenReturn(15f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Rye"));
    }

    @Test
    public void getReceiptWithIngredientContainsType() {
        when(bunMock.getName()).thenReturn("Rye");
        when(bunMock.getPrice()).thenReturn(40f);
        when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock.getName()).thenReturn("cheese sauce");
        when(ingredientMock.getPrice()).thenReturn(15f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("sauce"));
    }

    @Test
    public void getReceiptWithIngredientContainsName() {
        when(bunMock.getName()).thenReturn("Rye");
        when(bunMock.getPrice()).thenReturn(40f);
        when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock.getName()).thenReturn("cheese sauce");
        when(ingredientMock.getPrice()).thenReturn(15f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("cheese sauce"));
    }

    @Test
    public void getReceiptWithIngredientContainsPrice() {
        when(bunMock.getName()).thenReturn("Rye");
        when(bunMock.getPrice()).thenReturn(40f);
        when(ingredientMock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock.getName()).thenReturn("cheese sauce");
        when(ingredientMock.getPrice()).thenReturn(15f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Price:"));
    }
}