package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    Burger burger;
    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;
    @Mock
    Ingredient nextIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void addIngredient() {
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());

    }

    @Test
    public void removeIngredient() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredient() {
        burger.ingredients.addAll(Arrays.asList(ingredient, nextIngredient));
        List<Ingredient> actual = new ArrayList<>(Arrays.asList(nextIngredient, ingredient));
        burger.moveIngredient(1, 0);
        assertEquals(actual, burger.ingredients);
    }

    @Test
    public void getPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        when(bun.getPrice()).thenReturn(300F);
        when(ingredient.getPrice()).thenReturn(400F);
        assertEquals(1000, burger.getPrice(), 0);
    }

    @Test
    public void getReceipt() {
        burger.setBuns(bun);
        burger.ingredients.add(ingredient);
        Mockito.when(bun.getName()).thenReturn("Тестовая булочка");
        Mockito.when(bun.getPrice()).thenReturn(200F);
        Mockito.when(ingredient.getType()).thenReturn(FILLING);
        Mockito.when(ingredient.getName()).thenReturn("Тестовый наполнитель");
        Mockito.when(ingredient.getPrice()).thenReturn(100F);
        String expected = String.format("(==== %s ====)%n", bun.getName()) +
                String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(), ingredient.getName()) +
                String.format("(==== %s ====)%n", bun.getName()) +
                String.format("%nPrice: %f%n", burger.getPrice());
        assertEquals(expected, burger.getReceipt());
    }
}