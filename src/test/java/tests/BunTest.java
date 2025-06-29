package tests;

import org.junit.jupiter.api.Test;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    private Bun bun = new Bun("White bun", 50.00f);

    @Test
    public void getNameShouldReturnName() {
        assertEquals("White bun", bun.getName(),"Название не совпадает!");
    }

    @Test
    public void getPriceShouldReturnPrice() {
        assertEquals(50.00f, bun.getPrice(), 0.001f,"Цена не совпадает!");
    }
}
