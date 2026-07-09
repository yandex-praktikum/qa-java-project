import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;
import org.assertj.core.api.SoftAssertions;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;

    private Burger burger;

    private Ingredient cheese;
    private Ingredient beef;
    private Ingredient sauce;

    @Before
    public void setUp() {
        Mockito.when(bun.getPrice()).thenReturn(60.0f);
        Mockito.when(bun.getName()).thenReturn("Classic Bun");

        cheese = new Ingredient(IngredientType.FILLING, "Cheese", 40.0f);
        beef = new Ingredient(IngredientType.FILLING, "Beef Patty", 150.0f);
        sauce = new Ingredient(IngredientType.SAUCE, "Ketchup", 20.0f);

        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        SoftAssertions softly = new SoftAssertions();

        assertEquals("Изначально список ингредиентов пуст", 0, burger.ingredients.size());

        burger.addIngredient(cheese);

        softly.assertThat(burger.ingredients.size()).isEqualTo(1);
        softly.assertThat(burger.ingredients).contains(cheese);
        softly.assertThat(burger.ingredients.get(0)).isEqualTo(cheese);

        softly.assertAll();
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        burger.addIngredient(cheese);
        burger.addIngredient(beef);

        float expectedPrice = (60.0f * 2) + 40.0f + 150.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0.01f);
    }

    @Test
    public void removeIngredientTest() {
        SoftAssertions softly = new SoftAssertions();

        burger.setBuns(bun);
        burger.addIngredient(cheese);
        burger.addIngredient(beef);
        burger.addIngredient(sauce);

        softly.assertThat(burger.ingredients.size())
                .as("До удаления должно быть 3 ингредиента")
                .isEqualTo(3);

        burger.removeIngredient(1);

        softly.assertThat(burger.ingredients.size()).isEqualTo(2);
        softly.assertThat(burger.ingredients).doesNotContain(beef);
        softly.assertThat(burger.ingredients).contains(sauce);
        softly.assertThat(burger.ingredients.get(1)).isEqualTo(sauce);

        softly.assertAll();
    }


    @Test
    public void moveIngredientTest() {
        SoftAssertions softly = new SoftAssertions();

        burger.setBuns(bun);
        burger.addIngredient(cheese);
        burger.addIngredient(beef);
        burger.addIngredient(sauce);

        burger.moveIngredient(1, 0);

        softly.assertThat(burger.ingredients.get(0)).isEqualTo(beef);
        softly.assertThat(burger.ingredients.get(1)).isEqualTo(cheese);
        softly.assertThat(burger.ingredients.get(2)).isEqualTo(sauce);

        softly.assertAll();
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(bun);
        burger.addIngredient(cheese);
        burger.addIngredient(sauce);

        String receipt = burger.getReceipt();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(receipt).contains("(==== Classic Bun ====");
        softly.assertThat(receipt).contains("= filling Cheese =");
        softly.assertThat(receipt).contains("= sauce Ketchup =");
        softly.assertThat(receipt).contains("Price: ");
        softly.assertAll();
    }
}
