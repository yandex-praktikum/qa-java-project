import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IngredientTests {

    @Mock
    IngredientType ingredientType;

    @ParameterizedTest
    @CsvSource({
            "Говяжий метеорит, 3000",
            "Соус традиционный галактический, 15"
    })
    public void ingredientConstructorTest(String ingredientName, int ingredientPrice) {
        Ingredient ingredient = new Ingredient(ingredientType, ingredientName, ingredientPrice);
        assertEquals(ingredientName, ingredient.getName());
        assertEquals(ingredientPrice, ingredient.getPrice());
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    public void getTypeShouldReturnCorrectValue(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "test", 100);
        assertEquals(type, ingredient.getType());
    }
}
