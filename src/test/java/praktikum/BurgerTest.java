package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bunMock;

    @Mock
    private Ingredient ingredient1Mock;

    @Mock
    private Ingredient ingredient2Mock;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

}
