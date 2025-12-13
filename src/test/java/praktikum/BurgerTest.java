package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;

public class BurgerTest {
    Bun bunMock = mock(Bun.class);
    Ingredient ingredientObj = mock(Ingredient.class);

    @Test
    public void testSetBuns() {
        Burger burgerObj = new Burger();
        burgerObj.setBuns(bunMock);
        assertEquals(bunMock, burgerObj.bun);
    }

    @Test
    public void testAddIngredient() {
        Burger burgerObj = new Burger();
        burgerObj.addIngredient(ingredientObj);
        burgerObj.addIngredient(ingredientObj);
        assertEquals(2, burgerObj.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        Burger burgerObj = new Burger();
        Ingredient ingredientFirstObj = mock(Ingredient.class);
        Ingredient ingredientSecondObj = mock(Ingredient.class);
        burgerObj.addIngredient(ingredientFirstObj);
        burgerObj.addIngredient(ingredientSecondObj);
        burgerObj.removeIngredient(0);
        assertEquals(1, burgerObj.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        Burger burgerObj = new Burger();
        Ingredient ingredientFirstObj = mock(Ingredient.class);
        Ingredient ingredientSecondObj = mock(Ingredient.class);
        burgerObj.addIngredient(ingredientFirstObj);
        burgerObj.addIngredient(ingredientSecondObj);
        burgerObj.moveIngredient(1, 0);
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(burgerObj.ingredients.get(1)).isEqualTo(ingredientFirstObj);
            softAssertions.assertThat(burgerObj.ingredients.get(0)).isEqualTo(ingredientSecondObj);
        });
    }
}