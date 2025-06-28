package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.assertj.core.api.SoftAssertions;

import static org.junit.Assert.assertSame;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    SoftAssertions softAssertions = new SoftAssertions();
    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredientMockFirst;

    @Mock
    private Ingredient ingredientMockSecond;

    @Before
    public void setUp() {
        burger = new Burger();

    }

    @Test
    public void testSetBun() {
        burger.setBuns(bunMock);
        assertSame(burger.bun, bunMock);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredientMockFirst);
        Assert.assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {

        burger.addIngredient(ingredientMockFirst);
        burger.addIngredient(ingredientMockSecond);

        burger.removeIngredient(0);

        softAssertions.assertThat(burger.ingredients.size()).isEqualTo(1);
        softAssertions.assertThat(burger.ingredients.contains(ingredientMockFirst)).isFalse();
        softAssertions.assertThat(burger.ingredients.contains(ingredientMockSecond)).isTrue();
        softAssertions.assertAll();
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredientMockFirst);
        burger.addIngredient(ingredientMockSecond);

        burger.moveIngredient(0, 1);

        softAssertions.assertThat(burger.ingredients.size()).isEqualTo(2);
        softAssertions.assertThat(burger.ingredients.get(0)).isEqualTo(ingredientMockSecond);
        softAssertions.assertThat(burger.ingredients.get(1)).isEqualTo(ingredientMockFirst);
        softAssertions.assertAll();
    }







}
