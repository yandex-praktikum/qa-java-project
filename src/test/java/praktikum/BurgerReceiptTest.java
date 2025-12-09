package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.when;

public class BurgerReceiptTest extends BaseTest {

    @Before
    public void setUp() {
        super.setUp();
        when(mockSauce.getType()).thenReturn(IngredientType.SAUCE);
        when(mockFilling.getType()).thenReturn(IngredientType.FILLING);
    }
    @Test
    public void testGetReceiptWithMultipleIngredients() {
        SoftAssertions softly = new SoftAssertions();

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        String receipt = burger.getReceipt();
        String[] lines = receipt.split("\\R");

        softly.assertThat(lines[0].trim())
                .isEqualTo("(==== Test Bun ====)");

        softly.assertThat(lines[1].trim())
                .isEqualTo("= sauce Test Sauce =");
        softly.assertThat(lines[2].trim())
                .isEqualTo("= filling Test Filling =");

        softly.assertThat(lines[3].trim())
                .isEqualTo("(==== Test Bun ====)");

        softly.assertThat(lines[4].trim()).isEmpty();

        softly.assertThat(lines[5].trim()).startsWith("Price: ");

        softly.assertAll();
    }
}
