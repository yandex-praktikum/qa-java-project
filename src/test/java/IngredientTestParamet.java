
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Database;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)

public class IngredientTestParamet {

    private static List<Ingredient> availableIngredients;
    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTestParamet(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Проверка ингредиента №{index}: {0},{1},{2}")
    public static Collection<Object[]> getData() {
        Database databaseMock = new Database();
        availableIngredients = databaseMock.availableIngredients();

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
            Database database = new Database();
             availableIngredients = database.availableIngredients();

            boolean found = false;
            for (Ingredient ingredient : IngredientTestParamet.availableIngredients) {
                if (type == ingredient.getType() && name.equals(ingredient.getName()) && Math.abs(price - ingredient.getPrice()) <= 0.01) {
                    found = true;
                    break;
                }
            }
            assertTrue(String.format("Ингредиенты с типом '%s', названием '%s' и ценой '%,.2f' не найдены.", type, name, price), found);
        }
    }

