package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;
    @Mock
    private Ingredient ingredient2;

    @Test
    public void setBunsIsSuccess() {
        Burger burger = new Burger();
        Mockito.when(bun.getName()).thenReturn("bunName");
        burger.setBuns(bun);

        MatcherAssert.assertThat(
                "В бургер не добавляются передаваемые булочки",
                bun.getName(),
                equalTo(burger.bun.getName())
        );
    }
    @Test
    public void addIngredientIsSuccess() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);

        assertTrue(
                "Ингредиент не добавлен",
                burger.ingredients.contains(ingredient)
        );
    }
    @Test
    public void moveIngredientIsSuccess() {
        Mockito.when(ingredient.getName()).thenReturn("ingredientName");

        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        int currentIndex = burger.ingredients.indexOf(ingredient);
        int newIndex = burger.ingredients.indexOf(ingredient2);

        burger.moveIngredient(currentIndex, newIndex);

        MatcherAssert.assertThat(
                "Ингредиент не перемещается",
                ingredient.getName(),
                equalTo(burger.ingredients.get(newIndex).getName())
        );
    }
    @Test
    public void removeIngredientIsSuccess() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);

        int index = burger.ingredients.indexOf(ingredient);
        burger.removeIngredient(index);

        assertFalse(
                "Ингредиент не удаляется",
                burger.ingredients.contains(ingredient)
        );
    }
    @Test
    public void getPriceIsSuccess() {
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredient.getPrice()).thenReturn(250f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        MatcherAssert.assertThat(
                "Цена расчитывается некорректно",
                450f,
                equalTo(burger.getPrice())
        );

    }
    @Test
    public void getReceiptIsSuccess() {
        Mockito.when(bun.getName()).thenReturn("bunName");
        Mockito.when(bun.getPrice()).thenReturn(80f);
        Mockito.when(ingredient.getName()).thenReturn("ingredientName");
        Mockito.when(ingredient.getPrice()).thenReturn(100f);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);

        String lineSeparator = System.lineSeparator();
        String expectedReceipt =
                "(==== bunName ====)" + lineSeparator +
                        "= filling ingredientName =" + lineSeparator +
                        "(==== bunName ====)" + lineSeparator +
                        lineSeparator +
                        "Price: 260,000000" + lineSeparator;

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        MatcherAssert.assertThat(
                "Возвращается некорректная строка с рецептом",
                burger.getReceipt(),
                equalTo(expectedReceipt)
        );
    }
}
