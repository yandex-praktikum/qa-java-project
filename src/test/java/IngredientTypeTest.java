import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

public class IngredientTypeTest {
    public static String type1 = "SAUCE";
    public static String type2 = "FILLING";

    @DisplayName("Проверяем что в енаме есть SAUCE")
    @Test
    public void shouldContainSauce() {
        Assertions.assertEquals(type1, IngredientType.SAUCE.toString());
    }

    @DisplayName("Проверяем что в енаме есть FILLING")
    @Test
    public void shouldContainsFilling() {
        Assertions.assertEquals(type2, IngredientType.FILLING.toString());
    }
}
