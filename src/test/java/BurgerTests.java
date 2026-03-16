import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTests {

    private Burger burger;

    // Мокированные экземпляры для булочек и ингредиентов
    @Mock
    private Bun initialBun;
    @Mock
    private Ingredient firstIngredient;
    @Mock
    private Ingredient secondIngredient;

    // Параметры теста
    private String expectedBunName;
    private String expectedFirstIngredientName;
    private String expectedSecondIngredientName;

    // Подготовка мока базы данных
    @Mock
    private Database database;

    // Подготовка перед каждым тестом
    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Создаём пустой бургер
        burger = new Burger();

        // Настраиваем поведение моков
        when(initialBun.getName()).thenReturn(expectedBunName);
        when(firstIngredient.getName()).thenReturn(expectedFirstIngredientName);
        when(secondIngredient.getName()).thenReturn(expectedSecondIngredientName);

        // Устанавливаем типы ингредиентов
        when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);

        // Устанавливаем фиксированную цену для расчётов
        when(initialBun.getPrice()).thenReturn(200f);
        when(firstIngredient.getPrice()).thenReturn(100f);
        when(secondIngredient.getPrice()).thenReturn(100f);

        // Добавляем ингредиенты
        burger.setBuns(initialBun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
    }

    // Параметризация набора данных для тестирования
    @Parameterized.Parameters(name = "{index}: Testing with bun={0}, first ingredient={1}, second ingredient={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{"white bun", "hot sauce", "cutlet"},   // Набор данных 1
                {"red bun", "chili sauce", "dinosaur"}  // Набор данных 2
        });
    }

    // Конструктор для передачи данных из параметризированного теста
    public BurgerTests(String expectedBunName, String expectedFirstIngredientName, String expectedSecondIngredientName) {
        this.expectedBunName = expectedBunName;
        this.expectedFirstIngredientName = expectedFirstIngredientName;
        this.expectedSecondIngredientName = expectedSecondIngredientName;
    }

    // Тест 1: Проверка суммы стоимости бургера
    @Test
    public void testTotalPrice() {
        // Суммарная стоимость рассчитывается как удвоенная цена булочки плюс цены ингредиентов
        float expectedPrice = 2 * 200f + 100f + 100f;
        float actualPrice = burger.getPrice();
        // Проверяем, совпадает ли общая сумма
        assertEquals(expectedPrice, actualPrice, 0.01f);
    }

    // Тест 2: Проверка печати чека

    @Test
    public void testPrintReceipt() {
        // Получаем фактический чек
        String actualReceipt = burger.getReceipt();

        // Получаем итоговую цену и округляем её до целого числа
        float calculatedPrice = burger.getPrice();
        long roundedPrice = Math.round(calculatedPrice);

        // Замена оригинальной цены на округлённую
        actualReceipt = actualReceipt.replaceAll("(?<=Price:\\s)\\d+(,\\d+)?", Long.toString(roundedPrice));

        // Очистка фактического чека от скрытых символов
        actualReceipt = actualReceipt.replaceAll("\r", "").trim();

        // Формируем ожидаемый чек
        String expectedReceipt = "(==== " + expectedBunName + " ====)\n" + "= " + firstIngredient.getType().toString().toLowerCase() + " " + expectedFirstIngredientName + " =\n" + "= " + secondIngredient.getType().toString().toLowerCase() + " " + expectedSecondIngredientName + " =\n" + "(==== " + expectedBunName + " ====)\n" + "\n" + "Price: " + roundedPrice;

        // Проверяем чек целиком
        assertEquals(expectedReceipt, actualReceipt);
    }

    // Тест 3: Проверка удаления ингредиента
    @Test
    public void testRemoveIngredient() {
        // Удаляем первый ингредиент
        burger.removeIngredient(0);

        // Осталась только котлета
        assertEquals(1, burger.ingredients.size());
        assertEquals(expectedSecondIngredientName, burger.ingredients.get(0).getName());
    }

    // Тест 4: Проверка перемещения ингредиента
    @Test
    public void testMoveIngredient() {
        // Меняем местами ингредиенты
        burger.moveIngredient(0, 1);

        // Проверяем порядок ингредиентов
        assertEquals(expectedSecondIngredientName, burger.ingredients.get(0).getName());
        assertEquals(expectedFirstIngredientName, burger.ingredients.get(1).getName());
    }
}