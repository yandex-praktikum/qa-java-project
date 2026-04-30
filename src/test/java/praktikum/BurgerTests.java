package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {
    private Burger burger;
    @Mock
    private Bun bun;
    @Mock
    private Ingredient sauce;
    @Mock
    private Ingredient filling;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsSetsCorrectBunTest() {
        burger.setBuns(bun);
        Assert.assertEquals("Объект булки должен совпадать", bun, burger.bun);
    }

    @Test
    public void addIngredientAddsElementToListTest() {
        burger.addIngredient(sauce);
        Assert.assertTrue("Ингредиент должен быть добавлен в список", burger.ingredients.contains(sauce));
    }

    @Test
    public void removeIngredientRemovesFromListTest() {
        burger.addIngredient(sauce);
        burger.removeIngredient(0);
        Assert.assertTrue("Список ингредиентов должен стать пустым", burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientMovesTargetToNewPositionTest() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0, 1);
        Assert.assertEquals("Filling должен переместиться на позицию 0", filling, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientDisplacesOtherIngredientTest() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.moveIngredient(0, 1);
        Assert.assertEquals("Sauce должен переместиться на позицию 1", sauce, burger.ingredients.get(1));
    }

    private void setupReceiptMocks() {
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(sauce.getName()).thenReturn("hot sauce");
        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
    }

    @Test
    public void getReceiptContainsBunNameTest() {
        setupReceiptMocks();
        String receipt = burger.getReceipt();
        Assert.assertTrue("Чек должен содержать название булки", receipt.contains("(==== black bun ====)"));
    }

    @Test
    public void getReceiptContainsIngredientInfoTest() {
        setupReceiptMocks();
        String receipt = burger.getReceipt();
        Assert.assertTrue("Чек должен содержать информацию об ингредиенте", receipt.contains("= sauce hot sauce ="));
    }

    @Test
    public void getReceiptContainsTotalPriceTest() {
        setupReceiptMocks();
        String receipt = burger.getReceipt();
        Assert.assertTrue("Чек должен содержать итоговую цену", receipt.contains("Price: 300"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientInvalidIndexThrowsExceptionTest() {
        burger.removeIngredient(0);
    }
}

