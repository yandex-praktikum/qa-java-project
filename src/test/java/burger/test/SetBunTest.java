package burger.test;

import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;

import static org.junit.Assert.assertEquals;

public class SetBunTest {
    private Bun firstBun;
    private Bun newBun;
    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
        firstBun = new Bun("cosmoBun", 3);
        newBun = new Bun("moonBun", 3);
    }

    @Test
    public void settingBunTest() {
        burger.setBuns(firstBun);

        assertEquals(firstBun, burger.bun);
    }

    @Test
    public void setBunsShouldReplacePreviousBunsTest() {
        burger.setBuns(firstBun);
        burger.setBuns(newBun);
        assertEquals(newBun, burger.bun);

    }
}

