import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerAddIngredientTest {

    private Burger burger;

    @Mock
    private Ingredient mockIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testAddIngredientSuccess() {
        burger.addIngredient(mockIngredient);
        assertEquals(1, burger.ingredients.size());
    }
}