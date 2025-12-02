package praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private Burger burger;

    private final float expectedPrice;
    private final String bunName;
    private final float bunPrice;
    private final String fillingName;
    private final float fillingPrice;
    private final String sauceName;
    private final float saucePrice;
    private AutoCloseable mocks;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient filling;
    @Mock
    private Ingredient sauce;

    public BurgerParameterizedTest(String bunName, float bunPrice, String fillingName,
                                   float fillingPrice, String sauceName,
                                   float saucePrice, float expectedPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.fillingName = fillingName;
        this.fillingPrice = fillingPrice;
        this.sauceName = sauceName;
        this.saucePrice = saucePrice;
        this.expectedPrice = expectedPrice;
    }

    @Before
    public void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @Parameterized.Parameters(name = "{index}: Test with bunPrice={0}, fillingPrice={1}, saucePrice={2}, expectedPrice={3}")
    public static Object[][] data() {
        return new Object[][]{
                {"fluorescent bun", 90f, "beef meteorite", 250, "sauce spicy x", 15f, 445f},
                {"kratornaya bun", 110f, "meat of immortal mollusks", 415f, "sauce traditional galactic", 25f, 660f},
                {"fluorescent bun", 90f, "crispy mineral rings", 510f, "sauce branded", 35f, 725f}
        };
    }

    @Test
    public void testGetPricePositiveResult() {
        burger = new Burger();
        when(bun.getName()).thenReturn(bunName);
        when(bun.getPrice()).thenReturn(bunPrice);
        when(filling.getName()).thenReturn(fillingName);
        when(filling.getPrice()).thenReturn(fillingPrice);
        when(sauce.getName()).thenReturn(sauceName);
        when(sauce.getPrice()).thenReturn(saucePrice);
        burger.setBuns(bun);
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @After
    public void tearDown() throws Exception {
        burger = null;
        bun = null;
        sauce = null;
        filling = null;
        if (mocks != null) {
            mocks.close();
        }
    }
}
