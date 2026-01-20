package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final IngredientType type;
    private final String name;
    private final float price;

    public IngredientTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{index}: Ingredient {1}, type={0}, price={2}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                {IngredientType.SAUCE, "Соус Spicy-X", 90},
                {IngredientType.SAUCE, "Соус фирменный Space Sauce", 80},
                {IngredientType.SAUCE, "Соус традиционный галактический", 15},
                {IngredientType.SAUCE, "Соус с шипами Антарианского плоскоходца", 88},

                {IngredientType.FILLING, "Мясо бессмертных моллюсков Protostomia", 1337},
                {IngredientType.FILLING, "Говяжий метеорит (отбивная)", 3000},
                {IngredientType.FILLING, "Биокотлета из марсианской Магнолии", 424},
                {IngredientType.FILLING, "Филе Люмисцентного тетраодонтимформ", 988},
                {IngredientType.FILLING, "Хрустящие минеральные кольца", 300},
                {IngredientType.FILLING, "Плоды Фалленианского дерева", 874},
                {IngredientType.FILLING, "Кристаллы марсианских альфа-сахаридов", 762},
                {IngredientType.FILLING, "Мини-салат Экзо-Плантаго", 4400},
                {IngredientType.FILLING, "Сыр с астероидной плесенью", 4142}
        });
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0);
    }
}
