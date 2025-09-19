package practicum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.practicum.Burger;
import ru.practicum.Bun;
import ru.practicum.Ingredient;
import ru.practicum.IngredientType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

/**
 * Базовый тестовый класс для проверки функциональности класса Burger.
 * Содержит тесты, которые должны запускаться один раз без параметризации.
 */
public class BurgerBaseTest {

    protected Burger burger;
    protected Bun bun;
    protected Ingredient sauce;
    protected Ingredient filling;

    /**
     * Настройка тестового окружения перед каждым тестом.
     * Создает моки для булочки и ингредиентов.
     */
    @Before
    public void setUp() {
        burger = new Burger();
        bun = Mockito.mock(Bun.class);
        sauce = Mockito.mock(Ingredient.class);
        filling = Mockito.mock(Ingredient.class);

        // Настройка мока булочки
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        Mockito.when(bun.getName()).thenReturn("white bun");

        // Настройка мока соуса
        Mockito.when(sauce.getPrice()).thenReturn(50.0f);
        Mockito.when(sauce.getName()).thenReturn("hot sauce");
        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);

        // Настройка мока начинки
        Mockito.when(filling.getPrice()).thenReturn(100.0f);
        Mockito.when(filling.getName()).thenReturn("cutlet");
        Mockito.when(filling.getType()).thenReturn(IngredientType.FILLING);
    }

    /**
     * Тест проверяет корректность установки булочки в бургер.
     */
    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals("Булочка должна быть установлена", bun, burger.bun);
    }

    /**
     * Тест проверяет увеличение размера списка ингредиентов после добавления.
     */
    @Test
    public void testAddIngredientIncreasesSize() {
        int initialSize = burger.ingredients.size();
        burger.addIngredient(sauce);
        assertEquals("Размер списка должен увеличиться на 1", initialSize + 1, burger.ingredients.size());
    }

    /**
     * Тест проверяет, что добавленный ингредиент присутствует в списке.
     */
    @Test
    public void testAddIngredientContainsAdded() {
        burger.addIngredient(sauce);
        assertEquals("Добавленный ингредиент должен быть в списке", sauce, burger.ingredients.get(0));
    }

    /**
     * Тест проверяет уменьшение размера списка после удаления ингредиента.
     */
    @Test
    public void testRemoveIngredientDecreasesSize() {
        burger.addIngredient(sauce);
        int initialSize = burger.ingredients.size();
        burger.removeIngredient(0);
        assertEquals("Размер списка должен уменьшиться на 1", initialSize - 1, burger.ingredients.size());
    }

    /**
     * Тест проверяет корректность удаления конкретного ингредиента.
     */
    @Test
    public void testRemoveIngredientRemovesCorrect() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        assertEquals("После удаления должен остаться второй ингредиент", filling, burger.ingredients.get(0));
    }

    /**
     * Тест проверяет изменение позиции ингредиента после перемещения.
     */
    @Test
    public void testMoveIngredientChangesPosition() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(1, 0);
        assertEquals("Ингредиент должен быть перемещен на новую позицию", filling, burger.ingredients.get(0));
    }

    /**
     * Тест проверяет сдвиг остальных ингредиентов после перемещения.
     */
    @Test
    public void testMoveIngredientShiftsOther() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(1, 0);
        assertEquals("Остальные ингредиенты должны быть сдвинуты", sauce, burger.ingredients.get(1));
    }

    /**
     * Тест проверяет наличие названия булочки в рецепте.
     */
    @Test
    public void testReceiptContainsBunName() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertThat("Рецепт должен содержать название булочки", receipt.contains(bun.getName()), equalTo(true));
    }

    /**
     * Тест проверяет наличие типа ингредиента в рецепте.
     */
    @Test
    public void testReceiptContainsIngredientType() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        String receipt = burger.getReceipt();
        assertThat("Рецепт должен содержать тип ингредиента",
                receipt.contains(sauce.getType().name().toLowerCase()), equalTo(true));
    }

    /**
     * Тест проверяет наличие названия ингредиента в рецепте.
     */
    @Test
    public void testReceiptContainsIngredientName() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        String receipt = burger.getReceipt();
        assertThat("Рецепт должен содержать название ингредиента",
                receipt.contains(sauce.getName()), equalTo(true));
    }

    /**
     * Тест проверяет наличие общей цены в рецепте.
     */
    @Test
    public void testReceiptContainsTotalPrice() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);

        String receipt = burger.getReceipt();
        float expectedPrice = bun.getPrice() * 2 + sauce.getPrice();
        assertThat("Рецепт должен содержать общую цену",
                receipt.contains(String.format("%.6f", expectedPrice)), equalTo(true));
    }

    /**
     * Тест проверяет расчет цены только с булочкой.
     */
    @Test
    public void testPriceWithBunOnly() {
        burger.setBuns(bun);
        float expectedPrice = bun.getPrice() * 2;
        assertThat("Цена только с булочкой должна быть корректной", burger.getPrice(), equalTo(expectedPrice));
    }

    /**
     * Тест проверяет расчет цены с одним ингредиентом.
     */
    @Test
    public void testPriceWithSingleIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        float expectedPrice = bun.getPrice() * 2 + sauce.getPrice();
        assertThat("Цена с одним ингредиентом должна быть корректной", burger.getPrice(), equalTo(expectedPrice));
    }

    /**
     * Тест проверяет расчет цены с несколькими ингредиентами.
     */
    @Test
    public void testPriceWithMultipleIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        float expectedPrice = bun.getPrice() * 2 + sauce.getPrice() + filling.getPrice();
        assertThat("Цена с несколькими ингредиентами должна быть корректной", burger.getPrice(), equalTo(expectedPrice));
    }

    /**
     * Тест проверяет начало рецепта с информации о булочке.
     */
    @Test
    public void testReceiptStartsWithBun() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertThat("Рецепт должен начинаться с булочки",
                receipt.startsWith("(==== " + bun.getName() + " ====)"), equalTo(true));
    }

    /**
     * Тест проверяет окончание рецепта информацией о булочке.
     */
    @Test
    public void testReceiptEndsWithBun() {
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertThat("Рецепт должен заканчиваться булочкой",
                receipt.contains("(==== " + bun.getName() + " ====)"), equalTo(true));
    }

    /**
     * Тест проверяет формат строки с ингредиентом в рецепте.
     */
    @Test
    public void testReceiptFormatIngredientLines() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        String receipt = burger.getReceipt();

        String expectedLine = "= " + sauce.getType().name().toLowerCase() + " " + sauce.getName() + " =";
        assertThat("Рецепт должен содержать строку с ингредиентом",
                receipt.contains(expectedLine), equalTo(true));
    }
}