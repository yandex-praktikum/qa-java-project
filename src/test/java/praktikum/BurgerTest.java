package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

// Параметризованный запуск
@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    // Заглушки Моки
    private Bun mockBun;
    private Ingredient mockIngredient;

    // Параметры
    @Parameterized.Parameter
    public float bunPrice; //цена булочки

    @Parameterized.Parameter(1)
    public float ingredientPrice; //цена ингредиентов

    @Parameterized.Parameter(2)
    public float expectedPrice; //итог цена

    // Набор данных
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {50.0F, 50.0F, 150.0F},
                {75.0F, 75.0F, 225.0F},
                {100.0F, 100.0F, 300.0F},
        } ) ;
    }

    // Метод, выполняемый перед каждым тестом
    @Before
    public void setUp() {
        burger = new Burger();                    //новый бургер
        mockBun = mock(Bun.class);                //заглушку для булочки
        mockIngredient = mock(Ingredient.class);  //заглушку для ингредиента
    }

    // Тест: Проверка установки булочки в бургер
    @Test
    public void shouldSetBun() {
        when(mockBun.getName()).thenReturn("Булка64");
        when(mockBun.getPrice()).thenReturn(bunPrice);

        burger.setBuns(mockBun);

        assertEquals("Булка64", burger.bun.getName());
        assertEquals(bunPrice, burger.bun.getPrice(), 0);
    }

    // Тест: Проверка добавления ингредиента
    @Test
    public void shouldAddIngredient() {
        setUpMockIngredient("Добавка164", ingredientPrice);

        burger.addIngredient(mockIngredient);

        assertEquals(1, burger.ingredients.size());
        assertEquals("Добавка164", burger.ingredients.get(0).getName());
    }

    // Тест: Проверка удаления ингредиента по индексу
    @Test
    public void shouldRemoveIngredient() {
        setUpMockIngredient("Добавка164", ingredientPrice);

        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    // Тест: Проверка перемещения ингредиента в списке
    @Test
    public void shouldMoveIngredient() {
        Ingredient mockIngredient2 = mock(Ingredient.class);

        //имена ингредиентов
        when(mockIngredient.getName()).thenReturn("Добавка64");
        when(mockIngredient2.getName()).thenReturn("Добавка164");

        //добавление их в бургер
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient2);

        //перемещение
        burger.moveIngredient(0, 1);

        assertArrayEquals(new Ingredient[]{mockIngredient2, mockIngredient}, burger.ingredients.toArray());
    }

    // Тест: Проверка расчёта цены, если нет ингредиентов (только булки)
    @Test
    public void shouldCalculatePriceWithNoIngredients() {
        //цена булочки
        when(mockBun.getPrice()).thenReturn(bunPrice);

        burger.setBuns(mockBun);

        assertEquals(bunPrice * 2, burger.getPrice(), 0);
    }

    // Тест: Проверка расчёта цены с ингредиентом
    @Test
    public void shouldCalculatePriceWithIngredient() {

        when(mockBun.getPrice()).thenReturn(bunPrice);

        burger.setBuns(mockBun);

        setUpMockIngredient("Добавка64", ingredientPrice);

        burger.addIngredient(mockIngredient);

        assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    // Тест: Проверка формирования чека
    @Test
    public void shouldGenerateReceipt() {

        when(mockBun.getName()).thenReturn("Булка64");
        when(mockBun.getPrice()).thenReturn(bunPrice);

        burger.setBuns(mockBun);

        setUpMockIngredient("Добавка64", ingredientPrice);

        burger.addIngredient(mockIngredient);

        // Ожидаемый чек: булочки сверху и снизу, ингредиент посередине, итоговая цена
        String expectedReceipt = String.format(
                "(==== Булка64 ====)%n" +
                        "= filling Добавка64 =%n" +
                        "(==== Булка64 ====)%n" +
                        "%nPrice: %.6f%n", expectedPrice);

        assertEquals(expectedReceipt, burger.getReceipt());
    }

    // Для настройки заглушки ингредиента
    private void setUpMockIngredient(String name, float price) {
        when(mockIngredient.getName()).thenReturn(name);
        when(mockIngredient.getPrice()).thenReturn(price);
        when(mockIngredient.getType()).thenReturn(IngredientType.FILLING);
    }
}