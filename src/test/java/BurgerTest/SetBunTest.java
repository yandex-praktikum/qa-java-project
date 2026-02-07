package BurgerTest;

import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;

import static org.junit.Assert.assertEquals;

public class SetBunTest {
    private Bun bun1;
    private Bun bun2;
    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
        bun1 = new Bun("cosmoBun", 3);
        bun2 = new Bun("moonBun", 3);
    }

    @Test
    public void settingBunTest() {
        burger.setBuns(bun1);

        assertEquals(bun1, burger.bun);
    }

    @Test
    public void setBunsShouldReplacePreviousBuns() {
        burger.setBuns(bun1);
        burger.setBuns(bun2);
        assertEquals(bun2, burger.bun);

    }
}
