package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        when(bun.getPrice()).thenReturn(100.0f);
        when(ingredient1.getPrice()).thenReturn(50.0f);
        when(ingredient2.getPrice()).thenReturn(75.0f);

        float expectedPrice = 100.0f * 2 + 50.0f + 75.0f;
        assertEquals(expectedPrice, burger.getPrice());
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        when(bun.getName()).thenReturn("Test Bun");
        when(bun.getPrice()).thenReturn(100.0f);

        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("Test Sauce");
        when(ingredient1.getPrice()).thenReturn(50.0f);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== Test Bun ====)"));
        assertTrue(receipt.contains("= sauce Test Sauce ="));
        float expectedPrice = 250.0f;
        String expectedPriceString = String.format("%f", expectedPrice);
        assertTrue(receipt.contains("Price: " + expectedPriceString));
    }
}