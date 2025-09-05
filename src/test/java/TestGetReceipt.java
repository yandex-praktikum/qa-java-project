import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class TestGetReceipt {

    private Bun bun;
    private Ingredient[] ingredients;
    private String expectedReceipt;

    public TestGetReceipt (Bun bun,Ingredient[] ingredients,String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters (name = "Булочка {0}, Ингридиенты {1}, Рецепт {2}")
    public static Collection<Object[]> data(){
        Bun bun = Mockito.mock(Bun.class);
        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);

        when(bun.getName()).thenReturn("red bun");
        when(bun.getPrice()).thenReturn(300f);

        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("sour cream");
        when(ingredient1.getPrice()).thenReturn(200f);

        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getName()).thenReturn("sausage");
        when(ingredient2.getPrice()).thenReturn(300f);

        String expectedReceipt = String.format("(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n%n" +
                        "Price: %f%n",
                "red bun",
                ingredient1.getType().toString().toLowerCase(), ingredient1.getName(),
                ingredient2.getType().toString().toLowerCase(), ingredient2.getName(),
                "red bun",
                2 * 300f + 200f + 300f);

        return Arrays.asList(new Object[][] {
                {bun, new Ingredient[]{ingredient1, ingredient2}, expectedReceipt}
        });
    }

    @Test
    public  void testGetReceipt () {
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        assertEquals(expectedReceipt,burger.getReceipt());
    }

}
