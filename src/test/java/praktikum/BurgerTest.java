package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsSetsBunCorrectly() {
        burger.setBuns(bun);
        Assertions.assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsToIngredientsList() {
        burger.addIngredient(sauce);
        Assertions.assertEquals(1, burger.ingredients.size());
        Assertions.assertTrue(burger.ingredients.contains(sauce));
    }

    @Test
    public void removeIngredientRemovesFromIngredientsList() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        Assertions.assertEquals(1, burger.ingredients.size());
        Assertions.assertEquals(filling, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientChangesOrderCorrectly() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        Assertions.assertEquals(filling, burger.ingredients.get(0));
        Assertions.assertEquals(sauce, burger.ingredients.get(1));
    }

    @Test
    public void getPriceCalculatesTotalSumCorrectly() {
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(sauce.getPrice()).thenReturn(50f);
        Mockito.when(filling.getPrice()).thenReturn(150f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float expectedPrice = 400f;
        Assertions.assertEquals(expectedPrice, burger.getPrice(), 0.0f);
    }

    @Test
    public void getReceiptReturnsFormattedString() {
        Mockito.when(bun.getName()).thenReturn("Краторная булка N-200i");
        Mockito.when(bun.getPrice()).thenReturn(100f);

        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce.getName()).thenReturn("Соус традиционный галактический");
        Mockito.when(sauce.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);

        String receipt = burger.getReceipt();

        Assertions.assertTrue(receipt.contains("(==== Краторная булка N-200i ====)"));
        Assertions.assertTrue(receipt.contains("= sauce Соус традиционный галактический ="));
        String expectedPriceString = String.format("Price: %f", 250.0f);
        Assertions.assertTrue(receipt.contains(expectedPriceString));
    }
}
