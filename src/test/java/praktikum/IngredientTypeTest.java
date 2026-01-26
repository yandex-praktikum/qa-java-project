package praktikum;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;  // ✅ Hamcrest вместо JUnit

@Story("IngredientType Tests")
public class IngredientTypeTest {

    @Test
    @Description("Проверка: существует тип FILLING")
    public void testFillingTypeExists() {
        assertThat(IngredientType.FILLING, notNullValue());
    }

    @Test
    @Description("Проверка: существует тип SAUCE")
    public void testSauceTypeExists() {
        assertThat(IngredientType.SAUCE, notNullValue());
    }

    @Test
    @Description("Проверка: типы ингредиентов различны")
    public void testIngredientTypesAreDifferent() {
        assertNotEquals(IngredientType.FILLING, IngredientType.SAUCE);
    }

    @Test
    @Description("Проверка: FILLING тип определяется корректно")
    public void testFillingTypeComparison() {
        IngredientType type = IngredientType.FILLING;
        assertThat(type, equalTo(IngredientType.FILLING));
        assertThat(type, not(equalTo(IngredientType.SAUCE)));
    }

    @Test
    @Description("Проверка: SAUCE тип определяется корректно")
    public void testSauceTypeComparison() {
        IngredientType type = IngredientType.SAUCE;
        assertThat(type, equalTo(IngredientType.SAUCE));
        assertThat(type, not(equalTo(IngredientType.FILLING)));
    }

    @Test
    @Description("Проверка: можно получить все типы ингредиентов")
    public void testAllTypesAvailable() {
        IngredientType[] types = IngredientType.values();
        assertThat(types.length, greaterThanOrEqualTo(2));
    }
}

