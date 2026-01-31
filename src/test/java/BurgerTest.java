import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;
import java.util.List;
import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient;
    @Mock
    List<Ingredient> ingredients;

    int index;
    int newIndex;

    @Test
    public void testBurger() {
        Burger burger = new Burger();
    }

    @Test
    public void setBunsTest(){
        Burger burger = new Burger(bun, ingredients);
        burger.setBuns(bun);
        Assert.assertEquals(bun, burger.bun);
    }
    @Test
    public void addIngredientsTest(){
        Burger burger = new Burger(bun, ingredients);
        burger.addIngredient(ingredient);
        Assert.assertEquals(ingredients, burger.ingredients);
    }
    @Test
    public void removeIngredientTest(){
        Burger burger = new Burger(bun, ingredients);
        burger.removeIngredient(index);
        Mockito.verify(ingredients,Mockito.times(1)).remove(index);
    }
    @Test
    public void moveIngredientsTest(){
        Burger burger = new Burger(bun, ingredients);
        burger.moveIngredient(index, newIndex);
        Mockito.verify(ingredients,Mockito.times(1)).add(newIndex, ingredients.remove(index));
    }
    @Test
    public void getPriceBurgerTest(){
        Database database = new Database();
        List<Bun> buns = database.availableBuns();
        List<Ingredient> ingredients = database.availableIngredients();
        Burger burger = new Burger(bun, ingredients);
        Mockito.when(bun.getPrice()).thenReturn(buns.get(0).getPrice());
        assertEquals(1400, burger.getPrice(),0);
    }
}
