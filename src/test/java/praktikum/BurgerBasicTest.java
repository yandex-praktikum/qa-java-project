package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BurgerBasicTest extends BaseTest {
    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }
    @Test
    public void testAddIngredientIncreasesSize() {
        burger.addIngredient(mockSauce);
        assertEquals(1, burger.ingredients.size());
    }
    @Test
    public void testAddIngredientAddsCorrect() {
        burger.addIngredient(mockSauce);
        assertEquals(mockSauce,burger.ingredients.get(0));
    }
    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockSauce);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }
    @Test
    public void testMoveIngredient() {
        SoftAssertions softly = new SoftAssertions();
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        burger.moveIngredient(0,1);

        softly.assertThat(burger.ingredients.get(0)).isEqualTo(mockFilling);
        softly.assertThat(burger.ingredients.get(1)).isEqualTo(mockSauce);
        softly.assertAll();
    }
}
