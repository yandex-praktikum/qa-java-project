import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BurgerTest {

    Burger burger;

    @Before
    public void setUp(){
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        Bun expectedBun = Mockito.mock(Bun.class);
        burger.setBuns(expectedBun);
        assertEquals(expectedBun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Ingredient expectedIngredient = Mockito.mock(Ingredient.class);
        burger.addIngredient(expectedIngredient);
        assertEquals(expectedIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        Ingredient expectedIngredient = Mockito.mock(Ingredient.class);
        burger.addIngredient(expectedIngredient);
        assertEquals(expectedIngredient, burger.ingredients.get(0));
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void moveIngredientTest() {
        Ingredient expectedIngredient = Mockito.mock(Ingredient.class);
        Ingredient expectedIngredient1 = Mockito.mock(Ingredient.class);
        burger.addIngredient(expectedIngredient);
        burger.addIngredient(expectedIngredient1);
        burger.moveIngredient(0, 1);
        assertEquals(expectedIngredient, burger.ingredients.get(1));
        assertEquals(expectedIngredient1, burger.ingredients.get(0));
    }
}
