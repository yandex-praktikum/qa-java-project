package Test;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import praktikum.Bun;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BunTest {
    Bun bun;

    String name = "testName";
    float price = 3.5f;

    @BeforeEach
    public  void createBun(){
        bun = new Bun(name, price);
    }

    @Test
    public void getNameTest(){
        assertEquals(name, bun.getName());
    }

    @Test
    public void getPriceTest(){
        assertEquals(price, bun.getPrice());
    }
}
