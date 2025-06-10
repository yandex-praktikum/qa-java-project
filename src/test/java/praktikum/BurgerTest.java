package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.Arrays;

public class BurgerTest {

    private Burger burgerObject;

    @Mock
    private Ingredient onion;

    @Mock
    private Ingredient tomato;

    @Mock
    private Ingredient cheese;

    @Mock
    private Ingredient patty;

    @Mock
    private Bun mockBun;

    @Before
    public void setUp(){
        burgerObject = new Burger();
        burgerObject.ingredients.add(onion);
        burgerObject.ingredients.add(tomato);
        burgerObject.ingredients.add(cheese);
        burgerObject.ingredients.add(patty);
    }

    @Test
    public void setBunsShouldChangeBunTest() {
        burgerObject.setBuns(mockBun);
        Assert.assertEquals(mockBun, burgerObject.bun);
    }

    @Test
    public void addIngredientShouldIncreaseListTest() {
        int sizeBefore = burgerObject.ingredients.size();
        burgerObject.addIngredient(cheese);
        Assert.assertEquals(sizeBefore + 1, burgerObject.ingredients.size());
    }

    @Test
    public void removeIngredientTest() {
        int sizeBefore = burgerObject.ingredients.size();
        burgerObject.removeIngredient(2);
        Assert.assertEquals(sizeBefore - 1, burgerObject.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {
        int expectedPos = 2;
        int onionPosBefore = burgerObject.ingredients.indexOf(onion);
        burgerObject.moveIngredient(onionPosBefore,expectedPos);
        Assert.assertEquals(new ArrayList<>(Arrays.asList(tomato, cheese, onion, patty)), burgerObject.ingredients );

    }

}