package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    Burger burger = new Burger();

    @Mock
    private Bun bun;

    @Mock
    private Ingredient first_ingredient;

    @Mock
    private Ingredient second_ingredient;

    @Mock
    private Ingredient third_ingredient;

    //метод-хэлпер
    public void addMiltipleIngredients() {
        burger.addIngredient(first_ingredient);
        burger.addIngredient(second_ingredient);
        burger.addIngredient(third_ingredient);
    }


    @Test
    public void shouldSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void shouldSetNullBun() {
        burger.setBuns(null);
        assertNull(burger.bun);
    }

    @Test
    public void shouldAddIngredient() {
        burger.addIngredient(first_ingredient);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size()).isEqualTo(1);
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(first_ingredient);
        softly.assertAll();
    }

    @Test
    public void shouldAddMultipleIngredients() {
        addMiltipleIngredients();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size()).isEqualTo(3);
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(first_ingredient);
        softly.assertThat(burger.ingredients.get(1)).isEqualTo(second_ingredient);
        softly.assertThat(burger.ingredients.get(2)).isEqualTo(third_ingredient);
        softly.assertAll();
    }

    @Test
    public void removeIngredientShouldThrowExceptionWhenIndexGreaterThanSize() {
        addMiltipleIngredients();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(3));
    }

    @Test
    public void removeIngredientShouldThrowExceptionWhenListIsEmpty() {
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(0));
    }

    @Test
    public void moveIngredientShouldThrowExceptionWhenNewIndexGreaterThanSize() {
        addMiltipleIngredients();
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(0, 3));
    }

    @Test
    public void getReceiptShoudBePositiveWithEmptyIngredientsList() {
        Bun bun = Mockito.mock(Bun.class);
        Mockito.when(bun.getName()).thenReturn("classic bun");
        Mockito.when(bun.getPrice()).thenReturn(100f);

        Burger burger = new Burger();
        burger.setBuns(bun);

        String expected = String.format("(==== %s ====)%n", "classic bun")
                + String.format("(==== %s ====)%n", "classic bun")
                + String.format("%nPrice: %f%n", 200f);

        String actual = burger.getReceipt();
        assertEquals(expected, actual);
    }

    @Test
    public void getReceiptShouldThrowExceptionWhenBunNotSet() {
        Burger burger = new Burger();
        Assert.assertThrows(NullPointerException.class, () -> burger.getReceipt());
    }
}
