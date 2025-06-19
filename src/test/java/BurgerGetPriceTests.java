import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class BurgerGetPriceTests {

    @Mock
    private Bun bun = mock(Bun.class);
    @Mock
    private Ingredient ingredient1 = mock(Ingredient.class);
    @Mock
    private Ingredient ingredient2 = mock(Ingredient.class);

    private Burger burger;

    @BeforeEach
    public void setUp() {
        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
    }

    @ParameterizedTest(name = "{index}. Test data: Bun price:{0}, Ingredient prices:{1} and {2}, Expected burger price: {3}")
    @CsvSource({"5, 1.5, 2, 13.5",
                "1, 0, 0, 2",
                "0, 1, 0, 1",
                "0, 0, 1, 1"})
    public void getPricePositiveTest(float bunPrice, float ingredient1Price, float ingredient2Price, float expectedBurgerPrice) {
        Mockito.when(bun.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredient1.getPrice()).thenReturn(ingredient1Price);
        Mockito.when(ingredient2.getPrice()).thenReturn(ingredient2Price);

        float actualBurgerPrice = burger.getPrice();

        assertEquals(expectedBurgerPrice, actualBurgerPrice,
                String.format("Burger price calculation was expected to result in '%f', but actually was '%f'", expectedBurgerPrice, actualBurgerPrice));
    }
}
