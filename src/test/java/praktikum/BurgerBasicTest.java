package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerBasicTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient sauceIngredient;

    @Mock
    private Ingredient fillingIngredient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(mockBun.getName()).thenReturn("test bun");
        when(mockBun.getPrice()).thenReturn(100f);

        when(sauceIngredient.getPrice()).thenReturn(50f);
        when(sauceIngredient.getName()).thenReturn("hot sauce");
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);

        when(fillingIngredient.getPrice()).thenReturn(75f);
        when(fillingIngredient.getName()).thenReturn("cutlet");
        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertSame(mockBun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        int initialSize = burger.ingredients.size();
        Ingredient newIngredient = mock(Ingredient.class);

        burger.addIngredient(newIngredient);

        assertEquals(initialSize + 1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientAddsCorrectIngredient() {
        Ingredient newIngredient = mock(Ingredient.class);

        burger.addIngredient(newIngredient);

        assertTrue(burger.ingredients.contains(newIngredient));
    }

    @Test
    public void testRemoveIngredient() {
        burger.setBuns(mockBun);
        Ingredient ingredientToRemove = mock(Ingredient.class);
        burger.addIngredient(ingredientToRemove);
        int indexToRemove = burger.ingredients.size() - 1;

        burger.removeIngredient(indexToRemove);

        assertFalse(burger.ingredients.contains(ingredientToRemove));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientWithInvalidIndex() {
        burger.removeIngredient(999);
    }

    @Test
    public void testMoveIngredientRemovesFromSourceIndex() {
        burger.setBuns(mockBun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.moveIngredient(0, 1);

        assertNotSame("Ингредиент должен быть перемещен с исходной позиции",
                sauceIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientPlacesToTargetIndex() {
        burger.setBuns(mockBun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.moveIngredient(0, 1);

        assertEquals("Ингредиент должен быть перемещен на целевую позицию",
                sauceIngredient, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientInvalidIndex() {
        burger.setBuns(mockBun);
        burger.addIngredient(mock(Ingredient.class));
        burger.moveIngredient(5, 0);
    }

    @Test
    public void testGetReceiptNotNullWhenBunSet() {
        when(mockBun.getName()).thenReturn("special bun");
        burger.setBuns(mockBun);
        String receipt = burger.getReceipt();
        assertNotNull("Рецепт не должен быть null", receipt);
    }

    @Test
    public void testGetReceiptWithRealIngredients() {
        // Создаем реальные объекты
        Bun realBun = new Bun("real bun", 100f);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);
        Ingredient filling = new Ingredient(IngredientType.FILLING, "cutlet", 75f);

        // Создаем бургер
        Burger realBurger = new Burger();
        realBurger.setBuns(realBun);
        realBurger.addIngredient(sauce);
        realBurger.addIngredient(filling);

        // Получаем рецепт
        String receipt = realBurger.getReceipt();
        receipt = normalizeReceipt(receipt);

        // Ожидаемый результат вычисляем вручную
        float expectedPrice = 100f * 2 + 50f + 75f; // 325f
        char decimalSeparator = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
        String expectedReceipt =
                "(==== real bun ====)\n" +
                        "= sauce hot sauce =\n" +
                        "= filling cutlet =\n" +
                        "(==== real bun ====)\n\n" +
                        "Price: 325" + decimalSeparator + "000000\n";

        expectedReceipt = normalizeReceipt(expectedReceipt);

        assertEquals("Рецепт должен полностью совпадать",
                expectedReceipt, receipt);
    }

    @Test
    public void testGetReceiptWithOnlySauce() {
        Bun realBun = new Bun("white bun", 150f);
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "sour cream", 30f);

        Burger realBurger = new Burger();
        realBurger.setBuns(realBun);
        realBurger.addIngredient(sauce);

        String receipt = realBurger.getReceipt();
        receipt = normalizeReceipt(receipt);

        float expectedPrice = 150f * 2 + 30f; // 330f
        char decimalSeparator = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
        String expectedReceipt =
                "(==== white bun ====)\n" +
                        "= sauce sour cream =\n" +
                        "(==== white bun ====)\n\n" +
                        "Price: 330" + decimalSeparator + "000000\n";

        expectedReceipt = normalizeReceipt(expectedReceipt);

        assertEquals("Рецепт с одним соусом должен полностью совпадать",
                expectedReceipt, receipt);
    }

    @Test
    public void testGetReceiptNoIngredients() {
        Bun realBun = new Bun("empty bun", 50f);

        Burger realBurger = new Burger();
        realBurger.setBuns(realBun);

        String receipt = realBurger.getReceipt();
        receipt = normalizeReceipt(receipt);

        float expectedPrice = 50f * 2; // 100f
        char decimalSeparator = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
        String expectedReceipt =
                "(==== empty bun ====)\n" +
                        "(==== empty bun ====)\n\n" +
                        "Price: 100" + decimalSeparator + "000000\n";

        expectedReceipt = normalizeReceipt(expectedReceipt);

        assertEquals("Рецепт без ингредиентов должен полностью совпадать",
                expectedReceipt, receipt);
    }

    @Test
    public void testGetReceiptCompleteFormat() {
        Bun realBun = new Bun("burger bun", 80f);
        Ingredient sauce1 = new Ingredient(IngredientType.SAUCE, "ketchup", 20f);
        Ingredient filling1 = new Ingredient(IngredientType.FILLING, "cheese", 40f);
        Ingredient sauce2 = new Ingredient(IngredientType.SAUCE, "mayo", 25f);

        Burger realBurger = new Burger();
        realBurger.setBuns(realBun);
        realBurger.addIngredient(sauce1);
        realBurger.addIngredient(filling1);
        realBurger.addIngredient(sauce2);

        String receipt = realBurger.getReceipt();
        receipt = normalizeReceipt(receipt);

        float expectedPrice = 80f * 2 + 20f + 40f + 25f; // 245f
        char decimalSeparator = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
        String expectedReceipt =
                "(==== burger bun ====)\n" +
                        "= sauce ketchup =\n" +
                        "= filling cheese =\n" +
                        "= sauce mayo =\n" +
                        "(==== burger bun ====)\n\n" +
                        "Price: 245" + decimalSeparator + "000000\n";

        expectedReceipt = normalizeReceipt(expectedReceipt);

        assertEquals("Полный рецепт должен полностью совпадать",
                expectedReceipt, receipt);
    }

    private String normalizeReceipt(String receipt) {
        return receipt.replace("\r\n", "\n").replace("\r", "\n");
    }
}