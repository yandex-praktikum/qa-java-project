package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    // === Базовые операции ===

    @Test
    @DisplayName("Установка булочки в бургер")
    void testSetBuns() {
        when(mockBun.getName()).thenReturn("Белая булочка");
        burger.setBuns(mockBun);
        assertSame(mockBun, burger.bun);
    }

    @Test
    @DisplayName("Добавление ингредиента")
    void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size());
        assertSame(mockIngredient1, burger.ingredients.get(0));
    }

    // === Удаление ингредиентов ===

    @Test
    @DisplayName("Удаление ингредиента по индексу")
    void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.removeIngredient(1);

        assertEquals(2, burger.ingredients.size());
        assertSame(mockIngredient1, burger.ingredients.get(0));
        assertSame(mockIngredient3, burger.ingredients.get(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 10})
    @DisplayName("Удаление с неверным индексом - должно выбросить исключение")
    void testRemoveIngredientInvalidIndexThrowsException(int invalidIndex) {
        // Для пустого списка даже индекс 0 невалиден
        assertThrows(IndexOutOfBoundsException.class, () ->
                burger.removeIngredient(invalidIndex));

        // С одним элементом - индекс 0 валиден, другие нет
        burger.addIngredient(mockIngredient1);
        if (invalidIndex != 0) {
            assertThrows(IndexOutOfBoundsException.class, () ->
                    burger.removeIngredient(invalidIndex));
        }
    }

    // === Перемещение ингредиентов ===

    @Test
    @DisplayName("Перемещение ингредиента")
    void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(1, 0);

        assertEquals(3, burger.ingredients.size());
        assertSame(mockIngredient2, burger.ingredients.get(0));
        assertSame(mockIngredient1, burger.ingredients.get(1));
        assertSame(mockIngredient3, burger.ingredients.get(2));
    }

    @Test
    @DisplayName("Перемещение на тот же индекс - ничего не должно измениться")
    void testMoveIngredientToSameIndex() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.moveIngredient(0, 0);

        assertEquals(2, burger.ingredients.size());
        assertSame(mockIngredient1, burger.ingredients.get(0));
        assertSame(mockIngredient2, burger.ingredients.get(1));
    }

    @ParameterizedTest
    @MethodSource("provideInvalidMoveIndices")
    @DisplayName("Перемещение с неверным индексом - должно выбросить исключение")
    void testMoveIngredientInvalidIndexThrowsException(int fromIndex, int toIndex) {
        burger.addIngredient(mockIngredient1);
        assertThrows(IndexOutOfBoundsException.class, () ->
                burger.moveIngredient(fromIndex, toIndex));
    }

    private static Stream<Arguments> provideInvalidMoveIndices() {
        return Stream.of(
                Arguments.of(-1, 0),
                Arguments.of(10, 0),
                Arguments.of(0, 10),
                Arguments.of(0, -1)
        );
    }

    // === Расчет цены ===

    @ParameterizedTest
    @MethodSource("providePriceTestCases")
    @DisplayName("Расчет цены бургера в различных сценариях")
    void testGetPrice(float bunPrice, float[] ingredientPrices, float expectedTotal) {
        when(mockBun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(mockBun);

        for (float price : ingredientPrices) {
            Ingredient mockIngredient = mock(Ingredient.class);
            when(mockIngredient.getPrice()).thenReturn(price);
            burger.addIngredient(mockIngredient);
        }

        float actualTotal = burger.getPrice();
        assertEquals(expectedTotal, actualTotal, 0.001);
    }

    private static Stream<Arguments> providePriceTestCases() {
        return Stream.of(
                // Без ингредиентов
                Arguments.of(100.0f, new float[]{}, 200.0f), // 100 * 2 = 200
                Arguments.of(49.99f, new float[]{}, 99.98f), // 49.99 * 2 ≈ 99.98

                // С ингредиентами
                Arguments.of(50.0f, new float[]{100.0f, 30.0f}, 230.0f), // 100 + 100 + 30 = 230
                Arguments.of(49.99f, new float[]{0.0f, 150.5f}, 250.48f), // 99.98 + 0 + 150.5 = 250.48

                // Граничные случаи
                Arguments.of(0.0f, new float[]{100.0f}, 100.0f), // 0 + 100 = 100
                Arguments.of(25.5f, new float[]{0.0f, 0.0f, 0.0f}, 51.0f), // 51 + 0 = 51
                Arguments.of(99.99f, new float[]{0.01f}, 199.99f) // 199.98 + 0.01 = 199.99
        );
    }

    // === Генерация чека ===

    @Test
    @DisplayName("Получение чека для бургера без ингредиентов")
    void testGetReceiptWithoutIngredients() {
        when(mockBun.getName()).thenReturn("Белая булочка");
        when(mockBun.getPrice()).thenReturn(50.0f);
        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();
        String expectedReceipt = String.format("(==== %s ====)%n", "Белая булочка") +
                String.format("(==== %s ====)%n", "Белая булочка") +
                String.format("%nPrice: %f%n", 100.0f);

        assertEquals(expectedReceipt, receipt);
    }

    @Test
    @DisplayName("Получение чека для бургера с ингредиентами")
    void testGetReceiptWithIngredients() {
        when(mockBun.getName()).thenReturn("Черная булочка");
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredient1.getName()).thenReturn("котлета");
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient1.getPrice()).thenReturn(150.0f);
        when(mockIngredient2.getName()).thenReturn("соус spicy");
        when(mockIngredient2.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient2.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();
        String expectedReceipt = String.format("(==== %s ====)%n", "Черная булочка") +
                String.format("= filling котлета =%n") +
                String.format("= sauce соус spicy =%n") +
                String.format("(==== %s ====)%n", "Черная булочка") +
                String.format("%nPrice: %f%n", 400.0f);

        assertEquals(expectedReceipt, receipt);
    }

    @Test
    @DisplayName("Проверка формата цены в чеке")
    void testReceiptPriceFormat() {
        when(mockBun.getName()).thenReturn("Булочка");
        when(mockBun.getPrice()).thenReturn(100.5f);
        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();
        float expectedPrice = burger.getPrice();
        String expectedPriceFormat = String.format("%f", expectedPrice);

        assertTrue(receipt.contains(expectedPriceFormat),
                String.format("Чек должен содержать цену в формате %%f: %s", expectedPriceFormat));
    }
}