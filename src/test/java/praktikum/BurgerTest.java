package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.Collection;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockIngredient1;
    @Mock
    private Ingredient mockIngredient2;
    @Mock
    private Ingredient mockIngredient3;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBuns_SetsBunCorrectly() {
        burger.setBuns(mockBun);
        assertSame(mockBun, burger.bun);
    }

    @Test
    public void addIngredient_AddsIngredientToList() {
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size());
        assertSame(mockIngredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredient_RemovesIngredientAtIndex() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.removeIngredient(1);
        assertEquals(2, burger.ingredients.size());
        assertSame(mockIngredient1, burger.ingredients.get(0));
        assertSame(mockIngredient3, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredient_WithInvalidIndex_ThrowsException() {
        burger.removeIngredient(0);
    }

    @Test
    public void moveIngredient_MovesIngredientToNewPosition() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(0, 2);
        assertSame(mockIngredient2, burger.ingredients.get(0));
        assertSame(mockIngredient3, burger.ingredients.get(1));
        assertSame(mockIngredient1, burger.ingredients.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredient_WithInvalidFromIndex_ThrowsException() {
        burger.moveIngredient(0, 1);
    }

    @Test
    public void getPrice_WithMocks_ReturnsCorrectSum() {
        when(mockBun.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);

        when(mockIngredient1.getPrice()).thenReturn(50f);
        when(mockIngredient2.getPrice()).thenReturn(75f);

        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 100f * 2 + 50f + 75f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001);

        verify(mockBun, atLeastOnce()).getPrice();
        verify(mockIngredient1, atLeastOnce()).getPrice();
        verify(mockIngredient2, atLeastOnce()).getPrice();
    }

    @Test(expected = NullPointerException.class)
    public void getPrice_WithoutBun_ThrowsNullPointerException() {
        burger.addIngredient(mockIngredient1);
        burger.getPrice();
    }

    @Test
    public void getReceipt_WithMocks_ReturnsReceiptContainingBunAndIngredients() {
        when(mockBun.getName()).thenReturn("Black bun");
        when(mockBun.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);

        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("hot sauce");
        when(mockIngredient1.getPrice()).thenReturn(100f);

        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("cutlet");
        when(mockIngredient2.getPrice()).thenReturn(150f);

        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();

        // Проверяем, что чек содержит все необходимые элементы
        assertNotNull(receipt);
        assertFalse(receipt.isEmpty());
        assertTrue("Receipt should contain bun name", receipt.contains("Black bun"));
        assertTrue("Receipt should contain sauce", receipt.contains("sauce"));
        assertTrue("Receipt should contain hot sauce", receipt.contains("hot sauce"));
        assertTrue("Receipt should contain filling", receipt.contains("filling"));
        assertTrue("Receipt should contain cutlet", receipt.contains("cutlet"));
        assertTrue("Receipt should contain price label", receipt.contains("Price:"));
    }

    @Test
    public void getReceipt_WithNoIngredients_ReturnsReceiptWithOnlyBuns() {
        when(mockBun.getName()).thenReturn("White bun");
        when(mockBun.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();

        assertNotNull(receipt);
        assertFalse(receipt.isEmpty());
        assertTrue("Receipt should contain bun name", receipt.contains("White bun"));
        assertTrue("Receipt should contain price label", receipt.contains("Price:"));

        // Проверяем, что булка встречается два раза (верх и низ)
        int firstOccurrence = receipt.indexOf("(==== White bun ====)");
        int lastOccurrence = receipt.lastIndexOf("(==== White bun ====)");
        assertTrue("Bun should appear twice", firstOccurrence != lastOccurrence);
    }

    @Test
    public void getReceipt_WithRealObjects_ReturnsValidReceipt() {
        Bun realBun = new Bun("Black bun", 100f);
        Ingredient realIngredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        Ingredient realIngredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 150f);

        Burger realBurger = new Burger();
        realBurger.setBuns(realBun);
        realBurger.addIngredient(realIngredient1);
        realBurger.addIngredient(realIngredient2);

        String receipt = realBurger.getReceipt();

        assertNotNull(receipt);
        assertTrue(receipt.contains("Black bun"));
        assertTrue(receipt.contains("sauce"));
        assertTrue(receipt.contains("hot sauce"));
        assertTrue(receipt.contains("filling"));
        assertTrue(receipt.contains("cutlet"));
        assertTrue(receipt.contains("Price:"));
    }

    @Test
    public void moveIngredient_MovesFromFirstToLast() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(0, 2);

        assertSame(mockIngredient2, burger.ingredients.get(0));
        assertSame(mockIngredient3, burger.ingredients.get(1));
        assertSame(mockIngredient1, burger.ingredients.get(2));
    }

    @RunWith(Parameterized.class)
    public static class BurgerPriceParameterizedTest {
        private final float bunPrice;
        private final float ingredient1Price;
        private final float ingredient2Price;
        private final float expectedPrice;

        public BurgerPriceParameterizedTest(float bunPrice, float ingredient1Price,
                                            float ingredient2Price, float expectedPrice) {
            this.bunPrice = bunPrice;
            this.ingredient1Price = ingredient1Price;
            this.ingredient2Price = ingredient2Price;
            this.expectedPrice = expectedPrice;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {100f, 50f, 75f, 325f},
                    {200f, 100f, 100f, 600f},
                    {0f, 0f, 0f, 0f},
                    {150f, 30f, 20f, 350f},
                    {50f, 25f, 25f, 150f}
            });
        }

        @Test
        public void getPrice_CalculatesCorrectlyWithMocks() {
            Burger testBurger = new Burger();
            Bun bun = mock(Bun.class);
            Ingredient ing1 = mock(Ingredient.class);
            Ingredient ing2 = mock(Ingredient.class);

            when(bun.getPrice()).thenReturn(bunPrice);
            when(ing1.getPrice()).thenReturn(ingredient1Price);
            when(ing2.getPrice()).thenReturn(ingredient2Price);

            testBurger.setBuns(bun);
            testBurger.addIngredient(ing1);
            testBurger.addIngredient(ing2);

            assertEquals(expectedPrice, testBurger.getPrice(), 0.001);

            verify(bun).getPrice();
            verify(ing1).getPrice();
            verify(ing2).getPrice();
        }
    }
}