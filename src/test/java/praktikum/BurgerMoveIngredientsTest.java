package praktikum;

import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerMoveIngredientsTest {

    private Burger burger;

    private int indexOld;
    private int indexNew;
    private int firstElementAfterMove;
    private int secondElementAfterMove;
    private int thirdElementAfterMove;

    public BurgerMoveIngredientsTest (int indexOld, int indexNew, int firstElementAfterMove, int secondElementAfterMove, int thirdElementAfterMove) {
        this.indexOld = indexOld;
        this.indexNew = indexNew;
        this.firstElementAfterMove = firstElementAfterMove;
        this.secondElementAfterMove = secondElementAfterMove;
        this.thirdElementAfterMove = thirdElementAfterMove;

    }

    @Parameterized.Parameters(name = "Индексы: старый: {0}, новый :{1}; Новый порядок в списке: {2},{3},{4}")
    public static Object[][] testData() {
        return new Object[][]{
                {0, 2, 1, 2, 0},
                {2, 0, 2, 0, 1},
                {0, 0, 0, 1, 2},
                {1, 1, 0, 1, 2},
        };
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @After
    public void tearDown() {
        burger = null;
    }

    @Test
    @Description("Проверка изменения порядка ингредиентов в бургере")
    public void moveIngredientShouldMoveIngredientsInBurgerTest() {
        Ingredient firstIngredient = mock(Ingredient.class);
        Ingredient secondIngredient = mock(Ingredient.class);
        Ingredient thirdIngredient = mock(Ingredient.class);

        when(firstIngredient.getName()).thenReturn("kepchuk");
        when(secondIngredient.getName()).thenReturn("kotleta");
        when(thirdIngredient.getName()).thenReturn("mazik");

        List<Ingredient> expectedList = Arrays.asList(firstIngredient, secondIngredient, thirdIngredient);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.addIngredient(thirdIngredient);

        burger.moveIngredient(indexOld, indexNew);
        assertEquals("Ожидался порядок: " + expectedList.get(firstElementAfterMove).getName() + ", " +
                        expectedList.get(secondElementAfterMove).getName() + ", " +
                        expectedList.get(thirdElementAfterMove).getName() +
                        " фактический порядок " + burger.ingredients.get(0).getName() + ", " +
                        burger.ingredients.get(1).getName() + ", " +
                        burger.ingredients.get(2).getName() + ", ",
                Arrays.asList(expectedList.get(firstElementAfterMove).getName(),
                        expectedList.get(secondElementAfterMove).getName(),
                        expectedList.get(thirdElementAfterMove).getName()),
                Arrays.asList(burger.ingredients.get(0).getName(),
                        burger.ingredients.get(1).getName(),
                        burger.ingredients.get(2).getName()));
    }
}
