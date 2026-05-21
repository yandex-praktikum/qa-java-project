import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest extends BaseTest {

    @Parameterized.Parameter
    public int ingredientCount;

    @Parameterized.Parameter(1)
    public float expectedPrice;

    @Parameterized.Parameters(name = "Тестовые данные: ingredientCount={0}, expectedPrice={1}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {0, 200f},
                {1, 250f},
                {2, 300f}
        });
    }

    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getPriceWithDifferentIngredientTest() {
        burger.setBuns(bunMock);
        for (int i = 0; i < ingredientCount; i++) {
            burger.addIngredient(ingredientMock);
        }
        assertEquals("Цена должна рассчитываться корректно", expectedPrice, burger.getPrice(), 0.1);
    }

    @Test
    public void getReceiptWithDifferentIngredientsTest() {
        burger.setBuns(bunMock);
        for (int i = 0; i < ingredientCount; i++) {
            burger.addIngredient(ingredientMock);
        }
        String receipt = burger.getReceipt();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receipt).contains("Булочка");
        softly.assertThat(receipt).contains(String.format("Price: %f", expectedPrice));
        if (ingredientCount > 0) {
            softly.assertThat(receipt).contains("= filling Ингредиент =");
        }
        softly.assertAll();
    }


}
