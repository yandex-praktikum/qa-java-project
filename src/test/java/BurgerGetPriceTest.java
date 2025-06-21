import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.List;


//Тестирование метода getPrice класса Burger
@RunWith(Parameterized.class)
public class BurgerGetPriceTest {
    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final float expectedPrice;
    private Burger burger;

    public BurgerGetPriceTest(Bun bun, List<Ingredient> ingredients, float expectedPrice) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    //Создание массива параметров
    @Parameterized.Parameters(name = "Булка:{0}, Состав ингредиентов:{1}, ожидаемая цена:{2}")
    public static Object[][] burgerContents() {
        return new Object[][]{
                {Mocks.getMockBun(), List.of(Mocks.getMockMeat(),
                        Mocks.getMockSalad(), Mocks.getMockSauce()), 500F},//полный бургер
                {Mocks.getMockBun(), List.of(), 200F},//только булки
                {Mocks.getMockBun(), List.of(Mocks.getMockMeat()), 400F},// булки с мясом
        };
    }

    @Before
    //Создаем Бургер перед каждым тестом
    public void prepareBurger() {
        burger = new Burger();

    }

    @Test
    //Тестирование метода getPrice
    //Тест проверяет правильность подсчета цены бургера
    public void getPriceTest() {
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        Assert.assertEquals(expectedPrice, burger.getPrice(), 0.01F);
    }

    @Test(expected = NullPointerException.class)
    //Тестирование метода getPrice
    //Тест проверяет выброс NullPointerException
    public void shouldReturnPriceWithoutBun() {
        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
        burger.getPrice();
    }

}
