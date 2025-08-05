import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @Test
    @DisplayName("Проверка получения наименования")
    public void getNameBunTest(){
        Bun bun = new Bun("black bun", 100);
        assertEquals("black bun", bun.getName(), "Наименование булки отличается");

    }
    @Test
    @DisplayName("Проверка получения цены")
    public void getPriceBunTest(){
        Bun bun = new Bun("black bun", 100);
        assertEquals(100, bun.getPrice(), "Цена отличается");
    }

}
