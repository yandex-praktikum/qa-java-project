package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void testAvailableBunsReturnsFullAndCorrectList() {
        List<Bun> buns = database.availableBuns();

        // Проверяем точное количество булочек в БД
        Assert.assertEquals("В базе данных должно быть ровно 3 вида булочек", 3, buns.size());

        // Проверяем правильность и порядок всех булочек
        Assert.assertEquals("black bun", buns.get(0).getName());
        Assert.assertEquals(100.0f, buns.get(0).getPrice(), 0.0f);

        Assert.assertEquals("white bun", buns.get(1).getName());
        Assert.assertEquals(200.0f, buns.get(1).getPrice(), 0.0f);

        Assert.assertEquals("red bun", buns.get(2).getName());
        Assert.assertEquals(300.0f, buns.get(2).getPrice(), 0.0f);
    }

    @Test
    public void testAvailableIngredientsReturnsFullAndCorrectList() {
        List<Ingredient> ingredients = database.availableIngredients();

        // Проверяем точное количество ингредиентов в БД
        Assert.assertEquals("В базе данных должно быть ровно 6 ингредиентов", 6, ingredients.size());

        // Проверяем соусы (индексы 0, 1, 2)
        Assert.assertEquals(IngredientType.SAUCE, ingredients.get(0).getType());
        Assert.assertEquals("hot sauce", ingredients.get(0).getName());
        Assert.assertEquals(100.0f, ingredients.get(0).getPrice(), 0.0f);

        Assert.assertEquals(IngredientType.SAUCE, ingredients.get(1).getType());
        Assert.assertEquals("sour cream", ingredients.get(1).getName());

        Assert.assertEquals(IngredientType.SAUCE, ingredients.get(2).getType());
        Assert.assertEquals("chili sauce", ingredients.get(2).getName());

        // Проверяем начинки (индексы 3, 4, 5)
        Assert.assertEquals(IngredientType.FILLING, ingredients.get(3).getType());
        Assert.assertEquals("cutlet", ingredients.get(3).getName());
        Assert.assertEquals(100.0f, ingredients.get(3).getPrice(), 0.0f);

        Assert.assertEquals(IngredientType.FILLING, ingredients.get(4).getType());
        Assert.assertEquals("dinosaur", ingredients.get(4).getName());

        Assert.assertEquals(IngredientType.FILLING, ingredients.get(5).getType());
        Assert.assertEquals("sausage", ingredients.get(5).getName());
    }
}

