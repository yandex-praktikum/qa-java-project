package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Ingredient mockedIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBun() {
        Bun bun = new Bun("black bun", 100f);
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredientToList() {
        burger.addIngredient(mockedIngredient);
        assertEquals(1, burger.ingredients.size());
        assertSame(mockedIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveIngredientFromList() {
        Ingredient first = new Ingredient(IngredientType.SAUCE, "sauce", 50f);
        Ingredient second = new Ingredient(IngredientType.FILLING, "meat", 100f);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(second, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToNewIndex() {
        Ingredient first = new Ingredient(IngredientType.SAUCE, "sauce", 50f);
        Ingredient second = new Ingredient(IngredientType.FILLING, "meat", 100f);

        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.moveIngredient(0, 1);

        assertSame(second, burger.ingredients.get(0));
        assertSame(first, burger.ingredients.get(1));
    }

    @Test
    public void getPriceShouldReturnCorrectPriceWithMock() {
        Bun bun = new Bun("bun", 100f);
        burger.setBuns(bun);
        burger.addIngredient(mockedIngredient);

        when(mockedIngredient.getPrice()).thenReturn(50f);

        assertEquals(250f, burger.getPrice(), 0);
    }

    @Test
    public void getPriceShouldReturnCorrectPriceWithoutIngredients() {
        burger.setBuns(new Bun("bun", 100f));
        assertEquals(200f, burger.getPrice(), 0);
    }

    @Test
    public void getPriceShouldReturnCorrectPriceWithSeveralIngredients() {
        burger.setBuns(new Bun("bun", 100f));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "sauce", 50f));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "meat", 150f));

        assertEquals(400f, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptShouldReturnCorrectReceipt() {
        Bun bun = new Bun("bun", 100f);

        burger.setBuns(bun);
        burger.addIngredient(mockedIngredient);

        when(mockedIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(mockedIngredient.getName()).thenReturn("sauce");
        when(mockedIngredient.getPrice()).thenReturn(50f);

        String expected = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                "bun",
                "sauce",
                "sauce",
                "bun",
                250f
        );

        assertEquals(expected, burger.getReceipt());
    }
}