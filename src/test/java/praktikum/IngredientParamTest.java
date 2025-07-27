package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientParamTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientParamTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {IngredientType.SAUCE, "Соус Spicy-X", 90f},
                {IngredientType.SAUCE, "Соус фирменный Space Sauce", 80f},
                {IngredientType.SAUCE, "Соус традиционный галактический", 70f},
                {IngredientType.FILLING, "Мясо бессмертных моллюсков Protostomia", 300f},
                {IngredientType.FILLING, "Плоды Фалленианского дерева", 250f},
                {IngredientType.FILLING, "Хрустящие минеральные кольца", 200f}
        });
    }

    @Test
    public void ingredientShouldReturnCorrectName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void ingredientShouldReturnCorrectPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void ingredientShouldReturnCorrectType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }
}