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

@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    @Parameterized.Parameter(0)
    public float bunPrice;

    @Parameterized.Parameter(1)
    public float ingredientPrice;

    @Parameterized.Parameter(2)
    public float expectedPrice;

    @Parameterized.Parameters(name = "Цена булки: {0}, Цена ингредиента: {1}, Ожидаемая цена: {2}")
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

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
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

}
