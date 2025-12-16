package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerGeneralTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(mockBun.getName()).thenReturn("general bun");
        when(mockBun.getPrice()).thenReturn(100f);

        when(mockIngredient.getPrice()).thenReturn(50f);
        when(mockIngredient.getName()).thenReturn("general ingredient");
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
    }

    @Test
    public void testGetReceiptNotNull() {
        burger.setBuns(mockBun);
        String receipt = burger.getReceipt();
        assertNotNull("Рецепт не должен быть null", receipt);
    }

    @Test
    public void testGetReceiptNotEmpty() {
        burger.setBuns(mockBun);
        String receipt = burger.getReceipt();
        assertFalse("Рецепт не должен быть пустым", receipt.isEmpty());
    }

    @Test
    public void testGetReceiptCompleteFormat() {
        // Устанавливаем конкретные значения для теста
        when(mockBun.getName()).thenReturn("Краторная булка N-200i");
        when(mockBun.getPrice()).thenReturn(1255f);

        when(mockIngredient.getName()).thenReturn("Соус фирменный Space Sauce");
        when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient.getPrice()).thenReturn(80f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String receipt = burger.getReceipt();
        receipt = normalizeReceipt(receipt);

        // Используем DecimalFormatSymbols для получения правильного десятичного разделителя
        char decimalSeparator = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
        String expectedReceipt =
                "(==== Краторная булка N-200i ====)\n" +
                        "= sauce Соус фирменный Space Sauce =\n" +
                        "(==== Краторная булка N-200i ====)\n\n" +
                        "Price: 2590" + decimalSeparator + "000000\n";

        expectedReceipt = normalizeReceipt(expectedReceipt);

        assertEquals("Рецепт должен полностью соответствовать ожидаемому формату",
                expectedReceipt, receipt);
    }

    @Test
    public void testMoveIngredientEdgeCaseSameIndexKeepsOrder() {
        burger.setBuns(mockBun);
        Ingredient ingredientFirst = mock(Ingredient.class);
        Ingredient ingredientSecond = mock(Ingredient.class);

        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);

        // Запоминаем исходный порядок
        Ingredient originalFirst = burger.ingredients.get(0);
        Ingredient originalSecond = burger.ingredients.get(1);

        // Перемещение на ту же позицию
        burger.moveIngredient(0, 0);

        // Используем assertArrayEquals для проверки всего списка
        assertArrayEquals("При перемещении на ту же позицию порядок не должен измениться",
                new Ingredient[]{originalFirst, originalSecond},
                burger.ingredients.toArray());
    }

    @Test
    public void testPriceCalculationWithMultipleIngredients() {
        burger.setBuns(mockBun);

        Ingredient ingredientOne = mock(Ingredient.class);
        when(ingredientOne.getPrice()).thenReturn(30f);

        Ingredient ingredientTwo = mock(Ingredient.class);
        when(ingredientTwo.getPrice()).thenReturn(70f);

        Ingredient ingredientThree = mock(Ingredient.class);
        when(ingredientThree.getPrice()).thenReturn(20f);

        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        burger.addIngredient(ingredientThree);

        float expectedPrice = 100f * 2 + 30f + 70f + 20f;
        float actualPrice = burger.getPrice();

        assertEquals("Цена должна корректно рассчитываться для нескольких ингредиентов",
                expectedPrice, actualPrice, 0.01f);
    }

    private String normalizeReceipt(String receipt) {
        return receipt.replace("\r\n", "\n").replace("\r", "\n");
    }
}