package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Enclosed.class)
public class BurgerTest {

    // ========== ТЕСТЫ КОНСТРУКТОРА ==========
    public static class ConstructorTest {
        private Burger burger;

        @Before
        public void setUp() {
            burger = new Burger();
        }

        @Test
        public void shouldCreateEmptyIngredientsList() {
            assertTrue(burger.ingredients.isEmpty());
        }

        @Test
        public void shouldSetBunToNull() {
            assertNull(burger.bun);
        }
    }

    // ========== ТЕСТЫ setBuns ==========
    public static class SetBunsTest {
        private Burger burger;
        @Mock private Bun mockBun;

        @Before
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            burger = new Burger();
        }

        @Test
        public void shouldAssignBun() {
            burger.setBuns(mockBun);

            assertEquals(mockBun, burger.bun);
        }
    }

    // ========== ТЕСТЫ addIngredient ==========
    public static class AddIngredientTest {
        private Burger burger;
        @Mock private Ingredient mockSauce;

        @Before
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            burger = new Burger();
        }

        @Test
        public void shouldIncreaseIngredientsSize() {
            burger.addIngredient(mockSauce);

            assertEquals(1, burger.ingredients.size());
        }

        @Test
        public void shouldAddIngredientToTheEndOfList() {
            burger.addIngredient(mockSauce);

            assertEquals(mockSauce, burger.ingredients.get(0));
        }

        @Test
        public void shouldAddIngredientAtCorrectPosition_whenMultipleIngredients() {
            Ingredient mockFilling = mock(Ingredient.class);
            burger.addIngredient(mockSauce);
            burger.addIngredient(mockFilling);

            assertEquals(mockFilling, burger.ingredients.get(1));
        }
    }

    // ========== ТЕСТЫ removeIngredient ==========
    public static class RemoveIngredientTest {
        private Burger burger;
        @Mock private Ingredient mockSauce;
        @Mock private Ingredient mockFilling;

        @Before
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            burger = new Burger();
            burger.addIngredient(mockSauce);
            burger.addIngredient(mockFilling);
        }

        @Test
        public void shouldDecreaseIngredientsSize() {
            burger.removeIngredient(0);

            assertEquals(1, burger.ingredients.size());
        }

        @Test
        public void shouldRemoveIngredientAtSpecifiedIndex() {
            burger.removeIngredient(0);

            assertEquals(mockFilling, burger.ingredients.get(0));
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowException_whenIndexIsInvalid() {
            burger.removeIngredient(99);
        }
    }

    // ========== ТЕСТЫ moveIngredient ==========
    public static class MoveIngredientTest {
        private Burger burger;
        @Mock private Ingredient mockSauce;
        @Mock private Ingredient mockFilling;

        @Before
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            burger = new Burger();
            burger.addIngredient(mockSauce);
            burger.addIngredient(mockFilling);
        }

        @Test
        public void shouldMoveIngredientToNewPosition() {
            burger.moveIngredient(1, 0);

            assertEquals(mockFilling, burger.ingredients.get(0));
        }

        @Test
        public void shouldNotChangeOrder_whenMovingToSamePosition() {
            burger.moveIngredient(0, 0);

            assertEquals(mockSauce, burger.ingredients.get(0));
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowException_whenFromIndexIsInvalid() {
            burger.moveIngredient(99, 0);
        }

        @Test(expected = IndexOutOfBoundsException.class)
        public void shouldThrowException_whenToIndexIsInvalid() {
            burger.moveIngredient(0, 99);
        }
    }

    // ========== ТЕСТЫ getPrice ==========
    public static class GetPriceTest {
        private Burger burger;
        @Mock private Bun mockBun;

        @Before
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            burger = new Burger();
        }

        @Test
        public void shouldReturnDoubleBunPrice_whenNoIngredients() {
            when(mockBun.getPrice()).thenReturn(150.0f);
            burger.setBuns(mockBun);

            float actualPrice = burger.getPrice();

            assertEquals(300.0f, actualPrice, 0.01);
        }

        @Test
        public void shouldIncludeIngredientPriceInTotal() {
            when(mockBun.getPrice()).thenReturn(100.0f);
            Ingredient mockSauce = mock(Ingredient.class);
            when(mockSauce.getPrice()).thenReturn(50.0f);
            burger.setBuns(mockBun);
            burger.addIngredient(mockSauce);

            float actualPrice = burger.getPrice();

            assertEquals(250.0f, actualPrice, 0.01);
        }

        @Test
        public void shouldIncludeAllIngredientsPrices() {
            when(mockBun.getPrice()).thenReturn(100.0f);
            Ingredient mockSauce = mock(Ingredient.class);
            Ingredient mockFilling = mock(Ingredient.class);
            when(mockSauce.getPrice()).thenReturn(50.0f);
            when(mockFilling.getPrice()).thenReturn(30.0f);
            burger.setBuns(mockBun);
            burger.addIngredient(mockSauce);
            burger.addIngredient(mockFilling);

            float actualPrice = burger.getPrice();

            assertEquals(280.0f, actualPrice, 0.01);
        }

        @Test
        public void shouldCallBunGetPriceMethod() {
            burger.setBuns(mockBun);

            burger.getPrice();

            verify(mockBun).getPrice();
        }
    }

    // ========== ПАРАМЕТРИЗОВАННЫЕ ТЕСТЫ getPrice ==========
    @RunWith(Parameterized.class)
    public static class GetPriceParameterizedTest {
        private final float bunPrice;
        private final List<Float> ingredientPrices;
        private final float expectedPrice;

        public GetPriceParameterizedTest(float bunPrice, List<Float> ingredientPrices, float expectedPrice) {
            this.bunPrice = bunPrice;
            this.ingredientPrices = ingredientPrices;
            this.expectedPrice = expectedPrice;
        }

        @Parameterized.Parameters(name = "bun={0}, ingredients={1} => {2}")
        public static Iterable<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {100.0f, Arrays.asList(50.0f, 30.0f), 280.0f},
                    {50.0f, Arrays.asList(10.0f, 20.0f, 30.0f), 160.0f},
                    {200.0f, Arrays.asList(), 400.0f},
                    {0.0f, Arrays.asList(10.0f, 20.0f), 30.0f},
            });
        }

        @Test
        public void shouldCalculateCorrectPrice() {
            Burger burger = new Burger();
            Bun mockBun = mock(Bun.class);
            when(mockBun.getPrice()).thenReturn(bunPrice);
            burger.setBuns(mockBun);

            for (Float price : ingredientPrices) {
                Ingredient mockIngredient = mock(Ingredient.class);
                when(mockIngredient.getPrice()).thenReturn(price);
                burger.addIngredient(mockIngredient);
            }

            float actualPrice = burger.getPrice();

            assertEquals(expectedPrice, actualPrice, 0.01);
        }
    }

    // ========== ТЕСТЫ getReceipt ==========
    public static class GetReceiptTest {
        private Burger burger;
        @Mock private Bun mockBun;

        @Before
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            burger = new Burger();
            when(mockBun.getName()).thenReturn("black bun");
            when(mockBun.getPrice()).thenReturn(100.0f);
            burger.setBuns(mockBun);
        }

        @Test
        public void shouldIncludeBunNameInReceipt() {
            String receipt = burger.getReceipt();

            assertTrue(receipt.contains("black bun"));
        }

        @Test
        public void shouldIncludeSauceIngredientInReceipt() {
            Ingredient mockSauce = mock(Ingredient.class);
            when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
            when(mockSauce.getName()).thenReturn("hot sauce");
            burger.addIngredient(mockSauce);

            String receipt = burger.getReceipt();

            assertTrue(receipt.contains("= sauce hot sauce ="));
        }

        @Test
        public void shouldIncludeFillingIngredientInReceipt() {
            Ingredient mockFilling = mock(Ingredient.class);
            when(mockFilling.getType()).thenReturn(IngredientType.FILLING);
            when(mockFilling.getName()).thenReturn("cutlet");
            burger.addIngredient(mockFilling);

            String receipt = burger.getReceipt();

            assertTrue(receipt.contains("= filling cutlet ="));
        }

        @Test
        public void shouldIncludeTotalPriceInReceipt() {
            String receipt = burger.getReceipt();

            assertTrue(receipt.contains("Price: 200,000000"));
        }

        @Test
        public void shouldHaveFourLines_whenNoIngredients() {
            String[] lines = burger.getReceipt().split("\\n");

            assertEquals(4, lines.length);
        }

    }

    // ========== ИНТЕГРАЦИОННЫЕ ТЕСТЫ ==========
    public static class IntegrationTest {

        @Test
        public void shouldCalculatePriceCorrectly_withRealObjects() {
            Burger burger = new Burger();
            Bun bun = new Bun("test bun", 100);
            Ingredient sauce = new Ingredient(IngredientType.SAUCE, "test sauce", 50);

            burger.setBuns(bun);
            burger.addIngredient(sauce);

            float actualPrice = burger.getPrice();

            assertEquals(250.0f, actualPrice, 0.01);
        }

        @Test
        public void shouldIncludeBunNameInReceipt_withRealObjects() {
            Burger burger = new Burger();
            Bun bun = new Bun("test bun", 100);

            burger.setBuns(bun);

            assertTrue(burger.getReceipt().contains("test bun"));
        }

        @Test
        public void shouldIncludeIngredientInReceipt_withRealObjects() {
            Burger burger = new Burger();
            Bun bun = new Bun("test bun", 100);
            Ingredient sauce = new Ingredient(IngredientType.SAUCE, "test sauce", 50);

            burger.setBuns(bun);
            burger.addIngredient(sauce);

            assertTrue(burger.getReceipt().contains("sauce test sauce"));
        }
    }
}