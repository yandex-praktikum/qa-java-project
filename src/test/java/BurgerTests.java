import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    @Mock //Создаю заглушку для объекта булочка
    private Bun mockBun;

    @Mock //Создаю заглушку для объекта ингридиент1
    private Ingredient mockIngredient1;

    @Mock //Создаю заглушку для объекта ингридиент2
    private Ingredient mockIngredient2;

    private Burger burger;

    @Before //Создаю новый экземпляр бургера пред каждым тестом
    public void setUp() {
        burger = new Burger();
    }

    @Test //проверяю что метод setBuns() устанавливает булочку
    public void ShouldSetBunTest() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test  //проверяю что метод addIngredient()добавляет ингридиент
    public void ShouldAddIngredientTest() {
        burger.addIngredient(mockIngredient1);
        List<Ingredient> ingredients = burger.ingredients;
        assertEquals(1, ingredients.size());
        assertEquals(mockIngredient1, ingredients.get(0));
    }

    @Test // проверяю что метод removeIngredient()удаляет ингридиент по индексу
    public void ShouldRemoveIngredientByIndexTest() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.removeIngredient(0);
        List<Ingredient> ingredients = burger.ingredients;
        assertEquals(1, ingredients.size());
        assertEquals(mockIngredient2, ingredients.get(0));
    }

    @Test // проверяю что метод moveIngredient() перемещает ингридиент по индексу
    public void ShouldMoveToNewIndexTest() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.moveIngredient(0, 1);
        List<Ingredient> ingredients = burger.ingredients;
        assertEquals(mockIngredient2, ingredients.get(0));
        assertEquals(mockIngredient1, ingredients.get(1));
    }


}
