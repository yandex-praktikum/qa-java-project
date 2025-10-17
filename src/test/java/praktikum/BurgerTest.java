package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.assertj.core.api.SoftAssertions;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredientSauce;

    @Mock
    private Ingredient mockIngredientBun;

    @Mock
    private Ingredient mockIngredientFilling;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(mockBun);
        assertEquals("Установлена неверная булочка", mockBun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(mockIngredientSauce);
        assertEquals("Некорректное состояние списка ингредиентов после вызова метода addIngredient()", List.of(mockIngredientSauce), burger.ingredients);
    }

    @Test
    public void removeIngredientTest() {
        SoftAssertions softAssertions = new SoftAssertions();
        burger.addIngredient(mockIngredientSauce);
        burger.addIngredient(mockIngredientBun);
        burger.addIngredient(mockIngredientFilling);
        softAssertions.assertThat(burger.ingredients.size())
                .as("После добавления трёх ингредиентов размер списка должен быть равен 3")
                .isEqualTo(3);

        burger.removeIngredient(1);
        softAssertions.assertThat(burger.ingredients.size())
                .as("После удаления одного ингредиента (индекс 1) размер списка должен уменьшиться до 2")
                .isEqualTo(2);
        softAssertions.assertAll();
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(mockIngredientBun);
        burger.addIngredient(mockIngredientFilling);

        burger.moveIngredient(0, 1);
        assertEquals("Некорректное перемещение ингредиента между позициями", mockIngredientBun, burger.ingredients.get(1));
    }


    @Test
    public void getReceiptTest() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredientSauce);
        burger.addIngredient(mockIngredientBun);

        Mockito.when(mockBun.getName()).thenReturn("Space bun");
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);
        Mockito.when(mockIngredientSauce.getName()).thenReturn("Ingredient0");
        Mockito.when(mockIngredientBun.getName()).thenReturn("Ingredient1");
        Mockito.when(mockIngredientSauce.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(mockIngredientBun.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockIngredientSauce.getPrice()).thenReturn(150.0f);
        Mockito.when(mockIngredientBun.getPrice()).thenReturn(50.0f);

        String expectedReceipt = "(==== Space bun ====)\r\n" +
                "= filling Ingredient0 =\r\n" +
                "= sauce Ingredient1 =\r\n" +
                "(==== Space bun ====)\r\n" +
                "\r\n" +
                "Price: 400,000000\r\n";

        assertEquals("Ожидался чек:\n" + expectedReceipt + "\nБыл получен чек:\n" + burger.getReceipt(), expectedReceipt, burger.getReceipt());
    }

}
