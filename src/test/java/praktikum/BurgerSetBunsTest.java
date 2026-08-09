package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerSetBunsTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Bun mockFirstBun;

    @Mock
    private Bun mockSecondBun;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(mockBun);
        assertEquals("Булочка должна быть установлена", mockBun, burger.bun);
    }

    @Test
    public void setBunsShouldSetBunName() {
        String expectedName = "black bun";
        when(mockBun.getName()).thenReturn(expectedName);
        burger.setBuns(mockBun);
        assertEquals("Имя булочки должно совпадать", expectedName, burger.bun.getName());
    }

    @Test
    public void setBunsShouldSetBunPrice() {
        float expectedPrice = 100f;
        when(mockBun.getPrice()).thenReturn(expectedPrice);
        burger.setBuns(mockBun);
        assertEquals("Цена булочки должна совпадать", expectedPrice, burger.bun.getPrice(), 0.001f);
    }

    @Test
    public void setBunsShouldOverwritePreviousBun() {
        burger.setBuns(mockFirstBun);
        burger.setBuns(mockSecondBun);
        assertEquals("Должна быть добавлена вторая булочка", mockSecondBun, burger.bun);
    }

    @Test
    public void setBunsShouldAcceptNullBun() {
        burger.setBuns(null);
        assertNull("Булочка должна быть null", burger.bun);
    }
}

