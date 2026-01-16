import org.junit.Test;
import praktikum.IngredientType;

import static org.junit.Assert.assertNotNull;

public class IngredientTypeTest {
    @Test
    public void SAUCETest(){
        assertNotNull("Упс. Что-то пошло не так.", IngredientType.SAUCE);
    }

    @Test
    public void FILLINGTest() {
        assertNotNull("Упс. Что-то пошло не так.", IngredientType.FILLING);
    }
}
