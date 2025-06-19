import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;


    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient_FILLING;

    @Mock
    private Ingredient ingredient_SAUCE;


    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals("", bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        List<Ingredient> expected = List.of(ingredient_FILLING);
        burger.addIngredient(ingredient_FILLING);
        assertEquals("", expected, burger.ingredients);
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredient_FILLING);
        burger.removeIngredient(0);
        List<Ingredient> expected = new ArrayList<>();
        assertEquals("Метод не удаляет ингредиенты", expected, burger.ingredients);
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredient_FILLING);
        burger.addIngredient(ingredient_SAUCE);
        burger.moveIngredient(1,0);
        assertEquals("", ingredient_SAUCE, burger.ingredients.get(0));
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        when(bun.getPrice()).thenReturn(100f);
        burger.addIngredient(ingredient_FILLING);
        burger.addIngredient(ingredient_SAUCE);
        when(ingredient_FILLING.getPrice()).thenReturn(200f);
        when(ingredient_SAUCE.getPrice()).thenReturn(200f);
        assertEquals(600f, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptTest() {
        String getName = "Big";
        String getType = "filling";
        String getIngredientName = "cheese";
        float getPrice = 400f;

        StringBuilder expectedReceipt = new StringBuilder(String.format("(==== %s ====)%n", getName));
        expectedReceipt.append(String.format("= %s %s =%n", getType.toLowerCase(), getIngredientName));
        expectedReceipt.append(String.format("(==== %s ====)%n", getName));
        expectedReceipt.append(String.format("%nPrice: %f%n", getPrice));

        burger.setBuns(bun);
        burger.addIngredient(ingredient_FILLING);

        when(bun.getName()).thenReturn("Big");
        when(ingredient_FILLING.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient_FILLING.getName()).thenReturn("cheese");

        when(bun.getPrice()).thenReturn(100f);
        when(ingredient_FILLING.getPrice()).thenReturn(200f);

        assertEquals(expectedReceipt.toString(), burger.getReceipt());
    }

}
