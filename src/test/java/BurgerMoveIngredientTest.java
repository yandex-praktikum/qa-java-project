import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerMoveIngredientTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    private final int fromIndex;
    private final int toIndex;
    private final String testName;

    public BurgerMoveIngredientTest(String testName, int fromIndex, int toIndex) {
        this.testName = testName;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"Перемещение ингредиента с позиции 1 на позицию 0", 1, 0},
                {"Перемещение ингредиента с позиции 0 на позицию 1", 0, 1},
                {"Перемещение ингредиента с позиции 0 на позицию 2", 0, 2},
                {"Перемещение ингредиента с позиции 2 на позицию 0", 2, 0},
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();

        // Настройка моков для булочки
        when(mockBun.getName()).thenReturn("Test Bun");

        // Настройка моков для ингредиентов
        when(mockIngredient1.getName()).thenReturn("Ingredient 1");
        when(mockIngredient2.getName()).thenReturn("Ingredient 2");
        when(mockIngredient3.getName()).thenReturn("Ingredient 3");

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
    }

    @Test
    public void testMoveIngredientChangesPosition() {
        // Сохраняем ссылку на ингредиент до перемещения
        Ingredient ingredientBeforeMove = burger.ingredients.get(fromIndex);

        // Выполняем перемещение
        burger.moveIngredient(fromIndex, toIndex);

        // Проверяем, что ингредиент находится на новой позиции
        assertEquals("Ингредиент должен быть перемещен на новую позицию",
                ingredientBeforeMove, burger.ingredients.get(toIndex));
    }
}