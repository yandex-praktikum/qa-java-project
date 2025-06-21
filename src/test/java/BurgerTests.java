import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;


//Группа тестов для класса Burger
//Тестирование класса Burger
//@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {


    private Burger burger;
    private Bun mockBun;
    private Ingredient mockMeat;
    private Ingredient mockSalad;
    private Ingredient mockSauce;

    @Before
    //Создаем Бургер перед каждым тестом
    public void prepareBurger() {
        burger = new Burger();
        mockBun = Mocks.getMockBun();
        mockMeat = Mocks.getMockMeat();
        mockSalad = Mocks.getMockSalad();
        mockSauce = Mocks.getMockSauce();
    }

    @Test
    //Тестирование метода setBuns
    //Тест проверяет установку булочки для burger с использованием метода setBuns
    public void setBunsTest() {
        burger.setBuns(mockBun);
        Assert.assertSame("Булочка не та", mockBun, burger.bun);
    }

    @Test
    //Тестирование метода addIngredient
    //Тест проверяет, добавляется ли ингредиент в список
    public void addIngredientTest() {
        burger.addIngredient(mockMeat);
        assertEquals("Ингридиент не добавлен", mockMeat, burger.ingredients.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    //Тестирование метода removeIngredient
    //Тест проверяет, удаляется ли ингредиент из списка
    public void removeIngredientTest() {
        burger.addIngredient(mockSalad);
        burger.removeIngredient(0);
        assertEquals("Ингредиент не удален", mockSalad, burger.ingredients.get(0));
    }

    @Test
    //Тестирование метода moveIngredient
    //Тест проверяет, перемещается ли ингредиент по списку
    public void moveIngredientTest() {
        burger.addIngredient(mockSalad);
        burger.addIngredient(mockMeat);
        burger.addIngredient(mockSauce);
        burger.moveIngredient(2, 0);
        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(burger.ingredients.get(0)).as("Не тот ингредиент").isEqualTo(mockSauce);
        soft.assertThat(burger.ingredients.get(1)).as("Не тот ингредиент").isEqualTo(mockSalad);
        soft.assertThat(burger.ingredients.get(2)).as("Не тот ингредиент").isEqualTo(mockMeat);
        soft.assertAll();

    }


}
