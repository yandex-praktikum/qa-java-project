import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


//Тестирование метода getPrice класса Burger
@RunWith(Parameterized.class)
public class BurgerGetPriceTest {
    private final Bun bun;
    private final Ingredient[] ingredients;
    private final float expectedPrice;
    private Burger burger;

    public BurgerGetPriceTest(Bun bun, Ingredient[] ingredients, float expectedPrice) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    //Создание массива параметров
    @Parameterized.Parameters(name = "Булка:{0}, Состав ингредиентов:{1}, ожидаемая цена:{2}")
    public static Collection<Object[]> burgerContents() {
        Bun bun = mock(Bun.class);
        when(bun.getPrice()).thenReturn(100F);
        Ingredient meat = mock(Ingredient.class);
        when(meat.getPrice()).thenReturn(200F);
        Ingredient sauce = mock(Ingredient.class);
        when(sauce.getPrice()).thenReturn(30F);
        Ingredient salad = mock(Ingredient.class);
        when(salad.getPrice()).thenReturn(70F);
        return Arrays.asList(new Object[][]{{bun, new Ingredient[]{meat, sauce, salad}, 500F},//полный бургер
                {bun, new Ingredient[]{}, 200F},//только булки
                {bun, new Ingredient[]{meat}, 400F},// булки с мясом
        });
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
