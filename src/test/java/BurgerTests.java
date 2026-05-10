import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.List;

import static org.junit.Assert.*;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    private Burger burger;

    @Mock
    private Ingredient ingredientSauce;
    @Mock
    private Ingredient ingredientFilling;
    @Mock
    private Bun bun;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
   public void setBunsTest() {

        burger.setBuns(bun);
        assertSame("Объект булки в бургере не совпадает с ожидаемым", bun, burger.bun);
    }

    @Test
    public void addIngredientTest(){

       burger.addIngredient(ingredientSauce);
        assertEquals("Неверное количество ингредиентов в списке после добавления ингредиента",1, burger.ingredients.size());
    }

    @Test
    public void checkBurgerContainsIngredientTest() {

        burger.addIngredient(ingredientSauce);
        assertTrue("Добавленный ингредиент отсутствует в бургере", burger.ingredients.contains(ingredientSauce));
    }

    @Test
    public void removeIngredientTest(){

        burger.addIngredient(ingredientSauce);
        burger.removeIngredient(0);
        assertEquals("Неверное количество ингредиентов в списке после удаления ингредиента",0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest(){

        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);

        burger.moveIngredient(0,1);
        List<Ingredient> expected = List.of(ingredientFilling, ingredientSauce);

        assertEquals("Неверный порядок ингредиентов", expected, burger.ingredients);
    }

       @Test
    public void getPriceWithOnlyBunTest() {

        Mockito.when(bun.getPrice()).thenReturn(50f);

        burger.setBuns(bun);

        assertEquals("Цена рассчитана неверно",100f, burger.getPrice(), 0.0f);
    }

    @Test public void getReceiptTest() {

        Mockito.when(bun.getName()).thenReturn("булка");
        Mockito.when(bun.getPrice()).thenReturn(50f);
        Mockito.when(ingredientSauce.getType()).thenReturn(SAUCE);
        Mockito.when(ingredientSauce.getName()).thenReturn("острый");
        Mockito.when(ingredientSauce.getPrice()).thenReturn(10f);

        burger.setBuns(bun);
        burger.addIngredient(ingredientSauce);

        String actual = burger.getReceipt();
        StringBuilder expected = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));
        expected.append(String.format("= %s %s =%n", ingredientSauce.getType().toString().toLowerCase(),
                ingredientSauce.getName()));
        expected.append(String.format("(==== %s ====)%n", bun.getName()));
        expected.append(String.format("%nPrice: %f%n", burger.getPrice()));

        assertEquals("Чек отображается неверно", expected.toString(),actual);
    }
}
