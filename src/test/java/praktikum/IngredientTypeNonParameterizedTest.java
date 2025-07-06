package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTypeNonParameterizedTest {
    
    @Test
    public void valuesShouldReturnAllTypes() {
        IngredientType[] types = IngredientType.values();
        
        assertEquals(2, types.length);
        assertEquals(IngredientType.SAUCE, types[0]);
        assertEquals(IngredientType.FILLING, types[1]);
    }

    @Test
    public void enumShouldContainSauceType() {
        IngredientType[] types = IngredientType.values();
        boolean containsSauce = false;
        
        for (IngredientType type : types) {
            if (type == IngredientType.SAUCE) {
                containsSauce = true;
                break;
            }
        }
        
        assertTrue(containsSauce);
    }

    @Test
    public void enumShouldContainFillingType() {
        IngredientType[] types = IngredientType.values();
        boolean containsFilling = false;
        
        for (IngredientType type : types) {
            if (type == IngredientType.FILLING) {
                containsFilling = true;
                break;
            }
        }
        
        assertTrue(containsFilling);
    }
}