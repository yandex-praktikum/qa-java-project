package praktikum;

import java.util.Arrays;
import java.util.Collections;

public class BurgerTestData {
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, Collections.<Float>emptyList(), 200f},
                {100f, Arrays.asList(50f), 250f},
                {100f, Arrays.asList(50f, 25f), 275f},
                {199.99f, Arrays.asList(0.01f, 1000f), 199.99f * 2 + 0.01f + 1000f},
        });
    }
}
