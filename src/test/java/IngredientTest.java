import org.junit.Before;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

public class IngredientTest {
    private Ingredient ingredient;
    // Константы
    private final IngredientType type = IngredientType.SAUCE;
    private final String name = "Соус";
    private final float price = 1234.0F;

    @Before
    public void beforeEach() {
        this.ingredient = new Ingredient(
                type,
                name,
                price
        );
    }

    @Test
    public void getPriceTest() {
        // Проверяем, что цена ингредиента совпадает с заданной изначально
        assertEquals(price, ingredient.getPrice(),0.0F);
    }

    @Test
    public void getNameTest() {
        // Проверяем, что наименование ингредиента совпадает с заданным изначально
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void getTypeTest() {
        // Проверяем, что тип ингредиента совпадает с заданным изначально
        assertEquals(type, ingredient.getType());
    }
}
