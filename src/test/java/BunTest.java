import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;


    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    // Тестовые данные
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"Инопланетная булочка", 1000.0f},
                {"Межгалактическая булочка", 350.5f},
                {"", 0.0f},  // Булочка без названия
                {"Заплесневелая $%#! булочка", -1.0f},  // Отрицательная цена
                {null, 100.0f}  // Булочка с пустым значением
        });
    }

    // Тест цены и имени
    @Test
    public void testConstructorAndGetters() {
        Bun bun = new Bun(name, price);

        assertEquals("Название булочки должно совпадать", name, bun.getName());
        assertEquals("Цена булочки должна совпадать", price, bun.getPrice(), 0.001f);
    }
}