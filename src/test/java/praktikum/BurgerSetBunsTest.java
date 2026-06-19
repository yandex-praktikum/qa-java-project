package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerSetBunsTest {

    @Test
    public void shouldSetBun() {
        Burger burger = new Burger();
        Bun bun = mock(Bun.class);

        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }
}
