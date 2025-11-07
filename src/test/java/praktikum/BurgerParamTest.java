package praktikum;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerParamTest {

    @Parameterized.Parameters(name = "bun={0}, ingredients={1}, expectedPrice={2}")
    public static Iterable<Object[]> data() {
        return BurgerTestData.data();
    }

    private final float bunPrice;
    private final List<Float> ingredientPrices;
    private final float expectedPrice;

    public BurgerParamTest(float bunPrice, List<Float> ingredientPrices, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    private Burger burger;
    private Bun bunMock;

    @Before
    public void setUp() {
        burger = new Burger();

        bunMock = mock(Bun.class);
        when(bunMock.getName()).thenReturn("Test Bun");
        when(bunMock.getPrice()).thenReturn(bunPrice);

        burger.setBuns(bunMock);

        for (int i = 0; i < ingredientPrices.size(); i++) {
            Ingredient ing = mock(Ingredient.class, "ing-" + i);
            IngredientType type = (i % 2 == 0) ? IngredientType.SAUCE : IngredientType.FILLING;
            when(ing.getType()).thenReturn(type);
            when(ing.getName()).thenReturn((type == IngredientType.SAUCE ? "bbq" : "cutlet") + "-" + i);
            when(ing.getPrice()).thenReturn(ingredientPrices.get(i));
            burger.addIngredient(ing);
        }
    }

    @After
    public void tearDown() {
        burger = null;
        bunMock = null;
    }

    @Test
    public void getPrice() {
        float price = burger.getPrice();
        assertEquals(expectedPrice, price, 0.0001f);
    }

    @Test
    public void getReceiptInFooter() {
        String receipt = burger.getReceipt();

        String footer = String.format("(==== %s ====)%n", "Test Bun");
        assertThat("В чеке должен быть нижний разделитель", receipt, containsString(footer));
    }

    @Test
    public void getReceiptInHeader() {
        String receipt = burger.getReceipt();

        String header = String.format("(==== %s ====)%n", "Test Bun");
        assertTrue("В чеке должен быть заголовок с названием булочки", receipt.startsWith(header));
    }

    @Test
    public void getReceiptContainAllIngredientLines() {
        String receipt = burger.getReceipt();

        for (int i = 0; i < burger.ingredients.size(); i++) {
            Ingredient ing = burger.ingredients.get(i);
            String expectedLine = String.format("= %s %s =%n",
                    ing.getType().toString().toLowerCase(), ing.getName());
            assertThat("Должна присутствовать строка ингредиента " + i, receipt, containsString(expectedLine));
        }
    }
}
