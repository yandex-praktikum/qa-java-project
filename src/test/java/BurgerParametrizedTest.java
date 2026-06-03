import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import java.util.Arrays;
import java.util.Collection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Enclosed.class)
public class BurgerParametrizedTest {

    @RunWith(Parameterized.class)
    public static class BurgerPriceParameterizedTest {

        private final float bunPrice;
        private final float[] ingredientPrices;
        private final float expectedTotalPrice;


        public BurgerPriceParameterizedTest(float bunPrice, float[] ingredientPrices, float expectedTotalPrice) {
            this.bunPrice = bunPrice;
            this.ingredientPrices = ingredientPrices;
            this.expectedTotalPrice = expectedTotalPrice;
        }

        @Parameterized.Parameters(name = "{index}: булка={0}, ингредиенты={1}, ожидается={2}")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    { 100.0f, new float[]{}, 200.0f },                               // Только булка (100 * 2)
                    { 100.0f, new float[]{50.0f}, 250.0f },                          // Булка + 1 ингредиент
                    { 200.0f, new float[]{100.0f, 200.0f, 300.0f}, 1000.0f },        // Булка + 3 ингредиента
                    { 0.0f, new float[]{100.0f, 100.0f}, 200.0f }                    // Бесплатная булка
            });
        }

        @Test
        public void getPriceTest() {
            Burger burger = new Burger();

            Bun mockBun = mock(Bun.class);
            when(mockBun.getPrice()).thenReturn(bunPrice);
            burger.setBuns(mockBun);

            for (float price : ingredientPrices) {
                Ingredient mockIng = mock(Ingredient.class);
                when(mockIng.getPrice()).thenReturn(price);
                burger.addIngredient(mockIng);
            }

            assertThat(burger.getPrice())
                    .isCloseTo(expectedTotalPrice, within(0.001f));
        }
    }
}
