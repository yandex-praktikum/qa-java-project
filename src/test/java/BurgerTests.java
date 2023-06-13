import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

//Группа тестов для класса Burger
//Тестирование класса Burger
@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    @Mock
    Ingredient ingredientForAddition;
    @Mock
    Ingredient ingredientForAdditionAndDeletion;
    @Mock
    Ingredient ingredientZeroToTwo;
    @Mock
    Ingredient ingredientOneToZero;
    @Mock
    Ingredient ingredientTwoToOne;
    private Burger burger;
    @Mock
    private Bun bun;

    @Before
    //Создаем Бургер перед каждым тестом
    public void prepareBurger() {
        burger = new Burger();
    }

    @Test
    //Тестирование метода setBuns
    //Тест проверяет установку булочки для burger с использованием метода setBuns
    public void setBunsTest() {
        burger.setBuns(bun);
        Assert.assertSame("Булочка не та", bun, burger.bun);
    }

    @Test
    //Тестирование метода addIngredient
    //Тест проверяет, добавляется ли ингредиент в список
    public void addIngredientTest() {
        Assert.assertEquals(0, burger.ingredients.size());
        Assert.assertFalse(burger.ingredients.contains(ingredientForAddition));
        burger.addIngredient(ingredientForAddition);
        Assert.assertEquals(1, burger.ingredients.size());
        Assert.assertTrue(burger.ingredients.contains(ingredientForAddition));
    }

    @Test
    //Тестирование метода removeIngredient
    //Тест проверяет, удаляется ли ингредиент из список
    public void removeIngredientTest() {
        Assert.assertEquals(0, burger.ingredients.size());
        Assert.assertFalse(burger.ingredients.contains(ingredientForAddition));
        Assert.assertFalse(burger.ingredients.contains(ingredientForAdditionAndDeletion));
        burger.addIngredient(ingredientForAddition);
        burger.addIngredient(ingredientForAdditionAndDeletion);
        Assert.assertEquals(2, burger.ingredients.size());
        Assert.assertTrue(burger.ingredients.contains(ingredientForAddition));
        Assert.assertTrue(burger.ingredients.contains(ingredientForAdditionAndDeletion));
        burger.removeIngredient(burger.ingredients.indexOf(ingredientForAdditionAndDeletion));
        Assert.assertEquals(1, burger.ingredients.size());
        Assert.assertFalse(burger.ingredients.contains(ingredientForAdditionAndDeletion));
        Assert.assertTrue(burger.ingredients.contains(ingredientForAddition));
    }

    @Test
    //Тестирование метода moveIngredient
    //Тест проверяет, перемещается ли ингредиент по списку
    public void moveIngredientTest() {
        Assert.assertEquals(0, burger.ingredients.size());
        Assert.assertFalse(burger.ingredients.contains(ingredientZeroToTwo));
        Assert.assertFalse(burger.ingredients.contains(ingredientOneToZero));
        Assert.assertFalse(burger.ingredients.contains(ingredientTwoToOne));
        burger.addIngredient(ingredientZeroToTwo);
        burger.addIngredient(ingredientOneToZero);
        burger.addIngredient(ingredientTwoToOne);
        Assert.assertEquals(3, burger.ingredients.size());
        burger.moveIngredient(burger.ingredients.indexOf(ingredientZeroToTwo)
                , burger.ingredients.indexOf(ingredientTwoToOne));
        Assert.assertEquals(ingredientZeroToTwo, burger.ingredients.get(2));
        Assert.assertEquals(ingredientOneToZero, burger.ingredients.get(0));
        Assert.assertEquals(ingredientTwoToOne, burger.ingredients.get(1));
    }


}
