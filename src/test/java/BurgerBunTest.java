import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;


import static org.junit.Assert.assertEquals;



@RunWith(MockitoJUnitRunner.class)
public class BurgerBunTest {

    private Burger burger;

    @Mock
    private Bun bun;


    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBurgerBun() {
        Bun expectedBun = bun;
        burger.setBuns(expectedBun);
        Bun actualBun = burger.bun;
        assertEquals("Булочка должна быть выбрана", expectedBun, actualBun);
    }


}
