package praktikum.burgerTests;

import org.junit.Before;
import org.mockito.Mock;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

public abstract class BurgerBaseTest {

    protected Burger burger;

    @Mock
    protected Bun bun;
    @Mock
    protected Ingredient sauce;
    @Mock
    protected Ingredient filling;

    @Before
    public void setUp() {

        burger = new Burger();
        burger.setBuns(bun);

    }
}