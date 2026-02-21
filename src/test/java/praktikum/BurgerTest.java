package praktikum;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @After
    public void tearDown() {
        burger = null;
    }

    @Test
    @Description("Проверка установки булки в бургер")
    public void setBunsShouldSetBunTest() {
        Bun bun =  Mockito.mock(Bun.class);
        when(bun.getName()).thenReturn("S kunzhutom");
        burger.setBuns(bun);
        assertEquals("Булка " + bun.getName() + " не установилась в бургер (установлена " + burger.bun.getName() + ")",
                bun, burger.bun);
    }

    @Test
    @Description("Проверка добавления ингредиентов в бургер")
    public void addIngredientShouldAddIngredientInBurgerTest() {
        Ingredient ingredient = mock(Ingredient.class);
        when(ingredient.getName()).thenReturn("kepchuk");
        burger.addIngredient(ingredient);
        assertEquals("Добавленный ингредиент не совпадает (" + burger.ingredients.get(0).getName() +
                        " вместо " + ingredient.getName() + ")",
                ingredient, burger.ingredients.get(0));
    }

    @Test
    @Description("Проверка удаления ингредиентов из бургера")
    public void removeIngredientShouldDeleteIngredientFromBurgerTest() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.removeIngredient(0);
        assertEquals("В списке ингредиентов должен был остаться " + secondIngredient.getName() +
                        " вместо " + burger.ingredients.get(0).getName(),
                secondIngredient, burger.ingredients.get(0));
    }

    @Test
    @Description("Проверка расчета цены бургера")
    public void getPriceShouldReturnCorrectBurgerPriceTest() {
        Bun bun =  Mockito.mock(Bun.class);
        when(bun.getPrice()).thenReturn(150f);

        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);

        when(firstIngredient.getPrice()).thenReturn(50f);

        when(secondIngredient.getPrice()).thenReturn(250f);

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        float expectedPrice = 150f * 2 + 50f + 250f;
        float actualPrice = burger.getPrice();
        assertEquals("Неверно рассчитана цена бургера", expectedPrice, actualPrice, 0.0001f);
    }

    @Test
    @Description("Проверка рецепта бургера")
    public void getReceiptShouldReturnCorrectReceiptTest() {
        Bun bun =  Mockito.mock(Bun.class);
        when(bun.getName()).thenReturn("S oregano");
        when(bun.getPrice()).thenReturn(100f);

        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);

        when(firstIngredient.getName()).thenReturn("1000 ostrovov");
        when(firstIngredient.getPrice()).thenReturn(100f);
        when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);


        when(secondIngredient.getName()).thenReturn("indeyka");
        when(secondIngredient.getPrice()).thenReturn(200f);
        when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        String expectedReceipt = "(==== S oregano ====)\n" +
                                 "= sauce 1000 ostrovov =\n" +
                                 "= filling indeyka =\n" +
                                 "(==== S oregano ====)\n" +
                                 "\nPrice: 500,000000\n";
        String actualReceipt = (burger.getReceipt());

        assertEquals("Неверно составлен рецепт бургера", expectedReceipt, actualReceipt);
    }

    @Test
    @Description("Проверка рецепта бургера без ингредиентов")
    public void getReceiptShouldReturnCorrectReceiptWithoutIngredientsTest() {
        Bun bun =  Mockito.mock(Bun.class);
        when(bun.getName()).thenReturn("S oregano");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        String expectedReceipt = "(==== S oregano ====)\n" +
                "(==== S oregano ====)\n" +
                "\nPrice: 200,000000\n";
        String actualReceipt = (burger.getReceipt());

        assertEquals("Неверно составлен рецепт бургера", expectedReceipt, actualReceipt);
    }
}
