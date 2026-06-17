package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Assertions;

import java.util.stream.Stream;



public class BurgerAddIngredientParameterizedTest {

    // Метод-источник данных с тестовыми параметрами
    static Stream<Arguments> getIngredientData() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "hot sauce", 100f),
                Arguments.of(IngredientType.SAUCE, "sour cream", 200f),
                Arguments.of(IngredientType.SAUCE, "chili sauce", 300f),
                Arguments.of(IngredientType.FILLING, "cutlet", 100f),
                Arguments.of(IngredientType.FILLING, "dinosaur", 200f),
                Arguments.of(IngredientType.FILLING, "sausage", 300f)
        );
    }

    @ParameterizedTest
    @MethodSource("getIngredientData")
    @DisplayName("Параметризованный тест: добавление разных ингредиентов")
    public void testAddDifferentIngredients(IngredientType type, String name, float price) {
        // Создаём реальный Burger
        Burger realBurger = new Burger();
        Ingredient ingredient = new Ingredient(type, name, price);

        realBurger.addIngredient(ingredient);

        // Проверяем, что ингредиент добавился корректно
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, realBurger.ingredients.size()),
                () -> Assertions.assertEquals(type, realBurger.ingredients.get(0).getType()),
                () -> Assertions.assertEquals(name, realBurger.ingredients.get(0).getName()),
                () -> Assertions.assertEquals(price, realBurger.ingredients.get(0).getPrice(), 0.001)
        );
    }
}