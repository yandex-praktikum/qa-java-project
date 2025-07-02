import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import praktikum.Bun;

public class BunTest {

    Bun bun = new Bun("Серая", 500);

    @Test
    @DisplayName("Название булок")
    public void bunsNameTest() {
        Assertions.assertEquals("Серая", bun.getName());
    }

    @Test
    @DisplayName("Стоимость булок")
    public void bunsPriceTest() {
        Assertions.assertEquals(500, bun.getPrice());
    }

}
