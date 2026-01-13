package praktikum;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class IngredientTypeTest {

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void enumValueIsNotNull(IngredientType type) {
        assertNotNull(type);
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void enumNameIsNotNull(IngredientType type) {
        assertNotNull(type.name());
    }


}