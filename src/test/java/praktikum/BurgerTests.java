package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@DisplayName("Тестирование класса Burger")
public class BurgerTests {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    private Burger burger;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("Установка булочек в бургер")
    void setBunsShouldSetBun() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun, "Булочка должна быть установлена правильно");
    }

    @Test
    @DisplayName("Добавление ингредиента в пустой бургер")
    void addIngredientEmptyBurgerCorrect() {
        burger.addIngredient(mockIngredient1);
        assertEquals(1, burger.ingredients.size(), "Должен быть добавлен один ингредиент");
        assertEquals(mockIngredient1, burger.ingredients.get(0), "Добавленный ингредиент должен соответствовать ожидаемому");
    }

    @Test
    @DisplayName("Добавление нескольких ингредиентов в бургер")
    void addIngredientManyIngredientsCorrect() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        assertEquals(3, burger.ingredients.size(), "Должны быть добавлены все три ингредиента");
        assertEquals(mockIngredient1, burger.ingredients.get(0), "Первый ингредиент должен быть на верном месте");
        assertEquals(mockIngredient2, burger.ingredients.get(1), "Второй ингредиент должен быть на верном месте");
        assertEquals(mockIngredient3, burger.ingredients.get(2), "Третий ингредиент должен быть на верном месте");
    }

    @Test
    @DisplayName("Удаление ингредиента по индексу")
    void removeIngredientByIndexRemoveCorrect() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.removeIngredient(1); // Удаляем второй ингредиент

        assertEquals(2, burger.ingredients.size(), "После удаления должно остаться два ингредиента");
        assertEquals(mockIngredient1, burger.ingredients.get(0), "Первый ингредиент должен остаться на месте");
        assertEquals(mockIngredient3, burger.ingredients.get(1), "Третий ингредиент должен сместиться на позицию второго");
    }

    @Test
    @DisplayName("Перемещение ингредиента вверх по списку")
    void moveIngredientUpReorderCorrect() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(2, 0); // Перемещаем третий ингредиент в начало

        assertEquals(3, burger.ingredients.size(), "Количество ингредиентов не должно измениться");
        assertEquals(mockIngredient3, burger.ingredients.get(0), "Третий ингредиент должен стать первым");
        assertEquals(mockIngredient1, burger.ingredients.get(1), "Первый ингредиент должен стать вторым");
        assertEquals(mockIngredient2, burger.ingredients.get(2), "Второй ингредиент должен стать третьим");
    }

    @Test
    @DisplayName("Перемещение ингредиента вниз по списку")
    void moveIngredientDownReorderCorrect() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        burger.moveIngredient(0, 2); // Перемещаем первый ингредиент в конец

        assertEquals(3, burger.ingredients.size(), "Количество ингредиентов не должно измениться");
        assertEquals(mockIngredient2, burger.ingredients.get(0), "Второй ингредиент должен стать первым");
        assertEquals(mockIngredient3, burger.ingredients.get(1), "Третий ингредиент должен стать вторым");
        assertEquals(mockIngredient1, burger.ingredients.get(2), "Первый ингредиент должен стать третьим");
    }


    @Test
    @DisplayName("Расчет цены бургера без ингредиентов")
    void getPrice_EmptyBurger_ShouldCalculateBunPriceOnly() {

        when(mockBun.getPrice()).thenReturn(100f);
        burger.setBuns(mockBun);

        float price = burger.getPrice();

        verify(mockBun, times(1)).getPrice();
        assertEquals(200f, price, "Цена должна быть равна цене двух булочек");
    }

    @Test
    @DisplayName("Расчет цены бургера с ингредиентами")
    void getPriceBurgerWithIngredientsShouldTotalPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        when(mockBun.getPrice()).thenReturn(50f);
        when(mockIngredient1.getPrice()).thenReturn(30f);
        when(mockIngredient2.getPrice()).thenReturn(20f);

        float price = burger.getPrice();

        assertEquals(150f, price,"Цена должна быть суммой цен булочек и ингредиентов");
        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
    }

    @Test
    @DisplayName("Генерация чека с ингредиентами")
    void getReceiptShouldFormatCorrect() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        when(mockBun.getName()).thenReturn("Белая булочка");
        when(mockBun.getPrice()).thenReturn(50f);
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient1.getName()).thenReturn("Котлета");
        when(mockIngredient1.getPrice()).thenReturn(150f);

        String receipt = burger.getReceipt();

        assertAll(
                () -> assertTrue(receipt.contains("(==== Белая булочка ====)")),
                () -> assertTrue(receipt.contains("= filling Котлета =")),
                () -> assertTrue(receipt.contains("Price: 250"))
        );
    }

    @Test
    @DisplayName("Генерация чека без ингредиентов")
    void getReceiptEmptyBurgerShouldGenerateBasicReceipt() {
        burger.setBuns(mockBun);
        when(mockBun.getName()).thenReturn("Черная булочка");
        when(mockBun.getPrice()).thenReturn(100f);

        String receipt = burger.getReceipt();

        assertAll(
                () -> assertTrue(receipt.startsWith("(==== Черная булочка ====)")),
                () -> assertTrue(receipt.contains("Price: 200"))
        );
    }


}
