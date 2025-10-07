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
public class BurgerIngredientTest {

    @Mock
    private Bun bun;
    @Mock
    private Ingredient sauceIngredient;
    @Mock
    private Ingredient fillingIngredient;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testAddSauce() {


        Ingredient expectedSauce = sauceIngredient;


        burger.addIngredient(expectedSauce);


        Ingredient actualSauce = burger.ingredients.get(0);


        assertEquals("Первый ингредиент должен быть соусом", expectedSauce, actualSauce);


    }

    @Test
    public void testAddFilling() {


        Ingredient expectedFilling = fillingIngredient;


        burger.addIngredient(expectedFilling);


        Ingredient actualFilling = burger.ingredients.get(0);


        assertEquals("Второй ингредиент должен быть начинкой", expectedFilling, actualFilling);

    }


    @Test
    public void testRemoveIngredient() {

        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);
        int expectedCount = 1;


        burger.removeIngredient(0);
        int actualCount = burger.ingredients.size();


        assertEquals("Должен остаться один ингредиент", expectedCount, actualCount);
    }

    @Test
    public void testMoveIngredient() {

        burger.addIngredient(sauceIngredient);
        burger.addIngredient(fillingIngredient);


        Ingredient expectedSauceIngredient = fillingIngredient;


        burger.moveIngredient(0, 1);
        Ingredient actualFirstIngredient = burger.ingredients.get(0);


        assertEquals("Первый ингредиент должен быть начинкой",
                expectedSauceIngredient, actualFirstIngredient);

    }


}
