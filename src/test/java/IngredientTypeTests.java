import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import java.util.Arrays;

public class IngredientTypeTests {

    @Test
    @DisplayName("Перечисление должно содержать ровно 2 значения")
    void enumHaveCorrectAmountOfValues(){
        Assertions.assertEquals(2, IngredientType.values().length);
    }

    @Test
    @DisplayName("Перечисление должно содержать значения SAUCE и FILLING")
    void enumContainsSpecificValues(){
        Assertions.assertTrue(Arrays.asList(IngredientType.values()).contains(IngredientType.valueOf("sauce".toUpperCase())));
        Assertions.assertTrue(Arrays.asList(IngredientType.values()).contains(IngredientType.valueOf("filling".toUpperCase())));
    }
}
