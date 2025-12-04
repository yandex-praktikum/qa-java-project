package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTypeTest {

    @Test
    @DisplayName("Проверка что enum содержит оба типа ингредиентов")
    void shouldHaveBothIngredientTypes() {
        // Act
        IngredientType[] types = IngredientType.values();

        // Assert
        assertEquals(2, types.length);
        assertEquals(IngredientType.SAUCE, types[0]);
        assertEquals(IngredientType.FILLING, types[1]);
    }
}