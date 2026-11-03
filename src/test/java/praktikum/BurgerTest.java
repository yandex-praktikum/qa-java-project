package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    @DisplayName("Проверка установки булочки")
    @Description("Проверяем корректность добавления булочки в бургер")
    public void testSetBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test // отдельный тест на размер добавлен
    @DisplayName("Проверка размера списка при добавлении ингредиента")
    @Description("Проверяем, что после добавления ингредиента размер списка увеличивается")
    public void testAddIngredientSize() {
        burger.addIngredient(sauce);
        assertEquals(1, burger.ingredients.size());
    }

    @Test // отдельный тест на содержание добавлен
    @DisplayName("Проверка содержимого при добавлении ингредиента")
    @Description("Проверяем, что добавленный ингредиент содержится в списке")
    public void testAddIngredientContains() {
        burger.addIngredient(sauce);
        assertTrue(burger.ingredients.contains(sauce));
    }

    @Test // отдельный тест на размер после удаления добавлен
    @DisplayName("Проверка размера списка при удалении ингредиента")
    @Description("Проверяем, что после удаления ингредиента размер списка уменьшается")
    public void testRemoveIngredientSize() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());

    }

    @Test // отдельный тест на оставшийся элемент добавлен
    @DisplayName("Проверка правильности элемента после удаления")
    @Description("Проверяем, что на оставшемся индексе находится правильный ингредиент")
    public void testRemoveIngredientContent() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        assertEquals(filling, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Проверка перемещения ингредиента на первый индекс")
    @Description("Проверяем, что при перемещении ингредиента он встает на нужную позицию")
    public void testMoveIngredientFirstPosition() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
                // Перемещаем элемент с индекса 0 на индекс 1
        burger.moveIngredient(0, 1);

        assertEquals(filling, burger.ingredients.get(0));
            }

    @Test // Выделила атомарный тест
    @DisplayName("Проверка перемещения ингредиента на второй индекс")
    @Description("Проверяем, что второй элемент после перемещения сместился корректно")
    public void testMoveIngredientSecondPosition() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        assertEquals(sauce, burger.ingredients.get(1));
    }

    @Test // Выделила атомарный тест
    @DisplayName("Проверка наличия названия булки в чеке")
    @Description("Проверяем, что сгенерированный чек содержит название булки")
    public void testGetReceiptContainsBunName() {
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100.0f);

        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce.getName()).thenReturn("hot sauce");
        Mockito.when(sauce.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);

        String actualReceipt = burger.getReceipt();

        assertTrue(actualReceipt.contains("black bun"));
    }

    @Test // Выделила атомарный тест
    @DisplayName("Проверка наличия названия ингредиента в чеке")
    @Description("Проверяем, что сгенерированный чек содержит название ингредиента")
    public void testGetReceiptContainsIngredientName() {
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100.0f);

        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce.getName()).thenReturn("hot sauce");
        Mockito.when(sauce.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);

        String actualReceipt = burger.getReceipt();

        assertTrue(actualReceipt.contains("hot sauce"));
    }

    @Test // Выделила атомарный тест
    @DisplayName("Проверка наличия итоговой цены в чеке")
    @Description("Проверяем, что сгенерированный чек содержит общую стоимость бургера")
    public void testGetReceiptContainsTotalPrice() {
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100.0f);

        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce.getName()).thenReturn("hot sauce");
        Mockito.when(sauce.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);

        String actualReceipt = burger.getReceipt();

        assertTrue(actualReceipt.contains("250,000000") || actualReceipt.contains("250.000000"));
    }
}
