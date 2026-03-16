import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTests {

    private Ingredient ingredient;
    private IngredientType expectedType;
    private String expectedName;
    private float expectedPrice;

    // Конструктор для приёма параметров
    public IngredientTests(IngredientType type, String name, float price) {
        this.ingredient = new Ingredient(type, name, price);
        this.expectedType = type;
        this.expectedName = name;
        this.expectedPrice = price;
    }

    // Таблица данных для параметризации (создание ингредиентов с параметрами)
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "hot sauce", 100f},
                {IngredientType.SAUCE, "sour cream", 200f},
                {IngredientType.SAUCE, "chili sauce", 300f},
                {IngredientType.FILLING, "cutlet", 100f},
                {IngredientType.FILLING, "dinosaur", 200f},
                {IngredientType.FILLING, "sausage", 300f}
        });
    }

    // Тест 1: Проверка цены ингредиента
    @Test
    public void testGetPrice() {
        assertEquals(expectedPrice, ingredient.getPrice(), 0.01f);
    }

    // Тест 2: Проверка названия ингредиента
    @Test
    public void testGetName() {
        assertEquals(expectedName, ingredient.getName());
    }

    // Тест 3: Проверка типа ингредиента
    @Test
    public void testGetType() {
        assertEquals(expectedType, ingredient.getType());
    }

}