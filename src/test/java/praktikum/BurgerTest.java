package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {
    @Mock
    private Bun bun;

    @Mock
    private Ingredient firstIngredient;

    @Mock
    private Ingredient secondIngredient;

    private Burger burger;

    @BeforeEach
    public void createBurger() {
        burger = new Burger();
    }

    @Test
    @DisplayName("Проверка сеттера булочки")
    public void setBunsSuccess() {
        burger.setBuns(bun);
        assertSame(bun, burger.bun);
    }

    @Test
    @DisplayName("Проверка добавления ингредиентов")
    public void addIngredientSuccess() {
        assertEquals(0, burger.ingredients.size(), "Ингредиентов быть не должно");
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        Ingredient actualFirstIngredient = burger.ingredients.get(0);
        Ingredient actualSecondIngredient = burger.ingredients.get(1);
        assertSame(firstIngredient, actualFirstIngredient);
        assertSame(secondIngredient, actualSecondIngredient);
        assertEquals(2, burger.ingredients.size(), "Некорректное количество ингредиентов");
    }

    @Test
    @DisplayName("Проверка удаления ингредиента")
    public void removeIngredientSuccess() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        assertEquals(2, burger.ingredients.size(), "Ингредиенты не добавлены");
        burger.removeIngredient(0);
        Ingredient actualIngredient = burger.ingredients.get(0);
        assertEquals(1, burger.ingredients.size(), "Ингредиент не был удален");
        assertSame(secondIngredient, actualIngredient, "Удален не тот ингредиент");
    }

    @ParameterizedTest(name = "Удаление ингредиента по индексу {0}")
    @ValueSource(ints = {-1, 0, 1})
    @DisplayName("Проверка удаления ингредиента по недопустимому индексу")
    public void removeIngredientThrowsException(int i) {
        if (i != 0) burger.addIngredient(firstIngredient);
        assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(i));
    }


    @ParameterizedTest(name = "переместить ингредиент с позиции {0} на {1}")
    @CsvSource({
            "0, 1",
            "1, 0",
            "0, 0"
    })
    @DisplayName("Проверка перемещения ингредиентов")
    public void moveIngredientSuccess(int index, int newIndex) {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(index, newIndex);

        assertEquals(2, burger.ingredients.size(), "Количество ингредиентов не должно измениться");
        if (index != newIndex) {
            assertSame(secondIngredient, burger.ingredients.get(0), "На данной позиции некорректный ингредиент");
            assertSame(firstIngredient, burger.ingredients.get(1), "На данной позиции некорректный ингредиент");
        } else {
            assertSame(firstIngredient, burger.ingredients.get(0), "На данной позиции некорректный ингредиент");
            assertSame(secondIngredient, burger.ingredients.get(1), "На данной позиции некорректный ингредиент");
        }
    }

    @ParameterizedTest(name = "переместить ингредиент с позиции {0} на {1}")
    @CsvSource({
            "0, 100",
            "-1, 0"
    })
    @DisplayName("Проверка перемещения ингредиента по недопустимому индексу")
    public void moveIngredientThrowsException(int index, int newIndex) {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(index, newIndex));
    }


    @Test
    @DisplayName("Проверка расчета цены бургера")
    public void getPriceSuccess() {
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        Mockito.when(bun.getPrice()).thenReturn(30.5f);
        Mockito.when(firstIngredient.getPrice()).thenReturn(50f);
        Mockito.when(secondIngredient.getPrice()).thenReturn(100f);

        float actual = burger.getPrice();

        Mockito.verify(bun, Mockito.times(1)).getPrice();
        Mockito.verify(firstIngredient, Mockito.times(1)).getPrice();
        Mockito.verify(secondIngredient, Mockito.times(1)).getPrice();

        float expected = 211;

        assertEquals(expected, actual, "Цена бургера посчитана некорректно");
    }

    @ParameterizedTest(name = "{0} - {2} - {4}")
    @MethodSource("receiptCases")
    @DisplayName("Проверка корректности чека")
    public void getReceiptSuccess(String bunName,
                                  IngredientType firstType,
                                  String firstIngredientName,
                                  IngredientType secondType,
                                  String secondIngredientName,
                                  float bunPrice, float firstIngredientPrice, float secondIngredientPrice,
                                  String receipt) {
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(firstIngredient.getName()).thenReturn(firstIngredientName);
        Mockito.when(secondIngredient.getName()).thenReturn(secondIngredientName);
        Mockito.when(firstIngredient.getType()).thenReturn(firstType);
        Mockito.when(secondIngredient.getType()).thenReturn(secondType);

        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(firstIngredient.getPrice()).thenReturn(firstIngredientPrice);
        Mockito.when(secondIngredient.getPrice()).thenReturn(secondIngredientPrice);

        float expectedPrice = bunPrice * 2 + firstIngredientPrice + secondIngredientPrice;
        String expectedReceipt = String.format(receipt,
                bunName,
                firstType.toString().toLowerCase(),
                firstIngredientName,
                secondType.toString().toLowerCase(),
                secondIngredientName,
                bunName,
                expectedPrice);

        String actualReceipt = burger.getReceipt();

        assertEquals(expectedReceipt, actualReceipt, "Чек с информацией о бургере некорректный");
    }

    static Stream<Arguments> receiptCases() {
        String expectedReceipt =
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%nPrice: %f%n";

        return Stream.of(
                Arguments.of("Булочка с кунжутом",
                        IngredientType.FILLING,
                        "Две мясных котлеты гриль",
                        IngredientType.SAUCE,
                        "Специальный соус",
                        30.5f, 200f, 15f,
                        expectedReceipt)
        );
    }
}
