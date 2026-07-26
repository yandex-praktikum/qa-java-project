package praktikum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient1;

    @Mock
    Ingredient ingredient2;

    private static final float PRICE_WITH_INGREDIENTS = 99.99F * 2 + 100F + 50F;
    private static final float PRICE_WITHOUT_INGREDIENTS = 99.99F * 2;

    Burger burger = new Burger();

    private static Stream<Arguments> provideIngredientType() {
        return Stream.of(
                Arguments.of("Бургер с соусом", IngredientType.SAUCE, String.format("(==== Вкусная ====)%n= sauce Острый =%n(==== Вкусная ====)%n%nPrice: %f%n", PRICE_WITH_INGREDIENTS)),
                Arguments.of("Бургер с котлетой", IngredientType.FILLING, String.format("(==== Вкусная ====)%n= filling Острый =%n(==== Вкусная ====)%n%nPrice: %f%n", PRICE_WITH_INGREDIENTS))
        );
    }


    @Test
    @DisplayName("Выбор булочки")
    public void setBunTest() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    @DisplayName("Добавление ингредиента")
    public void addIngredientTest() {
        burger.addIngredient(ingredient1);
        assertSame(ingredient1,
                    burger.ingredients.get(0),
                    String.format("Неверный список ингредиентов: ожидался %s, фактически - %s", ingredient1.toString(), burger.ingredients.get(0).toString())
        );
    }

    @Test
    @DisplayName("Удаление ингредиента")
    public void removeIngredientTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertSame(ingredient2,
                    burger.ingredients.get(0),
                    String.format("Неверный список ингредиентов: ожидался %s, фактически - %s", ingredient2.toString(), burger.ingredients.get(0).toString())

        );
    }

    @Test
    @DisplayName("Перемещение ингредиента")
    public void moveIngredientTest() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertIterableEquals(
                List.of(ingredient2, ingredient1),
                burger.ingredients,
                String.format("Неверный список ингредиентов: ожидался %s, фактически - %s", List.of(ingredient2, ingredient1).toString(), burger.ingredients.toString())
        );

    }

    @Test
    @DisplayName("Поучение стоимости нормального бургера")
    void getPriceOfBurger(){
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        Mockito.when(bun.getPrice()).thenReturn(99.99F);
        Mockito.when(ingredient1.getPrice()).thenReturn(100.00F);
        Mockito.when(ingredient2.getPrice()).thenReturn(50.00F);
        float actualPrice = burger.getPrice();
        assertEquals(PRICE_WITH_INGREDIENTS,
                    actualPrice,
                    0.0001F,
                    String.format("Неверная стоимость бургера: ожидалось \"%f\", фактически - \"%f\"", PRICE_WITH_INGREDIENTS, actualPrice)

        );
    }


    @Test
    @DisplayName("Получение стоимости бургера без ингредиентов")
    void getPriceOfBurgerWithoutIngredients(){
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(99.99F);
        float actualPrice = burger.getPrice();
        assertEquals(PRICE_WITHOUT_INGREDIENTS,
                actualPrice,
                0.0001F,
                String.format("Неверная стоимость бургера: ожидалось \"%f\", фактически - \"%f\"", PRICE_WITHOUT_INGREDIENTS, actualPrice)

        );
    }

    @ParameterizedTest(name = "{0}")
    @DisplayName("Получение чека")
    @MethodSource("provideIngredientType")
    void getReceiptOfBurgerTest(String testName, IngredientType type, String receipt) {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        Mockito.when(bun.getPrice()).thenReturn(99.99F);
        Mockito.when(bun.getName()).thenReturn("Вкусная");
        Mockito.when(ingredient1.getPrice()).thenReturn(150.00F);
        Mockito.when(ingredient1.getName()).thenReturn("Острый");
        Mockito.when(ingredient1.getType()).thenReturn(type);
        String actualReceipt = burger.getReceipt();
        assertEquals(receipt,
                    actualReceipt,
                    String.format("Неверный чек: ожидалось \"%s\", фактически - \"%s\"", receipt, actualReceipt)
        );

    }

    @Test
    @DisplayName("Получение чека: бургер без ингредиентов")
    void getReceiptOfBurgerOnlyBunTest() {
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(99.99F);
        Mockito.when(bun.getName()).thenReturn("Вкусная");
        String receipt = String.format("(==== Вкусная ====)%n(==== Вкусная ====)%n%nPrice: %f%n", PRICE_WITHOUT_INGREDIENTS);
        String actualReceipt = burger.getReceipt();
        assertEquals(receipt,
                actualReceipt,
                String.format("Неверный чек: ожидалось \"%s\", фактически - \"%s\"", receipt, actualReceipt)
        );
    }

}