
import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;
    @Mock
    private Ingredient sauce;
    @Mock
    private Ingredient filling;
    private Burger burger;

    @Before
    public void createNewInstance() {
        burger = new Burger();
    }

    @Test
    public void setBuns() {
        burger.setBuns(bun);
        Bun actual = burger.bun;

        assertEquals("Неправильное использование зависимости от булочки в бургере", bun, actual);
    }

    @Test
    public void addIngredient() {
        burger.addIngredient(ingredient);
        List<Ingredient> expected = List.of(ingredient);
        List<Ingredient> actual = burger.ingredients;

        assertEquals("Неправильное добавление ингредиента в список", expected, actual);
    }

    @Test
    public void removeIngredient() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        List<Ingredient> actual = burger.ingredients;

        assertEquals("Неправильное удаление ингредиента из списка", List.of(), actual);
    }

    @Test
    public void moveIngredient() {
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        burger.moveIngredient(0, 1);
        Ingredient actual = burger.ingredients.get(1);

        assertEquals("Неправильное перемещение ингредиента в списке", filling, actual);
    }

    @Test
    public void getReceipto() {
        Mockito.when(bun.getName()).thenReturn("original");
        Mockito.when(bun.getPrice()).thenReturn(200.0f);
        burger.setBuns(bun);

        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("chili");
        Mockito.when(ingredient.getPrice()).thenReturn(20.0f);
        burger.addIngredient(ingredient);

        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));
        List<Ingredient> ingredients = burger.ingredients;

        for (Ingredient ingredient : ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredient.getType().name().toLowerCase(),
                    ingredient.getName()));
        }

        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        receipt.append(String.format("%nPrice: %f%n", burger.getPrice()));

        String expected = receipt.toString();
        String actual = burger.getReceipt();

        assertEquals("Некорректный рецепт бургера", expected, actual);
    }
}
