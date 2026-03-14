package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Тестирование типов ингредиентов")
public class IngredientTypeTest {

    @ParameterizedTest(name = "Тест {index}: {0}")
    @ValueSource(strings = {"SAUCE", "FILLING"})
    @DisplayName("Проверка правильности типов ингредиентов")
    public void ingredientTypeTest(String typeName) {
        assertEquals(IngredientType.valueOf(typeName).toString(), typeName,
                "Тип ингредиента не соответствует ожидаемому");
    }
}