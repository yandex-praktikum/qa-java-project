package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {
    Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient filling;

    @Mock
    Ingredient anotherFilling;

    @Mock
    Ingredient sauce;

    private int fromIndex;
    private int toIndex;
    private int indexToCheck;
    private int expectedIndex;

    public BurgerParameterizedTest(int fromIndex, int toIndex, int indexToCheck, int expectedIndex) {
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.indexToCheck = indexToCheck;
        this.expectedIndex = expectedIndex;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        burger.addIngredient(anotherFilling);
    }

    @Parameterized.Parameters
    public static Object[][] ingredientsAndIndexes() {
        return new Object[][]{
                // откуда - куда - какой индекс проверяем - ожидаемый элемент
                {0, 1, 1, 0},
                {0, 2, 0, 1},
                {0, 2, 2, 0},
                {2, 0, 0, 2},
                {2, 0, 1, 0},
                {2, 0, 2, 1},
                {1, 1, 1, 1},
        };
    }

    @Test
    public void moveIngredientsTest() {
        Ingredient[] ingredientsOriginalOrder = new Ingredient[] {filling, sauce, anotherFilling};
        burger.moveIngredient(fromIndex, toIndex);
        Ingredient actualIngredient = burger.ingredients.get(indexToCheck);
        assertEquals("Элемент не переместился'", ingredientsOriginalOrder[expectedIndex], actualIngredient);
    }
}