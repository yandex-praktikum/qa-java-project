import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerNonParameterizedTest {

    @Test
    public void setBuns_setsBunCorrectly() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);

        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    @Test
    public void addIngredient_addsIngredientToList() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredient_addsExactIngredient() {
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);

        assertSame(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void getPrice_returnsCorrectPrice() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        Ingredient tomato = mock(Ingredient.class);
        Ingredient cutlet = mock(Ingredient.class);
        Ingredient ketchup = mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(3.0f);
        when(tomato.getPrice()).thenReturn(10.0f);
        when(cutlet.getPrice()).thenReturn(5.0f);
        when(ketchup.getPrice()).thenReturn(1.0f);

        burger.setBuns(bun);
        burger.addIngredient(tomato);
        burger.addIngredient(cutlet);
        burger.addIngredient(ketchup);

        assertEquals(22.0f, burger.getPrice(), 0.01f);
    }
}
