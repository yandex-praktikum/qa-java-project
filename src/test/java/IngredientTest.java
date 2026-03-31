import praktikum.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.SAUCE;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

 class IngredientTest {
    static Ingredient ingredient;
    @BeforeAll
    static void setUpAll() {
        ingredient = new  Ingredient(SAUCE, "NachinkaSauce", 10);
    }
    @Test
    void getPriceReturnPrice(){
        float actual = ingredient.getPrice();
        assertEquals(10, actual);
    }
    @Test
    void getNameReturnName(){
        String actual = ingredient.getName();
        assertEquals("NachinkaSauce", actual);
    }
    @Test
    void getTypeReturnsType(){
        IngredientType actual = ingredient.getType();
        assertEquals(SAUCE, actual);
    }
    }
