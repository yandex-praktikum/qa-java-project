package praktikum;

import org.junit.Test;
import org.assertj.core.api.SoftAssertions;
import static org.junit.Assert.*;

public class BurgerBasicTest extends BaseTest {

    // Проверка, что булочка правильно устанавливается в бургер
    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }
    // Добавление ингредиента: размер списка увеличивается
    @Test
    public void testAddIngredientIncreasesSize() {
        burger.addIngredient(mockSauce);
        assertEquals(1, burger.ingredients.size());
    }
    // Добавление ингредиента: ингредиент добавляется правильно
    @Test
    public void testAddIngredientAddsCorrect() {
        burger.addIngredient(mockSauce);
        assertEquals(mockSauce, burger.ingredients.get(0));
    }
    // Удаление ингредиента: после добавления и удаления список пустой
    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockSauce);
        burger.removeIngredient(0);
        assertTrue(burger.ingredients.isEmpty());
    }

    // Перемещение ингредиента: первый ингредиент переходит на вторую позицию
    @Test
    public void testMoveIngredient() {
        SoftAssertions softly = new SoftAssertions();
        burger.addIngredient(mockSauce);
        burger.addIngredient(mockFilling);
        burger.moveIngredient(0, 1);
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(mockFilling);
        softly.assertThat(burger.ingredients.get(1)).isEqualTo(mockSauce);
        softly.assertAll();
    }
}