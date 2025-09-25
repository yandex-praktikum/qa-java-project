package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class BurgerMoveIngredientParameterizedTest {

    private final String name;
    private final int index;
    private final int newIndex;
    private final List<String> initialOrder;
    private final List<String> expectedOrder;

    private Burger burger;
    private Ingredient a;
    private Ingredient b;
    private Ingredient c;
    private Map<String, Ingredient> map;

    public BurgerMoveIngredientParameterizedTest(String name, int index, int newIndex, List<String> initialOrder, List<String> expectedOrder) {
        this.name = name;
        this.index = index;
        this.newIndex = newIndex;
        this.initialOrder = initialOrder;
        this.expectedOrder = expectedOrder;
    }

    @Before
    public void setUp() {
        burger = new Burger();

        a = mock(Ingredient.class);
        b = mock(Ingredient.class);
        c = mock(Ingredient.class);

        map = new HashMap<>();
        map.put("A", a);
        map.put("B", b);
        map.put("C", c);

        burger.ingredients.clear();
        for (String key : initialOrder) {
            burger.addIngredient(map.get(key));
        }
    }

    @Parameterized.Parameters(name = "{index}: {0} [{1}→{2}]")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"в конец", 0, 2, Arrays.asList("A","B","C"), Arrays.asList("B","C","A")},
                {"в начало", 2, 0, Arrays.asList("A","B","C"), Arrays.asList("C","A","B")},
                {"на то же место", 1, 1, Arrays.asList("A","B","C"), Arrays.asList("A","B","C")},
                {"из середины в начало", 1, 0, Arrays.asList("A","B","C"), Arrays.asList("B","A","C")},
                {"из середины в конец", 1, 2, Arrays.asList("A","B","C"), Arrays.asList("A","C","B")}
        });
    }

    @Test
    public void moveIngredientShouldReorderAccordingToIndices() {
        burger.moveIngredient(index, newIndex);

        List<Ingredient> expectedObjects = new ArrayList<>();
        for (String key : expectedOrder) {
            expectedObjects.add(map.get(key));
        }
        assertEquals(expectedObjects, burger.ingredients);
    }
}
