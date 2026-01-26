package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
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

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        when(mockBun.getName()).thenReturn("Булка");
        when(mockBun.getPrice()).thenReturn(26.0f);

        when(mockIngredient1.getName()).thenReturn("Котлета");
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient1.getPrice()).thenReturn(24.0f);

        when(mockIngredient2.getName()).thenReturn("Соус");
        when(mockIngredient2.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient2.getPrice()).thenReturn(90.0f);

        burger = new Burger();
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
        assertThat(receipt, containsString("Булка"));
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

        burger.removeIngredient(0);  // ✅ Удаляем первый (mockIngredient1)
        burger.removeIngredient(0);  // ✅ Удаляем второй (mockIngredient2 теперь на индексе 0)

        assertThat(burger.ingredients, empty());
    }
}

