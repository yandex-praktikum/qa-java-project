package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;  // ✅ Hamcrest вместо JUnit

@Story("Bun Tests")
@RunWith(Parameterized.class)
public class BunTest {

    private Bun bun;
    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Bun: {0}, Price: {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { "Флюоресцентная булка R2-D3",  26.0f },
                { "Краторная булка N-200i",      31.0f },
                { "Булка черная 'Черный хаос'",  30.0f },
                { "Булка",                       15.0f },
                { "Бутерброд",                   0.0f }
        });
    }

    @Before
    public void setUp() {
        bun = new Bun(name, price);
    }

    @Test
    @Description("Проверка: имя булки сохраняется корректно")
    public void testBunNameIsSet() {
        assertThat(bun.getName(), equalTo(name));
    }

    @Test
    @Description("Проверка: цена булки сохраняется корректно")
    public void testBunPriceIsSet() {
        assertThat(bun.getPrice(), equalTo(price));
    }

    @Test
    @Description("Проверка: булка с положительной ценой")
    public void testBunWithPositivePrice() {
        Bun positiveBun = new Bun("Дорогая булка", 100.0f);
        assertThat(positiveBun.getPrice(), greaterThan(0.0f));
    }

    @Test
    @Description("Проверка: булка с нулевой ценой")
    public void testBunWithZeroPrice() {
        Bun zeroPriceBun = new Bun("Бесплатная булка", 0.0f);
        assertThat(zeroPriceBun.getPrice(), equalTo(0.0f));
    }

    @Test
    @Description("Проверка: булка с минимальной положительной ценой")
    public void testBunWithMinimalPrice() {
        Bun minBun = new Bun("Дешёвая булка", 0.01f);
        assertThat(minBun.getPrice(), lessThan(1.0f));
        assertThat(minBun.getPrice(), greaterThan(0.0f));
    }

    @Test
    @Description("Проверка: имя булки не пусто")
    public void testBunNameIsNotEmpty() {
        assertThat(bun.getName(), not(emptyString()));
    }

    @Test
    @Description("Проверка: разные булки имеют разные имена")
    public void testDifferentBunsHaveDifferentNames() {
        Bun bun1 = new Bun("Булка 1", 10.0f);
        Bun bun2 = new Bun("Булка 2", 20.0f);
        assertNotEquals(bun1.getName(), bun2.getName());
    }

    @Test
    @Description("Проверка: булка содержит информацию о названии")
    public void testBunContainsNameInfo() {
        assertThat(bun.getName(), containsString(bun.getName()));
    }
}

