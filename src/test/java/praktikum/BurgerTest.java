package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BurgerTest {
    private final SoftAssertions assertions = new SoftAssertions();

    @Test
    public void shouldSetBuns() {
        Bun bun = Mockito.mock(Bun.class);
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void shouldAddIngredient() {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        assertions.assertThat(burger.ingredients.size()).isEqualTo(1);
        assertions.assertThat(burger.ingredients.get(0)).isEqualTo(ingredient);
        assertions.assertAll();
    }

    @Test
    public void shouldRemoveIngredient() {
        Ingredient firstIngredient = Mockito.mock(Ingredient.class);
        Ingredient secondIngredient = Mockito.mock(Ingredient.class);
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.removeIngredient(0);
        assertions.assertThat(burger.ingredients.size()).isEqualTo(1);
        assertions.assertThat(burger.ingredients.get(0)).isEqualTo(secondIngredient);
        assertions.assertAll();
    }

    @Test
    public void shouldMoveIngredient() {
        Ingredient firstIngredient = Mockito.mock(Ingredient.class);
        Ingredient secondIngredient = Mockito.mock(Ingredient.class);
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 1);
        assertions.assertThat(burger.ingredients.get(0)).isEqualTo(secondIngredient);
        assertions.assertThat(burger.ingredients.get(1)).isEqualTo(firstIngredient);
        assertions.assertAll();
    }

    @Test
    public void shouldGetPriceWhenBunsAndIngredientAdded() {
        Bun bun = Mockito.mock(Bun.class);
        when(bun.getPrice()).thenReturn(100.0f);
        Ingredient firstIngredient = Mockito.mock(Ingredient.class);
        when(firstIngredient.getPrice()).thenReturn(50.0f);
        Ingredient secondIngredient = Mockito.mock(Ingredient.class);
        when(secondIngredient.getPrice()).thenReturn(75.0f);
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        assertEquals(325.0f, burger.getPrice(), 0.001);
    }

    @Test
    public void shouldGetReceiptWhenBunsAndIngredientsAdded() {
        Bun bun = Mockito.mock(Bun.class);
        when(bun.getName()).thenReturn("Булка");
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("Соус");
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String receipt = burger.getReceipt();
        assertions.assertThat(receipt).contains("(==== Булка ====)");
        assertions.assertThat(receipt).contains("= sauce Соус =");
        assertions.assertAll();
    }

    @Test
    public void shouldGetPriceWhenMultipleIngredientsAdded() {
        Bun bun = Mockito.mock(Bun.class);
        when(bun.getPrice()).thenReturn(50.0f);
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (int i = 0; i < 5; i++) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            when(ingredient.getPrice()).thenReturn(20.0f);
            burger.addIngredient(ingredient);
        }
        assertEquals(200.0f, burger.getPrice(), 0.001);
    }

    @Test
    public void shouldGetReceiptWhenBurgerHasNoIngredients() {
        Bun bun = Mockito.mock(Bun.class);
        when(bun.getName()).thenReturn("Булка");
        Burger burger = new Burger();
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertions.assertThat(receipt).contains("(==== Булка ====)");
        assertions.assertThat(receipt.split("\\n").length - 2).isEqualTo(2);
        assertions.assertAll();
    }

    @Test
    public void shouldNotChangePositionWhenMovedToTheSamePosition() {
        Ingredient firstIngredient = Mockito.mock(Ingredient.class);
        Ingredient secondIngredient = Mockito.mock(Ingredient.class);
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 0);
        assertions.assertThat(burger.ingredients.get(0)).isEqualTo(firstIngredient);
        assertions.assertThat(burger.ingredients.get(1)).isEqualTo(secondIngredient);
        assertions.assertAll();
    }

    @Test
    public void shouldRemoveIngredientWhenLastIndex() {
        Ingredient firstIngredient = Mockito.mock(Ingredient.class);
        Ingredient secondIngredient = Mockito.mock(Ingredient.class);
        Burger burger = new Burger();
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.removeIngredient(1);
        assertions.assertThat(burger.ingredients).hasSize(1);
        assertions.assertThat(burger.ingredients.get(0)).isEqualTo(firstIngredient);
        assertions.assertAll();
    }

    @Test
    public void shouldAddIngredientsWhenCalledMultipleTimes() {
        Burger burger = new Burger();
        for (int i = 0; i < 10; i++) {
            Ingredient ingredient = Mockito.mock(Ingredient.class);
            burger.addIngredient(ingredient);
        }
        assertEquals(10, burger.ingredients.size());
    }
}