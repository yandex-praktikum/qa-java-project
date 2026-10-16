package praktikum;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

public class BaseTest {

    protected Burger burger;

    @Mock
    protected Bun bun;

    @Mock
    protected Ingredient cutlet;

    @Mock
    protected Ingredient cheese;

    @Mock
    protected Ingredient mayonnaise;

    @Before
    public void createMockAndClass() {
        MockitoAnnotations.openMocks(this);

        when(bun.getName()).thenReturn("Чёрная булка");
        when(bun.getPrice()).thenReturn(100f);

        when(cutlet.getName()).thenReturn("Котлета");
        when(cutlet.getType()).thenReturn(IngredientType.FILLING);
        when(cutlet.getPrice()).thenReturn(50f);

        when(cheese.getName()).thenReturn("Сыр");
        when(cheese.getType()).thenReturn(IngredientType.FILLING);
        when(cheese.getPrice()).thenReturn(50f);

        when(mayonnaise.getName()).thenReturn("Майонез");
        when(mayonnaise.getType()).thenReturn(IngredientType.SAUCE);
        when(mayonnaise.getPrice()).thenReturn(50f);

        burger = new Burger();
    }
}