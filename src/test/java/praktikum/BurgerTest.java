package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;


@Story("Burger Tests")
public class BurgerTest {

    private static final String BUN_NAME = "Булка";
    private static final float BUN_PRICE = 26.0f;

    private static final String FILLING_NAME = "Котлета";
    private static final IngredientType FILLING_TYPE = IngredientType.FILLING;
    private static final float FILLING_PRICE = 24.0f;

    private static final String SAUCE_NAME = "Соус";
    private static final IngredientType SAUCE_TYPE = IngredientType.SAUCE;
    private static final float SAUCE_PRICE = 90.0f;

    private Burger burger;

    private AutoCloseable mocks;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(mockBun.getName()).thenReturn(BUN_NAME );
        when(mockBun.getPrice()).thenReturn(BUN_PRICE);

        when(mockIngredient1.getName()).thenReturn(FILLING_NAME);
        when(mockIngredient1.getType()).thenReturn(FILLING_TYPE);
        when(mockIngredient1.getPrice()).thenReturn(FILLING_PRICE);

        when(mockIngredient2.getName()).thenReturn(SAUCE_NAME);
        when(mockIngredient2.getType()).thenReturn(SAUCE_TYPE);
        when(mockIngredient2.getPrice()).thenReturn(SAUCE_PRICE);

        burger = new Burger();
    }

    @After
    public void tearDown() throws Exception {
        if (mocks != null) {
            mocks.close();
        }
    }

    @Test
    @Description("Проверка: булка устанавливается корректно")
    public void testSetBun() {
        burger.setBuns(mockBun);
        assertThat(burger.bun, notNullValue());
        assertThat(burger.bun.getName(), equalTo("Булка"));
        verify(mockBun, atLeastOnce()).getName();
    }

    @Test
    @Description("Проверка: можно добавить ингредиент")
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        assertThat(burger.ingredients, hasItem(mockIngredient1));
    }

    @Test
    @Description("Проверка: можно добавить несколько ингредиентов")
    public void testAddMultipleIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        assertThat(burger.ingredients, hasItems(mockIngredient1, mockIngredient2));
        assertThat(burger.ingredients.size(), equalTo(2));
    }

    @Test
    @Description("Проверка: ингредиент удаляется из бургера по индексу")
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);  // ✅ Удаляем по индексу (первый ингредиент)

        assertThat(burger.ingredients, not(hasItem(mockIngredient1)));
        assertThat(burger.ingredients, hasItem(mockIngredient2));
        assertThat(burger.ingredients.size(), equalTo(1));
    }


    @Test
    @Description("Проверка: расчет цены бургера с булкой и ингредиентами")
    public void testBurgerPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 26.0f * 2 + 24.0f + 90.0f;
        assertThat(burger.getPrice(), equalTo(expectedPrice));
    }

    @Test
    @Description("Проверка: получить состав бургера")
    public void testGetReceipt() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();

        assertThat(receipt, notNullValue());

        float expectedPrice = 26.0f * 2 + 24.0f + 90.0f;

        String expectedReceipt = ""
                + String.format("(==== %s ====)%n", "Булка")
                + String.format("= %s %s =%n", "filling", "Котлета")
                + String.format("= %s %s =%n", "sauce", "Соус")
                + String.format("(==== %s ====)%n", "Булка")
                + String.format("%nPrice: %f%n", expectedPrice);

        assertThat(receipt, equalTo(expectedReceipt));
    }

    @Test
    @Description("Проверка: пустой бургер без булки")
    public void testEmptyBurgerWithoutBun() {
        burger.addIngredient(mockIngredient1);
        assertThat(burger.bun, nullValue());
    }

    @Test
    @Description("Проверка: бургер без ингредиентов")
    public void testBurgerWithoutIngredients() {
        burger.setBuns(mockBun);
        assertThat(burger.ingredients, empty());
    }

    @Test
    @Description("Проверка: кол-во вызовов методов на мокированной булке")
    public void testMockBunMethodCalls() {
        burger.setBuns(mockBun);
        burger.getPrice();

        verify(mockBun, atLeast(1)).getPrice();
    }

    @Test
    @Description("Проверка: ингредиенты хранятся в правильном порядке")
    public void testIngredientOrder() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        List<Ingredient> ingredients = burger.ingredients;
        assertThat(ingredients.get(0), equalTo(mockIngredient1));
        assertThat(ingredients.get(1), equalTo(mockIngredient2));
    }

    @Test
    @Description("Проверка: перемещение ингредиента")
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.moveIngredient(0, 1);

        assertThat(burger.ingredients.get(0), equalTo(mockIngredient2));
        assertThat(burger.ingredients.get(1), equalTo(mockIngredient1));
    }

    @Test
    @Description("Проверка: цена бургера только с булкой")
    public void testBurgerPriceWithOnlyBun() {
        burger.setBuns(mockBun);

        float expectedPrice = 26.0f * 2;
        assertThat(burger.getPrice(), equalTo(expectedPrice));
    }

    @Test
    @Description("Проверка: добавление одного ингредиента в список")
    public void testSingleIngredientAddition() {
        burger.addIngredient(mockIngredient1);

        assertThat(burger.ingredients.size(), equalTo(1));
        assertThat(burger.ingredients.get(0), sameInstance(mockIngredient1));
    }

    @Test
    @Description("Проверка: удаление всех ингредиентов по индексу")
    public void testRemoveAllIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);
        burger.removeIngredient(0);

        assertThat(burger.ingredients, empty());
    }
}

