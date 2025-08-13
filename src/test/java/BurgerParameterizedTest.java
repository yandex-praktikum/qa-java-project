import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class BurgerParameterizedTest {

    private final Bun bun;
    private final Ingredient ingredient;
    private final Ingredient newIngredient;
    private final float price;


    public BurgerParameterizedTest(Bun bun, Ingredient ingredient, Ingredient newIngredient, float price) {
        this.bun = bun;
        this.ingredient = ingredient;
        this.newIngredient = newIngredient;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getPrice(){
        return new Object[][] {
                {new Bun("Флюоресцентная булка R2-D3",988), new Ingredient(SAUCE,"Соус фирменный Space Sauce", 80), new Ingredient(FILLING, "Говяжий метеорит (отбивная)", 3000), 5056},
                {new Bun("Краторная булка N-200i",1255), new Ingredient(SAUCE, "Соус традиционный галактический", 15), new Ingredient(FILLING, "Биокотлета из марсианской Магнолии",424), 2949},
                {new Bun("Флюоресцентная булка R2-D3",988), new Ingredient(SAUCE, "Соус с шипами Антарианского плоскоходца", 88), new Ingredient(FILLING, "Филе Люминесцентного тетраодонтимформа", 988), 3052}
        };
    }

    @Test

    public void getPriceTest(){
        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(newIngredient);
        assertEquals(price, burger.getPrice(), 0.01);
    }
}
