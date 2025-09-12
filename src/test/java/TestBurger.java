import org.assertj.core.api.SoftAssertions;
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
    Ingredient ingredientFirst;
    @Mock
    Ingredient ingredientSecond;


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
        burger.addIngredient(ingredientFirst);

       SoftAssertions soft = new SoftAssertions();
       soft.assertThat(burger.ingredients.size()).isEqualTo(1);
       soft.assertThat(burger.ingredients.get(0)).isEqualTo(ingredientFirst);
       soft.assertAll();

   }

   @Test
    public void testRemoveIngredient () {
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);
        burger.removeIngredient(0);

       SoftAssertions soft = new SoftAssertions();
       soft.assertThat(burger.ingredients.size()).isEqualTo(1);
       soft.assertThat(burger.ingredients.get(0)).isEqualTo(ingredientSecond);
       soft.assertAll();
   }

   @Test
    public void testMoveIngredient () {
        burger.addIngredient(ingredientFirst);
        burger.addIngredient(ingredientSecond);
        burger.moveIngredient(0,1);

       SoftAssertions soft = new SoftAssertions();
       soft.assertThat(burger.ingredients.get(1)).isEqualTo(ingredientFirst);
       soft.assertThat(burger.ingredients.get(0)).isEqualTo(ingredientSecond);
       soft.assertAll();
       
   }


}
