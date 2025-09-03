package BurgerTests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BurgerSetBunTest extends BurgerBaseTest{
    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }
}
