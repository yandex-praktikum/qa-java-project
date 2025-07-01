package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BurgerTest {
    private Bun bun;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Burger burger;

    @BeforeEach
    void setUp() {
        bun = mock(Bun.class);
        ingredient1 = mock(Ingredient.class);
        ingredient2 = mock(Ingredient.class);
        burger = new Burger();
    }

    @Test
    void setBunsTest() {
        when(bun.getName()).thenReturn("red bun");
        when(bun.getPrice()).thenReturn(300.0f);

        burger.setBuns(bun);

        assertEquals("red bun", burger.bun.getName());
        assertEquals(300.0f, burger.bun.getPrice());
    }

    @Test
    void addIngredientTest() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    void removeIngredientTest() {
        burger.addIngredient(ingredient1);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    void moveIngredientTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(0, 1);
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    void getPriceTest() {
        when(bun.getPrice()).thenReturn(200.0f);
        when(ingredient1.getPrice()).thenReturn(300.0f);
        when(ingredient2.getPrice()).thenReturn(200.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = 200.0f * 2 + 300.0f + 200.0f;
        assertEquals(expectedPrice, burger.getPrice());
    }

    @Test
    void getReceiptTest() {
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(200.0f);
        when(ingredient1.getName()).thenReturn("sour cream");
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getPrice()).thenReturn(200.0f);
        when(ingredient2.getName()).thenReturn("dinosaur");
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getPrice()).thenReturn(200.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        float expectedPrice = bun.getPrice() * 2 + ingredient1.getPrice() + ingredient2.getPrice();
        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                "= %s %s =%n" +
                "= %s %s =%n" +
                "(==== %s ====)%n" +
                "%nPrice: %f%n",
                "white bun", "sauce", "sour cream", "filling", "dinosaur", "white bun", expectedPrice);
        assertEquals(expectedReceipt, burger.getReceipt());
    }
}