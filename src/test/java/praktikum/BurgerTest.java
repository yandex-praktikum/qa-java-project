package praktikum;

import org.junit.Test;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.*;


public class BurgerTest extends BaseTest {

    @Test
    public void setBunsTest() {
        when(bunMock.getName()).thenReturn("Супербулка");
        when(bunMock.getPrice()).thenReturn(30.0f);

        burger.setBuns(bunMock);

        softAssertions.assertThat(burger.bun.getName()).isEqualTo("Супербулка");
        softAssertions.assertThat(burger.bun.getPrice()).isEqualTo(30.0f);
        softAssertions.assertAll();
    }

    @Test
    public void addIngredientTest() {
        burger.ingredients.clear();
        burger.addIngredient(ingredientMock);
        softAssertions.assertThat(burger.ingredients.contains(ingredientMock)).isTrue();

        burger.ingredients.clear();
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock1);
        softAssertions.assertThat(burger.ingredients.size()).isEqualTo(2);
        softAssertions.assertAll();
    }

    @Test
    public void removeIngredientTest() {
        burger.ingredients.clear();
        softAssertions.assertThat(burger.ingredients.isEmpty()).isTrue();

        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.removeIngredient(0);
        burger.removeIngredient(0);

        softAssertions.assertThat(burger.ingredients.size()).isEqualTo(1);
        softAssertions.assertThat(burger.ingredients.get(0)).isEqualTo(ingredientMock2);
        softAssertions.assertThat(burger.ingredients.contains(ingredientMock)).isFalse();
        softAssertions.assertThat(burger.ingredients.contains(ingredientMock1)).isFalse();
        softAssertions.assertThat(burger.ingredients.contains(ingredientMock2)).isTrue();

        softAssertions.assertAll();
    }

    @Test
    public void moveIngredientTest() {
        burger.ingredients.clear();
        softAssertions.assertThat(burger.ingredients.isEmpty()).isTrue();

        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock1);
        burger.moveIngredient(0, 1);

        softAssertions.assertThat(burger.ingredients.get(1)).isEqualTo(ingredientMock);
        softAssertions.assertAll();
    }

    @Test
    public void getPriceTest() {
        when(bunMock.getPrice()).thenReturn(30.0f);
        when(ingredientMock.getPrice()).thenReturn(5.0f);
        when(ingredientMock1.getPrice()).thenReturn(10.0f);
        when(ingredientMock2.getPrice()).thenReturn(15.0f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float expected = 90.0f;
        float actual = burger.getPrice();

        softAssertions.assertThat(actual)
                .as("Цена бургера рассчитана неверно")
                .isEqualTo(expected, within(0.5f));
        softAssertions.assertAll();
    }

    @Test
    public void getReceiptTest() {
        when(bunMock.getName()).thenReturn("Флюоресцентная булка R2-D3");
        when(ingredientMock.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock.getName()).thenReturn("сыр с астероидной плесенью");
        when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock1.getName()).thenReturn("соус с шипами антарианского плоскоходца");

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock);
        burger.addIngredient(ingredientMock1);

        String expected = "(==== Флюоресцентная булка R2-D3 ====)%n" +
                "= filling сыр с астероидной плесенью =%n" +
                "= sauce соус с шипами антарианского плоскоходца =%n" +
                "(==== Флюоресцентная булка R2-D3 ====)%n" +
                "%n" +
                "Price: %.6f%n";

        float price = burger.getPrice();
        String expectedFormatted = String.format(expected, price);

        String actual = burger.getReceipt();

        softAssertions.assertThat(actual)
                .as("Чек должен полностью совпадать с ожидаемым форматом")
                .isEqualTo(expectedFormatted);

        softAssertions.assertAll();
    }
}