package tests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IngredientTypeTest {

    @Test
    @DisplayName("Проверка: что enum содержит конкретные значения")
    public void testEnumContainsSauceAndFilling() {

        IngredientType[] types = IngredientType.values();
        boolean hasSauce = false;
        boolean hasFilling = false;

        for (IngredientType type : types) {
            if (type == IngredientType.SAUCE) {
                hasSauce = true;
            }
            if (type == IngredientType.FILLING) {
                hasFilling = true;
            }
        }

        assertTrue(hasSauce, "Enum должен содержать SAUCE");
        assertTrue(hasFilling, "Enum должен содержать FILLING");
    }

    @Test
    @DisplayName("Проверка: что enum содержит ровно 2 значения")
    public void testEnumSize() {

        IngredientType[] types = IngredientType.values();
        assertEquals(2, types.length, "Enum должен содержать ровно 2 значения");
    }
}
