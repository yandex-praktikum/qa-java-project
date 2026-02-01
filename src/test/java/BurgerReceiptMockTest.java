import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerReceiptMockTest {

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient sauceMock;

    @Mock
    private Ingredient fillingMock;

    @Test
    public void getReceipt_ShouldFormatCorrectly() {
        // Настройка моков
        when(bunMock.getName()).thenReturn("black bun");
        when(bunMock.getPrice()).thenReturn(100.0f);

        when(sauceMock.getType()).thenReturn(IngredientType.SAUCE);
        when(sauceMock.getName()).thenReturn("sour cream");
        when(sauceMock.getPrice()).thenReturn(200.0f);

        when(fillingMock.getType()).thenReturn(IngredientType.FILLING);
        when(fillingMock.getName()).thenReturn("cutlet");
        when(fillingMock.getPrice()).thenReturn(100.0f);

        // Создание бургера
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        // ФАКТИЧЕСКИЙ ВЫВОД (для отладки - раскомментируйте при необходимости)
        String actual = burger.getReceipt();
        // System.out.println("ACTUAL:\n" + actual);

        // КОРРЕКТНОЕ ОЖИДАЕМОЕ ЗНАЧЕНИЕ с учетом платформы
        String expected = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n%n" +
                        "Price: %.6f%n",
                "black bun",
                "sauce", "sour cream",
                "filling", "cutlet",
                "black bun",
                500.0f // 100*2 + 200 + 100
        );

        assertEquals(expected, actual);
    }
}