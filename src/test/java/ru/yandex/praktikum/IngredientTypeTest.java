package ru.yandex.praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTypeTest {

    @Test
    @DisplayName("Количество значений в enum")
    void getNumberEnumValues() {

        IngredientType[] values = IngredientType.values();

        assertEquals(2, values.length);
    }

    @Test
    @DisplayName("Содержание значений в enum")
    void getNameEnumValues() {

    assertEquals("SAUCE", IngredientType.SAUCE.name());
        assertEquals("FILLING", IngredientType.FILLING.name());
    }

    @Test
    @DisplayName("Проверяем отсутствующее значение в enum")
    void checkMissingValue() {

        Executable executable = () -> IngredientType.valueOf("COLA");

        assertThrows(IllegalArgumentException.class, executable);
    }
}