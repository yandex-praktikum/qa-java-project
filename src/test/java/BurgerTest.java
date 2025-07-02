import java.util.stream.Stream;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class BurgerTest {

    private final Burger burger = new Burger();
    private final Ingredient catchup = new Ingredient(SAUCE, "Кекчук", 50);
    private final Ingredient horse = new Ingredient(FILLING, "Конина", 505);
    private final Bun bun = new Bun("Серая", 500);

    private static Stream<Arguments> burgerPriceProvider() {
        return Stream.of(
                Arguments.of(2.0f, new float[]{1.0f, 0.5f, 2.0f}, 7.5f),
                Arguments.of(1.5f, new float[]{0.5f, 0.5f, 0.5f}, 4.5f),
                Arguments.of(3.0f, new float[]{1.0f, 1.0f, 1.0f}, 9.0f)
        );
    }

    @Test
    @DisplayName("Установка булки")
    public void setBunTest() {
        burger.setBuns(bun);
        Assertions.assertEquals(bun, burger.bun);
    }

    @Test
    @DisplayName("Добавление ингридиентов")
    public void addIngredientsTest() {
        burger.addIngredient(horse);
        burger.addIngredient(catchup);

        Assertions.assertEquals(2, burger.ingredients.size());
    }

    @Test
    @DisplayName("Удаление ингридиентов")
    public void deleteIngredientsTest() {

        burger.addIngredient(horse);
        burger.addIngredient(catchup);
        burger.removeIngredient(0);

        Assertions.assertEquals(1, burger.ingredients.size());
    }

    @ParameterizedTest
    @MethodSource("burgerPriceProvider")
    public void GetPriceTest(float bunPrice, float[] ingredientPrices, float expectedPrice) {

        Bun bunMock = mock(Bun.class);
        when(bunMock.getPrice()).thenReturn(bunPrice);

        Burger burger = new Burger();
        burger.setBuns(bunMock);

        for (float price : ingredientPrices) {
            Ingredient ingredientMock = mock(Ingredient.class);
            when(ingredientMock.getPrice()).thenReturn(price);
            burger.addIngredient(ingredientMock);
        }

        assertEquals(expectedPrice, burger.getPrice(), 0.001);
    }

    @Test
    @DisplayName("Проверка рецепта")
    public void getReceptTest() {
        burger.setBuns(bun);
        burger.addIngredient(horse);
        burger.addIngredient(catchup);
        String receipt = burger.getReceipt();

        assertTrue(() -> receipt.contains("Кекчук") &&
                receipt.contains("Конина") &&
                receipt.contains("Серая") &&
                receipt.contains("1555"));

    }
}


