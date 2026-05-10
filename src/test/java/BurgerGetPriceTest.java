import org.junit.After;
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
public class BurgerGetPriceTest {

    private AutoCloseable closeable;

    private final float bunPrice;
    private final float saucePrice;
    private final float fillingPrice;
    private final float totalPrice;

    public BurgerGetPriceTest(float bunPrice, float saucePrice, float fillingPrice, float totalPrice) {

        this.bunPrice=bunPrice;
        this.saucePrice = saucePrice;
        this.fillingPrice = fillingPrice;
        this.totalPrice = totalPrice;
    }

    @Parameterized.Parameters(name = "булка={0}, начинка1={1}, начинка2={2}, цена={3}")
    public static Object[][] differentPrice() {
        return new Object[][]{
                {100f, 100f, 100f, 400f},
                {0, 100f, 100f, 200f},
                {100f, 0, 100f, 300f},
                {100f, 100f, 0, 300f},
                {0, 0, 0, 0},
                {15.7f, 23.8f, 99.9f, 155.1f},
                {10000f, 20000f, 30000f, 70000f}
        };
    }

    @Before
    public void init() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredientSauce;
    @Mock
    private Ingredient ingredientFilling;

    @Test
    public void correctPrice(){

        Burger burger = new Burger();

        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredientSauce.getPrice()).thenReturn(saucePrice);
        Mockito.when(ingredientFilling.getPrice()).thenReturn(fillingPrice);

        burger.setBuns(bun);
        burger.addIngredient(ingredientSauce);
        burger.addIngredient(ingredientFilling);

        float actualPrice = burger.getPrice();
        assertEquals(totalPrice, actualPrice, 0f);
    }

    @After
    public void tearDown() throws Exception {
        closeable.close();
    }
}

