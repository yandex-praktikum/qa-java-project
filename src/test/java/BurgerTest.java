
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Тестирование класса Burger")
class BurgerTest {

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredient1Mock;

    @Mock
    private Ingredient ingredient2Mock;

    private Burger burger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    @DisplayName("Проверка установки булочки")
    @Description("Метод setBuns должен корректно сохранять булочку в объекте Burger")
    void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun, "Булочка должна быть установлена в бургере");
    }

    @Test
    @DisplayName("Добавление и удаление ингредиентов")
    @Description("Проверяем, что ингредиенты корректно добавляются и удаляются из списка")
    void testAddAndRemoveIngredient() {
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);
        assertEquals(2, burger.ingredients.size(), "После добавления должно быть два ингредиента");

        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size(), "После удаления должен остаться один ингредиент");
        assertEquals(ingredient2Mock, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Перемещение ингредиентов")
    @Description("Метод moveIngredient должен корректно менять порядок ингредиентов в списке")
    void testMoveIngredient() {
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);

        burger.moveIngredient(0, 1);
        assertEquals(List.of(ingredient2Mock, ingredient1Mock), burger.ingredients);
    }

    @ParameterizedTest(name = "Цена бургера с булочкой {0} и ингредиентами {1} + {2}")
    @CsvSource({
            "100, 50, 25",
            "200, 0, 100",
            "150, 10, 10"
    })
    @DisplayName("Расчёт общей цены бургера")
    @Description("Метод getPrice должен корректно считать сумму стоимости булочек и ингредиентов")
    void testGetPrice(float bunPrice, float ing1Price, float ing2Price) {
        when(bunMock.getPrice()).thenReturn(bunPrice);
        when(ingredient1Mock.getPrice()).thenReturn(ing1Price);
        when(ingredient2Mock.getPrice()).thenReturn(ing2Price);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);

        float expectedPrice = bunPrice * 2 + ing1Price + ing2Price;
        assertEquals(expectedPrice, burger.getPrice(), 0.001, "Цена рассчитана неверно");
    }

    @Test
    @DisplayName("Проверка формирования чека")
    @Description("Метод getReceipt должен возвращать корректный текст чека с булочкой, ингредиентами и общей ценой")
    void testGetReceipt() {
        when(bunMock.getName()).thenReturn("Белая булочка");
        when(bunMock.getPrice()).thenReturn(100f);
        when(ingredient1Mock.getName()).thenReturn("Соус фирменный");
        when(ingredient1Mock.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient1Mock.getPrice()).thenReturn(50f);

        burger.setBuns(bunMock);
        burger.addIngredient(ingredient1Mock);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Белая булочка"));
        assertTrue(receipt.contains("Соус фирменный"));
        assertTrue(receipt.contains("Price"));
        assertTrue(receipt.contains("250"), "Общая цена должна быть корректной (100*2 + 50)");
    }
}
