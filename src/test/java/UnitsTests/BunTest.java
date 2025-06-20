package UnitsTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BunTest {

    @Mock
    private Bun bun;

    @Test
    public void bunGetNameTest() {
        Mockito.when(bun.getName()).thenReturn("Sesame");
        String expected = "Sesame";
        String actual = bun.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void bunGetPriceTest() {
        Mockito.when(bun.getPrice()).thenReturn(50.0f);
        float expected = 50.0f;
        float actual = bun.getPrice();
        assertEquals(expected, actual, 0.001);
    }
}