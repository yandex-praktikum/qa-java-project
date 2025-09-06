package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    private Ingredient mockIngredient;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Bun bun;

    @Before
    public void SetUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBunsTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredientTest() {
        burger.addIngredient(mockIngredient);
        assertTrue(burger.ingredients.contains(mockIngredient));
    }

    @Test
    public void removeIngredientShouldRemoveIngredientTest() {
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientShouldMoveIngredientTest() {
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient2);
        burger.moveIngredient(0, 1);
        assertEquals(mockIngredient, burger.ingredients.get(1));
    }

    @Test
    public void getPriceShouldGetCorrectPrice() {
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(100F);
        Mockito.when(mockIngredient.getPrice()).thenReturn(200F);
        Mockito.when(mockIngredient2.getPrice()).thenReturn(300F);
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient2);
        float expectedPrice = 700F;
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }
}
