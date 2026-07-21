package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    // ===== ТЕСТЫ ДЛЯ setBuns() и addIngredient() =====
    @Test
    public void testSetBunsAndAddIngredient() {
        Burger burger = new Burger();

        when(mockBun.getPrice()).thenReturn(2.0f);
        when(mockIngredient1.getPrice()).thenReturn(5.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        float expectedPrice = 2.0f * 2 + 5.0f; // 4 + 5 = 9
        assertEquals("Цена бургера должна быть 9", expectedPrice, burger.getPrice(), 0.001f);
    }

    // ===== ТЕСТЫ ДЛЯ removeIngredient() =====
    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();

        when(mockBun.getPrice()).thenReturn(2.0f);
        when(mockIngredient1.getPrice()).thenReturn(5.0f);
        when(mockIngredient2.getPrice()).thenReturn(3.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Проверяем цену до удаления
        float priceBefore = 2.0f * 2 + 5.0f + 3.0f; // 4 + 5 + 3 = 12
        assertEquals(priceBefore, burger.getPrice(), 0.001f);

        // Удаляем первый ингредиент
        burger.removeIngredient(0);

        // Проверяем цену после удаления
        float priceAfter = 2.0f * 2 + 3.0f; // 4 + 3 = 7
        assertEquals(priceAfter, burger.getPrice(), 0.001f);
    }

    // ===== ТЕСТЫ ДЛЯ moveIngredient() =====
    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();

        when(mockBun.getName()).thenReturn("Булочка");
        when(mockBun.getPrice()).thenReturn(1.0f);
        when(mockIngredient1.getName()).thenReturn("Соус 1");
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getPrice()).thenReturn(1.0f);
        when(mockIngredient2.getName()).thenReturn("Начинка 2");
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getPrice()).thenReturn(2.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        // Приводим к Unix-формату
        String actualBefore = burger.getReceipt().replace("\r\n", "\n");
        String expectedBefore = "(==== Булочка ====)\n" +
                "= sauce Соус 1 =\n" +
                "= filling Начинка 2 =\n" +
                "(==== Булочка ====)\n" +
                "\n" +
                "Price: 5,000000\n";
        assertEquals(expectedBefore, actualBefore);

        burger.moveIngredient(0, 1);

        String actualAfter = burger.getReceipt().replace("\r\n", "\n");
        String expectedAfter = "(==== Булочка ====)\n" +
                "= filling Начинка 2 =\n" +
                "= sauce Соус 1 =\n" +
                "(==== Булочка ====)\n" +
                "\n" +
                "Price: 5,000000\n";
        assertEquals(expectedAfter, actualAfter);
    }

    // ===== ТЕСТЫ ДЛЯ getReceipt() =====
    @Test
    public void testGetReceipt() {
        Burger burger = new Burger();

        when(mockBun.getName()).thenReturn("Крафтовая булочка");
        when(mockBun.getPrice()).thenReturn(2.0f);
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getName()).thenReturn("Чесночный соус");
        when(mockIngredient1.getPrice()).thenReturn(5.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        String actual = burger.getReceipt().replace("\r\n", "\n");
        String expected = "(==== Крафтовая булочка ====)\n" +
                "= sauce Чесночный соус =\n" +
                "(==== Крафтовая булочка ====)\n" +
                "\n" +
                "Price: 9,000000\n";

        assertEquals(expected, actual);
    }
    // ===== ТЕСТЫ ДЛЯ getPrice() (дополнительный) =====
    @Test
    public void testGetPriceWithMultipleIngredients() {
        Burger burger = new Burger();

        when(mockBun.getPrice()).thenReturn(2.0f);
        when(mockIngredient1.getPrice()).thenReturn(1.0f);
        when(mockIngredient2.getPrice()).thenReturn(2.0f);
        when(mockIngredient3.getPrice()).thenReturn(3.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        float expectedPrice = 2.0f * 2 + 1.0f + 2.0f + 3.0f; // 4 + 1 + 2 + 3 = 10
        assertEquals("Цена бургера должна быть 10", expectedPrice, burger.getPrice(), 0.001f);
    }
}