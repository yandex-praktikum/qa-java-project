package praktikum;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.Assert.assertEquals;

public class BurgerUnitTest {

    private Burger burger;

    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockSauce;
    @Mock
    private Ingredient mockFilling;

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(mockBun);
        assertThat(mockBun, sameInstance(burger.bun));
    }

    @Test
    public void addSingleIngredientShouldIncreaseSize() {
        burger.addIngredient(mockSauce);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addSingleIngredientShouldContainAddedIngredient() {
        burger.addIngredient(mockSauce);
        assertThat(mockSauce, sameInstance(burger.ingredients.get(0)));
    }

    @Test
    public void addMultipleIngredientsShouldIncreaseSize() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldDecreaseSize() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldKeepCorrectRemainingElement() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.removeIngredient(0);
        assertThat(mockFilling, sameInstance(burger.ingredients.get(0)));
    }

    @Test
    public void moveIngredientShouldChangeOrder() {
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.addIngredient(mockSauce);
        burger.moveIngredient(1, 2);

        collector.checkThat("первый элемент", burger.ingredients.get(0), sameInstance(mockSauce));
        collector.checkThat("второй элемент", burger.ingredients.get(1), sameInstance(mockSauce));
        collector.checkThat("третий элемент", burger.ingredients.get(2), sameInstance(mockFilling));
    }
}