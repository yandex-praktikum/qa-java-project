import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import praktikum.Burger;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class RemoveIngredientInvalidIndexTest {

    private final int index;

    public RemoveIngredientInvalidIndexTest(int index) {
        this.index = index;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{-1},
                new Object[]{0},
                new Object[]{100}
        );
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWithInvalidIndex() {
        Burger burger = new Burger();
        burger.removeIngredient(index);
    }
}
