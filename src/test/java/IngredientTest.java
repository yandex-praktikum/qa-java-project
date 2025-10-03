import org.junit.jupiter.api.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {
    @Test
    public void shouldGetName(){
        Ingredient ingredient = new Ingredient(IngredientType.FILLING,"1000 островов",200F);
        assertEquals("1000 островов",ingredient.getName(),"Неправильно возвращает имя");
    }

    @Test
    public void shouldGetPrice(){
        Ingredient ingredient = new Ingredient(IngredientType.FILLING,"1000 островов",200F);
        assertEquals(200F,ingredient.getPrice(),"Неправильно возвращает стоимость");
    }
    @Test
    public void shouldGetType(){
        Ingredient ingredient = new Ingredient(IngredientType.FILLING,"1000 островов",200F);
        assertEquals(IngredientType.FILLING, ingredient.getType(),"Неправильно возвращает тип");
    }
}
