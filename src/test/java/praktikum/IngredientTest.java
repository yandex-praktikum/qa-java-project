package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest extends BaseTest {

    private IngredientType type;
    private String name;
    private float price;
    private Ingredient ingredient;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{0}, название: {1}, цена: {2} монет")
    public static Object[][] getData() {
        return new Object[][]{
                {IngredientType.SAUCE, "Spicy-X", 90.0f},
                {IngredientType.SAUCE, "Space Sauce", 80.0f},
                {IngredientType.SAUCE, "Традиционный галактический", 15.0f},
                {IngredientType.SAUCE, "с шипами Антарианского плоскоходца", 88.0f},
                {IngredientType.FILLING, "Мясо бессмертных моллюсков Protostomia", 1337.0f},
                {IngredientType.FILLING, "Говяжий метеорит (отбивная)", 3000.0f},
                {IngredientType.FILLING, "Биокотлета из марсианской Магнолии", 424.0f},
                {IngredientType.FILLING, "Сыр с астероидной плесенью", 4142.0f},
        };
    }

    @Before
    public void initIngredient() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void getPriceTest() {
        assertEquals("Цена ингредиента не совпадает", price, ingredient.getPrice(), 0.5f);
    }

    @Test
    public void getNameTest() {
        assertEquals("Имя ингредиента не совпадает", name, ingredient.getName());
    }

    @Test
    public void getTypeTest() {
        assertEquals("Тип ингредиента не совпадает", type, ingredient.getType());
    }
}
