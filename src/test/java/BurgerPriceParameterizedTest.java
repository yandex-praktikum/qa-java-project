import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.List;



@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {

    float bunPrice;
    List<Float> ingredientPrices;
    float expectedPrice;


    public BurgerPriceParameterizedTest(float bunPrice, List<Float> ingredientPrices, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }


    @Parameterized.Parameters(name = "")
    public static Object[][] getCredentials() {
        return new Object[][]{
                {100.0f, List.of(100.0f), 300.0f}, //первая булка + первый соус
                {200.0f, List.of(), 400.0f}, //вторая булка без соуса и начинки
                {300.0f, List.of(100.0f), 700.0f}, // третья булка + первая начинка

                {100.0f, Arrays.asList(300.0f, 300.0f), 800.0f}, // булка + 2 одинаковых соуса
                {100.0f, Arrays.asList(200.0f, 200.0f), 600.0f}, // булка + 2 одинаковых начинки
                {100.0f, List.of(200.0f, 300.0f), 700.0f}, // булка + соус + начинка
        };
    }

    @Test
    public void testGetPrice(){
        Burger burger = new Burger();

        Bun bunMock = Mockito.mock(Bun.class);
        Mockito.when(bunMock.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bunMock);

        for (float price : ingredientPrices) {
            Ingredient ingredientMock = Mockito.mock(Ingredient.class);
            Mockito.when(ingredientMock.getPrice()).thenReturn(price);
            burger.addIngredient(ingredientMock);
        }

        Assert.assertEquals(expectedPrice, burger.getPrice() , 0.01f);
    }
}


