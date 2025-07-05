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

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BurgerRemoveIngredientTest {
    private final int initialSize;
    private final int removeIndex;
    private final int expectedSize;
    private final List<Integer> expectedOrderIndices;
    private final boolean expectException;

    private Ingredient mockIngredientCutlet;
    private Ingredient mockIngredientCheese;
    private Ingredient mockIngredientKetchup;

    private Burger burger;

    public BurgerRemoveIngredientTest(int initialSize, int removeIndex, int expectedSize, List<Integer> expectedOrderIndices, boolean expectException) {
        this.initialSize = initialSize;
        this.removeIndex = removeIndex;
        this.expectedSize = expectedSize;
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

        if (initialSize >= 1) burger.addIngredient(mockIngredientCutlet);
        if (initialSize >= 2) burger.addIngredient(mockIngredientCheese);
        if (initialSize >= 3) burger.addIngredient(mockIngredientKetchup);
    }

    @Parameterized.Parameters(name = "initialSize={0}, removeIndex={1} => expectedSize={2}, expectedOrderIndices={3}, expectException={4}")
    public static Object[][] data() {
        return new Object[][]{
                {3, 0, 2, List.of(1, 2), false},
                {3, 2, 2, List.of(0, 1), false},
                {3, 1, 2, List.of(0, 2), false},
                {1, 0, 0, List.of(), false},
                {0, 0, 0, null, true},
                {2, 5, 2, null, true},
                {2, -1, 2, null, true}
        };
    }

    // Тесты для метода removeIngredient()
    @Test
    public void removeIngredientListSizeTest() {
        if (expectException) {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> burger.removeIngredient(removeIndex)
            );
        } else {
            burger.removeIngredient(removeIndex);
            assertEquals(expectedSize, burger.ingredients.size());
        }
    }

    @Test
    public void removeIngredientListOrderTest() {
        if (expectException) {
            assertThrows(
                    IndexOutOfBoundsException.class,
                    () -> burger.removeIngredient(removeIndex)
            );
        } else {
            burger.removeIngredient(removeIndex);
            Ingredient[] expectedOrder = new Ingredient[expectedSize];
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
