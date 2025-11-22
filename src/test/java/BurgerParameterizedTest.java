import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.Collection;
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

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private Burger burger;

    @Mock
    private Bun mockBun;
    @Mock
    private Ingredient mockFilling;
    @Mock
    private Ingredient mockSauce;

    private float expectedPrice;
    private String bunName;
    private float bunPrice;
    private String fillingName;
    private float fillingPrice;
    private String sauceName;
    private float saucePrice;

    public BurgerParameterizedTest(String bunName, float bunPrice, String fillingName, float fillingPrice,
                                   String sauceName, float saucePrice, float expectedPrice) {
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
        MockitoAnnotations.initMocks(this);
        burger = new Burger();

        when(mockBun.getName()).thenReturn(bunName);
        when(mockBun.getPrice()).thenReturn(bunPrice);

        when(mockFilling.getName()).thenReturn(fillingName);
        when(mockFilling.getPrice()).thenReturn(fillingPrice);

        when(mockSauce.getName()).thenReturn(sauceName);
        when(mockSauce.getPrice()).thenReturn(saucePrice);

        burger.setBuns(mockBun);
        burger.addIngredient(mockFilling);
        burger.addIngredient(mockSauce);
    }

    @After
    public void tearDown() {
        burger = null;
        mockBun = null;
        mockSauce = null;
        mockFilling = null;
    }

    @Parameterized.Parameters(name = "{index}: Test with bunPrice={1}, fillingPrice={2}, saucePrice={3}, expectedPrice={4}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "black bun", 100F, "cutlet", 100F, "hot sauce", 100F, 400F },
                { "white bun", 200F, "dinosaur", 200F, "sour cream", 200F, 800F },
                { "red bun", 300F, "sausage", 300F, "chili sauce", 300F, 1200F }
        });
    }

    @Test
    public void getPriceTest() {
        assertEquals("Цена рассчитана неправильно", expectedPrice, burger.getPrice(), 0.0001);
    }
}