package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient sauce;
    private Ingredient filling;

    @Before
    public void setUp() {
        burger = new Burger();
        bun = new Bun("white bun", 100f);
        sauce = new Ingredient(IngredientType.SAUCE, "hot sauce", 50f);
        filling = new Ingredient(IngredientType.FILLING, "cutlet", 200f);
    }

    @Test
    public void setBunsSetsBunField() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientAddsToList() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        List<Ingredient> ingredients = burger.ingredients;
        assertEquals(2, ingredients.size());
        assertEquals(sauce, ingredients.get(0));
        assertEquals(filling, ingredients.get(1));
    }

    @Test
    public void removeIngredientRemovesByIndex() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        List<Ingredient> ingredients = burger.ingredients;
        assertEquals(1, ingredients.size());
        assertEquals(filling, ingredients.get(0));
    }

    @Test
    public void moveIngredientChangesOrder() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        List<Ingredient> ingredients = burger.ingredients;
        assertEquals(filling, ingredients.get(0));
        assertEquals(sauce, ingredients.get(1));
    }

    @Test
    public void getPriceCalculatesCorrectSum() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);   // 50
        burger.addIngredient(filling); // 200

        float expected = 100f * 2 + 50f + 200f;  // 450
        assertEquals(expected, burger.getPrice(), 0.0f);
    }

    @Test
    public void getPriceWorksWithMockitoMocks() {
        Burger burger = new Burger();

        Bun bunMock = Mockito.mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(100f);

        Ingredient ingredientMock = Mockito.mock(Ingredient.class);
        when(ingredientMock.getPrice()).thenReturn(50f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);

        float expected = 100f * 2 + 50f;
        assertEquals(expected, burger.getPrice(), 0.0f);
    }

    @Test
    public void getReceiptReturnsFormattedString() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();

        // первая строка с названием булки
        assertTrue(receipt.startsWith(String.format("(==== %s ====)%n", bun.getName())));
        // строки с ингредиентами
        assertTrue(receipt.contains("sauce hot sauce"));
        assertTrue(receipt.contains("filling cutlet"));
        // строка с ценой
        String expectedPriceLine = String.format("Price: %f%n", burger.getPrice());
        assertTrue(receipt.contains(expectedPriceLine));
    }
}
