package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BurgerTest {

    private final float bunPrice;
    private final float[] ingredientPrices;
    private final float expectedPrice;

    public BurgerTest(float bunPrice, float[] ingredientPrices, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Bun price: {0}, Ingredients: {1}, Expected: {2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100, new float[]{}, 200},
                {150, new float[]{50}, 350},
                {200, new float[]{100, 150}, 650}
        });
    }

    @Test
    public void shouldCalculateCorrectPrice() {
        // Arrange
        Burger burger = new Burger();
        Bun bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bunMock);

        for (float price : ingredientPrices) {
            Ingredient ingredientMock = mock(Ingredient.class);
            when(ingredientMock.getPrice()).thenReturn(price);
            burger.addIngredient(ingredientMock);
        }

        // Act & Assert
        assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void shouldSetBunsCorrectly() {
        Burger burger = new Burger();
        Bun bunMock = mock(Bun.class);
        burger.setBuns(bunMock);
        assertSame(bunMock, burger.bun);
    }

    @Test
    public void shouldAddIngredient() {
        Burger burger = new Burger();
        Ingredient ingredientMock = mock(Ingredient.class);
        burger.addIngredient(ingredientMock);
        assertEquals(1, burger.ingredients.size());
        assertSame(ingredientMock, burger.ingredients.get(0));
    }

    @Test
    public void shouldRemoveIngredient() {
        Burger burger = new Burger();
        Ingredient ingredientMock = mock(Ingredient.class);
        burger.addIngredient(ingredientMock);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void shouldMoveIngredient() {
        Burger burger = new Burger();
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.moveIngredient(0, 1);
        assertSame(second, burger.ingredients.get(0));
        assertSame(first, burger.ingredients.get(1));
    }

    @Test
    public void shouldGenerateCorrectReceipt() {
        // Arrange
        Burger burger = new Burger();
        Bun bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("Black Bun");
        burger.setBuns(bunMock);

        Ingredient sauceMock = mock(Ingredient.class);
        when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceMock.getName()).thenReturn("Spicy Sauce");

        Ingredient fillingMock = mock(Ingredient.class);
        when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
        when(fillingMock.getName()).thenReturn("Beef Patty");

        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        when(bunMock.getPrice()).thenReturn(200f);
        when(sauceMock.getPrice()).thenReturn(100f);
        when(fillingMock.getPrice()).thenReturn(150f);

        // Act
        String receipt = burger.getReceipt();

        // Assert
        String expected = "(==== Black Bun ====)\n" +
                "= sauce Spicy Sauce =\n" +
                "= filling Beef Patty =\n" +
                "(==== Black Bun ====)\n\n" +
                "Price: 650.000000\n";
        assertEquals(expected, receipt);
    }
}
