package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.assertj.core.api.SoftAssertions;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockSauce;

    @Mock
    private Ingredient mockFilling;

    private final String bunName;
    private final float bunPrice;
    private final IngredientType sauceType;
    private final String sauceName;
    private final float saucePrice;
    private final IngredientType fillingType;
    private final String fillingName;
    private final float fillingPrice;
    private final float expectedTotalPrice;

    public BurgerParameterizedTest(
            String bunName,
            float bunPrice,
            IngredientType sauceType,
            String sauceName,
            float saucePrice,
            IngredientType fillingType,
            String fillingName,
            float fillingPrice,
            float expectedTotalPrice) {

        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.sauceType = sauceType;
        this.sauceName = sauceName;
        this.saucePrice = saucePrice;
        this.fillingType = fillingType;
        this.fillingName = fillingName;
        this.fillingPrice = fillingPrice;
        this.expectedTotalPrice = expectedTotalPrice;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(mockBun.getName()).thenReturn(bunName);
        when(mockBun.getPrice()).thenReturn(bunPrice);
        when(mockSauce.getType()).thenReturn(sauceType);
        when(mockSauce.getName()).thenReturn(sauceName);
        when(mockSauce.getPrice()).thenReturn(saucePrice);
        when(mockFilling.getType()).thenReturn(fillingType);
        when(mockFilling.getName()).thenReturn(fillingName);
        when(mockFilling.getPrice()).thenReturn(fillingPrice);
    }

    @Parameterized.Parameters(name = "Тест {index}: {0} + {2} {3} + {5} {6} = {8}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"black bun", 100.0f,
                        IngredientType.SAUCE, "hot sauce", 100.0f,
                        IngredientType.FILLING, "cutlet", 100.0f,
                        400.0f},

                {"white bun", 200.0f,
                        IngredientType.SAUCE, "sour cream", 200.0f,
                        IngredientType.FILLING, "dinosaur", 200.0f,
                        800.0f},

                {"red bun", 300.0f,
                        IngredientType.SAUCE, "chili sauce", 300.0f,
                        IngredientType.FILLING, "sausage", 300.0f,
                        1200.0f},

                {"special bun", 50.0f,
                        IngredientType.SAUCE, "free sauce", 0.0f,
                        IngredientType.FILLING, "free filling", 0.0f,
                        100.0f}
        });
    }

    @Test
    public void getPriceParameterizedTest() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(burger.getPrice())
                .as("Общая цена должна быть корректно рассчитана")
                .isEqualTo(expectedTotalPrice);
        softAssertions.assertThat(burger.getPrice())
                .as("Цена должна быть неотрицательной")
                .isGreaterThanOrEqualTo(0.0f);
        softAssertions.assertAll();
    }

    @Test
    public void getReceiptContentParameterizedTest() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        String receipt = burger.getReceipt();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать название булочки: " + bunName)
                .contains(bunName);
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать тип соуса в нижнем регистре")
                .contains(sauceType.toString().toLowerCase());
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать название соуса")
                .contains(sauceName);
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать тип начинки в нижнем регистре")
                .contains(fillingType.toString().toLowerCase());
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать название начинки")
                .contains(fillingName);
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать общую цену")
                .contains("Price:");
        softAssertions.assertAll();
    }

    @Test
    public void receiptFormatAndStructureParameterizedTest() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        String receipt = burger.getReceipt();

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(receipt)
                .as("Первая строка должна содержать булочку в скобках")
                .contains("(==== " + bunName + " ====)");
        softAssertions.assertThat(receipt)
                .as("Чек должен содержать булочку в конце")
                .contains("(==== " + bunName + " ====)");
        softAssertions.assertThat(receipt)
                .as("Последняя строка должна содержать слово Price")
                .contains("Price:");
        softAssertions.assertAll();
    }
}