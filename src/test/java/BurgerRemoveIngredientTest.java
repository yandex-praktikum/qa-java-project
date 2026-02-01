import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class BurgerRemoveIngredientTest {

    private Ingredient cheeseSauce;
    private Ingredient tomato;

    private final int indexToRemove;
    private final int expectedSize;
    private final Integer checkIndex;
    private final int expectedIngredientIndex;

    public BurgerRemoveIngredientTest(
            int indexToRemove,
            int expectedSize,
            Integer checkIndex,
            int expectedIngredientIndex
    ) {
        this.indexToRemove = indexToRemove;
        this.expectedSize = expectedSize;
        this.checkIndex = checkIndex;
        this.expectedIngredientIndex = expectedIngredientIndex;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, 1, 0, 1}, // удалили 0 → остался tomato
                {1, 1, 0, 0}  // удалили 1 → остался cheeseSauce
        });
    }

    @Before
    public void setUp() {
        cheeseSauce = mock(Ingredient.class);
        tomato = mock(Ingredient.class);
    }

    @Test
    public void removeIngredient_removesIngredientCorrectly() {
        Burger burger = new Burger();
        burger.addIngredient(cheeseSauce);
        burger.addIngredient(tomato);

        burger.removeIngredient(indexToRemove);

        assertEquals(expectedSize, burger.ingredients.size());

        if (checkIndex != null) {
            Ingredient[] source = {cheeseSauce, tomato};
            assertSame(source[expectedIngredientIndex], burger.ingredients.get(checkIndex));
        }
    }
}

