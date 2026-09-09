package praktikum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTypeTest {

    @Test
    public void testGetSauceTypePositiveResult(){
        assertEquals(IngredientType.valueOf("SAUCE"),IngredientType.SAUCE);
    }

    @Test
    public void testGetFillingTypePositiveResult(){
        assertEquals(IngredientType.valueOf("FILLING"),IngredientType.FILLING);
    }
}
