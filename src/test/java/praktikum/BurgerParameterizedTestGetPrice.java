package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerParameterizedTestGetPrice {

    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final float expectedPrice;

    public BurgerParameterizedTestGetPrice(Bun bun, List<Ingredient> ingredients, float expectedPrice) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Тест {index}: Ожидаемая цена = {2}")
    public static Collection<Object[]> getTestData() {
        Bun blackBun = new Bun("black bun", 100.0f);
        Bun whiteBun = new Bun("white bun", 200.0f);

        Ingredient hotSauce = new Ingredient(IngredientType.SAUCE, "hot sauce", 100.0f);
        Ingredient cutlet = new Ingredient(IngredientType.FILLING, "cutlet", 100.0f);
        Ingredient sausage = new Ingredient(IngredientType.FILLING, "sausage", 300.0f);

        return Arrays.asList(new Object[][]{
                { blackBun, Collections.emptyList(), 200.0f },
                { blackBun, Collections.singletonList(hotSauce), 300.0f },
                { whiteBun, Arrays.asList(hotSauce, cutlet), 600.0f },
                { whiteBun, Arrays.asList(hotSauce, cutlet, sausage), 900.0f }
        });
    }

    @Test
    public void getPriceCalculatesCorrectly() {
        Burger burger = new Burger();
        burger.setBuns(bun);

        for (Ingredient ingredient : ingredients) {
            burger.addIngredient(ingredient);
        }

        float actualPrice = burger.getPrice();

        assertEquals("Итоговая цена бургера рассчитана неверно", Float.valueOf(expectedPrice), Float.valueOf(actualPrice));
    }
}
