package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    void setBunsSetsBunToBurger() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    void addIngredientAddsIngredientToList() {
        burger.addIngredient(sauce);
        assertEquals(List.of(sauce), burger.ingredients);
    }

    @Test
    void removeIngredientRemovesIngredientByIndex() {
        burger.addIngredient(sauce);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    void moveIngredientChangesIngredientOrder() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        assertEquals(List.of(filling, sauce), burger.ingredients);
    }

    @Test
    void getPriceCalculatesTotalBurgerPrice() {
        when(bun.getPrice()).thenReturn(100.0f);
        when(sauce.getPrice()).thenReturn(50.0f);
        when(filling.getPrice()).thenReturn(75.5f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(325.5f, burger.getPrice(), 0.001f);
    }

    @Test
    void getReceiptReturnsFormattedBurgerReceipt() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100.0f);

        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(50.0f);

        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(100.0f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String expectedReceipt = String.format(
                "(==== black bun ====)%n" +
                        "= sauce hot sauce =%n" +
                        "= filling cutlet =%n" +
                        "(==== black bun ====)%n" +
                        "%nPrice: %f%n",
                350.0f
        );

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}