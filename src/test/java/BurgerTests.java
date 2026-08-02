import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import org.mockito.Mock;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class BurgerTests {

    private Burger burger;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient list1;

    @Mock
    Ingredient list2;

    @Mock
    private Bun bun;

    @BeforeEach
    public void setUp() {
        burger = new Burger(); // Инициализируем объект перед каждым тестом
    }

    @Test
    public void setBunsCorrect() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientToList() {
        burger.addIngredient(ingredient);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientFromList() {
        burger.addIngredient(list1);
        burger.addIngredient(list2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertEquals(list2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientInList() {
        burger.addIngredient(list1);
        burger.addIngredient(list2);

        burger.moveIngredient(0, 1);

        assertEquals(2, burger.ingredients.size());
        assertEquals(list2, burger.ingredients.get(0));
        assertEquals(list1, burger.ingredients.get(1));
    }

    @Test
    public void getPriceCalculatesTotalPrice() {
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        Mockito.when(ingredient.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        // Стоимость = (100 * 2) + 50 = 250
        assertEquals(250.0f, burger.getPrice(), 0.01f);
    }

    @Test
    public void getReceiptFormatReceipt() {
        Mockito.when(bun.getName()).thenReturn("white bun");
        Mockito.when(bun.getPrice()).thenReturn(100.0f);

        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("hot sauce");
        Mockito.when(ingredient.getPrice()).thenReturn(50.0f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expectedReceipt = String.format(
                "(==== white bun ====)%n" +
                        "= sauce hot sauce =%n" +
                        "(==== white bun ====)%n" +
                        "%nPrice: %f%n", 250.0f
        );

        assertEquals(expectedReceipt, burger.getReceipt());
    }

}
