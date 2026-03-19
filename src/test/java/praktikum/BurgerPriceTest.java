package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerPriceTest {


    @Mock
    private Bun bun;

   private Burger burger;


    public float bunPrice;
    public List<Ingredient> ingredients;
    public float expectedPrice;

    public BurgerPriceTest(float bunPrice, List<Ingredient> ingredients, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.ingredients =ingredients;
        this.expectedPrice=expectedPrice;

}

    @Parameterized.Parameters (name = "Булочка:{0} | Цена:{2}")
    public static Collection<Object[]> burgerTestData() {
        return Arrays.asList(new Object[][] {
                {100f, List.of(), 200f},
                {200f, List.of(new Ingredient(IngredientType.FILLING, "cutlet", 100f)), 500f},
                {300f, List.of(new Ingredient(IngredientType.SAUCE, "hot sauce", 100f)), 700f},
                {100f, List.of(new Ingredient(IngredientType.FILLING, "dinosaur", 200f),
                                new Ingredient(IngredientType.SAUCE, "sour cream", 200f)),600f},
                {200f, List.of(new Ingredient(IngredientType.FILLING, "cutlet", 100f),
                        new Ingredient(IngredientType.FILLING, "sausage", 300f)),800f}
        });
    }

    @Before
    public void setUp() {

        MockitoAnnotations.openMocks(this);
        burger = new Burger();

        when(bun.getPrice()).thenReturn(bunPrice);

        burger.setBuns(bun);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }
    }

    @Test
    public void testGetPrice() {//тест получения цены
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.001f);
    }
}
