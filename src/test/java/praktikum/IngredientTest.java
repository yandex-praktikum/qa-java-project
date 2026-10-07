package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

public class IngredientTest {

    private static final String NAME = "Мясной хлеб";
    private static final float PRICE = 10.0f;
    private static final float DELTA = 0.0001f;

    private Ingredient ingredient;

    @BeforeEach
    void initBun() {
        ingredient = new Ingredient(FILLING, NAME, PRICE);
    }

    @Test
    @DisplayName("Проверка метода getName()")
    void getNameTest() {
        assertEquals(NAME, ingredient.getName());
    }

    @Test
    @DisplayName("Проверка метода getPrice()")
    void getPriceTest() {
        assertEquals(PRICE, ingredient.getPrice(), DELTA);
    }

    @Test
    @DisplayName("Проверка метода getType()")
    void getTypeTest() {
        assertEquals(FILLING, ingredient.getType());
    }

    @ParameterizedTest(name = "[{index}] type={0}, name={1}, price={2}")
    @MethodSource("ingredientProvider")
    @DisplayName("Конструктор корректно сохраняет type, name и price в соответствующие поля")
    void constructorTest(IngredientType expectedType, String expectedName, float expectedPrice) {
        Ingredient ingredientActual = new Ingredient(expectedType, expectedName, expectedPrice);
        assertEquals(expectedType, ingredientActual.type);
        assertEquals(expectedName, ingredientActual.name);
        assertEquals(expectedPrice, ingredientActual.price, DELTA);
    }

    static Stream<Arguments> ingredientProvider() {
        return Stream.of(
                Arguments.of(FILLING, "Котлета", 100f),
                Arguments.of(FILLING, "", 100f),
                Arguments.of(FILLING, null, 100f),
                Arguments.of(FILLING, "Сыр с плесенью", Float.MAX_VALUE),
                Arguments.of(FILLING, "Лук", Float.MIN_VALUE),
                Arguments.of(SAUCE, "Кетчунез", 0f)
        );
    }
}
