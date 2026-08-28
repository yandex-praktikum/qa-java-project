import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    @Test
    public void setBunsSetsPassedBun() {
        Burger burger = new Burger();
        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredientIncreasesIngredientsCount() {
        Burger burger = new Burger();
        burger.addIngredient(sauce);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientAddsPassedIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(sauce);

        assertSame(sauce, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientDecreasesIngredientsCount() {
        Burger burger = new Burger();
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientRemovesIngredientByIndex() {
        Burger burger = new Burger();
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(0);

        assertSame(filling, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientWithWrongIndexThrowsException() {
        Burger burger = new Burger();
        burger.removeIngredient(0);
    }

    @Test
    public void moveIngredientMovesIngredientToNewIndex() {
        Burger burger = new Burger();
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0, 1);

        assertSame(sauce, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientShiftsOtherIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0, 1);

        assertSame(filling, burger.ingredients.get(0));
    }

    @Test
    public void getPriceWithoutIngredientsReturnsDoubleBunPrice() {
        when(bun.getPrice()).thenReturn(100F);
        Burger burger = new Burger();
        burger.setBuns(bun);

        assertEquals(200F, burger.getPrice(), 0);
    }

    @Test
    public void getPriceWithIngredientsReturnsSum() {
        when(bun.getPrice()).thenReturn(100F);
        when(sauce.getPrice()).thenReturn(50F);
        when(filling.getPrice()).thenReturn(150F);
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(400F, burger.getPrice(), 0);
    }

    @Test
    public void getPriceCallsBunGetPriceOnce() {
        when(bun.getPrice()).thenReturn(100F);
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.getPrice();

        verify(bun, times(1)).getPrice();
    }

    @Test
    public void getPriceCallsIngredientGetPriceOnce() {
        when(bun.getPrice()).thenReturn(100F);
        when(sauce.getPrice()).thenReturn(50F);
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.getPrice();

        verify(sauce, times(1)).getPrice();
    }

    @Test
    public void getReceiptWithIngredientsReturnsExpectedText() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100F);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getPrice()).thenReturn(100F);
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(sauce);

        String expectedReceipt = String.format("(==== %s ====)%n", "black bun")
                + String.format("= %s %s =%n", "sauce", "hot sauce")
                + String.format("(==== %s ====)%n", "black bun")
                + String.format("%nPrice: %f%n", 300F);

        assertEquals(expectedReceipt, burger.getReceipt());
    }

    @Test
    public void getReceiptWithoutIngredientsReturnsExpectedText() {
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(200F);
        Burger burger = new Burger();
        burger.setBuns(bun);

        String expectedReceipt = String.format("(==== %s ====)%n", "white bun")
                + String.format("(==== %s ====)%n", "white bun")
                + String.format("%nPrice: %f%n", 400F);

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}