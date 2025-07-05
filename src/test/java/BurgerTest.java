import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    @Mock
    private Ingredient mockIngredient2;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    /**
     * Проверка метода setBuns: проверяет, что булочка устанавливается корректно.
     */
    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals("Булочка не установлена правильно", mockBun, burger.bun);
    }

    /**
     * Проверка метода addIngredient: проверяет, что ингредиент добавляется корректно.
     */
    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient);
        assertTrue("Ингредиент не добавлен", burger.ingredients.contains(mockIngredient));
    }

    /**
     * Проверка метода removeIngredient: проверяет, что ингредиент удаляется корректно.
     */
    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        assertFalse("Ингредиент не удален", burger.ingredients.contains(mockIngredient));
    }

    /**
     * Проверка метода moveIngredient: проверяет, что ингредиенты перемещаются корректно.
     */
    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockIngredient2);
        burger.moveIngredient(0, 1);

        List<Ingredient> expectedIngredients = Arrays.asList(mockIngredient2, mockIngredient);
        assertEquals("Ингредиенты не перемещены правильно",
                expectedIngredients,
                burger.ingredients);
    }

    /**
     * Проверка метода getPrice: проверяет, что цена бургера рассчитывается корректно.
     */
    @Test
    public void testGetPrice() {
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);
        Mockito.when(mockIngredient.getPrice()).thenReturn(50.0f);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        assertEquals("Цена бургера рассчитана неправильно", 250.0f, burger.getPrice(), 0.0f);
    }

    /**
     * Проверка метода getReceipt: проверяет, что квитанция о бургере возвращается корректно.
     */
    @Test
    public void testGetReceipt() {
        Mockito.when(mockBun.getName()).thenReturn("black bun");
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);
        Mockito.when(mockIngredient.getName()).thenReturn("hot sauce");
        Mockito.when(mockIngredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockIngredient.getPrice()).thenReturn(50.0f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String expectedReceipt = String.format("(==== %s ====)%n= sauce %s =%n(==== %s ====)%n%nPrice: %f%n",
                "black bun", "hot sauce", "black bun", 250.0f);

        assertEquals("Квитанция о бургере не соответствует ожидаемой", expectedReceipt, burger.getReceipt());
    }
}
