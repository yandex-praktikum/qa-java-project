package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;

    private List<Ingredient> value;

    @Before
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Bun bun = Mockito.mock(Bun.class);
        when(bun.getName()).thenReturn("burger");
        when(bun.getPrice()).thenReturn((float) 1.0);

        Ingredient ingredient = new Ingredient(
                IngredientType.SAUCE,
                "ingredient",
                (float) 1.0
        );

        Ingredient ingredient0 = new Ingredient(
                IngredientType.FILLING,
                "ingredient0",
                (float) 2.0
        );

        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient0);

        Field field = burger.getClass().getDeclaredField("ingredients");
        field.setAccessible(true);
        value = (List) field.get(burger);
    }

    @Test
    public void removeIngredient() {
        assertEquals(2, value.size());
        assertEquals(IngredientType.SAUCE, value.get(0).getType());
        assertEquals(IngredientType.FILLING, value.get(1).getType());

        burger.removeIngredient(1);

        assertEquals(1, value.size());
        assertEquals(IngredientType.SAUCE, value.get(0).getType());
    }

    @Test
    public void moveIngredient() {
        assertEquals(2, value.size());
        assertEquals(IngredientType.SAUCE, value.get(0).getType());
        assertEquals(IngredientType.FILLING, value.get(1).getType());

        burger.moveIngredient(0, 1);

        assertEquals(2, value.size());
        assertEquals(IngredientType.FILLING, value.get(0).getType());
        assertEquals(IngredientType.SAUCE, value.get(1).getType());
    }

    @Test
    public void getPrice() {
        assertEquals((float) 5.0, burger.getPrice(), 0.0);
    }

    @Test
    public void getReceipt() {
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("sauce ingredient"));
        assertTrue(receipt.contains("Price: 5,000000"));
    }

}