import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TestBurger {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient1;
    @Mock
    Ingredient ingredient2;


    private Burger burger;

    @Before
    public void setUp () {
        burger = new Burger();
    }


    @Test
    public void testSetBuns () {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

   @Test
    public void testAddIngredient () {
        burger.addIngredient(ingredient1);
        assertEquals(1,burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
   }

   @Test
    public void testRemoveIngredient () {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertEquals(1,burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));

   }

   @Test
    public void testMoveIngredient () {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0,1);
        assertEquals(ingredient1, burger.ingredients.get(1));
        assertEquals(ingredient2, burger.ingredients.get(0));
   }


}
