package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BurgerTest {

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testIngredientsListShouldBeEmptyInitially() {
        assertTrue("Список ингредиентов должен быть пустым при создании бургера",
                burger.ingredients.isEmpty());
    }

    @Test
    public void testBunShouldBeNullInitially() {
        assertNull("Булочка должна быть null при создании бургера",
                burger.bun);
    }
}