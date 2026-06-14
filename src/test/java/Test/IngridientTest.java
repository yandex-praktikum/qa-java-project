package Test;

import org.junit.jupiter.api.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static praktikum.IngredientType.SAUCE;

public class IngridientTest {

    public IngredientType type;
    public String name = "TestName";
    public float price = 10.2f;

    Ingredient ingredient;

    @Test
    public void getNameReturnsTest(){
        Ingredient ingredient = new Ingredient(SAUCE, name,price);
        assertEquals(name, ingredient.getName());
    }
    @Test
    public void getPriceReturnsTest(){
        Ingredient ingredient = new Ingredient(SAUCE, name, price);
        assertEquals(price, ingredient.getPrice());
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    public void getTypeReturnsType(IngredientType type){
        Ingredient ingredient = new Ingredient(type, "test", 10f);

        assertEquals(type, ingredient.getType());
    }




}
