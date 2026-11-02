package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class BurgerTest {

    @Parameterized.Parameter(0)
    public float bunPrice;

    @Parameterized.Parameter(1)
    public float ingredientPrice;

    @Parameterized.Parameter(2)
    public float expectedPrice;

    @Parameterized.Parameters(name = "Цена булки: {0}, Цена ингредиента: {1}, Ожидаемая цена: {2}") // * добавлено
    public static Object[][] getData() {
        return new Object[][] {
                { 100.0f, 50.0f, 250.0f },
                { 200.0f, 150.0f, 550.0f },
                { 50.0f, 25.0f, 125.0f }
        };
    }

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
    @DisplayName("Проверка перемещения ингредиента")
    @Description("Проверяем изменение порядка ингредиентов в списке при перемещении")
    public void testMoveIngredient() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        // Перемещаем элемент с индекса 0 на индекс 1
        burger.moveIngredient(0, 1);

        assertEquals(filling, burger.ingredients.get(0));
        assertEquals(sauce, burger.ingredients.get(1));
    }

    @Test
    @DisplayName("Параметризованный тест расчёта стоимости бургера")
    @Description("Проверяем правильность расчета общей стоимости бургера с разными ценами компонентов") // * добавлено и параметризовано
    public void testGetPriceParameterized() {
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(sauce.getPrice()).thenReturn(ingredientPrice);

        burger.setBuns(bun);
        burger.addIngredient(sauce);

        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    @DisplayName("Проверка генерации чека")
    @Description("Проверяем корректность текстового содержимого чека для готового бургера")
    public void testGetReceipt() {
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100.0f);

        Mockito.when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(sauce.getName()).thenReturn("hot sauce");
        Mockito.when(sauce.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);


        String actualReceipt = burger.getReceipt();

        // Проверяем ключевые части чека
        assertTrue(actualReceipt.contains("black bun"));
        assertTrue(actualReceipt.contains("hot sauce"));
        assertTrue(actualReceipt.contains("250,000000") || actualReceipt.contains("250.000000"));
    }
}
