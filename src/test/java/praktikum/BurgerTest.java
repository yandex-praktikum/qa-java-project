package praktikum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class BurgerTest {
    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    private Burger burger;

    @Test
    public void setBunsCorrect() {
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientSuccess() {
        burger.addIngredient(ingredient);
        Assert.assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientSuccess() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        Assert.assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientSuccess() {
        Ingredient sauce = Mockito.mock(Ingredient.class);
        Ingredient salad = Mockito.mock(Ingredient.class);
        burger.addIngredient(sauce);
        burger.addIngredient(salad);
        burger.moveIngredient(0, 1);
        Assert.assertEquals(salad, burger.ingredients.get(0));
    }

    @Test
    public void getBurgerReceiptSuccess(){
        Mockito.when(bun.getName()).thenReturn("Краторная булка N-200i");
        Mockito.when(ingredient.getName()).thenReturn("Кристаллы марсианских альфа-сахаридов");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(bun.getPrice()).thenReturn(1255.0f);
        Mockito.when(ingredient.getPrice()).thenReturn(762.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expectedReceipt = String.format(
                "(==== Краторная булка N-200i ====)%n" +
                        "= filling Кристаллы марсианских альфа-сахаридов =%n" +
                        "(==== Краторная булка N-200i ====)%n" +
                        "%nPrice: %f%n", 3272.0f
        );
        String actualReceipt = burger.getReceipt();
        Assert.assertEquals(expectedReceipt, actualReceipt);
    }

}
