package praktikum;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class BaseBurgerTest {

    protected Burger burger;

    @Mock
    protected Bun mockBun;

    @Mock
    protected Ingredient mockIngredientSauce;

    @Mock
    protected Ingredient mockIngredientFilling;

    @Before
    public void setUp() {
        burger = new Burger();
    }
}
