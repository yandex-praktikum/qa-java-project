package praktikum;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {
    @DisplayName("Получение всех типов ингредиентов")
    @Description("Проверяет, что метод values() возвращает все значения.")
    @Test
    void valuesReturnsAllIngredientTypes(){
        assertArrayEquals(
                new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING},
                IngredientType.values()
        );
    }

    @DisplayName("Получение типа ингредиентов по имени")
    @Description("Проверяет, что метод valueOf() возвращает соответствующий элемент для переданного имени.")
    @Test
    void valuesOfReturnsCorrectIngredientType(){
        assertEquals(
                IngredientType.SAUCE,
                IngredientType.valueOf("SAUCE")
        );
        assertEquals(
                IngredientType.FILLING,
                IngredientType.valueOf("FILLING")
        );
    }
}
