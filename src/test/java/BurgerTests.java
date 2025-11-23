import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static data.DataModel.*;

public class BurgerTests {

    private Burger burger;
    private Bun bun;
    private Ingredient firstIngredient;
    private Ingredient secondIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
        bun = new Bun("black bun", PRICE_OF_BUN_1);
        firstIngredient = new Ingredient(IngredientType.SAUCE, "hot sauce", PRICE_OF_SAUCE_1);
        secondIngredient = new Ingredient(IngredientType.FILLING, "cutlet", PRICE_OF_FILLY_1);
    }

    @Test
    public void checkSetBunsBurgerHaveBun() {
        burger.setBuns(bun);
        Assert.assertEquals("У бургера не установлена булочка", bun, burger.bun);
    }

    @Test
    public void checkAddIngredientIncreaseIngredientsCount() {
        int initialSize = burger.ingredients.size();
        burger.addIngredient(firstIngredient);

        Assert.assertEquals("Количество ингредиентов не увеличилось на 1",
                initialSize + 1, burger.ingredients.size());
    }

    @Test
    public void checkRemoveIngredientDecreaseIngredientsCount() {
        burger.addIngredient(firstIngredient);
        int sizeAfterAdd = burger.ingredients.size();

        burger.removeIngredient(0);

        Assert.assertEquals("Количество ингредиентов не уменьшилось на 1",
                sizeAfterAdd - 1, burger.ingredients.size());
    }

    @Test
    public void checkMoveIngredientChangeIngredientPosition() {
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        burger.moveIngredient(0, 1);

        Assert.assertEquals("Первый ингредиент не переместился на вторую позицию",
                firstIngredient, burger.ingredients.get(1));
    }
}