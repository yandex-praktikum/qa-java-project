package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size()).isEqualTo(1);
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(ingredient);
        softly.assertAll();
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        Ingredient secondIngredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 1);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size()).isEqualTo(2);
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(secondIngredient);
        softly.assertThat(burger.ingredients.get(1)).isEqualTo(ingredient);
        softly.assertAll();
    }

    @Test
    public void testGetPrice() {
        when(bun.getPrice()).thenReturn(50f);
        when(ingredient.getPrice()).thenReturn(200f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertEquals(300f, burger.getPrice(), 0.01);
    }
}