package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

@RunWith(Parameterized.class)
public class BurgerPriceTest {

    private final Bun bunMock;
    private final Ingredient[] ingredientMock;
    private final Float expectedPrice;

    public BurgerPriceTest(Bun bunMock, Ingredient[] ingredientMock, Float expectedPrice) {
        this.bunMock = bunMock;
        this.ingredientMock = ingredientMock;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Тестовые данные: {0} {1} {2}")
    public static Object[][] getTestData() {
        Bun bunMock1 =mock(Bun.class);
        Mockito.when(bunMock1.getPrice()).thenReturn(100f);
        Mockito.when(bunMock1.getName()).thenReturn("black bun");

        Bun bunMock2 =mock(Bun.class);
        Mockito.when(bunMock2.getPrice()).thenReturn(200f);
        Mockito.when(bunMock2.getName()).thenReturn("white bun");

        Bun bunMock3 =mock(Bun.class);
        Mockito.when(bunMock3.getPrice()).thenReturn(300f);
        Mockito.when(bunMock3.getName()).thenReturn("red bun");

        Ingredient ingredientMock1 =mock(Ingredient.class);
        Mockito.when(ingredientMock1.getPrice()).thenReturn(100f);
        Mockito.when(ingredientMock1.getName()).thenReturn("cutlet");

        Ingredient ingredientMock2 =mock(Ingredient.class);
        Mockito.when(ingredientMock2.getPrice()).thenReturn(200f);
        Mockito.when(ingredientMock2.getName()).thenReturn("dinosaur");

        Ingredient ingredientMock3 =mock(Ingredient.class);
        Mockito.when(ingredientMock3.getPrice()).thenReturn(300f);
        Mockito.when(ingredientMock3.getName()).thenReturn("sausage");

        return new Object[][]{
                {bunMock1, new Ingredient[]{}, 100f*2},
                {bunMock2, new Ingredient[]{ingredientMock1}, 200f*2+100f},
                {bunMock3, new Ingredient[]{ingredientMock1, ingredientMock2}, 300f*2+100f+200f},
                {bunMock1, new Ingredient[]{ingredientMock1, ingredientMock2, ingredientMock3}, 100f*2+100f+200f+300f},

        };
    }

    @Test
    public void getPriceTest(){
        Burger burger = new Burger();
        burger.setBuns(bunMock);
        for (Ingredient ingredient : ingredientMock){
            burger.addIngredient(ingredient);
        }
        Assert.assertEquals(expectedPrice, burger.getPrice(),0.0001f);
    }


}

