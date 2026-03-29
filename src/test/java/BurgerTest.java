import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    @Mock
    Bun bun;

    Burger burger = new Burger();


    private static Stream<Arguments> listDoubleIngredients() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "ketchup", 120.00F, IngredientType.FILLING, "cutlet", 400.00F),
                Arguments.of(IngredientType.SAUCE, "mayonnaise", 144.56F, IngredientType.FILLING, "cheese", 55.55F)
        );
    }

    private static Stream<Arguments> listIngredients() {
        return Stream.of(
                Arguments.of(IngredientType.SAUCE, "ketchup", 120.00F),
                Arguments.of(IngredientType.SAUCE, "mayonnaise", 144.56F),
                Arguments.of(IngredientType.FILLING, "cutlet", 400.00F),
                Arguments.of(IngredientType.FILLING, "cheese", 55.55F)
        );
    }

    @Test
    void setBunsTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @ParameterizedTest
    @MethodSource("listIngredients")
    void addIngredientTest(IngredientType type, String name, float price) {
        Ingredient ingredientOrList = new Ingredient(type, name, price);
        burger.addIngredient(ingredientOrList);
        Ingredient last = burger.ingredients.get(burger.ingredients.size() - 1);
        assertEquals(type, last.getType(), "Переданное значение ТИПА ингредиента не совпадает с записанным в список");
        assertEquals(name, last.getName(), "Переданное значение ИМЕНИ ингредиента не совпадает с записанным в список");
        assertEquals(price, last.getPrice(), "Переданное значение ЦЕНЫ ингредиента не совпадает с записанным в список");
    }

    @ParameterizedTest
    @MethodSource("listDoubleIngredients")
    void removeIngredientTest(IngredientType oneType, String oneName, float onePrice, IngredientType twoType, String twoName, float twoPrice) {
        //Создаем два ингредиента
        Ingredient ingredientOne = new Ingredient(oneType, oneName, onePrice);
        Ingredient ingredientTwo = new Ingredient(twoType, twoName, twoPrice);
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);

        int startSize = burger.ingredients.size();
        int lastIngredients = burger.ingredients.size() - 1;
        burger.removeIngredient(lastIngredients);
        assertEquals(startSize - 1, burger.ingredients.size(), "Количество элементов не совпадает");
    }

    @ParameterizedTest
    @MethodSource("listDoubleIngredients")
    void moveIngredientTest(IngredientType oneType, String oneName, float onePrice, IngredientType twoType, String twoName, float twoPrice) {
        //Создаем два ингредиента
        Ingredient ingredientOne = new Ingredient(oneType, oneName, onePrice);
        Ingredient ingredientTwo = new Ingredient(twoType, twoName, twoPrice);
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        //Находим индекс второго ингредиента и меняем его индекс
        int indexTwoIngredient = burger.ingredients.indexOf(ingredientTwo);
        burger.moveIngredient(1, 0);
        int indexTwoIngredientNew = burger.ingredients.indexOf(ingredientTwo);
        assertEquals(indexTwoIngredient - 1, indexTwoIngredientNew, "Индекс элемента списка не совпадает с заданным значением");
    }

    @Test
    void getPriceReturn() {
        burger.setBuns(bun);
        when(bun.getPrice()).thenReturn(300.00F);

        float burgerPrice = burger.getPrice();
        assertEquals(bun.getPrice() * 2, burgerPrice, "Стоимость ингредиентов не совпадает");
    }

    @Test
    void getReceiptTest() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ketchup", 100.00F);
        burger.addIngredient(ingredient);

        when(bun.getName()).thenReturn("Хлебная с кунжутом");
        when(bun.getPrice()).thenReturn(300.00F);
        burger.setBuns(bun);
        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== Хлебная с кунжутом ====)"), "Название булочки не совпадает");
        assertTrue(receipt.contains("ketchup"), "Название ингредиента не совпадает");
        assertTrue(receipt.contains("700"), "Общая стоимость рецепта не совпадает");
    }

}
