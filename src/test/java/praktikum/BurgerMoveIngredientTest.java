package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerMoveIngredientTest {

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
    public void moveIngredientShouldMoveIngredientToNewPositionFirst() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        burger.moveIngredient(2, 0);

        assertEquals("Элемент с индексом 0 должен быть третьим ингредиентом", mockIngredientThird, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToNewPositionSecond() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        burger.moveIngredient(2, 0);

        assertEquals("Элемент с индексом 1 должен быть первым ингредиентом", mockIngredientFirst, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToNewPositionThird() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        burger.moveIngredient(2, 0);

        assertEquals("Элемент с индексом 2 должен быть вторым ингредиентом", mockIngredientSecond, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToEndFirst() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        burger.moveIngredient(0, 2);

        assertEquals("Элемент с индексом 0 должен быть вторым ингредиентом", mockIngredientSecond, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToEndSecond() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        burger.moveIngredient(0, 2);

        assertEquals("Элемент с индексом 1 должен быть третьим ингредиентом", mockIngredientThird, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToEndThird() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        burger.moveIngredient(0, 2);

        assertEquals("Элемент с индексом 2 должен быть первым ингредиентом", mockIngredientFirst, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToSamePositionFirst() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        burger.moveIngredient(0, 0);

        assertEquals("Первый элемент не должен измениться", mockIngredientFirst, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToSamePositionSecond() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        burger.moveIngredient(0, 0);

        assertEquals("Второй элемент не должен измениться", mockIngredientSecond, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientShouldThrowExceptionWhenFromIndexOutOfBounds() {
        burger.moveIngredient(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientShouldThrowExceptionWhenToIndexOutOfBounds() {
        burger.addIngredient(mockIngredientFirst);
        burger.moveIngredient(0, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientShouldThrowExceptionWhenFromIndexIsNegative() {
        burger.addIngredient(mockIngredientFirst);
        burger.moveIngredient(-1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientShouldThrowExceptionWhenToIndexIsNegative() {
        burger.addIngredient(mockIngredientFirst);
        burger.moveIngredient(0, -1);
    }
}

