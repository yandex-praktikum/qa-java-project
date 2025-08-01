package praktikum;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import static org.junit.Assert.*;

public class IngredientTypeTest {

    SoftAssertions softAssertions = new SoftAssertions();

    private final String SAUCE = "SAUCE";
    private final String FILLING = "FILLING";

    @Test
    public void valuesCountEnumTest() {
        IngredientType[] sizes = IngredientType.values();
        assertEquals("Должно быть 2 типа ингредиентов", 2, sizes.length);
    }

    @Test
    public void checkValuesNotNull() {
        for (IngredientType t : IngredientType.values()) {
            assertNotNull("Тип ингредиента не должен быть null", t);
        }
    }

    @Test
    public void valuesEnumTest() {
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(IngredientType.SAUCE.toString())
                .as("Проверяем значение перечисления: соус")
                .isEqualTo(SAUCE);

        softAssertions.assertThat(IngredientType.FILLING.toString())
                .as("Проверяем значение перечисления: соус")
                .isEqualTo(FILLING);

        softAssertions.assertAll();
    }
}