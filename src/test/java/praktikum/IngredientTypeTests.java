package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Тестирование класса IngredientType")
public class IngredientTypeTests {

    @Test
    @DisplayName("Перечисление содержит все ожидаемые значения")
    void shouldContainAllExpectedValues() {
        IngredientType[] values = IngredientType.values();

        assertEquals(2, values.length);
        assertEquals(IngredientType.SAUCE, values[0]);
        assertEquals(IngredientType.FILLING, values[1]);
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("Каждое значение перечисления должно быть доступно")
    void eachEnumValueShouldBeAccessible(IngredientType type) {
        assertNotNull(type);
    }

    @ParameterizedTest
    @ValueSource(strings = {"SAUCE", "FILLING"})
    @DisplayName("Преобразование строки в значение перечисления")
    void shouldConvertStringToEnumValue(String enumName) {
        IngredientType type = assertDoesNotThrow(() ->
                IngredientType.valueOf(enumName)
        );
        assertEquals(enumName, type.name());
    }

}
