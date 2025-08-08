package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BurgerTest {

    private static final float DELTA = 0.001f;

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBurgerBun() {
        burger.setBuns(bun);
        assertEquals("Булка должна быть установлена", bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredientToList() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveCorrectItem() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldChangeOrderOfIngredients() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(1, 0);

        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceShouldReturnCorrectTotal() {
        when(bun.getPrice()).thenReturn(125f);
        when(ingredient1.getPrice()).thenReturn(80f);
        when(ingredient2.getPrice()).thenReturn(300f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = 125f * 2 + 80f + 300f;
        assertEquals(expectedPrice, burger.getPrice(), DELTA);
    }

    @Test
    public void getReceiptShouldReturnCorrectFormat() {
        when(bun.getName()).thenReturn("Флюоресцентная булка R2-D3");
        when(bun.getPrice()).thenReturn(125f);

        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("Соус фирменный");
        when(ingredient1.getPrice()).thenReturn(80f);

        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getName()).thenReturn("Мясо бессмертных моллюсков Protostomia");
        when(ingredient2.getPrice()).thenReturn(300f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String expectedReceipt = String.format(
                "(==== %s ====)\n" +
                        "= %s %s =\n" +
                        "= %s %s =\n" +
                        "(==== %s ====)\n" +
                        "\nPrice: %.1f\n",
                bun.getName(),
                ingredient1.getType().toString().toLowerCase(),
                ingredient1.getName(),
                ingredient2.getType().toString().toLowerCase(),
                ingredient2.getName(),
                bun.getName(),
                125f * 2 + 80f + 300f
        );

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
