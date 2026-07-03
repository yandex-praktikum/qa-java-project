package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {

    @Test
    public void shouldContainSauceValue() {
        assertEquals("SAUCE",
                IngredientType.SAUCE.name());
    }

    @Test
    public void shouldContainFillingValue() {
        assertEquals("FILLING",
                IngredientType.FILLING.name());
    }
}