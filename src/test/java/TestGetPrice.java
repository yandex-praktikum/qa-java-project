import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class TestGetPrice {

    private Bun bun;
    private Ingredient[] ingredients;
    private float expectedPrice;

    public TestGetPrice (Bun bun, Ingredient[] ingredients, float expectedPrice) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters (name = "Булочка {0}, Ингридиенты {1}, Цена {2}")
    public static Collection<Object[]> data(){
        Bun bun = Mockito.mock(Bun.class);
        Ingredient ingredientFirst = Mockito.mock(Ingredient.class);
        Ingredient ingredientSecond = Mockito.mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(1f);
        when(ingredientFirst.getPrice()).thenReturn(1f);
        when(ingredientSecond.getPrice()).thenReturn(0.5f);

        return Arrays.asList(new Object[][] {
                {bun, new Ingredient[]{},2f},
                {bun, new Ingredient[]{ingredientFirst},3f},
                {bun, new Ingredient[]{ingredientFirst, ingredientSecond},3.5f},
        });
    }

    @Test
    public void testGetPrice () {
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        assertEquals(expectedPrice,burger.getPrice(), 0.001);
    }

}
