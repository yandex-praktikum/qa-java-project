package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class BunTest {

    @Mock
    private Bun bun;


    @Test
    public void getNameBunConstructor() {
        bun = new Bun("Булка1", 11.f);

        assertEquals("Булка1", bun.getName());
    }

    @Test
    public void getPriceBunConstructor() {
        bun = new Bun("Булка1", 11.f);

        assertEquals(11.f, bun.getPrice(), 0.001f);
    }
}
