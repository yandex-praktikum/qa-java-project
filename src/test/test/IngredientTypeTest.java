package test;

import org.junit.jupiter.api.*;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тесты перечисления IngredientType")
class IngredientTypeTest {

    @Test
    @DisplayName("Перечисление содержит ровно 2 значения")
    void shouldHaveExactlyTwoValues() {
        assertEquals(2, IngredientType.values().length);
    }

    @Test
    @DisplayName("Перечисление содержит значение SAUCE")
    void shouldContainSauce() {
        assertDoesNotThrow(() -> IngredientType.valueOf("SAUCE"));
    }

    @Test
    @DisplayName("Перечисление содержит значение FILLING")
    void shouldContainFilling() {
        assertDoesNotThrow(() -> IngredientType.valueOf("FILLING"));
    }


    @Test
    @DisplayName("SAUCE имеет ordinal 0")
    void sauceShouldHaveOrdinalZero() {
        assertEquals(0, IngredientType.SAUCE.ordinal());
    }

    @Test
    @DisplayName("FILLING имеет ordinal 1")
    void fillingShouldHaveOrdinalOne() {
        assertEquals(1, IngredientType.FILLING.ordinal());
    }

    @Test
    @DisplayName("values() возвращает значения в порядке SAUCE, FILLING")
    void valuesShouldReturnInDeclarationOrder() {
        IngredientType[] values = IngredientType.values();

        assertArrayEquals(
                new IngredientType[]{IngredientType.SAUCE, IngredientType.FILLING},
                values
        );
    }


    @Test
    @DisplayName("name() для SAUCE возвращает \"SAUCE\"")
    void sauceNameShouldBeCorrect() {
        assertEquals("SAUCE", IngredientType.SAUCE.name());
    }

    @Test
    @DisplayName("name() для FILLING возвращает \"FILLING\"")
    void fillingNameShouldBeCorrect() {
        assertEquals("FILLING", IngredientType.FILLING.name());
    }


    @Test
    @DisplayName("valueOf(\"SAUCE\") возвращает SAUCE")
    void valueOfShouldReturnSauce() {
        assertSame(IngredientType.SAUCE, IngredientType.valueOf("SAUCE"));
    }

    @Test
    @DisplayName("valueOf(\"FILLING\") возвращает FILLING")
    void valueOfShouldReturnFilling() {
        assertSame(IngredientType.FILLING, IngredientType.valueOf("FILLING"));
    }

    @Test
    @DisplayName("valueOf() чувствителен к регистру: \"sauce\" бросает исключение")
    void valueOfShouldBeCaseSensitive() {
        assertThrows(IllegalArgumentException.class,
                () -> IngredientType.valueOf("sauce"));
    }

    @Test
    @DisplayName("valueOf() с несуществующим именем бросает IllegalArgumentException")
    void valueOfShouldThrowForUnknownName() {
        assertThrows(IllegalArgumentException.class,
                () -> IngredientType.valueOf("UNKNOWN"));
    }

    @Test
    @DisplayName("valueOf(null) бросает NullPointerException")
    void valueOfNullShouldThrow() {
        assertThrows(NullPointerException.class,
                () -> IngredientType.valueOf(null));
    }


    @Test
    @DisplayName("toString() по умолчанию возвращает имя константы")
    void toStringShouldReturnName() {
        assertEquals("SAUCE", IngredientType.SAUCE.toString());
        assertEquals("FILLING", IngredientType.FILLING.toString());
    }

    @Test
    @DisplayName("Одна и та же константа равна сама себе")
    void sameConstantShouldBeEqual() {
        assertEquals(IngredientType.SAUCE, IngredientType.SAUCE);
    }

    @Test
    @DisplayName("Разные константы не равны")
    void differentConstantsShouldNotBeEqual() {
        assertNotEquals(IngredientType.SAUCE, IngredientType.FILLING);
    }

    @Test
    @DisplayName("equals() возвращает false для null")
    void equalsShouldReturnFalseForNull() {
        assertNotEquals(IngredientType.SAUCE, null);
    }

    @Test
    @DisplayName("hashCode() одинаков для одной и той же константы")
    void hashCodeShouldBeConsistent() {
        assertEquals(IngredientType.SAUCE.hashCode(), IngredientType.SAUCE.hashCode());
    }
}