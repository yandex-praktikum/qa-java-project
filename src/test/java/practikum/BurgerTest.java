package practikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.assertj.core.api.SoftAssertions;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest extends BurgerTestBase {

    @Before
    public void setUp() {
        super.init();
    }

    @Test
    public void testSetBunsName() {
        when(bun.getName()).thenReturn("black bun");
        burger.setBuns(bun);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.bun.getName()).isEqualTo("black bun");
        softly.assertAll();
    }

    @Test
    public void testSetBunsPrice() {
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.bun.getPrice()).isEqualTo(100f);
        softly.assertAll();
    }

    @Test
    public void testAddIngredientSize() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients).hasSize(1);
        softly.assertAll();
    }

    @Test
    public void testRemoveIngredientSize() {
        Ingredient sauce = mock(Ingredient.class);
        Ingredient filling = mock(Ingredient.class);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients).hasSize(1);
        softly.assertAll();
    }

    @Test
    public void testMoveIngredientPosition() {
        Ingredient sauce1 = mock(Ingredient.class);
        Ingredient filling = mock(Ingredient.class);
        when(sauce1.getType()).thenReturn(IngredientType.SAUCE);
        when(filling.getType()).thenReturn(IngredientType.FILLING);

        burger.addIngredient(sauce1);
        burger.addIngredient(filling);
        burger.moveIngredient(1, 0);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(0).getType()).isEqualTo(IngredientType.FILLING);
        softly.assertThat(burger.ingredients.get(1).getType()).isEqualTo(IngredientType.SAUCE);
        softly.assertAll();
    }

    @Test
    public void testGetPriceCalculation() {
        when(bun.getPrice()).thenReturn(100f);
        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.getPrice()).isEqualTo(250f); // 100*2 + 50
        softly.assertAll();
    }

    @Test
    public void testGetReceiptContainsBun() {
        when(bun.getName()).thenReturn("white bun");
        burger.setBuns(bun);

        String receipt = burger.getReceipt();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receipt).contains("(==== white bun ====)");
        softly.assertAll();
    }

    @Test
    public void testGetReceiptContainsIngredients() {
        when(bun.getName()).thenReturn("white bun");
        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");

        burger.setBuns(bun);
        burger.addIngredient(sauce);

        String receipt = burger.getReceipt();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receipt).contains("= sauce hot sauce =");
        softly.assertAll();
    }

    @Test
    public void testGetReceiptWithNoIngredients() {
        when(bun.getName()).thenReturn("red bun");
        burger.setBuns(bun);

        String receipt = burger.getReceipt();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receipt).contains("(==== red bun ====)");
        softly.assertThat(receipt).doesNotContain("= ");
        softly.assertAll();
    }
}