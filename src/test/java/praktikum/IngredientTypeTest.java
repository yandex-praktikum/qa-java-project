package praktikum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

/**
Как будто бы этот класс теста лишний, но по заданию нужно проверить
 */
@RunWith(MockitoJUnitRunner.class)
public class IngredientTypeTest {
    @Test
    public void sauceAndFillingNotNull() {
        assertNotNull(SAUCE);
        assertNotNull(FILLING);
        assertNotEquals(SAUCE, FILLING);
    }
}
