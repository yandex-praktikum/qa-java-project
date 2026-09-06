package praktikum;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {

    @DisplayName("Добавление булочки в бургер")
    @Description("Проверяем, что метод setBuns() добавляет булочку в бургер")
    @Test
    void setBunsSetsBurgerBun(){
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @DisplayName("Добавление ингредиента в бургер")
    @Description("Проверяем, что метод addIngredient() добавляет переданный ингредиент в список ингредиентов бургера")
    @Test
    void addIngredientAddsIngredientToBurger(){
        Burger burger = new Burger();
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient, burger.ingredients.get(0));
    }

    @DisplayName("Удаление ингредиента из бургера")
    @Description("Проверяем, что метод removeIngredient() удаляет ингредиент из списка по индексу")
    @Test
    void removeIngredientRemovesIngredientByIndex(){
        Burger burger = new Burger();
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(second, burger.ingredients.get(0));
    }

    @DisplayName("Перемещение ингредиента в списке")
    @Description("Проверяем, что метод moveIngredient() перемещает ингредиент с исходной позиции на указанную")
    @Test
    void moveIngredientMovesIngredientToNewIndex(){
        Burger burger = new Burger();
        Ingredient first = mock(Ingredient.class);
        Ingredient second = mock(Ingredient.class);
        Ingredient third = mock(Ingredient.class);
        burger.addIngredient(first);
        burger.addIngredient(second);
        burger.addIngredient(third);
        burger.moveIngredient(0, 2);
        assertEquals(2, burger.ingredients.indexOf(first));
        assertEquals(0, burger.ingredients.indexOf(second));
        assertEquals(1, burger.ingredients.indexOf(third));
    }

    @DisplayName("Расчет стоимости бургера")
    @Description("Проверяем, что стоимость бургера рассчитывается как двойная стоймость булочки плюс стоимость всех добавленных ингредиентов")
    @Test
    void getPriceReturnsDoubleBunPricePlusIngredientPrices(){
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        Ingredient sauce = mock(Ingredient.class);
        Ingredient filling = mock(Ingredient.class);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        when(bun.getPrice()).thenReturn(100.0f);
        when(sauce.getPrice()).thenReturn(50.0f);
        when(filling.getPrice()).thenReturn(200.0f);
        assertEquals(450.0f, burger.getPrice());
    }

    @DisplayName("Формирование чека для бургера")
    @Description("Проверяем, что getReceipt() формирует чек с названием булочки, типами и названиями ингредиентов и итоговой стоимостью бургера.")
    @Test
    void getReceiptReturnsCorrectReceipt() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);
        Ingredient sauce = mock(Ingredient.class);
        Ingredient filling = mock(Ingredient.class);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        when(bun.getName()).thenReturn("sesame bun");
        when(bun.getPrice()).thenReturn(100.0f);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(50.0f);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(200.0f);
        String expected = String.format(
                "(==== sesame bun ====)%n" +
                        "= sauce hot sauce =%n" +
                        "= filling cutlet =%n" +
                        "(==== sesame bun ====)%n" +
                        "%nPrice: 450.000000%n"
        );
        assertEquals(expected, burger.getReceipt());
    }
}
