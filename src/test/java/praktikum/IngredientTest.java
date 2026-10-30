package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    @ParameterizedTest(name = "type = {0}, name = {1}, price = {2}")
    @CsvSource({
            "SAUCE, hot sauce, 100",
            "SAUCE, sour cream, 200",
            "FILLING, cutlet, 100",
            "FILLING, dinosaur, 200"
    })
    @DisplayName("Геттер getType возвращает тип ингредиента")
    void getTypeReturnsProvidedType(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
    }

    @ParameterizedTest(name = "type = {0}, name = {1}, price = {2}")
    @CsvSource({
            "SAUCE, hot sauce, 100",
            "SAUCE, sour cream, 200",
            "FILLING, cutlet, 100",
            "FILLING, dinosaur, 200"
    })
    @DisplayName("Геттер getName возвращает имя ингредиента")
    void getNameReturnsProvidedName(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(name, ingredient.getName());
    }

    @ParameterizedTest(name = "type = {0}, name = {1}, price = {2}")
    @CsvSource({
            "SAUCE, hot sauce, 100",
            "SAUCE, sour cream, 200",
            "FILLING, cutlet, 100",
            "FILLING, dinosaur, 200"
    })
    @DisplayName("Геттер getPrice возвращает цену ингредиента")
    void getPriceReturnsProvidedPrice(IngredientType type, String name, float price) {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(price, ingredient.getPrice(), 0.001f);
    }
}
