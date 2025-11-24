import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {


    @Mock
    Bun bunMock;

    @Mock
    Ingredient sauceIngredient;

    @Mock
    Ingredient fillingIngredient;

    Burger burger;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void testAddIngredientSize() {
        burger.addIngredient(sauceIngredient);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientValue() {
        burger.addIngredient(sauceIngredient);
        assertEquals(sauceIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientReducesSize() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientKeepsCorrectElement() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.removeIngredient(0);

        assertEquals(fillingIngredient, burger.ingredients.get(0));
    }


    @Test
    public void testMoveIngredientNewFirstPosition() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(fillingIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientNewSecondPosition() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(sauceIngredient, burger.ingredients.get(1));
    }


    @Test
    public void testGetReceiptFormatsCorrectly() {

        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100f);

        when(sauceIngredient.getName()).thenReturn("hot sauce");
        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredient.getPrice()).thenReturn(50f);

        burger.setBuns(bunMock);
        burger.addIngredient(sauceIngredient);

        String receipt = burger.getReceipt();

        String expected =
                "(==== black bun ====)\n" +
                        "= sauce hot sauce =\n" +
                        "(==== black bun ====)\n\n" +
                        "Price: 250,000000\n";

        assertEquals(
                expected.replace("\r", "").trim(),
                receipt.replace("\r", "").trim()
        );

    }

}
