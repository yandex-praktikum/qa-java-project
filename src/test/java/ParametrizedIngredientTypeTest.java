import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;


    @RunWith(Parameterized.class)
    public class ParametrizedIngredientTypeTest {



        private final IngredientType type;
        private final String expectedName;

        public ParametrizedIngredientTypeTest(IngredientType type, String expectedName) {
            this.type = type;
            this.expectedName = expectedName;
        }

        @Parameterized.Parameters(name = "Тип ингредиента: {1}")  // Более понятное описание

        public static Object[][] getIngredientTypeData() {

            return new Object[][] {
                    {IngredientType.SAUCE, "SAUCE"},
                    {IngredientType.FILLING, "FILLING"}
            };
        }

        @Test

        public void checkIngredientTypeName() {

            assertEquals("Ингредиент назван не правильно", expectedName, type.name());
        }
    }
