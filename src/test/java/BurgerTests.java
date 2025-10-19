import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class BurgerTests {

    private Burger burger;
    private Bun bunMock;
    private Ingredient ingredientMock1;
    private Ingredient ingredientMock2;

    @BeforeEach
    public void setUp() {
        burger = new Burger();

        // создаём моки
        bunMock = mock(Bun.class);
        Mockito.when(bunMock.getName()).thenReturn("test bun");
        Mockito.when(bunMock.getPrice()).thenReturn(100f);

        ingredientMock1 = mock(Ingredient.class);
        Mockito.when(ingredientMock1.getName()).thenReturn("ingredient1");
        Mockito.when(ingredientMock1.getPrice()).thenReturn(50f);
        Mockito.when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);

        ingredientMock2 = mock(Ingredient.class);
        Mockito.when(ingredientMock2.getName()).thenReturn("ingredient2");
        Mockito.when(ingredientMock2.getPrice()).thenReturn(30f);
        Mockito.when(ingredientMock2.getType()).thenReturn(IngredientType.SAUCE);
    }

    @Test
    @DisplayName("Установка булки в бургер работает корректно")
    public void setBunsSetsBunCorrectly() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    @DisplayName("Добавление ингредиента в бургер добавляет его в список")
    public void addIngredientAddsToList() {
        burger.addIngredient(ingredientMock1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock1, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Удаление ингредиента по индексу работает корректно")
    public void removeIngredientRemovesByIndex() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredientMock2, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Перемещение ингредиента меняет порядок в списке")
    public void moveIngredientChangesOrder() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.moveIngredient(0, 1);
        assertEquals(ingredientMock1, burger.ingredients.get(1));
    }

    @Test
    @DisplayName("Расчёт общей стоимости бургера выполняется правильно")
    public void getPriceCalculatesTotalCorrectly() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        float expected = 100 * 2 + 50 + 30;
        assertEquals(expected, burger.getPrice());
    }

    @Test
    @DisplayName("Формирование чека отображается корректно")
    public void getReceiptReturnsCorrectReceipt() {
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        String lineSeparator = System.lineSeparator();
        String expected = String.format(
                "(==== %s ====)%s" +
                        "= %s %s =%s" +
                        "= %s %s =%s" +
                        "(==== %s ====)%s%s" +
                        "Price: %.6f%s",
                bunMock.getName(), lineSeparator,
                ingredientMock1.getType().toString().toLowerCase(), ingredientMock1.getName(), lineSeparator,
                ingredientMock2.getType().toString().toLowerCase(), ingredientMock2.getName(), lineSeparator,
                bunMock.getName(), lineSeparator, lineSeparator,
                burger.getPrice(), lineSeparator
        );

        assertEquals(expected.trim(), burger.getReceipt().trim(), "Формирование чека отображается не корректно");

        System.out.println("EXPECTED:\n" + expected);
        System.out.println("ACTUAL:\n" + burger.getReceipt());
    }
}
