import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private Burger burger;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient1;
    @Mock
    private Ingredient ingredient2;

    @Parameterized.Parameter
    public float bunPrice;
    @Parameterized.Parameter(1)
    public float ingredient1Price;
    @Parameterized.Parameter(2)
    public float ingredient2Price;
    @Parameterized.Parameter(3)
    public float expectedPrice;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {100f, 50f, 75f, 325f},
                {200f, 100f, 150f, 650f},
                {50f, 25f, 25f, 150f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();

        when(bun.getPrice()).thenReturn(bunPrice);
        when(ingredient1.getPrice()).thenReturn(ingredient1Price);
        when(ingredient2.getPrice()).thenReturn(ingredient2Price);
    }

    @Test
    public void testParameterizedGetPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }
}