package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    private static final float DELTA = 0.0001f;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    private Burger burger;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("Проверка метода setBun()")
    void setBunTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    @DisplayName("Проверка повторного вызова setBun() - перезапись булочки")
    void setBunTwiceTest() {
        burger.setBuns(bun);
        Bun newBun = mock(Bun.class);
        burger.setBuns(newBun);
        assertEquals(newBun, burger.bun);
    }

    @Test
    @DisplayName("Проверка метода добавления ингредиента addIngredient()")
    void addIngredientTest() {
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Проверка метода удаления ингредиента removeIngredient()")
    void removeIngredientTest() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    @DisplayName("Проверка метода moveIngredient()")
    void moveIngredientShouldReorderList() {
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        burger.addIngredient(second);
        burger.addIngredient(third);

        burger.moveIngredient(0, 2);

        assertEquals(List.of(second, third, ingredient), burger.ingredients);
    }

    @ParameterizedTest(name = "bun={0}, ingredient prices={1} -> ожидаемая цена {2}")
    @MethodSource("priceProvider")
    @DisplayName("Проверка метода getPrice()")
    void getPriceParameterized(float bunPrice, List<Float> ingredientPrices, float expected) {
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);

        for (float price : ingredientPrices) {
            Ingredient ing = mock(Ingredient.class);
            when(ing.getPrice()).thenReturn(price);
            burger.addIngredient(ing);
        }

        assertEquals(expected, burger.getPrice(), DELTA);
    }

    @Test
    @DisplayName("Проверка метода getReceipt()")
    void getReceiptFullCheck() {
        when(bun.getName()).thenReturn("Образцовая булочка");
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient.getName()).thenReturn("Образцовый соус");
        when(ingredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expected =
                "(==== Образцовая булочка ====)" + System.lineSeparator() +
                        "= sauce Образцовый соус =" + System.lineSeparator() +
                        "(==== Образцовая булочка ====)" + System.lineSeparator() +
                        System.lineSeparator() +
                        "Price: 250,000000" + System.lineSeparator();

        assertEquals(expected, burger.getReceipt());
    }


    static Stream<Arguments> priceProvider() {
        return Stream.of(
                Arguments.of(100f, List.of(), 200f),
                Arguments.of(100f, List.of(50f), 250f),
                Arguments.of(100f, List.of(50f, 30f), 280f),
                Arguments.of(0f, List.of(70f), 70f),
                Arguments.of(80f, List.of(0f, 0f, 0f), 160f),
                Arguments.of(10.5f, List.of(0.25f, 1.25f), 22.5f)
        );
    }
}
