
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(Parameterized.class)
public class BurgerRemoveIngredientParameterizedTest extends BaseBurgerTest {

    private final int indexToRemove;
    private final String expectedRemainingName;
    @Mock
    private Ingredient firstIngredient;
    @Mock
    private Ingredient secondIngredient;
    private AutoCloseable mocks;
    @Before
    public void initMocks() {
        mocks = MockitoAnnotations.openMocks(this);
        when(firstIngredient.getName()).thenReturn("First");
        when(secondIngredient.getName()).thenReturn("Second");
    }


    public BurgerRemoveIngredientParameterizedTest(int indexToRemove, String expectedRemainingName) {
        this.indexToRemove = indexToRemove;
        this.expectedRemainingName = expectedRemainingName;
    }

    @Parameterized.Parameters(name = "Удалить индекс {0}, останется: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, "Second"},
                {1, "First"},
        });
    }

    @Test
    public void testRemoveIngredient_parametrized() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.removeIngredient(indexToRemove);

        assertEquals(expectedRemainingName, burger.ingredients.get(0).getName());
    }

    @After
    public void tearDown() throws Exception {
        mocks.close();
    }
}

