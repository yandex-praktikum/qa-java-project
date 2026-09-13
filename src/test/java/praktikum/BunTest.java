package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    public static Object[][] getData() {
        return new Object[][]{
                {"black bun", 100},
                {"white bun", 200},
                {"red bun", 300}
        };
    }

    @ParameterizedTest
    @MethodSource("getData")
    public void testCheckBunName(String name, float price){
        Bun bun = new Bun(name, price);
        assertEquals(name, bun.getName(),"Неверное название");
    }

    @ParameterizedTest
    @MethodSource("getData")
    public void testCheckBunPrice(String name, float price){
        Bun bun = new Bun(name, price);
        assertEquals(price, bun.getPrice(),"Неверное название");
    }
}

