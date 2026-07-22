import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import static org.mockito.Mockito.*;
import org.junit.Assert;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    // Тесты для метода setBuns
    @Test
    public void testSetBuns() {
        Bun bun = mock(Bun.class);
        burger.setBuns(bun);
        Assert.assertSame(bun, burger.bun);
    }

    // Тесты для метода addIngredient
    @Test
    public void testAddIngredientShouldIncreaseSize() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testAddIngredientShouldContainAddedIngredient() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        Assert.assertTrue(burger.ingredients.contains(ingredient));
    }

    // Тесты для метода removeIngredient
    @Test
    public void testRemoveIngredientShouldDecreaseSize() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        final int INDEX_TO_REMOVE = 0;
        final int EXPECTED_SIZE = 1;

        burger.removeIngredient(INDEX_TO_REMOVE);

        Assert.assertEquals(EXPECTED_SIZE, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientShouldRemoveCorrectElement() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        final int INDEX_TO_REMOVE = 0;

        burger.removeIngredient(INDEX_TO_REMOVE);

        Assert.assertFalse(burger.ingredients.contains(firstIngredient));
    }

    @Test
    public void testRemoveIngredientShouldKeepOtherElements() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        final int INDEX_TO_REMOVE = 0;

        burger.removeIngredient(INDEX_TO_REMOVE);

        Assert.assertTrue(burger.ingredients.contains(secondIngredient));
    }

    // Тесты для метода moveIngredient
    @Test
    public void testMoveIngredientShouldKeepSize() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        final int FROM_INDEX = 0;
        final int TO_INDEX = 2;
        final int EXPECTED_SIZE = 3;

        burger.moveIngredient(FROM_INDEX, TO_INDEX);

        Assert.assertEquals(EXPECTED_SIZE, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientShouldMoveElementToCorrectPosition() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        final int FROM_INDEX = 0;
        final int TO_INDEX = 2;

        burger.moveIngredient(FROM_INDEX, TO_INDEX);

        Assert.assertSame(firstIngredient, burger.ingredients.get(TO_INDEX));
    }

    // Разделяем тест с двумя проверками на два отдельных теста
    @Test
    public void testMoveIngredientShouldShiftSecondElementToFirst() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        final int FROM_INDEX = 0;
        final int TO_INDEX = 2;

        burger.moveIngredient(FROM_INDEX, TO_INDEX);

        Assert.assertSame(secondIngredient, burger.ingredients.get(0));
    }

    @Test
    public void testMoveIngredientShouldShiftThirdElementToSecond() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        final int FROM_INDEX = 0;
        final int TO_INDEX = 2;

        burger.moveIngredient(FROM_INDEX, TO_INDEX);

        Assert.assertSame(thirdIngredient, burger.ingredients.get(1));
    }
}