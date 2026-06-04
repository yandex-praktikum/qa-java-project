package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientKetchup;

    @Mock
    private Ingredient mockIngredientCutlet;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();

        when(mockBun.getName()).thenReturn("Сдоба");
        when(mockBun.getPrice()).thenReturn(10.0F);
        when(mockIngredientKetchup.getName()).thenReturn("Кетчуп");
        when(mockIngredientKetchup.getPrice()).thenReturn(10.0F);
        when(mockIngredientKetchup.getType()).thenReturn(SAUCE);
        when(mockIngredientCutlet.getName()).thenReturn("Котлета");
        when(mockIngredientCutlet.getPrice()).thenReturn(10.0F);
        when(mockIngredientCutlet.getType()).thenReturn(FILLING);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientKetchup);
        burger.addIngredient(mockIngredientCutlet);
    }

    @Test
    public void setBunsValidData() {
        Bun bun = new Bun("Сдоба", 10);
        Burger newBurger = new Burger();
        newBurger.setBuns(bun);
        assertEquals("Цена должна быть 20.0 (булочка 10*2)", 20.0, newBurger.getPrice(), 0.01);
    }

    @Test
    public void getPriceSumBunPriceAndIngredients() {
        assertEquals("Цена должна быть: булочка 10*2=20 + ингредиенты 10+10=20 = 40",
                40.0, burger.getPrice(), 0.01);
    }

    @Test
    public void removeIngredientBurgerSuccess() {
        burger.removeIngredient(0);
        assertEquals("После удаления ингредиента должна остаться только булочка c одним инредиентом",
                30.0, burger.getPrice(), 0.01);
    }

    @Test
    public void moveIngredientBurgerSuccess() throws NoSuchFieldException, IllegalAccessException {
        burger.moveIngredient(1, 0);

        Field field = Burger.class.getDeclaredField("ingredients");
        field.setAccessible(true);
        @SuppressWarnings("unchecked")
        List<Ingredient> ingredients = (List<Ingredient>) field.get(burger);

        assertSame("На позиции 0 должна быть котлета (была перемещена с позиции 1)",
                mockIngredientCutlet, ingredients.get(0));
        assertSame("На позиции 1 должен быть кетчуп (сдвинулся с позиции 0)",
                mockIngredientKetchup, ingredients.get(1));
    }

    @Test
    public void getReceiptValidFormat() {
        String expected = String.format(
                "(==== %s ====)%n" +
                        "= sauce Кетчуп =%n" +
                        "= filling Котлета =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                "Сдоба", "Сдоба", 40.0
        );

        assertEquals("Формат чека не соответствует ожидаемому", expected, burger.getReceipt());
    }
}