import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;



@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {


    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient firstingredient;
    Ingredient secondingredient;

    @Test
    public void testSetBuns() {
        Burger burger = new Burger();
        burger.setBuns(bun);

        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);

        Assert.assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        burger.removeIngredient(1);

        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(firstingredient);
        burger.addIngredient(secondingredient);
        burger.moveIngredient(1, 0);

        Assert.assertEquals(secondingredient, burger.ingredients.get(0));
    }
    }


