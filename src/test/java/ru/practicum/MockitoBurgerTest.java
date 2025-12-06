package ru.practicum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MockitoBurgerTest {
    @Mock
    private Bun mockBun;

    @Mock
    private Bun mockAnotherBun;

    @Mock
    private Ingredient mockIngredientBun;

    @Mock
    private Ingredient mockIngredientFilling;

    @Mock
    private Ingredient mockIngredientAdditional;

    private Burger burger;
    private Burger burgerAnother;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // Создаем объект
        burger = new Burger();
        // Добавляем булочку
        burger.setBuns(mockBun);
    }

    @Test
    // Проверяем конструктор
    public void testBurgerConstructor() {
        // Создаем объект
        burgerAnother = new Burger();
        // Проверяем, что объект создался
        assertNotNull(burgerAnother);
    }

    @Test
    // Проверяем что добавилась именно та булочка
    public void testSetBuns() {
        // Добавляем другую булочку в бургер
        burger.setBuns(mockAnotherBun);

        // Assert - проверяем через чек
        when(mockAnotherBun.getName()).thenReturn("черная булочка");
        String receipt = burger.getReceipt();

        // Проверяем, что в чеке есть название установленной булки
        assertTrue(receipt.contains("черная булочка"));
    }

    @Test
    // Проверяем добавление ингредиента
    public void testAddIngredient() {
        // Добавляем ингредиент в бургер
        burger.addIngredient(mockIngredientBun);

        when(mockIngredientBun.getName()).thenReturn("помидорка");
        when(mockIngredientBun.getType()).thenReturn(IngredientType.FILLING);
        String receipt = burger.getReceipt();

        // Проверяем, что в чеке есть название установленной начинки
        assertTrue(receipt.contains("помидорка"));

    }

    @Test
    // Проверяем добавление типа начинка ингредиента
    public void testAddIngredientTypeFilling() {
        // Добавляем ингредиент в бургер
        burger.addIngredient(mockIngredientBun);

        when(mockIngredientBun.getName()).thenReturn("помидорка");
        when(mockIngredientBun.getType()).thenReturn(IngredientType.FILLING);
        String receipt = burger.getReceipt();

        // Проверяем, что в чеке есть тип начинка
        assertTrue(receipt.contains("filling"));

    }

    @Test
    // Проверяем добавление типа соус ингредиента
    public void testAddIngredientTypeSause() {
        // Добавляем ингредиент в бургер
        burger.addIngredient(mockIngredientBun);

        when(mockIngredientBun.getName()).thenReturn("сладкий");
        when(mockIngredientBun.getType()).thenReturn(IngredientType.SAUCE);
        String receipt = burger.getReceipt();

        // Проверяем, что в чеке есть тип соус
        assertTrue(receipt.contains("sauce"));

    }

    @Test
    public void testRemoveIngredientAbsent() {
        // Добавляем два ингредиента в бургер
        burger.addIngredient(mockIngredientBun);
        burger.addIngredient(mockIngredientFilling);

        // Настраиваем данные с lenient()
        lenient().when(mockIngredientBun.getType()).thenReturn(IngredientType.SAUCE);
        lenient().when(mockIngredientBun.getName()).thenReturn("кетчуп");
        lenient().when(mockIngredientFilling.getType()).thenReturn(IngredientType.FILLING);
        lenient().when(mockIngredientFilling.getName()).thenReturn("сыр");

        burger.removeIngredient(0);
        String receipt = burger.getReceipt();
        assertFalse("В чеке не должно быть кетчупа после удаления", receipt.contains("кетчуп"));
    }


    @Test
    // Проверяем наличие второго ингредиента, после удаления первого
    public void testRemoveIngredientAvailabilityOfRemaining() {
        // Добавляем два ингредиента в бургер
        burger.addIngredient(mockIngredientBun);
        burger.addIngredient(mockIngredientFilling);

        lenient().when(mockIngredientBun.getType()).thenReturn(IngredientType.SAUCE);
        lenient().when(mockIngredientBun.getName()).thenReturn("кетчуп");
        lenient().when(mockIngredientFilling.getType()).thenReturn(IngredientType.FILLING);
        lenient().when(mockIngredientFilling.getName()).thenReturn("сыр");

        burger.removeIngredient(0);
        String receipt = burger.getReceipt();
        assertTrue("В чеке должен остаться сыр после удаления", receipt.contains("сыр"));
    }


    @Test(expected = IndexOutOfBoundsException.class)
    // Проверяем наличие исключения, при удалении ингредиента с неверным индексом
    public void testRemoveIngredientInvalidIndex() {
        // Arrange - добавляем только один ингредиент
        burger.addIngredient(mockIngredientBun);

        // Пытаемся удалить ингредиент с несуществующим индексом
        // Ожидаем, что выбросится IndexOutOfBoundsException
        burger.removeIngredient(5);
    }


    @Test
    // Проверяем перемещение ингредиента на новую позицию
    public void testMoveIngredient() {
        // Добавляем три ингредиента в бургер
        burger.addIngredient(mockIngredientBun);
        burger.addIngredient(mockIngredientFilling);
        burger.addIngredient(mockIngredientAdditional);

        // Настраиваем данные для всех ингредиентов
        when(mockIngredientBun.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientBun.getName()).thenReturn("майонез");
        when(mockIngredientFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientFilling.getName()).thenReturn("салат");
        when(mockIngredientAdditional.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientAdditional.getName()).thenReturn("помидорка");

        // Перемещаем майонез (индекс 0) на позицию после помидора (индекс 2)
        burger.moveIngredient(0, 2);

        // Получаем чек после перемещения
        String receipt = burger.getReceipt();

        // Проверяем новый порядок ингредиентов в чеке
        // Находим позиции каждого ингредиента в строке чека
        int saladIndex = receipt.indexOf("салат");
        int tomatoIndex = receipt.indexOf("помидорка");

        // Проверяем, что салат теперь первый, потом помидор, потом майонез
        assertTrue("Салат должен быть перед помидором", saladIndex < tomatoIndex);
    }

    public void testMoveIngredientAdditional() {
        // Добавляем три ингредиента в бургер
        burger.addIngredient(mockIngredientBun);
        burger.addIngredient(mockIngredientFilling);
        burger.addIngredient(mockIngredientAdditional);

        // Настраиваем данные для всех ингредиентов
        when(mockIngredientBun.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientBun.getName()).thenReturn("майонез");
        when(mockIngredientFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientFilling.getName()).thenReturn("салат");
        when(mockIngredientAdditional.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientAdditional.getName()).thenReturn("помидорка");

        // Перемещаем майонез (индекс 0) на позицию после помидора (индекс 2)
        burger.moveIngredient(0, 2);

        // Получаем чек после перемещения
        String receipt = burger.getReceipt();

        // Проверяем новый порядок ингредиентов в чеке
        // Находим позиции каждого ингредиента в строке чека

        int tomatoIndex = receipt.indexOf("помидорка");
        int mayoIndex = receipt.indexOf("майонез");

         assertTrue("Помидор должен быть перед майонезом", tomatoIndex < mayoIndex);
    }

    // Проверяем перемещение ингредиента с неверным индексом, ожидаем исключение
    @Test(expected = IndexOutOfBoundsException.class)
    public void testMoveIngredientInvalidIndex() {
        // Arrange - добавляем один ингредиент
        burger.addIngredient(mockIngredientBun);

        // Пытаемся переместить ингредиент на несуществующую позицию
        // Ожидаем, что выбросится IndexOutOfBoundsException
        burger.moveIngredient(0, 5);
    }

    @Test
    // Проверяем расчет цены бургера с булочкой и без ингредиентов
    public void testGetPriceOnlyBun() {
        // Настраиваем цену булочки
        when(mockBun.getPrice()).thenReturn(50.0f);

        // Получаем цену бургера (без ингредиентов)
        float price = burger.getPrice();

        // Проверяем, что цена равна 2 * цена булочки
        assertEquals("Цена бургера без ингредиентов должна быть 2 * цена булочки", 100.0f, price, 0.001f);
    }

    @Test
    // Проверяем расчет цены бургера с одним ингредиентом
    public void testGetPriceWithOneIngredient() {
        // Настраиваем цены булочки и ингредиента
        when(mockBun.getPrice()).thenReturn(50.0f);
        when(mockIngredientBun.getPrice()).thenReturn(30.0f);
        // Добавляем ингредиент в бургер
        burger.addIngredient(mockIngredientBun);

        // Получаем цену бургера
        float price = burger.getPrice();

        // Проверяем расчет: 2 булочки по 50 + ингредиент 30 = 130
        assertEquals("Цена бургера с одним ингредиентом должна быть 130.0", 130.0f, price, 0.001f);
    }

    @Test
    // Проверяем расчет цены бургера с несколькими ингредиентами
    public void testGetPriceWithMultipleIngredients() {
        // Настраиваем цены всех компонентов
        when(mockBun.getPrice()).thenReturn(100.0f);
        when(mockIngredientBun.getPrice()).thenReturn(50.0f);
        when(mockIngredientFilling.getPrice()).thenReturn(75.0f);
        when(mockIngredientAdditional.getPrice()).thenReturn(25.0f);

        // Добавляем все три ингредиента в бургер
        burger.addIngredient(mockIngredientBun);
        burger.addIngredient(mockIngredientFilling);
        burger.addIngredient(mockIngredientAdditional);

        // Получаем общую цену бургера
        float price = burger.getPrice();

        // Проверяем расчет: 2*100 + 50 + 75 + 25 = 350
        assertEquals("Цена бургера с тремя ингредиентами должна быть 350.0", 350.0f, price, 0.001f);
    }

    @Test
    // Проверяем формирование полного чека с несколькими ингредиентами
    public void testGetReceiptWithMultipleIngredients() {
        // Настраиваем данные булочки
        when(mockBun.getName()).thenReturn("белая булочка");
        when(mockBun.getPrice()).thenReturn(50.0f);

        // Настраиваем данные для трех ингредиентов разных типов
        when(mockIngredientBun.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientBun.getName()).thenReturn("кетчуп");
        when(mockIngredientBun.getPrice()).thenReturn(10.0f);
        when(mockIngredientFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientFilling.getName()).thenReturn("котлета");
        when(mockIngredientFilling.getPrice()).thenReturn(50.0f);
        when(mockIngredientAdditional.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientAdditional.getName()).thenReturn("майонез");
        when(mockIngredientAdditional.getPrice()).thenReturn(15.0f);

        // Добавляем все ингредиенты в бургер
        burger.addIngredient(mockIngredientBun);
        burger.addIngredient(mockIngredientFilling);
        burger.addIngredient(mockIngredientAdditional);

        // Получаем чек бургера
        String receipt = burger.getReceipt();

        String expectedReceipt =
                "(==== белая булочка ====)\r\n" +
                        "= sauce кетчуп =\r\n" +
                        "= filling котлета =\r\n" +
                        "= sauce майонез =\r\n" +
                        "(==== белая булочка ====)\r\n" +
                        "\r\n" +
                        "Price: 175,000000\r\n";

        assertEquals("Чек должен соответствовать ожидаемому формату",
                expectedReceipt, receipt);
    }

    @Test
    // Проверяем, что ингредиенты сохраняют порядок добавления в чеке
    public void testIngredientsOrderInReceipt() {
        // Настраиваем данные булочки
        when(mockBun.getName()).thenReturn("ржаная булочка");
        // Настраиваем данные для трех ингредиентов
        when(mockIngredientBun.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientBun.getName()).thenReturn("горчица");
        when(mockIngredientFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientFilling.getName()).thenReturn("салат");
        when(mockIngredientAdditional.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientAdditional.getName()).thenReturn("помидор");
        // Добавляем ингредиенты в определенном порядке
        burger.addIngredient(mockIngredientBun);
        burger.addIngredient(mockIngredientFilling);
        burger.addIngredient(mockIngredientAdditional);

        // Получаем чек бургера
        String receipt = burger.getReceipt();

        // Проверяем порядок ингредиентов в чеке
        int mustardIndex = receipt.indexOf("горчица");
        int saladIndex = receipt.indexOf("салат");


        // Проверяем, что ингредиенты идут в том же порядке, в котором были добавлены
        assertTrue("Горчица должна быть перед салатом", mustardIndex < saladIndex);
    }

    @Test
    // Проверяем, что ингредиенты сохраняют порядок добавления в чеке
    public void testIngredientsAdditionalOrderInReceipt() {
        // Настраиваем данные булочки
        when(mockBun.getName()).thenReturn("ржаная булочка");
        // Настраиваем данные для трех ингредиентов
        when(mockIngredientBun.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredientBun.getName()).thenReturn("горчица");
        when(mockIngredientFilling.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientFilling.getName()).thenReturn("салат");
        when(mockIngredientAdditional.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredientAdditional.getName()).thenReturn("помидор");
        // Добавляем ингредиенты в определенном порядке
        burger.addIngredient(mockIngredientBun);
        burger.addIngredient(mockIngredientFilling);
        burger.addIngredient(mockIngredientAdditional);

        // Получаем чек бургера
        String receipt = burger.getReceipt();

        // Проверяем порядок ингредиентов в чеке
        int saladIndex = receipt.indexOf("салат");
        int tomatoIndex = receipt.indexOf("помидор");

        // Проверяем, что ингредиенты идут в том же порядке, в котором были добавлены
        assertTrue("Салат должен быть перед помидором", saladIndex < tomatoIndex);
    }

    @Test
    // Проверяем вывод чека с пустым бургером (только булочка)
    public void testEmptyBurgerReceipt() {
        // Настраиваем данные булочки
        when(mockBun.getName()).thenReturn("обычная булочка");
        when(mockBun.getPrice()).thenReturn(40.0f);

        // Получаем чек пустого бургера (без ингредиентов)
        String receipt = burger.getReceipt();

        String expectedReceipt =
                "(==== обычная булочка ====)\r\n" +
                        "(==== обычная булочка ====)\r\n" +
                        "\r\n" +
                        "Price: 80,000000\r\n";

        // Проверяем, что чек полностью соответствует ожидаемому формату
        assertEquals("Чек пустого бургера должен соответствовать ожидаемому формату",
                expectedReceipt, receipt);
    }
}


