import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class IngredientTypeTest {
    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient sauceMock;

    @Mock
    private Ingredient fillingMock;

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
        burger.setBuns(bunMock);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(sauceMock);
        assertEquals(1, burger.ingredients.size());
        assertSame(sauceMock, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(sauceMock);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(sauceMock);
        burger.addIngredient(fillingMock);

        // Перемещаем соус с позиции 0 на позицию 1
        burger.moveIngredient(0, 1);

        // Проверяем, что позиции теперь новые
        assertSame(fillingMock, burger.ingredients.get(0));
        assertSame(sauceMock, burger.ingredients.get(1));
    }
}
