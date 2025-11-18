package tests;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    Burger burger;

    @Mock
    Bun bun;

    @Mock
    Ingredient sauce;

    @Mock
    Ingredient filling;

    @BeforeEach
    void setUp() {
        burger = new Burger();
        lenient().when(bun.getPrice()).thenReturn(100f);
        lenient().when(bun.getName()).thenReturn("Мок-булка");

        lenient().when(sauce.getPrice()).thenReturn(50f);
        lenient().when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        lenient().when(sauce.getName()).thenReturn("Мок-соус");

        lenient().when(filling.getPrice()).thenReturn(250f);
        lenient().when(filling.getType()).thenReturn(IngredientType.FILLING);
        lenient().when(filling.getName()).thenReturn("Мок-начинка");
    }

    @Test
    @DisplayName("Проверка: метод setBuns() корректно сохраняет булочку")
    public void setBunsShouldAssignBunCorrectly() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun, "Успешно сохраненные булочки");
    }

    @Test
    @DisplayName("Проверка: метод addIngredient() корректно добавляет ингредиенты")
    public void addIngredientShouldAddCorrectly() {
        burger.addIngredient(filling);

        assertTrue(burger.ingredients.contains(filling),
                "Добавленный ингредиент должен быть в списке ингредиентов");
    }

    @Test
    @DisplayName("removeIngredient() уменьшает количество ингредиентов")
    void removeIngredientShouldDecreaseSize() {
        burger.addIngredient(filling);
        burger.addIngredient(sauce);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    @DisplayName("removeIngredient() оставляет корректный ингредиент после удаления")
    void removeIngredientShouldKeepCorrectIngredient() {
        burger.addIngredient(filling);
        burger.addIngredient(sauce);

        burger.removeIngredient(0);

        assertEquals(sauce, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Проверка: moveIngredient() переставляет ингредиенты")
    void moveIngredientShouldWorkCorrectly() {


        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        List<Ingredient> expected = List.of(sauce, filling, filling);
        assertEquals(expected, burger.ingredients, "Ингредиенты должны быть переставлены корректно");
    }

    @Test
    @DisplayName("Проверка: getPrice() возвращает верную сумму")
    void getPriceShouldWorkCorrectly() {
        burger.setBuns(bun);
        burger.addIngredient(filling);
        burger.addIngredient(sauce);

        float expectedPrice = 100f * 2 + 250f + 50f; // 500
        assertEquals(expectedPrice, burger.getPrice(), "Сумма цены бургера должна быть корректной");
    }

    @Test
    @DisplayName("Проверка: getReceipt() возвращает строку чека в правильном формате")
    void getReceiptShouldReturnCorrectString() {
        burger.setBuns(bun);
        burger.addIngredient(filling);
        burger.addIngredient(sauce);

        String receipt = burger.getReceipt();

        String expected = String.format(
                "(==== %s ====)%n" +
                        "= filling %s =%n" +
                        "= sauce %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n",
                bun.getName(), filling.getName(), sauce.getName(), bun.getName(), burger.getPrice()
        );

        assertEquals(expected, receipt, "Чек должен быть в правильном формате");
    }
}