package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class BurgerTest {

    // ====== Константы ======
    private static final float BUN_PRICE_SMALL = 100f;
    private static final float BUN_PRICE_MEDIUM = 150f;
    private static final float BUN_PRICE_LARGE = 200f;

    private static final float SAUCE_PRICE = 50f;
    private static final float FILLING_PRICE = 75f;

    // ====== Моки ======
    private Burger burger;
    private Bun bunMock;
    private Ingredient sauceIngredient;
    private Ingredient fillingIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
        bunMock = mock(Bun.class);
        sauceIngredient = mock(Ingredient.class);
        fillingIngredient = mock(Ingredient.class);

        when(bunMock.getName()).thenReturn("Test Bun");
        when(bunMock.getPrice()).thenReturn(BUN_PRICE_SMALL);

        when(sauceIngredient.getName()).thenReturn("Tomato Sauce");
        when(sauceIngredient.getPrice()).thenReturn(SAUCE_PRICE);
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);

        when(fillingIngredient.getName()).thenReturn("Beef Patty");
        when(fillingIngredient.getPrice()).thenReturn(FILLING_PRICE);
        when(fillingIngredient.getType()).thenReturn(IngredientType.FILLING);
    }

    // ====== Тесты булки ======
    @Test
    public void testSetBun() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    // ====== Тесты добавления ингредиентов ======
    @Test
    public void testAddSauceIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceIngredient);
        assertEquals(sauceIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testAddFillingIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(fillingIngredient);
        assertEquals(fillingIngredient, burger.ingredients.get(0));
    }

    // ====== Тесты удаления ингредиентов ======
    @Test
    public void testRemoveIngredientKeepsCorrectIngredient() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.removeIngredient(0);
        assertEquals(fillingIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientListSize() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    // ====== Тесты перемещения ингредиентов ======
    @Test
    public void testMoveIngredientFirstPosition() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.moveIngredient(0, 1);
        assertEquals(fillingIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientSecondPosition() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.moveIngredient(0, 1);
        assertEquals(sauceIngredient, burger.ingredients.get(1));
    }

    // ====== Параметризированные тесты цены ======
    private Object[] parametersForBurgerPriceTest() {
        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getPrice()).thenReturn(SAUCE_PRICE);

        Ingredient filling = mock(Ingredient.class);
        when(filling.getPrice()).thenReturn(FILLING_PRICE);

        return new Object[]{
                new Object[]{BUN_PRICE_SMALL, new Ingredient[]{sauce, filling}, 325f},
                new Object[]{BUN_PRICE_LARGE, new Ingredient[]{sauce}, 450f},
                new Object[]{BUN_PRICE_MEDIUM, new Ingredient[]{}, 300f}
        };
    }

    @Test
    @Parameters(method = "parametersForBurgerPriceTest")
    public void testGetPriceParameterized(float bunPrice, Ingredient[] ingredients, float expectedPrice) {
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(bunPrice);
        when(bun.getName()).thenReturn("Test Bun");

        Burger burger = new Burger();
        burger.setBuns(bun);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }

    // ====== Параметризированные тесты добавления ингредиентов ======
    private Object[] parametersForAddIngredientTest() {
        return new Object[]{
                new Object[]{sauceIngredient},
                new Object[]{fillingIngredient}
        };
    }

    @Test
    @Parameters(method = "parametersForAddIngredientTest")
    public void testAddIngredientParameterized(Ingredient ingredient) {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredient);
        assertTrue(burger.ingredients.contains(ingredient));
    }

    // ====== Тесты рецепта ======
    @Test
    public void testGetReceiptFull() {
        burger.setBuns(bunMock);
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        String expectedReceipt = String.format(Locale.US,
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %.2f%n",
                bunMock.getName(),
                sauceIngredient.getType().toString().toLowerCase(), sauceIngredient.getName(),
                fillingIngredient.getType().toString().toLowerCase(), fillingIngredient.getName(),
                bunMock.getName(),
                burger.getPrice()
        );

        assertEquals(expectedReceipt, burger.getReceipt());
    }

    @Test
    public void testGetReceiptEmptyIngredients() {
        burger.setBuns(bunMock);
        String expectedReceipt = String.format(Locale.US,
                "(==== %s ====)%n" +
                        "(==== %s ====)%n%n" +
                        "Price: %.2f%n",
                bunMock.getName(),
                bunMock.getName(),
                burger.getPrice());
        assertEquals(expectedReceipt, burger.getReceipt());
    }

    @Test
    public void testGetReceiptWithoutBun() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        try {
            burger.getReceipt();
            fail("Expected NullPointerException when bun is null");
        } catch (NullPointerException ignored) {}
    }

    @Test
    public void testGetPriceWithoutBun() {
        assertEquals(0f, burger.getPrice(), 0.01);
    }
}