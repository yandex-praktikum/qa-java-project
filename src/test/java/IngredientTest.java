import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.SAUCE;

public class IngredientTest {


    @Test
    @DisplayName("Проверка получения цены")
    public void getPriceTest(){
        Ingredient ingredient = new Ingredient(SAUCE,"hot sauce",100);
        assertEquals(100, ingredient.getPrice(), "Цена отличается");
    }

    @Test
    @DisplayName("Проверка получения наименования")
    public void getNameTest(){
        Ingredient ingredient = new Ingredient(SAUCE,"hot sauce",100);
     assertEquals("hot sauce", ingredient.getName(), "Наименования отличаются");
    }


    @ParameterizedTest
    @EnumSource(IngredientType.class)
    @DisplayName("Проверка получения типов ингредиента")
    public void ingredientTypeTest(IngredientType type) {
        Ingredient ingredient = new Ingredient(type,"hot sauce",100);
        assertEquals(type, ingredient.getType(), "Тип ингредиента не совпадает");
    }
}
