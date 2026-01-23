package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static utils.Utils.getRandomBun;
import static utils.Utils.getRandomIngredient;

@ExtendWith(MockitoExtension.class)
public class BurgerTests {

    @Mock
    private Bun bunMock;

    @Test
    void testCanSetBunsOfBurger() {

        Burger burger = new Burger();
        Bun bun = getRandomBun();
        burger.setBuns(bun);

        assertEquals(bun.getName(), burger.bun.getName());
    }

    @Test
    void testCanAddIngredientToBurger() {

        Burger burger = new Burger();
        Ingredient ingredient = getRandomIngredient();
        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    void testCanRemoveIngredientFromBurger() {

        Burger burger = new Burger();
        burger.ingredients.add(getRandomIngredient());
        burger.ingredients.add(getRandomIngredient());
        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }


    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "2, 0",
            "1, 2",
            "1, 1"
    })
    void testCanMoveIngredientInBurger(int index, int newIndex) {

        Burger burger = new Burger();
        burger.ingredients.add(getRandomIngredient());
        burger.ingredients.add(getRandomIngredient());
        burger.ingredients.add(getRandomIngredient());
        Ingredient moved = burger.ingredients.get(index);
        burger.moveIngredient(index, newIndex);

        assertEquals(moved, burger.ingredients.get(newIndex));
    }

    @ParameterizedTest
    @MethodSource("priceData")
    void testGetPriceReturnsTotalBurgerPrice(float bunPrice, float[] ingredientPrices, float expectedTotalPrice) {

        Burger burger = new Burger();
        burger.bun = bunMock;
        when(bunMock.getPrice()).thenReturn(bunPrice);

        for (float price : ingredientPrices) {
            Ingredient ingredientMock = mock(Ingredient.class);
            when(ingredientMock.getPrice()).thenReturn(price);
            burger.addIngredient(ingredientMock);
        }

        float actualPrice = burger.getPrice();

        assertEquals(expectedTotalPrice, actualPrice, 0.05);
    }

    static Stream<Arguments> priceData() {
        return Stream.of(
                // bunPrice, ingredientPrices, expectedTotalPrice
                Arguments.of(100, new float[]{200}, 400),
                Arguments.of(200, new float[]{300, 100, 100}, 900),
                Arguments.of(300, new float[]{}, 600),
                Arguments.of(0, new float[]{0}, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("receiptData")
    void testGetReceiptReturnsStringBuiltBurgerReceipt(
            String bunName,
            float bunPrice,
            List<Ingredient> ingredientList,
            String expectedReceipt
    ) {

        Burger burger = new Burger();
        burger.bun = bunMock;

        // Замокать bun
        when(bunMock.getName()).thenReturn(bunName);
        when(bunMock.getPrice()).thenReturn(bunPrice);

        // Замокать ingredient
        for (Ingredient i : ingredientList) {
            Ingredient ingredientMock = mock(Ingredient.class);
            when(ingredientMock.getType()).thenReturn(i.type);
            when(ingredientMock.getName()).thenReturn(i.name);
            when(ingredientMock.getPrice()).thenReturn(i.price);
            burger.addIngredient(ingredientMock);
        }

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt);
    }

    static Stream<Arguments> receiptData() {
        return Stream.of(
                // bunName, bunPrice, List<Ingredient>, expectedReceipt
                Arguments.of(
                        "black bun",
                        100,
                        List.of(new Ingredient(IngredientType.FILLING, "cutlet", 100)),
                        "(==== black bun ====)\r\n" +
                                "= filling cutlet =\r\n" +
                                "(==== black bun ====)\r\n" +
                                "\r\nPrice: 300,000000\r\n"
                ),
                Arguments.of(
                        "white bun",
                        200,
                        List.of(
                                new Ingredient(IngredientType.SAUCE, "sour cream", 200),
                                new Ingredient(IngredientType.SAUCE, "chili sauce", 300),
                                new Ingredient(IngredientType.FILLING, "cutlet", 100),
                                new Ingredient(IngredientType.FILLING, "sausage", 300),
                                new Ingredient(IngredientType.FILLING, "sausage", 300)
                        ),
                        "(==== white bun ====)\r\n" +
                                "= sauce sour cream =\r\n" +
                                "= sauce chili sauce =\r\n" +
                                "= filling cutlet =\r\n" +
                                "= filling sausage =\r\n" +
                                "= filling sausage =\r\n" +
                                "(==== white bun ====)\r\n" +
                                "\r\nPrice: 1600,000000\r\n"
                ),
                Arguments.of(
                        "red bun",
                        300,
                        List.of(),
                        "(==== red bun ====)\r\n" +
                                "(==== red bun ====)\r\n" +
                                "\r\nPrice: 600,000000\r\n"
                )
        );
    }
}
