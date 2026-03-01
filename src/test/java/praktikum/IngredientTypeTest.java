package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTypeTest {

    @Test
    @DisplayName("Проверка количества ингредиентов в enum")
    public void testValues() {
        IngredientType[] types = IngredientType.values();
        assertEquals(2, types.length, "Всего 2 типа ингредиентов: SAUCE и FILLING");
    }

    @ParameterizedTest
    @DisplayName("Проверка получения ожидаемых ингредиентов")
    @CsvSource({
            "SAUCE, SAUCE",
            "FILLING, FILLING"
    })
    public void testValueOf(String type, IngredientType ingredientType) {
        assertEquals(ingredientType, IngredientType.valueOf(type));
    }
}