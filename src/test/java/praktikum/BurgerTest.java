package praktikum;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для класса Burger (с моками)")
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

    @BeforeEach
    public void setUp() {
        burger = new Burger();
    }

    // ==================== ТЕСТЫ ДЛЯ setBuns ====================

    @Test
    @DisplayName("setBuns: булочка устанавливается корректно")
    public void testSetBuns() {
        burger.setBuns(mockBun);

        // Проверяем, что ссылка установлена
        Assertions.assertSame(mockBun, burger.bun);
    }

    @Test
    @DisplayName("setBuns: перезапись булочки")
    public void testSetBunsOverwritesPreviousBun() {
        // Создаём моки для двух булочек
        Bun mockBun1 = mock(Bun.class);
        Bun mockBun2 = mock(Bun.class);

        // Устанавливаем первую булочку
        burger.setBuns(mockBun1);
        Assertions.assertSame(mockBun1, burger.bun);

        // Устанавливаем вторую булочку (перезаписываем)
        burger.setBuns(mockBun2);
        Assertions.assertSame(mockBun2, burger.bun);

        // Проверяем, что метод getName() НЕ вызывался ни для одной булочки
        verify(mockBun1, never()).getName();
        verify(mockBun2, never()).getName();
    }

    // ==================== ТЕСТЫ ДЛЯ addIngredient ====================

    @Test
    @DisplayName("addIngredient: добавление одного ингредиента")
    public void testAddSingleIngredient() {
        burger.addIngredient(mockIngredient1);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, burger.ingredients.size()),
                () -> Assertions.assertSame(mockIngredient1, burger.ingredients.get(0))
        );
    }

    @Test
    @DisplayName("addIngredient: добавление нескольких ингредиентов")
    public void testAddMultipleIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        Assertions.assertAll(
                () -> Assertions.assertEquals(3, burger.ingredients.size()),
                () -> Assertions.assertSame(mockIngredient1, burger.ingredients.get(0)),
                () -> Assertions.assertSame(mockIngredient2, burger.ingredients.get(1)),
                () -> Assertions.assertSame(mockIngredient3, burger.ingredients.get(2))
        );
    }

    @Test
    @DisplayName("addIngredient: добавление одинаковых ингредиентов (разные объекты)")
    public void testAddDuplicateIngredients() {
        Ingredient mockDuplicate = mock(Ingredient.class);

        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockDuplicate);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, burger.ingredients.size()),
                () -> Assertions.assertNotSame(mockIngredient1, mockDuplicate)
        );
    }

    // ==================== ТЕСТЫ ДЛЯ removeIngredient ====================

    @Test
    @DisplayName("removeIngredient: удаление первого ингредиента")
    public void testRemoveFirstIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.removeIngredient(0);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, burger.ingredients.size()),
                () -> Assertions.assertSame(mockIngredient2, burger.ingredients.get(0)),
                () -> Assertions.assertSame(mockIngredient3, burger.ingredients.get(1)),
                () -> Assertions.assertFalse(burger.ingredients.contains(mockIngredient1))
        );
    }

    @Test
    @DisplayName("removeIngredient: удаление ингредиента из середины")
    public void testRemoveMiddleIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.removeIngredient(1);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, burger.ingredients.size()),
                () -> Assertions.assertSame(mockIngredient1, burger.ingredients.get(0)),
                () -> Assertions.assertSame(mockIngredient3, burger.ingredients.get(1)),
                () -> Assertions.assertFalse(burger.ingredients.contains(mockIngredient2))
        );
    }

    @Test
    @DisplayName("removeIngredient: удаление последнего ингредиента")
    public void testRemoveLastIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.removeIngredient(2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(2, burger.ingredients.size()),
                () -> Assertions.assertSame(mockIngredient1, burger.ingredients.get(0)),
                () -> Assertions.assertSame(mockIngredient2, burger.ingredients.get(1)),
                () -> Assertions.assertFalse(burger.ingredients.contains(mockIngredient3))
        );
    }

    @Test
    @DisplayName("removeIngredient: удаление из пустого списка - исключение")
    public void testRemoveFromEmptyList() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.removeIngredient(0);
        });
    }

    @Test
    @DisplayName("removeIngredient: удаление с невалидным индексом - исключение")
    public void testRemoveWithInvalidIndex() {
        burger.addIngredient(mockIngredient1);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.removeIngredient(5);
        });
    }

    // ==================== ТЕСТЫ ДЛЯ moveIngredient ====================

    @Test
    @DisplayName("moveIngredient: перемещение первого ингредиента в центр")
    public void testMoveFirstIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(0, 1);

        Assertions.assertAll(
                () -> Assertions.assertEquals(3, burger.ingredients.size()),
                () -> Assertions.assertSame(mockIngredient2, burger.ingredients.get(0)),
                () -> Assertions.assertSame(mockIngredient1, burger.ingredients.get(1)),
                () -> Assertions.assertSame(mockIngredient3, burger.ingredients.get(2))
        );
    }

    @Test
    @DisplayName("moveIngredient: перемещение ингредиента из середины")
    public void testMoveIngredientFromMiddle() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(1, 2);

        Assertions.assertAll(
                () -> Assertions.assertEquals(3, burger.ingredients.size()),
                () -> Assertions.assertSame(mockIngredient1, burger.ingredients.get(0)),
                () -> Assertions.assertSame(mockIngredient3, burger.ingredients.get(1)),
                () -> Assertions.assertSame(mockIngredient2, burger.ingredients.get(2))
        );
    }

    @Test
    @DisplayName("moveIngredient: перемещение последнего ингредиента в начало")
    public void testMoveLastIngredientToFirst() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(2, 0);

        Assertions.assertAll(
                () -> Assertions.assertEquals(3, burger.ingredients.size()),
                () -> Assertions.assertSame(mockIngredient3, burger.ingredients.get(0)),
                () -> Assertions.assertSame(mockIngredient1, burger.ingredients.get(1)),
                () -> Assertions.assertSame(mockIngredient2, burger.ingredients.get(2))
        );
    }

    @Test
    @DisplayName("moveIngredient: перемещение с невалидным индексом - исключение")
    public void testMoveWithInvalidIndex() {
        burger.addIngredient(mockIngredient1);

        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            burger.moveIngredient(5, 0);
        });
    }

    // ==================== ТЕСТЫ ДЛЯ getPrice ====================

    @Test
    @DisplayName("getPrice: цена = цена булочки * 2 (без ингредиентов)")
    public void testGetPriceOnlyBun() {
        when(mockBun.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);

        float price = burger.getPrice();

        Assertions.assertEquals(200f, price, 0.001);
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    @DisplayName("getPrice: цена = цена булочки * 2 + цена ингредиента")
    public void testGetPriceWithOneIngredient() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getPrice()).thenReturn(50f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        float price = burger.getPrice();

        Assertions.assertEquals(250f, price, 0.001);
        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
    }

    @Test
    @DisplayName("getPrice: цена с несколькими ингредиентами")
    public void testGetPriceWithMultipleIngredients() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getPrice()).thenReturn(50f);
        when(mockIngredient2.getPrice()).thenReturn(75f);
        when(mockIngredient3.getPrice()).thenReturn(25f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        float price = burger.getPrice();

        Assertions.assertEquals(350f, price, 0.001); // 200 + 50 + 75 + 25 = 350

        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
        verify(mockIngredient3, times(1)).getPrice();
    }

    // ==================== ТЕСТЫ ДЛЯ getReceipt ====================

    @Test
    @DisplayName("getReceipt: чек с только булочкой")
    public void testGetReceiptOnlyBun() {
        when(mockBun.getName()).thenReturn("test bun");
        when(mockBun.getPrice()).thenReturn(100f);

        burger.setBuns(mockBun);

        String receipt = burger.getReceipt();
        String expected = String.format(
                "(==== test bun ====)%n" +
                        "(==== test bun ====)%n" +
                        "%nPrice: 200,000000%n"
        );

        Assertions.assertEquals(expected, receipt);
        verify(mockBun, times(2)).getName();
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    @DisplayName("getReceipt: чек с булочкой и одним ингредиентом")
    public void testGetReceiptWithOneIngredient() {
        when(mockBun.getName()).thenReturn("test bun");
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("test sauce");
        when(mockIngredient1.getPrice()).thenReturn(50f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String receipt = burger.getReceipt();
        String expected = String.format(
                "(==== test bun ====)%n" +
                        "= sauce test sauce =%n" +
                        "(==== test bun ====)%n" +
                        "%nPrice: 250,000000%n"
        );

        Assertions.assertEquals(expected, receipt);
        verify(mockIngredient1, atLeastOnce()).getType();
        verify(mockIngredient1, atLeastOnce()).getName();
    }

    @Test
    @DisplayName("getReceipt: чек с несколькими ингредиентами")
    public void testGetReceiptWithMultipleIngredients() {
        when(mockBun.getName()).thenReturn("test bun");
        when(mockBun.getPrice()).thenReturn(100f);

        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("sauce 1");
        when(mockIngredient1.getPrice()).thenReturn(50f);

        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getName()).thenReturn("filling 1");
        when(mockIngredient2.getPrice()).thenReturn(75f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();
        String expected = String.format(
                "(==== test bun ====)%n" +
                        "= sauce sauce 1 =%n" +
                        "= filling filling 1 =%n" +
                        "(==== test bun ====)%n" +
                        "%nPrice: 325,000000%n"
        );

        Assertions.assertEquals(expected, receipt);
    }
}