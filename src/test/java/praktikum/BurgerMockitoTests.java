package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerMockitoTests {
    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientOne;

    @Mock
    private Ingredient mockIngredientTwo;

    @Mock
    private Ingredient mockIngredientThree;

    @Before
    public void setUp() {
        this.burger = new Burger();
    }

    @Test
    public void testThatSetBunAddNotNull() {
        burger.setBuns(mockBun);
        Assert.assertNotNull(burger.bun);
    }

    @Test
    public void testThatAddIngredientIncreaseIngredientsSize() {
        burger.addIngredient(mockIngredientOne);
        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testThatRemoveIngredientDecreaseIngredientSize() {
        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);
        burger.removeIngredient(0);
        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveIngredientWithEmptyList() {
        burger.removeIngredient(0);
    }

    @Test
    public void testThatMoveIngredientChangeIngredientIndex() {
        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);
        burger.addIngredient(mockIngredientThree);
        burger.moveIngredient(0, 2);
        Assert.assertEquals(mockIngredientOne, burger.ingredients.get(2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientWithInvalidIndex() {
        burger.addIngredient(mockIngredientOne);
        burger.moveIngredient(5, 0);
    }

    @Test
    public void testThatReceiptBuildCorrectly() {

        when(mockBun.getName()).thenReturn("Булочка тестовая");
        when(mockBun.getPrice()).thenReturn(50.0f);


        when(mockIngredientOne.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientOne.getName()).thenReturn("Биокотлета из марсианской Магнолии");
        when(mockIngredientOne.getPrice()).thenReturn(75.0f);


        when(mockIngredientTwo.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientTwo.getName()).thenReturn("Соус традиционный галактический");
        when(mockIngredientTwo.getPrice()).thenReturn(30.0f);


        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientOne);
        burger.addIngredient(mockIngredientTwo);


        String actualReceipt = burger.getReceipt();

        String expectedReceipt =
                "(==== Булочка тестовая ====)\r\n" +
                        "= filling Биокотлета из марсианской Магнолии =\r\n" +
                        "= sauce Соус традиционный галактический =\r\n" +
                        "(==== Булочка тестовая ====)\r\n\r\n" +
                        "Price: 205,000000\r\n";

        Assert.assertEquals(
                "Метод getReceipt() должен возвращать корректный чек",
                expectedReceipt,
                actualReceipt
        );
    }

}