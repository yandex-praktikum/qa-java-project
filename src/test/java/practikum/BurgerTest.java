package practikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();

        when(bun.getPrice()).thenReturn(50f);
        when(bun.getName()).thenReturn("Булка");

        when(ingredient.getPrice()).thenReturn(20f);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("Начинка");

        burger.setBuns(bun);
    }

    @Test
    public void setBunsSetsCorrectBun() {
        assertEquals(bun, burger.getBuns());
    }

    @Test
    public void addIngredientIncreasesPrice() {
        burger.addIngredient(ingredient);
        assertEquals(120f, burger.getPrice(), 0.001);
    }

    @Test
    public void removeIngredientDecreasesPrice() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(100f, burger.getPrice(), 0.001);
    }

    @Test
    public void moveIngredientDoesNotBreakPrice() {
        // Создаём мок и сразу стаббим только то, что реально используется в тесте
        Ingredient second = mock(Ingredient.class);
        when(second.getPrice()).thenReturn(10f);
        // Не стаббим getType() и getName(), так как они не используются в этом тесте

        burger.addIngredient(ingredient);
        burger.addIngredient(second);
        burger.moveIngredient(0, 1);

        assertEquals(130f, burger.getPrice(), 0.001);
    }

    @Test
    public void getReceiptReturnsCorrectFormat() {
        String expectedReceipt = String.format("(==== Булка ====)%n") +
                String.format("= filling Начинка =%n") +
                String.format("(==== Булка ====)%n") +
                String.format("%nPrice: 120,000000%n");

        burger.addIngredient(ingredient);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }

    @Test
    public void getReceiptWithMultipleIngredientsReturnsCorrectFormat() {
        Ingredient secondIngredient = mock(Ingredient.class);
        when(secondIngredient.getPrice()).thenReturn(30f);
        when(secondIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(secondIngredient.getName()).thenReturn("Соус");

        String expectedReceipt = String.format("(==== Булка ====)%n") +
                String.format("= filling Начинка =%n") +
                String.format("= sauce Соус =%n") +
                String.format("(==== Булка ====)%n") +
                String.format("%nPrice: 150,000000%n");

        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }
}