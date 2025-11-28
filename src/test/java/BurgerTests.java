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
    public void setUp(){
        burger=new Burger();
    }

@Test //проверяю что метод устанавливает булочку
    public void ShouldSetBunTest(){
        burger.setBuns(mockBun);
        assertEquals(mockBun,burger.bun);
}

@Test  //проверяю что метод добавляет ингридиент
    public void ShouldAddIngredientTest(){
        burger.addIngredient(mockIngredient1);
    List<Ingredient> ingredients=burger.ingredients;
    assertEquals(1,ingredients.size());
    assertEquals(mockIngredient1,ingredients.get(0));
}







}
