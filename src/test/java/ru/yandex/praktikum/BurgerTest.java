package ru.yandex.praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.function.Executable;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @BeforeEach
    void setUp() {
        burger = new Burger();

        burger.setBuns(bun);
    }

    @Test
    @DisplayName("Добавление ингридиента")
    void addIngredientForBurger() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Хрустящие минеральные кольца", 300);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    @DisplayName("Удаление ингридиента")
    void removeIngredientFromBurger() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Хрустящие минеральные кольца", 300);

        burger.addIngredient(ingredient);
        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    @DisplayName("Удаление ингридиента по неправильному индексу")
    void removeUnknownIndexIngredient() {

        Executable executable = () -> burger.removeIngredient(10);
        assertThrows(IndexOutOfBoundsException.class, executable);
    }

    @Test
    @DisplayName("Перемещение ингридиента")
    void moveIngredientForBurger() {
        Ingredient firstIngredient = new Ingredient(IngredientType.FILLING, "Хрустящие минеральные кольца", 300);
        Ingredient secondIngredient = new Ingredient(IngredientType.SAUCE, "Соус Spicy-X", 90);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        assertEquals(secondIngredient, burger.ingredients.get(0));
        assertEquals(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    @DisplayName("Перемещение ингридиента в несуществующий индекс")
    void moveUnknownIndexIngredient() {

        burger.addIngredient(new Ingredient(IngredientType.FILLING, "cutlet", 100));

        Executable executable = () -> burger.moveIngredient(0, 10);
        assertThrows(IndexOutOfBoundsException.class, executable);
    }

    @ParameterizedTest
    @MethodSource("priceElements")
    @DisplayName("Расчёт цены")
    public void priceCalculate(float ingredientPrice) {

        when(bun.getPrice()).thenReturn(55f);

        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "sausage", ingredientPrice);

        burger.addIngredient(ingredient);

        float expected = 55f * 2 + ingredientPrice;
        assertEquals(expected, burger.getPrice(), 0.001);
    }

    static Stream<Float> priceElements() {
        return Stream.of(0f, 99f, 1555.5f);

    }

    @Test
    @DisplayName("Получение квитанции")
    void getCorrectReceipt() {

        when(bun.getName()).thenReturn("Краторная булка N-200i");

        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "Хрустящие минеральные кольца", 300);

        burger.addIngredient(ingredient);

        String receipt = burger.getReceipt();

        String expected = String.format(
                "(==== %s ====)%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n",
                "Краторная булка N-200i", "filling", "Хрустящие минеральные кольца", "Краторная булка N-200i", burger.getPrice());

        assertEquals(expected, receipt);
    }
}