package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;



@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Spy
    Bun bun = new Bun("Кунжут+Орегано", 0.03F);

    @Spy
    Ingredient ingredient1 = new Ingredient(SAUCE, "mazik", 0.25F);

    @Spy
    Ingredient ingredient2 = new Ingredient(FILLING, "pepper", 0.4F);

    @Spy
    Burger burger;

    @Test
    public void setBuns() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addOneIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));

    }
//Перестраховочка
    @Test
    public void addTwoIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals(2, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
        assertEquals(ingredient2, burger.ingredients.get(1));

    }

    @Test
    public void removeIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

//Проверяем move и убеждаемся что 2 ингредиент не пропал
    @Test
    public void moveIngredient() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals(ingredient1, burger.ingredients.get(0));
        assertEquals(ingredient2, burger.ingredients.get(1));
        burger.moveIngredient(0,1);
        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void getPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
//        Гений маркетинга тот кто придумал булочку *2 считать.
        float expected = bun.getPrice() * 2 + ingredient1.getPrice() + ingredient2.getPrice();
        assertEquals(expected, burger.getPrice(), 0.01);
    }

    @Test
    public void getReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient2);
        String expected = String.format("(==== %s ====)%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n",
                bun.getName(), ingredient2.getType().toString().toLowerCase(),ingredient2.getName(), bun.getName(), burger.getPrice());
//        System.out.println(expected);
//        System.out.println(burger.getReceipt());
        assertEquals(expected, burger.getReceipt());
    }
}