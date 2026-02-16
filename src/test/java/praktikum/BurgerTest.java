package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class BurgerTest {

    private Burger burger;
    @Mock
    private Bun bunMock;
    @Mock
    private Ingredient ingredientMock1;
    @Mock
    private Ingredient ingredientMock2;
    @Mock
    private Ingredient ingredientMock3;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    @Test
    @DisplayName("Тестируем, что переданная булка сеттится в переменную")
    void testSetBuns() {

        when(bunMock.getName()).thenReturn("Булка космос");

        burger.setBuns(bunMock);

        assertSame(bunMock, burger.bun, "Должна быть сохранена ссылка на переданный объект, а не создан новый объект");
        assertEquals("Булка космос", burger.bun.getName(), "Имя булки должно быть корректным");
        verify(bunMock,  Mockito.times(1)).getName();

    }

    @Test
    @DisplayName("Добавление ингредиента в бургер")
    void testAddIngredient_ShouldAddToIngredientsList() {

        when(ingredientMock1.getName()).thenReturn("Сыр");
        when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock1.getPrice()).thenReturn(10.10F);

        burger.addIngredient(ingredientMock1);

        assertEquals(1, burger.ingredients.size(),
                String.format("Список ингредиентов должен содержать 1 элемент, а сейчас содержит: %s", burger.ingredients.size()));
        assertSame(ingredientMock1, burger.ingredients.get(0), "Добавленный ингредиент должен быть в списке");
        assertEquals("Сыр", burger.ingredients.get(0).getName(), "Имя ингредиента должно быть корректным");
        assertEquals(IngredientType.FILLING, burger.ingredients.get(0).getType(), "Тип ингредиента должно быть корректным");
        assertEquals(10.10F, burger.ingredients.get(0).getPrice(), "Цена ингредиента должна быть корректным");

        verify(ingredientMock1,  Mockito.times(1)).getName();
        verify(ingredientMock1,  Mockito.times(1)).getType();
        verify(ingredientMock1,  Mockito.times(1)).getPrice();

    }


    @Test
    @DisplayName("Добавление нескольких ингредиентов")
    void testAddMultipleIngredients_ShouldAddAll() {

        when(ingredientMock1.getName()).thenReturn("Сыр");
        when(ingredientMock2.getName()).thenReturn("Котлета");

        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        assertEquals(2, burger.ingredients.size(), "Список ингредиентов должен содержать 2 элемента");
        assertSame(ingredientMock1, burger.ingredients.get(0), "Первый ингредиент должен быть в списке");
        assertSame(ingredientMock2, burger.ingredients.get(1), "Второй ингредиент должен быть в списке");
        assertEquals("Сыр", burger.ingredients.get(0).getName());
        assertEquals("Котлета", burger.ingredients.get(1).getName());
    }

    @Test
    @DisplayName("Удаление ингредиента по индексу")
    void testRemoveIngredient_ShouldRemoveFromList() {

        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        burger.removeIngredient(1);

        assertEquals(2, burger.ingredients.size(), "После удаления должен остаться 2 ингредиента");
        assertSame(ingredientMock1, burger.ingredients.get(0), "Первый ингредиент должен остаться на месте");
        assertSame(ingredientMock3, burger.ingredients.get(1), "Третий ингредиент должен стать вторым");
        assertFalse(burger.ingredients.contains(ingredientMock2), "Удаленный ингредиент не должен содержаться в списке");
    }

    @Test
    @DisplayName("Удаление ингредиента по некорректному индексу должно выбрасывать исключение")
    void testRemoveIngredientWithInvalidIndex_ShouldThrowException() {
        burger.addIngredient(ingredientMock1);

        assertThrows(IndexOutOfBoundsException.class,
                () -> burger.removeIngredient(5),
                "При некорректном индексе должно выбрасываться IndexOutOfBoundsException");
    }

    @Test
    @DisplayName("Перемещение ингредиента")
    void testMoveIngredient_ShouldChangePosition() {

        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);
        burger.addIngredient(ingredientMock3);

        burger.moveIngredient(0, 2);

        assertEquals(3, burger.ingredients.size(), "Количество ингредиентов не должно измениться");
        assertSame(ingredientMock2, burger.ingredients.get(0), "После перемещения первый элемент должен измениться");
        assertSame(ingredientMock3, burger.ingredients.get(1), "После перемещения второй элемент должен измениться");
        assertSame(ingredientMock1, burger.ingredients.get(2), "После перемещения третий элемент должен измениться");
    }

    @Test
    @DisplayName("Перемещение ингредиента на ту же позицию не должно изменять список")
    void testMoveIngredientToSamePositionShouldNotChangeList() {
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        List<Ingredient> originalList = List.copyOf(burger.ingredients);

        burger.moveIngredient(0, 0);

        assertEquals(originalList, burger.ingredients,
                "При перемещении на ту же позицию список не должен измениться");
    }

    @Test
    @DisplayName("Расчет цены бургера без ингредиентов")
    void testGetPrice_WithoutIngredients() {

        burger.setBuns(bunMock);
        when(bunMock.getPrice()).thenReturn(100.0f);

        float price = burger.getPrice();

        assertEquals(200.0f, price, 0.001,
                "Цена бургера без ингредиентов должна быть равна цене булки * 2");
        verify(bunMock, Mockito.times(1)).getPrice();
    }

    @ParameterizedTest
    @CsvSource({
            "100.0, 50.15, 75.15",
            "10.0, 0.0, 0.0",
            "10.0, 0.0, 5.0",
            "10.0, 5.0, 0.0"
    })
    @DisplayName("Параметризованный тест расчета цены")
    void parameterizedPriceTest(float bunPrice, float ingredient1Price,
                                float ingredient2Price) {

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(ingredientMock1.getPrice()).thenReturn(ingredient1Price);
        when(ingredientMock2.getPrice()).thenReturn(ingredient2Price);

        float price = burger.getPrice();

        float expectedPrice = bunPrice * 2 + ingredient1Price + ingredient2Price;

        assertEquals(expectedPrice, price, 0.001,
                "Цена бургера должна корректно рассчитываться с учетом булки и ингредиентов");

        verify(bunMock, Mockito.times(1)).getPrice();
        verify(ingredientMock1, Mockito.times(1)).getPrice();
        verify(ingredientMock2, Mockito.times(1)).getPrice();
    }

    @Test
    @DisplayName("Цена бургера без установленной булки")
    void testGetPriceWithoutBunShouldThrowException() {

        assertThrows(NullPointerException.class,
                () -> burger.getPrice(),
                "При расчете цены без установленной булки должно выбрасываться исключение");
    }


    @Test
    @DisplayName("Генерация чека для бургера без ингредиентов")
    void testGetReceipt_WithoutIngredients() {

        String bunName = "Булка 'Сладкый пэрсык'";

        burger.setBuns(bunMock);
        when(bunMock.getName()).thenReturn(bunName);
        when(bunMock.getPrice()).thenReturn(100.0f);


        String receipt = burger.getReceipt();

        String expectedReceipt = String.format("(==== %s ====)%n", bunName) +
                String.format("(==== %s ====)%n", bunName) +
                String.format("%nPrice: %f%n", 200.0f);

        assertEquals(expectedReceipt, receipt, "Чек для бургера без ингредиентов должен быть корректным");
        verify(bunMock, times(2)).getName();
        verify(bunMock, times(1)).getPrice();
    }


    @Test
    @DisplayName("Генерация чека для бургера с ингредиентами")
    void testGetReceipt_WithIngredients() {

        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        burger.addIngredient(ingredientMock2);

        when(bunMock.getName()).thenReturn("Краторная булка");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(ingredientMock1.getName()).thenReturn("Сыр");
        when(ingredientMock1.getType()).thenReturn(IngredientType.FILLING);
        when(ingredientMock1.getPrice()).thenReturn(50.0f);

        when(ingredientMock2.getName()).thenReturn("Соус spicy");
        when(ingredientMock2.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientMock2.getPrice()).thenReturn(75.0f);

        String receipt = burger.getReceipt();

        String expectedReceipt = getReceipt(burger);

        assertEquals(expectedReceipt, receipt, "Чек для бургера с ингредиентами должен быть корректным");

    }

    public String getReceipt(Burger burger) {
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", burger.bun.getName()));

        for (Ingredient ingredient : burger.ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }

        receipt.append(String.format("(==== %s ====)%n", burger.bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));

        return receipt.toString();
    }




}