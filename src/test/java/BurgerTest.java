import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun bun;
    private Burger burger;


    @Before
    public void setUp() {
        burger = new Burger();
    }


    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }


    @Test
    public void getPriceTest() {
        when(bun.getPrice()).thenReturn(100F);
        burger.setBuns(bun);

        Ingredient ingredient1 = mock(Ingredient.class);
        when(ingredient1.getPrice()).thenReturn(100F);
        burger.addIngredient(ingredient1);

        Ingredient ingredient2 = mock(Ingredient.class);
        when(ingredient2.getPrice()).thenReturn(100F);
        burger.addIngredient(ingredient2);
        float price = burger.getPrice();

        assertEquals(400F, price, 0.001F);
    }

    @Test
    public void getReceiptTest() {
        when(bun.getName()).thenReturn("red bun");
        burger.setBuns(bun);

        Ingredient ingredient1 = mock(Ingredient.class);
        when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1.getName()).thenReturn("sour cream");
        burger.addIngredient(ingredient1);

        Ingredient ingredient2 = mock(Ingredient.class);
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient2.getName()).thenReturn("dinosaur");
        burger.addIngredient(ingredient2);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("red bun"));
        assertTrue(receipt.contains("sour cream"));
        assertTrue(receipt.contains("dinosaur"));
    }

    @Test
    public void moveIngredientTest() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        assertEquals(ingredient1, burger.ingredients.get(0));
        assertEquals(ingredient2, burger.ingredients.get(1));
        assertEquals(ingredient3, burger.ingredients.get(2));

        burger.moveIngredient(1, 2);

        assertEquals(ingredient1, burger.ingredients.get(0));
        assertEquals(ingredient3, burger.ingredients.get(1));
        assertEquals(ingredient2, burger.ingredients.get(2));
    }
}
