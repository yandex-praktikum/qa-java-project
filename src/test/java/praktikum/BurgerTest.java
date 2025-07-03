package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    private final List<Ingredient> ingredients;
    private final float expectedPrice;

    public BurgerTest(List<Ingredient> ingredients, float expectedPrice) {
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Arrays.asList(createMockIngredient(100.0f, "SAUCE", "Chili")), 200.0f + 100.0f},
                {Arrays.asList(
                        createMockIngredient(50.0f, "SAUCE", "Hot"),
                        createMockIngredient(75.0f, "FILLING", "Cutlet")
                ), 200.0f + 125.0f}
        });
    }

    private static Ingredient createMockIngredient(float price, String type, String name) {
        Ingredient ingredient = org.mockito.Mockito.mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(price);
        when(ingredient.getType()).thenReturn(IngredientType.valueOf(type));
        when(ingredient.getName()).thenReturn(name);
        return ingredient;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();

        when(bun.getPrice()).thenReturn(100.0f);
        when(bun.getName()).thenReturn("Black Bun");
        burger.setBuns(bun);

        when(ingredient1.getPrice()).thenReturn(50.0f);
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("Chili Sauce");

        when(ingredient2.getPrice()).thenReturn(75.0f);
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getName()).thenReturn("Cutlet");
    }

    @Test
    public void setBuns_SetsBunCorrectly() {
        Burger testBurger = new Burger();
        testBurger.setBuns(bun);
        assertSame(bun, testBurger.bun);
    }

    @Test
    public void addIngredient_IncreasesIngredientsSize() {
        int initialSize = burger.ingredients.size();
        burger.addIngredient(ingredient1);
        assertEquals(initialSize + 1, burger.ingredients.size());
    }

    @Test
    public void removeIngredient_DecreasesIngredientsSize() {
        burger.addIngredient(ingredient1);
        int initialSize = burger.ingredients.size();
        burger.removeIngredient(0);
        assertEquals(initialSize - 1, burger.ingredients.size());
    }

    @Test
    public void moveIngredient_ChangesIngredientPosition() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        Ingredient firstIngredient = burger.ingredients.get(0);
        burger.moveIngredient(0, 1);

        assertEquals(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void getPrice_ReturnsCorrectPrice() {
        burger.addIngredient(ingredient1);
        float expected = 100.0f * 2 + 50.0f;
        assertEquals(expected, burger.getPrice(), 0.01);
    }

    @Test
    public void getReceipt_ContainsBunName() {
        burger.addIngredient(ingredient1);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Black Bun"));
    }

    @Test
    public void getReceipt_ContainsIngredientName() {
        burger.addIngredient(ingredient1);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Chili Sauce"));
    }

    @Test
    public void getReceipt_ContainsTotalPrice() {
        burger.addIngredient(ingredient1);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("Price:"));
    }

    @Test
    public void parameterizedGetPrice_ReturnsCorrectPriceForDifferentIngredients() {
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }
}