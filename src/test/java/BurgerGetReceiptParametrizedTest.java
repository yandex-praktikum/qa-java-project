import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerGetReceiptParametrizedTest {

@Mock
private Bun mockBun;
@Mock
private Ingredient mockIngredient1;
@Mock
private Ingredient mockIngredient2;

private Burger burger;

private final String expectedReceipt;
private final List<Ingredient> ingredients;

public BurgerGetPriceParameterizedTest(String expectedReceipt,List<Ingredient> ingredients){
    this.expectedReceipt=expectedReceipt;
    this.ingredients=ingredients;
}
@Parameterized.Parameters
    public static Collection<Object[]> data(){
    return Arrays.asList(new Object[][]{
            {
                "(==== BunName ====)\n" +
                        "(==== BunName ====)\n" +
                        "\n"+
                        "Price: 20.000000\n",
                    List.of()
            },
            {
                    "(==== BunName ====)\n" +
                            "= sauce sauce1 =\n" +
                            "(==== BunName ====)\n" +
                            "\n" +
                            "Price: 25.000000\n",
                    List.of(mockIngredient("sauce", "sauce1", 5.0f))
            },
            {
                    "(==== BunName ====)\n" +
                            "= filling fill1 =\n" +
                            "= sauce sauce2 =\n" +
                            "(==== BunName ====)\n" +
                            "\n" +
                            "Price: 35.000000\n",
                    List.of(
                            mockIngredient("filling", "fill1", 10.0f),
                            mockIngredient("sauce", "sauce2", 5.0f)
                    )
            }
    });
}

private static Ingredient mockIngredient(String type, String name, float price){
    Ingredient ingredient = Mockito.mock(Ingredient.class);
    when(ingredient.getType().toString()).thenReturn(type);
    when(ingredient.getName()).thenReturn(name);
    when(ingredient.getPrice()).thenReturn(price);
    return ingredient;
}




}
