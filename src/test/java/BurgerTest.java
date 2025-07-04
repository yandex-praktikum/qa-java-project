import org.junit.Assert;
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
    private Bun staticBun;

    @Mock
    private Ingredient staticIngredient;

    @Mock
    private Ingredient staticSpareIngredient;

    @Test
    public void setBunsTest() {
        Burger burger = new Burger();
        burger.setBuns(staticBun);
        Assert.assertEquals(staticBun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(staticIngredient);
        Assert.assertTrue(burger.ingredients.contains(staticIngredient));

    }

    @Test
    public void removeIngredientTest() {
        Burger burger = new Burger();
                burger.addIngredient(staticIngredient);
        burger.removeIngredient(0);
        Assert.assertFalse(burger.ingredients.contains(staticIngredient));
    }

    @Test
    public void moveIngredientTest() {
        Burger burger = new Burger();
        burger.addIngredient(staticIngredient);
        burger.addIngredient(staticSpareIngredient);
        burger.moveIngredient(0, 1);
        Assert.assertEquals(burger.ingredients.get(1), staticIngredient);
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        float price = 988;
        Mockito.when(staticBun.getPrice()).thenReturn(price);
        Mockito.when(staticIngredient.getPrice()).thenReturn(price);
        burger.setBuns(staticBun);
        burger.addIngredient(staticIngredient);
        Assert.assertEquals(price * 2 + price, burger.getPrice(), 0);
    }

       @Test
       public void getReceiptTest() {
        Burger burger = new Burger();
        Bun bun = new Bun("Флюоресцентная булка R2-D3", 988);
        String name = "Флюоресцентная булка R2-D3";
        String sauce = "Соус Spicy-X";
        String filling = "Мясо бессмертных моллюсков Protostomia";

        burger.setBuns(bun);
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, sauce, 90));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, filling, 1337));
        Assert.assertEquals("(==== " + name + " ====)\r\n" +
                                    "= sauce " + sauce + " =\r\n" +
                                    "= filling " + filling + " =\r\n" +
                                    "(==== " + name + " ====)\r\n" +
                                    "\r\nPrice: 3403,000000\r\n", burger.getReceipt());
    }
}
