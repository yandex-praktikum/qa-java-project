import org.junit.jupiter.api.Test;
import praktikum.IngredientType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;

public class IngredientTypeTest {

    @Test
    public void souseExistTest() {
        assertThat(IngredientType.valueOf("SAUCE"), not(nullValue()));
    }

    @Test
    public void fillingExistTest() {
        assertThat(IngredientType.valueOf("FILLING"), not(nullValue()));
    }
}
