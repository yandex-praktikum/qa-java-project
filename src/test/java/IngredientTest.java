import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
@RunWith(Parameterized.class)

public class IngredientTest {

    private final Database database = new Database();
    private static List<Ingredient> availableIngredients;

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @BeforeClass
    public static void setup() {
        Database database = new Database();
        availableIngredients = database.availableIngredients();
    }

    @Parameterized.Parameters(name = "Проверка ингредиента №{index}: {0},{1},{2}")
    public static Collection<Object[]> getData() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100f},
                {IngredientType.SAUCE, "sour cream", 200f},
                {IngredientType.SAUCE, "chili sauce", 300f},
                {IngredientType.FILLING, "cutlet", 100f},
                {IngredientType.FILLING, "dinosaur", 200f},
                {IngredientType.FILLING, "sausage", 300f}
        });
    }

    @Test
    public void ingredientByParametersTest() {
        boolean found = false;
        for (Ingredient ingredient : availableIngredients) {
            if (type == ingredient.getType() && name.equals(ingredient.getName()) && Math.abs(price - ingredient.getPrice()) <= 0.01) {
                found = true;
                break;
            }
        }
        assertTrue(String.format("Ингредиенты с типом '%s', названием '%s' и ценой %.2f не найдены.", type, name, price), found);
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




