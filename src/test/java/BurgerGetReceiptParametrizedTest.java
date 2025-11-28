import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.List;

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




}
