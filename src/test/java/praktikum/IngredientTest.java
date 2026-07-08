package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class IngredientTest {

    @Mock
    private Ingredient ingredient;

    @Test
    public void getIngredientConstructorSauce() {
        ingredient = new Ingredient(SAUCE, "Сырный", 11.f);

        assertEquals(SAUCE, ingredient.getType());
        assertEquals("Сырный", ingredient.getName());
        assertEquals(11.f, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void getIngredientConstructorFilling(){
        ingredient = new Ingredient(FILLING,"Салат", 1.f);

        assertEquals(FILLING, ingredient.getType());
        assertEquals("Салат", ingredient.getName());
        assertEquals(1.f, ingredient.getPrice(), 0.001f);
        }
}
