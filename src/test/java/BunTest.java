import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {


    @Test
    public void shouldGetName(){
        Bun  bun = new Bun("булка", 21.1F);
        assertEquals("булка",bun.getName(),"Неправильно возвращает имя");
    }

    @Test
    public void shouldGetPrice(){
        Bun bun = new Bun("bulka", 21.1F);
        assertEquals(21.1F, bun.getPrice(),"Неправильно возвращает цену");
    }
}
