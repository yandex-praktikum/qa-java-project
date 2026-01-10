import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.testng.asserts.SoftAssert;
import praktikum.Ingredient;
import praktikum.IngredientType;

public class IngredientTest {
    SoftAssert softAssert = new SoftAssert();
    @ParameterizedTest
    @CsvSource({
            "FILLING, cutlet, 100.0",
            "SAUCE, hot sauce, 100.0",
            "FILLING, dinosaur, 200.0",
            "SAUCE, chili sauce, 300.0"
    })
    void testIngredient(String typeStr, String name, float price) {

        IngredientType type = IngredientType.valueOf(typeStr);

        Ingredient ingredient = new Ingredient(type, name, price);

        softAssert.assertEquals(type, ingredient.getType());
        softAssert.assertEquals(name, ingredient.getName());
        softAssert.assertEquals(price, ingredient.getPrice());
        softAssert.assertAll();
    }
}
