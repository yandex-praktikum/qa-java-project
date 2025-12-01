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

    // Проверка чека с двумя ингредиентами
    @Test
    public void testGetReceiptWithMultipleIngredients() {
        SoftAssertions softly = new SoftAssertions();

        burger.setBuns(mockBun);
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);

        String receipt = burger.getReceipt();
        String[] lines = receipt.split("\n");
        // System.out.println("Receipt: " + receipt);  // Можно оставить для отладки, но в продакшене убрать

        softly.assertThat(lines[0].trim())
                .isEqualTo("(==== Test Bun ====)");

        softly.assertThat(lines[1].trim())
                .isEqualTo("= sauce Test Sauce =");

        softly.assertThat(lines[2].trim())
                .isEqualTo("= filling Test Filling =");

        softly.assertThat(lines[3].trim())
                .isEqualTo("(==== Test Bun ====)");

        softly.assertThat(lines[4])
                .isBlank();

        softly.assertThat(lines[5])
                .startsWith("Price: ");

        softly.assertAll();
    }
}
