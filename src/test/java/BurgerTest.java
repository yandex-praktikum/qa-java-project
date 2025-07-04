import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient sauceMock;

    @Mock
    private Ingredient fillingMock;

    @Mock
    private Ingredient ingredient1Mock;

    @Mock
    private Ingredient ingredient2Mock;

    @Test
    public void testGetPrice() {
        // Задали значение используя стабы
        when(bunMock.getPrice()).thenReturn(100.0f);
        when(ingredient1Mock.getPrice()).thenReturn(50.0f);
        when(ingredient2Mock.getPrice()).thenReturn(30.0f);

        Burger burger = new Burger();
        burger.setBuns(bunMock);
        burger.addIngredient(ingredient1Mock);
        burger.addIngredient(ingredient2Mock);

        // Цена за бургер
        float expectedPrice = 100.0f * 2 + 50.0f + 30.0f;

        System.out.println("\nОжидаемая цена: " + expectedPrice);

        assertEquals(expectedPrice, burger.getPrice(), 0.001f);

        // Проверка вызова для мок
        verify(bunMock, times(1)).getPrice();
        verify(ingredient1Mock).getPrice();
        verify(ingredient2Mock).getPrice();

        float actualPrice = burger.getPrice();
        System.out.println("\nФактическая цена: " + actualPrice);
    }

    @Test
    public void testGetReceipt() {

        when(bunMock.getName()).thenReturn("Краторная булочка");
        when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceMock.getName()).thenReturn("Сырный соус");
        when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
        when(fillingMock.getName()).thenReturn("Говяжий метеорит");

        Burger burger = new Burger();
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        String receipt = burger.getReceipt();

        // Проверка чека
        String expectedReceipt =
                "(==== Краторная булочка ====)\r\n" +
                        "= sauce Сырный соус =\r\n" +
                        "= filling Говяжий метеорит =\r\n" +
                        "(==== Краторная булочка ====)\r\n\r\n" +
                        "Price: 0,000000\r\n";

        System.out.println("Проверяем структуру чека:");
        System.out.println(expectedReceipt);

        assertEquals(expectedReceipt, receipt);
    }
}
