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
public class BurgerTest {
    @Mock
    private Bun bun;

    @Mock
    private Ingredient firstIngredient, secondIngredient, thirdIngredient;

    private Burger burger;

    @Before
    public void init() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        Assert.assertEquals(bun.getName(), burger.bun.getName());
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(firstIngredient);
        Assert.assertEquals("Не получилось добавить ингредиент", 1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        Assert.assertEquals("Не получилось добавить ингредиент", 2, burger.ingredients.size());
        burger.removeIngredient(1);
        Assert.assertEquals("Не получилось удалить ингредиент", 1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);
        Assert.assertEquals("Не получилось добавить ингредиент", 3, burger.ingredients.size());

        burger.moveIngredient(1, 2);

        Assert.assertEquals(secondIngredient, burger.ingredients.get(2));
        Assert.assertEquals(thirdIngredient, burger.ingredients.get(1));
    }

    @Test
    public void getPrice() {
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        Mockito.when(bun.getPrice()).thenReturn(11F);
        Mockito.when(firstIngredient.getPrice()).thenReturn(1F);
        Mockito.when(secondIngredient.getPrice()).thenReturn(2F);

        float expectedValue = (11F * 2) + 1F + 2F;

        Assert.assertEquals(
                "Фактическая стоимость отличается от ожидаемой", expectedValue, burger.getPrice(), 0.00001);

    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        Mockito.when(bun.getName()).thenReturn("Кравсбургер");
        Mockito.when(firstIngredient.getName()).thenReturn("Первый ингредиент");
        Mockito.when(secondIngredient.getName()).thenReturn("Второй ингредиент");
        Mockito.when(bun.getPrice()).thenReturn(12F);
        Mockito.when(firstIngredient.getPrice()).thenReturn(3F);
        Mockito.when(secondIngredient.getPrice()).thenReturn(4F);
        Mockito.when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);

        String expectedValue = "(==== Кравсбургер ====)\r\n" +
                "= sauce Первый ингредиент =\r\n" +
                "= filling Второй ингредиент =\r\n" +
                "(==== Кравсбургер ====)\r\n" +
                "\r\n" +
                "Price: 31,000000\r\n";

        Assert.assertEquals(expectedValue, burger.getReceipt());
    }

}
