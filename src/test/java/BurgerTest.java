import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @BeforeEach
    public void createBurger() {
        burger = new Burger();
    }

    public static Stream<Arguments> dataBunForTest() {
        return Stream.of(
                Arguments.of("black bun", 100),
                Arguments.of("white bun", 200),
                Arguments.of( "red bun", 300)
        );
    }

    public static Stream<Arguments> dataIngredientForTest() {
        return Stream.of(
                Arguments.of(FILLING, "cutlet", 100),
                Arguments.of(FILLING, "dinosaur", 200),
                Arguments.of(FILLING, "sausage", 300),
                Arguments.of(SAUCE, "hot sauce", 100),
                Arguments.of(SAUCE, "sour cream", 200),
                Arguments.of(SAUCE, "chili sauce", 300)
        );
    }
    @ParameterizedTest
    @MethodSource("dataBunForTest")
    @DisplayName("Проверка добавления булки")
    public void setBunNameTest(String name, int price) {
        bun = new Bun(name, price);
        burger.setBuns(bun);
        assertEquals(name, burger.bun.getName());
        assertEquals(price, burger.bun.getPrice());

    }

    @ParameterizedTest
    @MethodSource("dataIngredientForTest")
    @DisplayName("Проверка добавления ингредиента")
    public void addIngredientTest(IngredientType type, String name, float price){
        Ingredient ingredient = new Ingredient(type, name, price);
        burger.addIngredient(ingredient);
        assertEquals(name, burger.ingredients.get(0).getName());
        assertEquals(price, burger.ingredients.get(0).getPrice());
        assertEquals(type, burger.ingredients.get(0).getType());
    }

    @Test
    @DisplayName("Проверка удаления ингредиента")
    public void removeIngredientTest(){
        Ingredient ingredient = new Ingredient(SAUCE, "Название", 25);
        int correctSize = burger.ingredients.size();
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        assertEquals(correctSize, burger.ingredients.size());

    }
    @Test
    @DisplayName("Проверка перемещения ингредиента")
    public void moveIngredientTest(){
        Ingredient ingredientOne = new Ingredient(SAUCE, "Ингридиент1", 25);
        Ingredient ingredientTwo = new Ingredient(SAUCE, "Ингридиент2", 25);
        Ingredient ingredientThree = new Ingredient(SAUCE, "Ингридиент3", 25);
        burger.addIngredient(ingredientOne);
        burger.addIngredient(ingredientTwo);
        burger.addIngredient(ingredientThree);
        burger.moveIngredient(1, 0);
        assertEquals(ingredientTwo, burger.ingredients.get(0));
        assertEquals(ingredientOne, burger.ingredients.get(1));
        assertEquals(ingredientThree, burger.ingredients.get(2));
    }

    @Test
    @DisplayName("Проверка получения цены")
    public void getPriceTest(){
        when(bun.getPrice()).thenReturn(50F);
        when(ingredient.getPrice()).thenReturn(50F);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        assertEquals(150F, burger.getPrice());
    }

    @Test
    @DisplayName("Проверка получения чека")
    public void getReceiptTest(){
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(50F);
        when(ingredient.getType()).thenReturn(SAUCE);
        when(ingredient.getName()).thenReturn("hot sauce");
        when(ingredient.getPrice()).thenReturn(50F);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.getReceipt();

        String expected = String.format("(==== black bun ====)%n" +
                                        "= sauce hot sauce =%n" +
                                        "(==== black bun ====)%n" +
                                        "%n" +
                                        "Price: 150,000000%n");
        assertEquals(expected, burger.getReceipt());

    }
}
