package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @DisplayName("getPrice возвращает цену ингредиента")
    @Test
    void getPriceReturnsPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "AnyIngredient", 15.0f);
        assertEquals(15.0f, ingredient.getPrice());//проверить еще раз про дельту для флоат
    }

    @DisplayName("getName возвращает имя ингредиента")
    @Test
    void getNameReturnsName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "AnyIngredient", 10.0f);
        assertEquals("AnyIngredient", ingredient.getName());
    }

    @DisplayName("getType возвращает тип ингредиента")
    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void getTypeReturnsCorrectType(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "test", 10.0f);
        assertEquals(type, ingredient.getType());
    }

}
