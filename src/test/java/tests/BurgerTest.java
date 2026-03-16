package tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
    private Bun bun;

    @Mock
    private Ingredient sauceIngredient;

    @Mock
    private Ingredient fillingIngredient;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBunCorrectly() {
        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldIncreaseIngredientsSize() {
        burger.addIngredient(sauceIngredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddCorrectIngredient() {
        burger.addIngredient(sauceIngredient);

        Ingredient actualIngredient = burger.ingredients.get(0);

        assertEquals(sauceIngredient, actualIngredient);
    }

    @Test
    public void removeIngredientShouldDecreaseIngredientsSize() {
        burger.addIngredient(sauceIngredient);

        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientShouldMoveIngredientToNewPosition() {
        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);

        burger.moveIngredient(0, 1);

        Ingredient movedIngredient = burger.ingredients.get(1);

        assertEquals(sauceIngredient, movedIngredient);
    }

    @Test
    public void getReceiptShouldReturnCorrectReceipt() {

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(sauceIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceIngredient.getName()).thenReturn("hot sauce");
        when(sauceIngredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(sauceIngredient);

        String receipt = burger.getReceipt();

        String expectedReceipt = String.format(
                "(==== %s ====)%n" + "= %s %s =%n" + "(==== %s ====)%n" + "%nPrice: %f%n",
                "black bun",
                "sauce",
                "hot sauce",
                "black bun",
                250f
        );

        assertEquals(expectedReceipt, receipt);
    }
}