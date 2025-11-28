import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerGetPriceParameterizedTest {

    private static final float DELTA = 0.001f;


    private final String testName; // название теста (для читаемости в отчётах)
    private final float expectedPrice;
    private final Ingredient[] testIngredients;

    private Bun mockBan;
    private Burger burger;

    public BurgerGetPriceParameterizedTest(String testName, float expectedPrice, Ingredient[] testIngredients) {
        this.testName = testName;
        this.expectedPrice = expectedPrice;
        this.testIngredients = testIngredients;
    }

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"2 Булки(по 50 руб.) + соус(50 руб.)",
                        150.0f,
                        new Ingredient[]{createMockIngredient( 50.0f)
                        }
                },
                {"2 Булки(по 50 руб.) + начинка(70 руб.)+ соус(50 руб.)",
                        220.0f,
                        new Ingredient[]{
                                createMockIngredient( 70.0f),
                                createMockIngredient( 50.0f)
                        }
                },
                {"Только булки(по 50 руб.) без ингредиентов",
                        100.0f,
                        new Ingredient[]{
                        }
                },
                {"2 Булки(по 50 руб.) + две начинки (80 + 10 руб.) + соус (10 руб.)",
                        200.0f,
                        new Ingredient[]{
                                createMockIngredient( 80.0f),
                                createMockIngredient(10.0f),
                                createMockIngredient( 10.0f)
                        }
                }
        });
    }

    private static Ingredient createMockIngredient(float price) {
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        when(ingredient.getPrice()).thenReturn(price);
        return ingredient;
    }

    @Before
    public void setUp() {
        mockBan = mock(Bun.class);
        when(mockBan.getPrice()).thenReturn(50.0f);
        burger = new Burger();
        burger.setBuns(mockBan);

        for (Ingredient ingredient : testIngredients) {
            burger.addIngredient(ingredient);
        }

    }

    @Test
    public void getPriceTest() {
        float actualPrice = burger.getPrice();
        assertEquals("Test case: " + testName, expectedPrice, actualPrice, DELTA);
    }
}

