package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.*;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient hotSauce;
    private Ingredient cutlet;
    private Ingredient chiliSauce;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        burger = new Burger();

        bun = MockFactory.createDefaultBun();
        hotSauce = MockFactory.createHotSauce();
        cutlet = MockFactory.createCutlet();
        chiliSauce = MockFactory.createChiliSauce();
    }

    //ТЕСТЫ УСТАНОВКИ БУЛОЧКИ

    @Test
    public void shouldSetBun() {
        burger.setBuns(bun);

        Bun actualBun = burger.bun;
        assertNotNull("Булочка должна быть установлена", actualBun);
    }

    @Test
    public void shouldSetCorrectBunName() {
        burger.setBuns(bun);

        String actualName = burger.bun.getName();
        assertEquals("Название булочки должно совпадать", "black bun", actualName);
    }

    @Test
    public void shouldSetCorrectBunPrice() {
        burger.setBuns(bun);

        float actualPrice = burger.bun.getPrice();
        assertEquals("Цена булочки должна совпадать", 100.0f, actualPrice, 0.001f);
    }

    // ТЕСТЫ ДОБАВЛЕНИЯ ИНГРЕДИЕНТОВ

    @Test
    public void shouldAddIngredient() {
        burger.addIngredient(hotSauce);

        int ingredientsCount = burger.ingredients.size();
        assertEquals("Должен быть добавлен один ингредиент", 1, ingredientsCount);
    }

    @Test
    public void shouldAddCorrectIngredient() {
        burger.addIngredient(hotSauce);

        Ingredient actualIngredient = burger.ingredients.get(0);
        assertEquals("Ингредиент должен быть тем же", hotSauce, actualIngredient);
    }

    @Test
    public void shouldAddMultipleIngredients() {
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);

        int ingredientsCount = burger.ingredients.size();
        assertEquals("Должно быть два ингредиента", 2, ingredientsCount);
    }

    @Test
    public void shouldKeepFirstIngredientOrder() {
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);

        Ingredient firstIngredient = burger.ingredients.get(0);
        assertEquals("Первый ингредиент должен быть hotSauce", hotSauce, firstIngredient);
    }

    @Test
    public void shouldKeepSecondIngredientOrder() {
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);

        Ingredient secondIngredient = burger.ingredients.get(1);
        assertEquals("Второй ингредиент должен быть cutlet", cutlet, secondIngredient);
    }

    //ТЕСТЫ УДАЛЕНИЯ ИНГРЕДИЕНТОВ

    @Test
    public void shouldRemoveIngredientByIndex() {
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);
        burger.addIngredient(chiliSauce);

        burger.removeIngredient(1);

        int ingredientsCount = burger.ingredients.size();
        assertEquals("После удаления должно быть два ингредиента", 2, ingredientsCount);
    }

    @Test
    public void shouldKeepFirstIngredientAfterRemoval() {
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);
        burger.addIngredient(chiliSauce);

        burger.removeIngredient(1);

        Ingredient firstIngredient = burger.ingredients.get(0);
        assertEquals("Первый ингредиент должен остаться hotSauce", hotSauce, firstIngredient);
    }

    @Test
    public void shouldKeepSecondIngredientAfterRemoval() {
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);
        burger.addIngredient(chiliSauce);

        burger.removeIngredient(1);

        Ingredient secondIngredient = burger.ingredients.get(1);
        assertEquals("Второй ингредиент должен стать chiliSauce", chiliSauce, secondIngredient);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenRemovingWithInvalidIndex() {
        burger.addIngredient(hotSauce);

        burger.removeIngredient(5);
    }

    //ТЕСТЫ ПЕРЕМЕЩЕНИЯ ИНГРЕДИЕНТОВ

    @Test
    public void shouldMoveIngredient() {
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);
        burger.addIngredient(chiliSauce);

        burger.moveIngredient(0, 2);

        int ingredientsCount = burger.ingredients.size();
        assertEquals("Количество ингредиентов не должно измениться", 3, ingredientsCount);
    }

    @Test
    public void shouldMoveIngredientToCorrectFirstPosition() {
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);
        burger.addIngredient(chiliSauce);

        burger.moveIngredient(0, 2);

        Ingredient ingredientAtFirstPosition = burger.ingredients.get(0);
        assertEquals("Первый элемент должен быть cutlet", cutlet, ingredientAtFirstPosition);
    }

    @Test
    public void shouldMoveIngredientToCorrectSecondPosition() {
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);
        burger.addIngredient(chiliSauce);

        burger.moveIngredient(0, 2);

        Ingredient ingredientAtSecondPosition = burger.ingredients.get(1);
        assertEquals("Второй элемент должен быть chiliSauce", chiliSauce, ingredientAtSecondPosition);
    }

    @Test
    public void shouldMoveIngredientToCorrectThirdPosition() {
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);
        burger.addIngredient(chiliSauce);

        burger.moveIngredient(0, 2);

        Ingredient ingredientAtThirdPosition = burger.ingredients.get(2);
        assertEquals("Третий элемент должен быть hotSauce", hotSauce, ingredientAtThirdPosition);
    }

    @Test
    public void shouldKeepOrderWhenMovingToSamePosition() {
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);

        burger.moveIngredient(0, 0);

        Ingredient firstIngredient = burger.ingredients.get(0);
        assertEquals("Первый элемент должен остаться hotSauce", hotSauce, firstIngredient);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenMovingWithInvalidSourceIndex() {
        burger.addIngredient(hotSauce);

        burger.moveIngredient(5, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionWhenMovingWithInvalidTargetIndex() {
        burger.addIngredient(hotSauce);

        burger.moveIngredient(0, 5);
    }

    //ТЕСТЫ РАСЧЕТА ЦЕНЫ

    @Test
    public void shouldCalculatePriceWithOnlyBun() {
        burger.setBuns(bun);

        float actualPrice = burger.getPrice();
        float expectedPrice = 100.0f * 2;

        assertEquals("Цена должна быть удвоенной ценой булочки", expectedPrice, actualPrice, 0.001f);
    }

    @Test
    public void shouldCalculatePriceWithBunAndOneIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(hotSauce);

        float actualPrice = burger.getPrice();
        float expectedPrice = (100.0f * 2) + 50.0f;

        assertEquals("Цена должна включать стоимость булочки и ингредиента", expectedPrice, actualPrice, 0.001f);
    }

    @Test
    public void shouldCalculatePriceWithBunAndTwoIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);

        float actualPrice = burger.getPrice();
        float expectedPrice = (100.0f * 2) + 50.0f + 150.0f;

        assertEquals("Цена должна включать стоимость булочки и всех ингредиентов", expectedPrice, actualPrice, 0.001f);
    }

    //ТЕСТЫ ФОРМИРОВАНИЯ ЧЕКА

    @Test
    public void shouldGenerateReceiptWithOnlyBun() {
        burger.setBuns(bun);

        String receipt = burger.getReceipt();
        String expected = String.format("(==== %s ====)%n(==== %s ====)%n%nPrice: %f%n",
                "black bun", "black bun", 200.0f);

        assertEquals("Чек должен быть правильным", expected, receipt);
    }

    @Test
    public void shouldGenerateReceiptWithBunAndOneIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(hotSauce);

        String receipt = burger.getReceipt();
        String expected = String.format("(==== %s ====)%n= sauce hot sauce =%n(==== %s ====)%n%nPrice: %f%n",
                "black bun", "black bun", 250.0f);

        assertEquals("Чек должен содержать ингредиент", expected, receipt);
    }

    @Test
    public void shouldGenerateReceiptWithMultipleIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);

        String receipt = burger.getReceipt();
        String expected = String.format("(==== %s ====)%n= sauce hot sauce =%n= filling cutlet =%n(==== %s ====)%n%nPrice: %f%n",
                "black bun", "black bun", 400.0f);

        assertEquals("Чек должен содержать все ингредиенты", expected, receipt);
    }

    @Test
    public void shouldGenerateReceiptWithCorrectIngredientOrder() {
        burger.setBuns(bun);
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);

        String receipt = burger.getReceipt();
        int sauceIndex = receipt.indexOf("sauce hot sauce");
        int fillingIndex = receipt.indexOf("filling cutlet");

        assertTrue("Соус должен быть раньше начинки в чеке", sauceIndex < fillingIndex);
    }

    @Test
    public void shouldGenerateReceiptWithBunName() {
        burger.setBuns(bun);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать название булочки", receipt.contains("black bun"));
    }

    @Test
    public void shouldGenerateReceiptWithPriceLabel() {
        burger.setBuns(bun);

        String receipt = burger.getReceipt();

        assertTrue("Чек должен содержать Price:", receipt.contains("Price:"));
    }

    //ТЕСТЫ НАЧАЛЬНОГО СОСТОЯНИЯ

    @Test
    public void shouldHaveEmptyIngredientListInitially() {
        int ingredientsCount = burger.ingredients.size();

        assertEquals("Список ингредиентов должен быть пустым при создании", 0, ingredientsCount);
    }

    @Test
    public void shouldHaveNullBunInitially() {
        Bun actualBun = burger.bun;

        assertNull("Булочка должна быть null при создании", actualBun);
    }

    //ТЕСТЫ ГРАНИЧНЫХ СЛУЧАЕВ

    @Test
    public void shouldAddIngredientToEmptyBurger() {
        burger.setBuns(bun);
        burger.addIngredient(hotSauce);

        int ingredientsCount = burger.ingredients.size();
        assertEquals("Должен быть один ингредиент", 1, ingredientsCount);
    }

    @Test
    public void shouldGenerateReceiptAfterMovingIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(hotSauce);
        burger.addIngredient(cutlet);
        burger.addIngredient(chiliSauce);

        burger.moveIngredient(0, 2);

        String receipt = burger.getReceipt();

        assertNotNull("Чек не должен быть null", receipt);
    }
}