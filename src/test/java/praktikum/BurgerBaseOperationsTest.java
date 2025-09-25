package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import java.util.Arrays;

public class BurgerBaseOperationsTest {

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsShouldStoreSameReference() {
        Bun bun = mock(Bun.class);
        burger.setBuns(bun);
        Assert.assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAppendToEnd() {
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        Ingredient c = mock(Ingredient.class);

        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.addIngredient(c);

        Assert.assertEquals(Arrays.asList(a, b, c), burger.ingredients);
    }

    @Test
    public void removeIngredientShouldLeaveCorrectOrder() {
        Ingredient a = mock(Ingredient.class);
        Ingredient b = mock(Ingredient.class);
        Ingredient c = mock(Ingredient.class);

        burger.addIngredient(a);
        burger.addIngredient(b);
        burger.addIngredient(c);

        burger.removeIngredient(1);

        Assert.assertEquals(Arrays.asList(a, c), burger.ingredients);
    }
}