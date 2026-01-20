package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static praktikum.TestConstants.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauceIngredient;

    @Mock
    private Ingredient fillingIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals("Булочка должна быть установлена", bun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        int initialSize = burger.ingredients.size();
        burger.addIngredient(sauceIngredient);
        assertEquals("Количество ингредиентов должно увеличиться на 1",
                initialSize + 1, burger.ingredients.size());
    }

    @Test
    public void testAddedIngredientIsPresent() {
        burger.addIngredient(sauceIngredient);
        assertTrue("Добавленный ингредиент должен присутствовать",
                burger.ingredients.contains(sauceIngredient));
    }

    @Test
    public void testAddMultipleIngredients() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        assertEquals("Должно быть 2 ингредиента", 2, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(sauceIngredient);
        burger.removeIngredient(0);
        assertTrue("Список должен быть пуст после удаления",
                burger.ingredients.isEmpty());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientInvalidIndex() {
        burger.removeIngredient(0);
    }

    @Test
    public void testMoveIngredientFirstToSecondPosition() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.moveIngredient(0, 1);
        assertEquals("Ингредиент должен переместиться на вторую позицию",
                sauceIngredient, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredientSecondToFirstPosition() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        burger.moveIngredient(1, 0);
        assertEquals("Второй ингредиент должен стать первым",
                fillingIngredient, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientInvalidIndex() {
        burger.moveIngredient(0, 1);
    }

    @Test
    public void testGetPrice() {
        when(bun.getPrice()).thenReturn(WHITE_BUN_PRICE);
        when(sauceIngredient.getPrice()).thenReturn(SOUR_CREAM_PRICE);
        when(fillingIngredient.getPrice()).thenReturn(DINOSAUR_PRICE);

        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        float expectedPrice = WHITE_BUN_PRICE * 2 + SOUR_CREAM_PRICE + DINOSAUR_PRICE;
        assertEquals("Цена должна быть рассчитана корректно",
                expectedPrice, burger.getPrice(), 0.0);
    }

    @Test
    public void testGetReceipt() {
        when(bun.getName()).thenReturn(RED_BUN_NAME);
        when(bun.getPrice()).thenReturn(RED_BUN_PRICE);
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredient.getName()).thenReturn(CHILI_SAUCE_NAME);
        when(sauceIngredient.getPrice()).thenReturn(CHILI_SAUCE_PRICE);

        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);

        String expectedReceipt = String.format(RECEIPT_STRUCTURE,
                RED_BUN_NAME, "sauce", CHILI_SAUCE_NAME,
                RED_BUN_NAME, RED_BUN_PRICE * 2 + CHILI_SAUCE_PRICE);

        assertEquals("Чек должен быть корректным",
                expectedReceipt, burger.getReceipt());
    }

    @Test
    public void testGetPriceWithDifferentBun() {
        when(bun.getPrice()).thenReturn(BLACK_BUN_PRICE);
        when(fillingIngredient.getPrice()).thenReturn(SAUSAGE_PRICE);

        burger.setBuns(bun);
        burger.addIngredient(fillingIngredient);

        float expectedPrice = BLACK_BUN_PRICE * 2 + SAUSAGE_PRICE;
        assertEquals("Цена должна быть рассчитана с другой булочкой",
                expectedPrice, burger.getPrice(), 0.0);
    }

    @Test
    public void testGetReceiptWithFilling() {
        when(bun.getName()).thenReturn(BLACK_BUN_NAME);
        when(bun.getPrice()).thenReturn(BLACK_BUN_PRICE);
        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
        when(fillingIngredient.getName()).thenReturn(CUTLET_NAME);
        when(fillingIngredient.getPrice()).thenReturn(CUTLET_PRICE);

        burger.setBuns(bun);
        burger.addIngredient(fillingIngredient);

        String expectedReceipt = String.format(RECEIPT_STRUCTURE,
                BLACK_BUN_NAME, "filling", CUTLET_NAME,
                BLACK_BUN_NAME, BLACK_BUN_PRICE * 2 + CUTLET_PRICE);

        assertEquals("Чек должен быть корректным с начинкой",
                expectedReceipt, burger.getReceipt());
    }
}