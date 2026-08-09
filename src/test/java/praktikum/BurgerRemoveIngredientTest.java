package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerRemoveIngredientTest {

    private Burger burger;

    @Mock
    private Ingredient mockIngredientFirst;

    @Mock
    private Ingredient mockIngredientSecond;

    @Mock
    private Ingredient mockIngredientThird;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void removeIngredientShouldReduceListSize() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        int initialSize = burger.ingredients.size();

        burger.removeIngredient(1);

        assertEquals("Размер списка должен уменьшиться на 1", initialSize - 1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldNotContainRemovedIngredient() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        burger.removeIngredient(1);

        assertFalse("Список не должен содержать удалённый ингредиент", burger.ingredients.contains(mockIngredientSecond));
    }

    @Test
    public void removeIngredientShouldContainFirstIngredient() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        burger.removeIngredient(1);

        assertTrue("Первый ингредиент должен остаться", burger.ingredients.contains(mockIngredientFirst));
    }

    @Test
    public void removeIngredientShouldContainThirdIngredient() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        burger.removeIngredient(1);

        assertTrue("Третий ингредиент должен остаться", burger.ingredients.contains(mockIngredientThird));
    }

    @Test
    public void removeIngredientShouldRemoveFirstIngredientSize() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        burger.removeIngredient(0);

        assertEquals("Размер списка должен быть 1", 1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldRemoveFirstIngredient() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        burger.removeIngredient(0);

        assertFalse("Первый ингредиент должен быть удалён", burger.ingredients.contains(mockIngredientFirst));
    }

    @Test
    public void removeIngredientShouldContainSecondIngredient() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        burger.removeIngredient(0);

        assertTrue("Второй ингредиент должен остаться", burger.ingredients.contains(mockIngredientSecond));
    }

    @Test
    public void removeIngredientShouldRemoveLastIngredientSize() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        burger.removeIngredient(1);

        assertEquals("Размер списка должен быть 1", 1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldRemoveLastIngredient() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        burger.removeIngredient(1);

        assertFalse("Второй ингредиент должен быть удалён", burger.ingredients.contains(mockIngredientSecond));
    }

    @Test
    public void removeIngredientShouldContainFirstIngredientAfterRemoval() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        burger.removeIngredient(1);

        assertTrue("Первый ингредиент должен остаться", burger.ingredients.contains(mockIngredientFirst));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientShouldThrowExceptionWhenIndexOutOfBounds() {
        burger.removeIngredient(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientShouldThrowExceptionWhenIndexIsNegative() {
        burger.removeIngredient(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredientShouldThrowExceptionWhenIndexIsTooLarge() {
        burger.addIngredient(mockIngredientFirst);
        burger.removeIngredient(5);
    }
}
