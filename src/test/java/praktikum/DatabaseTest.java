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

    // ТЕСТЫ ДЛЯ БУЛОЧЕК

    @Test
    public void testAvailableBunsReturnsCorrectListSize() {
        List<Bun> buns = database.availableBuns();
        Assert.assertEquals("В базе данных должно быть ровно 3 вида булочек", 3, buns.size());
    }

    @Test
    public void testBlackBunHasCorrectName() {
        List<Bun> buns = database.availableBuns();
        Assert.assertEquals("Имя булочки некорректно", "black bun", buns.get(0).getName());
    }

    @Test
    public void testBlackBunHasCorrectPrice() {
        List<Bun> buns = database.availableBuns();
        Assert.assertEquals("Цена булочки некорректна", 100.0f, buns.get(0).getPrice(), 0.0f);
    }

    @Test
    public void testWhiteBunHasCorrectName() {
        List<Bun> buns = database.availableBuns();
        Assert.assertEquals("Имя булочки некорректно", "white bun", buns.get(1).getName());
    }

    @Test
    public void testWhiteBunHasCorrectPrice() {
        List<Bun> buns = database.availableBuns();
        Assert.assertEquals("Цена булочки некорректна", 200.0f, buns.get(1).getPrice(), 0.0f);
    }

    @Test
    public void testRedBunHasCorrectName() {
        List<Bun> buns = database.availableBuns();
        Assert.assertEquals("Имя булочки некорректно", "red bun", buns.get(2).getName());
    }

    @Test
    public void testRedBunHasCorrectPrice() {
        List<Bun> buns = database.availableBuns();
        Assert.assertEquals("Цена булочки некорректна", 300.0f, buns.get(2).getPrice(), 0.0f);
    }

    // ТЕСТЫ ДЛЯ ИНГРЕДИЕНТОВ

    @Test
    public void testAvailableIngredientsReturnsCorrectListSize() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("В базе данных должно быть ровно 6 ингредиентов", 6, ingredients.size());
    }

    @Test
    public void testHotSauceHasCorrectType() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Тип ингредиента некорректен", IngredientType.SAUCE, ingredients.get(0).getType());
    }

    @Test
    public void testHotSauceHasCorrectName() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Имя ингредиента некорректно", "hot sauce", ingredients.get(0).getName());
    }

    @Test
    public void testHotSauceHasCorrectPrice() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Цена ингредиента некорректна", 100.0f, ingredients.get(0).getPrice(), 0.0f);
    }

    @Test
    public void testSourCreamHasCorrectType() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Тип ингредиента некорректен", IngredientType.SAUCE, ingredients.get(1).getType());
    }

    @Test
    public void testSourCreamHasCorrectName() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Имя ингредиента некорректно", "sour cream", ingredients.get(1).getName());
    }

    @Test
    public void testChiliSauceHasCorrectType() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Тип ингредиента некорректен", IngredientType.SAUCE, ingredients.get(2).getType());
    }

    @Test
    public void testChiliSauceHasCorrectName() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Имя ингредиента некорректно", "chili sauce", ingredients.get(2).getName());
    }

    @Test
    public void testCutletHasCorrectType() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Тип ингредиента некорректен", IngredientType.FILLING, ingredients.get(3).getType());
    }

    @Test
    public void testCutletHasCorrectName() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Имя ингредиента некорректно", "cutlet", ingredients.get(3).getName());
    }

    @Test
    public void testCutletHasCorrectPrice() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Цена ингредиента некорректна", 100.0f, ingredients.get(3).getPrice(), 0.0f);
    }

    @Test
    public void testDinosaurHasCorrectType() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Тип ингредиента некорректен", IngredientType.FILLING, ingredients.get(4).getType());
    }

    @Test
    public void testDinosaurHasCorrectName() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Имя ингредиента некорректно", "dinosaur", ingredients.get(4).getName());
    }

    @Test
    public void testSausageHasCorrectType() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Тип ингредиента некорректен", IngredientType.FILLING, ingredients.get(5).getType());
    }

    @Test
    public void testSausageHasCorrectName() {
        List<Ingredient> ingredients = database.availableIngredients();
        Assert.assertEquals("Имя ингредиента некорректно", "sausage", ingredients.get(5).getName());
    }
}

