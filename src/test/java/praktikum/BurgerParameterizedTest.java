package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    private final String bunName;
    private final float bunPrice;
    private final IngredientType ingredientType;
    private final String ingredientName;
    private final float ingredientPrice;

    public BurgerParameterizedTest(String bunName, float bunPrice, IngredientType ingredientType,
                                   String ingredientName, float ingredientPrice) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredientType = ingredientType;
        this.ingredientName = ingredientName;
        this.ingredientPrice = ingredientPrice;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {"black bun", 100.0f, IngredientType.SAUCE, "hot sauce", 50.0f},
                {"white bun", 200.0f, IngredientType.FILLING, "cutlet", 75.0f},
                {"red bun", 150.5f, IngredientType.SAUCE, "chili sauce", 80.25f}
        };
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(mockBun.getName()).thenReturn(bunName);
        when(mockBun.getPrice()).thenReturn(bunPrice);

        when(mockIngredient.getType()).thenReturn(ingredientType);
        when(mockIngredient.getName()).thenReturn(ingredientName);
        when(mockIngredient.getPrice()).thenReturn(ingredientPrice);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.bun).isNotNull();
        softly.assertThat(burger.bun.getName()).isEqualTo(bunName);
        softly.assertThat(burger.bun.getPrice()).isEqualTo(bunPrice);
        softly.assertAll();
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients).hasSize(1);
        softly.assertThat(burger.ingredients.get(0).getName()).isEqualTo(ingredientName);
        softly.assertAll();
    }

    @Test
    public void testGetPriceWithOneIngredient() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        float expectedPrice = (bunPrice * 2) + ingredientPrice;
        float actualPrice = burger.getPrice();

        assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    public void testGetReceiptWithOneIngredient() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String receipt = burger.getReceipt();
        float expectedPrice = (bunPrice * 2) + ingredientPrice;

        String expectedReceipt = String.format("(==== %s ====)%n", bunName) +
                String.format("= %s %s =%n", ingredientType.toString().toLowerCase(), ingredientName) +
                String.format("(==== %s ====)%n", bunName) +
                String.format("%nPrice: %f%n", expectedPrice);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receipt).isEqualTo(expectedReceipt);
        softly.assertAll();
    }

    @Test
    public void testGetReceiptContainsRequiredElements() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String receipt = burger.getReceipt();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receipt).isNotNull();
        softly.assertThat(receipt).contains(bunName);
        softly.assertThat(receipt).contains(ingredientName);
        softly.assertThat(receipt).contains(ingredientType.toString().toLowerCase());
        softly.assertAll();
    }
}