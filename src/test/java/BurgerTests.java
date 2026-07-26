import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    Burger burger;

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;
    @Mock
    Ingredient anotherIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void shouldSetBun() {
        burger.setBuns(bun);

        Assert.assertEquals("Булочка не добавлена в бургер", bun, burger.bun);
    }

    @Test
    public void shouldAddIngredient() {
        burger.addIngredient(ingredient);
        Assert.assertTrue(burger.ingredients.contains(ingredient));
    }

    @Test
    public void shouldRemoveIngredient() {
        burger.addIngredient(ingredient);
        int index = burger.ingredients.indexOf(ingredient);
        burger.removeIngredient(index);
        Assert.assertFalse(burger.ingredients.contains(ingredient));
    }

    @Test
    public void shouldMoveIngredient() {
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);
        burger.moveIngredient(0, 1);
        Assert.assertEquals(ingredient, burger.ingredients.get(1));
        Assert.assertEquals(anotherIngredient, burger.ingredients.get(0));
    }

    @Test
    public void shouldReturnCorrectReceipt() {
        burger.addIngredient(ingredient);
        burger.addIngredient(anotherIngredient);
        burger.setBuns(bun);
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("hot sauce");
        Mockito.when(anotherIngredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(anotherIngredient.getName()).thenReturn("cutlet");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredient.getPrice()).thenReturn(100f);
        Mockito.when(anotherIngredient.getPrice()).thenReturn(100f);
        String expectedReceipt = String.format(
                "(==== black bun ====)%n" +
                        "= sauce hot sauce =%n" +
                        "= filling cutlet =%n" +
                        "(==== black bun ====)%n" +
                        "%nPrice: %f%n", 400.0f
        );
        String actualReceipt = burger.getReceipt();
        Assert.assertEquals("Текст чека не совпадает с ожидаемым", expectedReceipt, actualReceipt);

    }

    @Test
    public void shouldReturnCorrectReceiptWithoutIngredients() {
        burger.setBuns(bun);
        Mockito.when(bun.getName()).thenReturn("white bun");
        Mockito.when(bun.getPrice()).thenReturn(200f);
        String expectedReceipt = String.format(
                "(==== white bun ====)%n" +
                        "(==== white bun ====)%n" +
                        "%nPrice: %f%n", 400.0f
        );
        String actualReceipt = burger.getReceipt();
        Assert.assertEquals("Текст чека не совпадает с ожидаемым", expectedReceipt, actualReceipt);
    }

}
