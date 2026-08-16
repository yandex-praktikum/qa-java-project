package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class BunParamTest {
    private final String name;
    private final String price;
    public BunParamTest(String name, String price) {
        this.name = name;
        this.price = price;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> dataForTest() {
        return Arrays.asList(new Object[][]{
                {"чиз", "15"},
                {"гамб", "12"}
        });
    }
}
