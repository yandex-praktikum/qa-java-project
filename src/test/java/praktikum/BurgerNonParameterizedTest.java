package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

// Отдельный класс для непараметризованных тестов Burger
public class BurgerNonParameterizedTest {

    protected Burger burger;

    @Mock
    protected Bun bun;

    @Mock
    protected Ingredient cutlet;

    @Mock
    protected Ingredient cheese;

    @Mock
    protected Ingredient mayonnaise;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Настраиваем моки
        when(bun.getName()).thenReturn("Чёрная булка");
        when(bun.getPrice()).thenReturn(100f);

        when(cutlet.getName()).thenReturn("Котлета");
        when(cutlet.getType()).thenReturn(IngredientType.FILLING);
        when(cutlet.getPrice()).thenReturn(50f);

        when(cheese.getName()).thenReturn("Сыр");
        when(cheese.getType()).thenReturn(IngredientType.FILLING);
        when(cheese.getPrice()).thenReturn(50f);

        when(mayonnaise.getName()).thenReturn("Майонез");
        when(mayonnaise.getType()).thenReturn(IngredientType.SAUCE);
        when(mayonnaise.getPrice()).thenReturn(50f);

        burger = new Burger();
    }

    // --- Тесты: один тест - одна проверка ---

    @Test
    public void testReceiptContainsBunName() {
        burger.setBuns(bun);
        burger.addIngredient(cutlet);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать название булки", receipt.contains("Чёрная булка"));
    }

    @Test
    public void testReceiptContainsIngredientName() {
        burger.setBuns(bun);
        burger.addIngredient(cutlet);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать ингредиент", receipt.contains("Котлета"));
    }

    @Test
    public void testReceiptContainsPriceLabel() {
        burger.setBuns(bun);
        burger.addIngredient(cutlet);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать метку цены", receipt.contains("Price:"));
    }

    @Test
    public void testReceiptContainsCorrectPriceValueForOneIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(cutlet);

        String receipt = burger.getReceipt();

        // Проверяем, что цена 250 присутствует в чеке
        assertTrue("Чек должен содержать цену 250", receipt.contains("250"));
    }

    // Разделение теста структуры чека на отдельные тесты
    @Test
    public void testReceiptStartsWithTopBun() {
        burger.setBuns(bun);
        burger.addIngredient(cutlet);

        String receipt = burger.getReceipt();
        String lineSep = System.lineSeparator();

        assertTrue("Чек должен начинаться с верхней булки", receipt.startsWith("(==== Чёрная булка ====)" + lineSep));
    }

    @Test
    public void testReceiptContainsIngredientLine() {
        burger.setBuns(bun);
        burger.addIngredient(cutlet);

        String receipt = burger.getReceipt();
        String lineSep = System.lineSeparator();

        assertTrue("Чек должен содержать строку ингредиента", receipt.contains("= filling Котлета =" + lineSep));
    }

    @Test
    public void testReceiptContainsBottomBunWithSpacing() {
        burger.setBuns(bun);
        burger.addIngredient(cutlet);

        String receipt = burger.getReceipt();
        String lineSep = System.lineSeparator();

        String expectedPattern = lineSep + "(==== Чёрная булка ====)" + lineSep;
        assertTrue("Чек должен содержать нижнюю булку с предшествующими пустыми строками",
                receipt.contains(expectedPattern));
    }


    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertNotNull("Булочка должна быть установлена", burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(cutlet);
        assertEquals("Должен быть добавлен один ингредиент", 1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(cutlet);
        burger.removeIngredient(0);
        assertEquals("Список ингредиентов должен быть пустым после удаления", 0, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientMovedElement() {
        burger.addIngredient(cutlet);
        burger.addIngredient(cheese);
        burger.addIngredient(mayonnaise);

        burger.moveIngredient(0, 1);

        assertEquals("Ингредиент cutlet должен быть перемещен на позицию 1", cutlet, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredientShiftedElement() {
        burger.addIngredient(cutlet);
        burger.addIngredient(cheese);
        burger.addIngredient(mayonnaise);

        burger.moveIngredient(0, 1);

        assertEquals("Ингредиент cheese должен переместиться на позицию 0", cheese, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientUnchangedElement() {
        burger.addIngredient(cutlet);
        burger.addIngredient(cheese);
        burger.addIngredient(mayonnaise);

        burger.moveIngredient(0, 1);

        assertEquals("Ингредиент mayonnaise должен остаться на позиции 2", mayonnaise, burger.ingredients.get(2));
    }

    @Test
    public void testBurgerWithBunOnlyPrice() {
        burger.setBuns(bun);
        assertEquals("Цена бургера только с булкой должна быть 2 * price булки", 200f, burger.getPrice(), 0.01);
    }

    @Test(expected = NullPointerException.class)
    public void testGetPriceWithoutBunThrowsException() {
        burger.getPrice();
    }

    @Test(expected = NullPointerException.class)
    public void testGetReceiptWithoutBunThrowsException() {
        burger.getReceipt();
    }

    @Test
    public void testEmptyBurgerReceiptContainsBunName() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать название булки", receipt.contains("Чёрная булка"));
    }

    @Test
    public void testEmptyBurgerReceiptContainsPriceLabel() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать цену", receipt.contains("Price:"));
    }

    @Test
    public void testEmptyBurgerReceiptContainsCorrectPriceValue() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();

        // Проверяем, что цена 200 присутствует в чеке
        assertTrue("Чек должен содержать цену 200", receipt.contains("200"));
    }

    @Test
    public void testReceiptContainsCorrectPriceValueGeneral() {
        burger.setBuns(bun);
        burger.addIngredient(cutlet);

        String receipt = burger.getReceipt();

        // Проверяем, что цена 250 присутствует в чеке
        assertTrue("Чек должен содержать цену 250", receipt.contains("250"));
    }
}