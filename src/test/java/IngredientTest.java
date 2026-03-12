import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IngredientTest {

    private final Database database = new Database();
    private List<Ingredient> availableIngredients;


    @BeforeEach
    void setup() {
        availableIngredients = database.availableIngredients();
    }

    @ParameterizedTest(name = "Проверка ингредиента №{index}: {0},{1},{2}")
    @CsvSource({
            "SAUCE,hot sauce,100",
            "SAUCE,sour cream,200",
            "SAUCE,chili sauce,300",
            "FILLING,cutlet,100",
            "FILLING,dinosaur,200",
            "FILLING,sausage,300"
    })
    void ingredientByParametersTest(IngredientType type, String name, float price) {
        boolean found = false;
        for (Ingredient ingredient : availableIngredients) {
            if (type == ingredient.getType() && name.equals(ingredient.getName()) && Math.abs(price - ingredient.getPrice()) <= 0.01) {
                found = true;
                break;
            }
        }
        assertTrue(found, String.format("Ингредиенты с типом '%s', названием '%s' и ценой %.2f не найдены.", type, name, price));
    }

    @Test
    public void getPriceTest() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "ketchup", 150f);
        float currentPrice = ingredient.getPrice();
        assertEquals(150f, currentPrice, 0.01);

    }

    @Test
    public void getNameTest() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "meatball", 75f);
        String currentName = ingredient.getName();
        assertEquals("meatball", currentName);
    }

    @Test
    public void getIngredientTypeTest() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "mustard", 50f);
        IngredientType currentType = ingredient.getType();
        assertEquals(IngredientType.SAUCE, currentType);
    }
}



