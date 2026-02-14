import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

public class IngredientTests {

    Ingredient ingredient;

    final IngredientType INGREDIENT_TYPE = IngredientType.FILLING;
    final String INGREDIENT_NAME = "Ingredient name";
    final float INGREDIENT_PRICE = 2;

    @BeforeEach
    @DisplayName("Создание объекта класса Ingredient с тестовыми параметрами")
    void setUp(){
        ingredient = new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE);
    }

    @Test
    @DisplayName("Метод getName() должен возвращать название ингредиента")
    void getNameReturnsCurrentName(){
        Assertions.assertEquals(INGREDIENT_NAME, ingredient.getName());
    }

    @Test
    @DisplayName("Метод getPrice() должен возвращать цену ингредиента")
    void getPriceReturnCurrentPrice(){
        Assertions.assertEquals(INGREDIENT_PRICE, ingredient.getPrice());
    }

    @Test
    @DisplayName("Метод getType() должен возвращать тип ингредиента")
    void getTypeReturnCurrentType(){
        Assertions.assertEquals(INGREDIENT_TYPE, ingredient.getType());
    }
}
