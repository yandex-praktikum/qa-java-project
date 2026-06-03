
import org.junit.Before;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.mockito.Mockito.when;

public class BaseBurgerTest {

    protected Burger burger;
    protected Bun bunStub;
    protected Ingredient ingredientStub;


    @Before
    public void setUp() {
        burger = new Burger();

        // Мок булочки
        bunStub = Mockito.mock(Bun.class);
        when(bunStub.getPrice()).thenReturn(100f);
        when(bunStub.getName()).thenReturn("Test Bun");
        burger.setBuns(bunStub);

        // Мок ингредиента (для тестов, где он нужен)
        ingredientStub = Mockito.mock(Ingredient.class);
        when(ingredientStub.getPrice()).thenReturn(50f);
        when(ingredientStub.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredientStub.getName()).thenReturn("Hot Sauce");
    }
}
