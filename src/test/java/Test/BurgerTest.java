package Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import praktikum.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private Burger burger;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsSetsBun() {
        Bun bun = mock(Bun.class);
        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsIngredient() {
        Ingredient i = mock(Ingredient.class);

        burger.addIngredient(i);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientWorks() {
        Ingredient i = mock(Ingredient.class);

        burger.addIngredient(i);
        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientWorks() {
        Ingredient i1 = mock(Ingredient.class);
        Ingredient i2 = mock(Ingredient.class);

        burger.addIngredient(i1);
        burger.addIngredient(i2);

        burger.moveIngredient(0, 1);

        assertEquals(i2, burger.ingredients.get(0));
        assertEquals(i1, burger.ingredients.get(1));
    }


    @Test
    public void getPriceWithBunAndIngredients() {
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(100f);

        Ingredient i1 = mock(Ingredient.class);
        Ingredient i2 = mock(Ingredient.class);

        when(i1.getPrice()).thenReturn(10f);
        when(i2.getPrice()).thenReturn(20f);

        burger.setBuns(bun);
        burger.addIngredient(i1);
        burger.addIngredient(i2);

        assertEquals(230f, burger.getPrice());
    }

    @Test
    public void getPriceWithOnlyBun() {
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(100f);

        burger.setBuns(bun);

        assertEquals(200f, burger.getPrice());
    }

    @Test
    public void getReceiptSingleIngredient() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("hot sauce");

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("black bun"));
        assertTrue(receipt.contains("hot sauce"));
        assertTrue(receipt.contains("Price"));
    }

    @Test
    public void getReceiptMultipleIngredients() {
        Bun bun = mock(Bun.class);
        when(bun.getName()).thenReturn("bun");

        Ingredient i1 = mock(Ingredient.class);
        Ingredient i2 = mock(Ingredient.class);

        when(i1.getType()).thenReturn(IngredientType.SAUCE);
        when(i1.getName()).thenReturn("s1");

        when(i2.getType()).thenReturn(IngredientType.FILLING);
        when(i2.getName()).thenReturn("f1");

        burger.setBuns(bun);
        burger.addIngredient(i1);
        burger.addIngredient(i2);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("s1"));
        assertTrue(receipt.contains("f1"));
    }


    @Test
    public void moveIngredientSameIndexDoesNotCrash() {
        Ingredient i = mock(Ingredient.class);

        burger.addIngredient(i);

        assertDoesNotThrow(() -> burger.moveIngredient(0, 0));
    }
}