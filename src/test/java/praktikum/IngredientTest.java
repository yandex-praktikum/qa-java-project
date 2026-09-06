package praktikum;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {
    @DisplayName("Получение типа, названия и цены ингредиента")
    @Description("Проверяем, что методы getType(), getName() и getPrice() возвращают верные значения")
    @ParameterizedTest
    @CsvSource({
            "SAUCE, hot sauce, 100.0",
            "FILLING, cutlet, 200.5"
    })
    void gettersReturnConstructorValues(IngredientType type, String name, float price){
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice());
    }
}
