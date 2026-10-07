package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IngredientTypeTest {

    @Test
    @DisplayName("Перечисление содержит ровно две константы")
    void enumShouldContainExactlyTwoConstants() {
        assertEquals(2, IngredientType.values().length);
    }

    @Test
    @DisplayName("Типы ингредиентов содержат SAUCE и FILLING")
    void enumShouldContainBothConstants() {
        List<IngredientType> values = Arrays.asList(IngredientType.values());
        assertTrue(values.contains(IngredientType.SAUCE));
        assertTrue(values.contains(IngredientType.FILLING));
    }

}
