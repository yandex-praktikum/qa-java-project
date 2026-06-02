import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerRemoveIngredientTest {

    private Burger burger;

    @Mock
    private Ingredient mockIngredient1;
    @Mock
    private Ingredient mockIngredient2;

    @Before
    public void setUp() {
        burger = new Burger();
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        System.out.println(burger.ingredients);
    }

    @Test
    public void testRemoveIngredientSuccess() {
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        System.out.println(burger.ingredients);
    }
}