import org.junit.jupiter.api.Test;
import praktikum.Bun;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class BunTest {

    // Unit-тесты на методы класса Bun.
    @Test
    public void testBun(){
        Bun bun = new Bun("Очень вкусный бургер", 100.00F);
        assertEquals("Очень вкусный бургер", bun.getName());
        assertEquals(100.00F, bun.getPrice());
    }

}
