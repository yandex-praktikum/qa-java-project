import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    @Mock
    Bun bun;
    @Mock
    Ingredient firstIngredient;
    @Mock
    Ingredient secondIngredient;

    private final float bunPrice;
    private final float firstIngredientPrice;
    private final float secondIngredientPrice;
    private final float expectedPrice;

    public BurgerPriceTest(float bunPrice, float firstIngredientPrice, float secondIngredientPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.firstIngredientPrice = firstIngredientPrice;
        this.secondIngredientPrice = secondIngredientPrice;
        this.expectedPrice = expectedPrice;
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Parameterized.Parameters(name = "Стоимость: булка-{0}, состав-{1},{2}, итого-{3}")
    public static Object[][] dataTest() {
        return new Object[][]{
                {100, 300, 200, 700},
                {200, 200, 300, 900},
                {300, 100, 100, 800}
        };
    }

    @Test
    public void getPriceTest() {
        Burger burger = new Burger();
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);
        Mockito.when(firstIngredient.getPrice()).thenReturn(firstIngredientPrice);
        Mockito.when(secondIngredient.getPrice()).thenReturn(secondIngredientPrice);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        float price = burger.getPrice();
        assertEquals("Неверная сумма бургера", expectedPrice, price, 0.01f);
    }
}
