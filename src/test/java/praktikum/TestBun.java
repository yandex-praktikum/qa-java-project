package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestBun {

    private final String name;
    private final float price;

    public TestBun(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Bun(\"{0}\", {1})")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"\uD83C\uDF11", 50},
                {"Кремниевая булка", 50f},
                {"Булка@Андромеда", 100},
                {"Белка&Стрелка", 3},
                {"Ø<Æ:ÅÅÅ>;....#iii", 888},
                {"Сверхмассивная булка из туманности Ориона созвездия Кассеопея", 44},
                {"Лунная", 105.5f},
                {"Лунная", Float.MAX_VALUE},
                {"Лунная", Float.MIN_VALUE},
                {"Лунная", 0},
                {"Лунная", -9},
        });
    }

    @Test
    public void testBunName() {
        Bun bun = new Bun(name,price);
        assertEquals(name, bun.getName());
    }

    @Test
    public void testBunPrice() {
        Bun bun = new Bun(name,price);
        assertEquals(price, bun.getPrice(), 0.00001);
    }
}
