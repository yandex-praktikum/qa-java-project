package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }

    @Test
    public void bunsShouldContainCorrectBuns() {
        List<Bun> buns = database.availableBuns();

        assertEquals("Должно быть 2 булки", 2, buns.size());

        assertEquals("Флюоресцентная булка R2-D3", buns.get(0).getName());
        assertEquals(988, buns.get(0).getPrice(), 0.001);

        assertEquals("Краторная булка N-200i", buns.get(1).getName());
        assertEquals(1255, buns.get(1).getPrice(), 0.001);

    }

    @Test
    public void ingredientsShouldContainCorrectIngredients() {
        List<Ingredient> ingredients = database.availableIngredients();

        assertEquals("Должно быть 6 ингредиентов", 6, ingredients.size());

        // Проверяем соусы
        Ingredient sauce1 = ingredients.get(0);
        assertEquals(IngredientType.SAUCE, sauce1.getType());
        assertEquals("Соус фирменный Space Sauce", sauce1.getName());
        assertEquals(80, sauce1.getPrice(), 0.001);

        Ingredient sauce2 = ingredients.get(1);
        assertEquals(IngredientType.SAUCE, sauce2.getType());
        assertEquals("Соус Spicy-X", sauce2.getName());
        assertEquals(90, sauce2.getPrice(), 0.001);

        Ingredient sauce3 = ingredients.get(2);
        assertEquals(IngredientType.SAUCE, sauce3.getType());
        assertEquals("Соус традиционный галактический", sauce3.getName());
        assertEquals(15, sauce3.getPrice(), 0.001);

        Ingredient filling1 = ingredients.get(3);
        assertEquals(IngredientType.FILLING, filling1.getType());
        assertEquals("Мясо бессмертных моллюсков Protostomia", filling1.getName());
        assertEquals(1337, filling1.getPrice(), 0.001);

        Ingredient filling2 = ingredients.get(4);
        assertEquals(IngredientType.FILLING, filling2.getType());
        assertEquals("Плоды Фалленианского дерева", filling2.getName());
        assertEquals(874, filling2.getPrice(), 0.001);

        Ingredient filling3 = ingredients.get(5);
        assertEquals(IngredientType.FILLING, filling3.getType());
        assertEquals("Хрустящие минеральные кольца", filling3.getName());
        assertEquals(300, filling3.getPrice(), 0.001);
    }
}

