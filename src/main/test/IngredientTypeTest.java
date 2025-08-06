import org.junit.jupiter.api.Test;
import praktikum.IngredientType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {

    @Test
    public void testIngredientTypeTest(){
        IngredientType[] values = IngredientType.values();
        assertEquals(2, values.length);
    }

}
