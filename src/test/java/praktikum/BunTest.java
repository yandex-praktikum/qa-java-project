package praktikum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @Test
    public void  getNameShouldReturnName(){
        Bun bun = new Bun("BigMac", 50.50f);
        String actual = bun.getName();
        assertEquals(
                "BigMac",
                actual,
                "Имя булочки не соответствует ожидаемому");
    }

    @Test
    public void  getPriceShouldReturnPrice(){
        Bun bun = new Bun("BigMac", 60.50f);
        float actual = bun.getPrice();
        assertEquals(
                60.50f,
                actual,
                "Цена булочки не соответствует ожидаемому");
    }
}
