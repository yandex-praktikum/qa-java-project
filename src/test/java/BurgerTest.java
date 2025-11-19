import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class BurgerTest {

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }
    @Test
    public void testSetBuns() {
        // Arrange
        Bun bun = mock(Bun.class);

        // Act
        burger.setBuns(bun);

        // Assert
        assertEquals("Булочка должна быть установлена", bun, burger.bun);
    }

    @Test
    public void testAddIngredientIncreasesSize() {
        // Arrange
        Ingredient ingredient = mock(Ingredient.class);

        // Act
        burger.addIngredient(ingredient);

        // Assert
        assertEquals("Размер должен увеличиться на 1", 1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredientDecreasesSize() {
        // Arrange
        Ingredient ingredient = mock(Ingredient.class);

        burger.addIngredient(ingredient);
        int initialSize = burger.ingredients.size();

        // Act
        burger.removeIngredient(0);

        // Assert - ОДНА проверка
        assertEquals("Размер должен уменьшиться на 1", initialSize - 1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientChangesPosition() {
        // Arrange
        Ingredient sauce = mock(Ingredient.class);
        Ingredient cutlet = mock(Ingredient.class);
        Ingredient cheese = mock(Ingredient.class);

        burger.addIngredient(sauce);
        burger.addIngredient(cutlet);
        burger.addIngredient(cheese);

        // Исходный порядок: [sauce, cutlet, cheese]
        // Act - перемещаем sauce (индекс 0) на позицию 2
        burger.moveIngredient(0, 2);

        // Assert - одна проверка
        // Новый порядок должен быть: [cutlet, cheese, sauce]
        assertEquals("Элемент должен переместиться в указанную позицию",
                sauce, burger.ingredients.get(2));
    }

    @Test
    public void testMoveIngredientShiftsOtherElements() {
        // Arrange
        Ingredient sauce = mock(Ingredient.class);
        Ingredient cutlet = mock(Ingredient.class);
        Ingredient cheese = mock(Ingredient.class);

        burger.addIngredient(sauce);
        burger.addIngredient(cutlet);
        burger.addIngredient(cheese);

        // Act - перемещаем первый элемент
        burger.moveIngredient(0, 2);

        // Assert - одна проверка, что остальные элементы сдвинулись
        assertEquals("Остальные элементы должны сдвинуться корректно",
                cutlet, burger.ingredients.get(0));
    }

}
