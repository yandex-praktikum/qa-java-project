package praktikum.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThrows;

@RunWith(Parameterized.class)
public class BurgerMoveIngredientTest {
    private final int moveFromIndex;
    private final int moveToIndex;
    private final List<Integer> expectedOrderIndices;
    private final boolean expectException;

    private Ingredient mockIngredientCutlet;
    private Ingredient mockIngredientCheese;
    private Ingredient mockIngredientKetchup;

    private Burger burger;

    public BurgerMoveIngredientTest(int moveFromIndex, int moveToIndex, List<Integer> expectedOrderIndices, boolean expectException) {
        this.moveFromIndex = moveFromIndex;
        this.moveToIndex = moveToIndex;
        this.expectedOrderIndices = expectedOrderIndices;
        this.expectException = expectException;
    }

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockIngredientCutlet = Mockito.mock(Ingredient.class);
        mockIngredientCheese = Mockito.mock(Ingredient.class);
        mockIngredientKetchup = Mockito.mock(Ingredient.class);

        burger = new Burger();

        burger.addIngredient(mockIngredientCutlet);
        burger.addIngredient(mockIngredientCheese);
        burger.addIngredient(mockIngredientKetchup);
    }

    // Тестовые данные
    @Parameterized.Parameters(name = "fromIndex={0}, toIndex={1} => expectedListOrder={2}, expectException={3}")
    public static Object[][] data() {
        return new Object[][]{
                {0, 2, List.of(1, 2, 0), false},
                {1, 1, List.of(0, 1, 2), false},
                {2, 0, List.of(2, 0, 1), false},
                {5, 0, null, true},
                {0, 5, null, true},
                {-1, 0, null, true},
                {0, -1, null, true}
        };
    }

    // Тест для метода moveIngredient()
    @Test
    public void moveIngredientTest() {
        if (expectException) {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> burger.moveIngredient(moveFromIndex, moveToIndex)
            );
        } else {
            burger.moveIngredient(moveFromIndex, moveToIndex);
            Ingredient[] expectedOrder = new Ingredient[3];
            for (int i = 0; i < expectedOrderIndices.size(); i++) {
                switch (expectedOrderIndices.get(i)) {
                    case 0:
                        expectedOrder[i] = mockIngredientCutlet;
                        break;
                    case 1:
                        expectedOrder[i] = mockIngredientCheese;
                        break;
                    case 2:
                        expectedOrder[i] = mockIngredientKetchup;
                        break;
                }
            }
            assertArrayEquals(burger.ingredients.toArray(), expectedOrder);
        }
    }
}
