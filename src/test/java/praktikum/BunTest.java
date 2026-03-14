package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunTest {

    private final String name;
    private final float price;

    public BunTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"black bun", 100f},
                {"white bun", 200f},
                {"red bun", 300f},
                {"", 0f},
                {"very long bun name with special characters !@#$%", 999.99f}
        };
    }

    @Test
    public void testBunConstructorAndGetters() {
        Bun bun = new Bun(name, price);

        assertEquals("Bun name should match constructor parameter", name, bun.getName());
        assertEquals("Bun price should match constructor parameter", price, bun.getPrice(), 0.001);
    }

    @Test
    public void testBunFieldsAreAccessible() {
        Bun bun = new Bun(name, price);

        assertEquals("Bun name field should be accessible", name, bun.name);
        assertEquals("Bun price field should be accessible", price, bun.price, 0.001);
    }

    @Test
    public void testBunWithMock() {
        Bun mockBun = Mockito.mock(Bun.class);

        Mockito.when(mockBun.getName()).thenReturn("mock bun");
        Mockito.when(mockBun.getPrice()).thenReturn(150f);

        assertEquals("Mock bun name should be 'mock bun'", "mock bun", mockBun.getName());
        assertEquals("Mock bun price should be 150", 150f, mockBun.getPrice(), 0.001);

        Mockito.verify(mockBun).getName();
        Mockito.verify(mockBun).getPrice();
    }
}