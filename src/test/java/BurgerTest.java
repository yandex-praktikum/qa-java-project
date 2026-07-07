import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    private Burger burger;
    public List<Ingredient> ingredients = new ArrayList<>();

    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient1;
    @Mock
    private Ingredient ingredient2;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }


    @Test
    void shouldSetBun() {
        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    void  shouldAddIngredient(){
        Ingredient ingredient = new Ingredient(SAUCE, "Томат", 10);
        ingredients.add(ingredient);
        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredients.get(0), burger.ingredients.get(0));
    }

    @Test
    void shouldRemoveIngredient(){
        Ingredient ingredient = new Ingredient(SAUCE, "Томат", 10);
        ingredients.add(ingredient);

        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);

        burger.removeIngredient(1);

        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredients.get(0), burger.ingredients.get(0));
    }

    @Test
    void shouldMoveIngredient() {
        // Arrange
        Ingredient firstIngredient = new Ingredient(FILLING, "Томат", 10);
        Ingredient secondIngredient = new Ingredient(SAUCE, "Кетчуп", 5);

        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        // Act
        burger.moveIngredient(1, 0);

        // Assert
        assertEquals(secondIngredient, burger.ingredients.get(0));
        assertEquals(firstIngredient, burger.ingredients.get(1));
    }

    @Test
    void shouldReturnCorrectPrice() {

        when(bun.getPrice()).thenReturn(100f);
        when(ingredient1.getPrice()).thenReturn(10f);
        when(ingredient2.getPrice()).thenReturn(5f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        assertEquals(215f, burger.getPrice());
    }

        @Test
        void shouldReturnReceipt() {
            when(bun.getName()).thenReturn("Black");
            when(bun.getPrice()).thenReturn(100f);

            when(ingredient1.getType()).thenReturn(SAUCE);
            when(ingredient1.getName()).thenReturn("Кетчуп");
            when(ingredient1.getPrice()).thenReturn(10f);

            burger.setBuns(bun);
            burger.addIngredient(ingredient1);

            String receipt = burger.getReceipt();

            assertTrue(receipt.contains("Black"));
            assertTrue(receipt.contains("sauce Кетчуп"));
            assertTrue(receipt.contains("Price:"));
        }

}
