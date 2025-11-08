package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;


@RunWith(MockitoJUnitRunner.class)
public class IngredientTest {

    private IngredientType ingredientType;

    @Mock
    private Ingredient ingredient1;
    @Mock
    private Ingredient ingredient2;



    @Before
    public void setUp() {
        ingredient1 = new Ingredient(SAUCE, "mazik", 0.25F);
        ingredient2 = new Ingredient(FILLING, "mazik", 0.25F);
    }

    @Test
    public void testGetPrice() {
        assertEquals(0.25F, ingredient1.getPrice(), 0.01);
    }

    @Test
    public void testTestGetName() {
        assertEquals("mazik", ingredient1.getName());
    }

    @Test
    public void testGetType() {
        assertEquals(FILLING, ingredient2.getType());
    }
}