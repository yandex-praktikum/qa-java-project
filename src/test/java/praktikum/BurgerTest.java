package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockFirstIngredient;

    @Mock
    private Ingredient mockSecondIngredient;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(mockBun);
        Assert.assertEquals("Неверно установлена булочка", mockBun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(mockFirstIngredient);
        Assert.assertEquals("Неверно добавлен ингредиент",
                List.of(mockFirstIngredient), burger.ingredients);
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(mockFirstIngredient);
        int ingredientsListSize = burger.ingredients.size();
        burger.removeIngredient(0);
        Assert.assertEquals("Неверная длина списка ингредиентов",
                ingredientsListSize - 1, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(mockFirstIngredient);
        burger.addIngredient(mockSecondIngredient);
        burger.moveIngredient(0, 1);
        Assert.assertEquals("Неверно перемещён ингредиент",
                mockFirstIngredient, burger.ingredients.get(1));
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(mockBun);
        Mockito.when(mockBun.getName()).thenReturn("black bun");
        Mockito.when(mockBun.getPrice()).thenReturn(100.0f);

        burger.addIngredient(mockFirstIngredient);
        Mockito.when(mockFirstIngredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(mockFirstIngredient.getName()).thenReturn("dinosaur");
        Mockito.when(mockFirstIngredient.getPrice()).thenReturn(200.0f);

        burger.addIngredient(mockSecondIngredient);
        Mockito.when(mockSecondIngredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(mockSecondIngredient.getName()).thenReturn("chili sauce");
        Mockito.when(mockSecondIngredient.getPrice()).thenReturn(300.0f);

        String expected = String.format("(==== black bun ====)%n"
                + "= filling dinosaur =%n"
                + "= sauce chili sauce =%n"
                + "(==== black bun ====)%n"
                + "%nPrice: %f%n", 700.0f);

        Assert.assertEquals("Неверно сформирован чек", expected, burger.getReceipt());
    }
}
