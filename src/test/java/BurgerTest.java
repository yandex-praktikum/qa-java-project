import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient cheese;

    @Mock
    private Ingredient cutlet;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
        burger.setBuns(bun);
    }

    @Test
    public void testSetBuns() {
        Bun newBun = mock(Bun.class);
        burger.setBuns(newBun);
        assertEquals(newBun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(cheese);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.size())
            .as("Проверка размера списка ингредиентов")
            .isEqualTo(1);
        softly.assertThat(burger.ingredients.get(0))
            .as("Проверка добавленного ингредиента")
            .isEqualTo(cheese);
        softly.assertAll();
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(cheese);
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(cheese);
        burger.addIngredient(cutlet);
        burger.moveIngredient(0, 1);
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(burger.ingredients.get(0))
            .as("Ингредиент на позиции 0 должен быть cutlet")
            .isEqualTo(cutlet);

        softly.assertThat(burger.ingredients.get(1))
            .as("Ингредиент на позиции 1 должен быть cheese")
            .isEqualTo(cheese);

        softly.assertAll();
    }
}