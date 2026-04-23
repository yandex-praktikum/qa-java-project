package praktikum;

import org.junit.Test;
import praktikum.Bun;
import static org.junit.Assert.assertEquals;

public class BunTest {
    @Test
    /**
     * Тест проверяет, что метод getName() возвращает ожидаемое имя булочки
     */
    public void testBunGetName(){
        Bun bun = new Bun("Saratov", 164.0F);
        assertEquals("Saratov", bun.getName());
    }
    @Test
    /**
     * Тест проверяет, что метод getPrice() возвращает ожидаемую цену булочки
     */
    public void testBunGetPrice(){
        Bun bun = new Bun("Saratov", 164.0F);
        assertEquals(164.0F, bun.getPrice(), 0);
    }
}
