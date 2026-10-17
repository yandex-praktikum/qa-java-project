package burger;

import org.junit.Before;
import org.mockito.Mock;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

public abstract class BurgerBaseTest {

    protected Burger burger;

    @Mock
    protected Bun mockBun;
    @Mock
    protected Ingredient mockSauce;
    @Mock
    protected Ingredient mockFilling;

    @Before
    public void setUp() {
        burger = new Burger();
        burger.setBuns(mockBun);
    }
}