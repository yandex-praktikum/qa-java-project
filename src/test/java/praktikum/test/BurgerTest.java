package praktikum.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Mock
    private Ingredient ingredient2;

    @Spy
    private Burger burger;

    @Before
    public void startUp() {
        when(bun.getPrice()).thenReturn(100f);
        when(bun.getName()).thenReturn("black bun");

        when(ingredient.getPrice()).thenReturn(200f);
        when(ingredient.getName()).thenReturn("sour cream");
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);

        when(ingredient2.getPrice()).thenReturn(300f);
        when(ingredient2.getName()).thenReturn("sausage");
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        verify(burger).setBuns(bun);
        assertNotNull(burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredient);
        assertTrue(burger.ingredients.contains(ingredient));
    }

    @Test
    public void removeIngredientTest() {
        burger.ingredients.add(ingredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        burger.ingredients.add(ingredient);
        burger.ingredients.add(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient, burger.ingredients.get(1));
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void getPriceReturnPriceTest() {
        burger.setBuns(bun);
        burger.ingredients.add(ingredient);
        burger.ingredients.add(ingredient2);
        float expectedPrice = (100f * 2) + 200f + 300f;
        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }

    @Test
    public void getReceiptReturnReceiptTest() {
        burger.setBuns(bun);
        burger.ingredients.add(ingredient);
        burger.ingredients.add(ingredient2);

        String expectedReceipt =
                "(==== black bun ====)\n" +
                "= sauce sour cream =\n" +
                "= filling sausage =\n" +
                "(==== black bun ====)\n" +
                "\n" +
                "Price: 700,000000\n";

        String normalizedExpected = expectedReceipt.replaceAll("\r\n", "\n");
        String normalizedActual = burger.getReceipt().replaceAll("\r\n", "\n");

        assertEquals(normalizedExpected, normalizedActual);
    }
}
