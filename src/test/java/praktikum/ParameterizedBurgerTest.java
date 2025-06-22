package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

@RunWith(Parameterized.class)
public class ParameterizedBurgerTest {

    private Burger burger;
    private String bunName;
    private float bunPrice;
    private String oneIngredientName;
    private float oneIngredientPrice;
    private IngredientType ingredientType;
    private float expectedFullPrice;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    public ParameterizedBurgerTest(String bunName, float bunPrice, String oneIngredientName, float oneIngredientPrice, IngredientType ingredientType, float expectedFullPrice){
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.oneIngredientName = oneIngredientName;
        this.oneIngredientPrice = oneIngredientPrice;
        this.ingredientType = ingredientType;
        this.expectedFullPrice = expectedFullPrice;
    }

    @Parameterized.Parameters
    public static Object[][] data(){
        return new Object[][]{
                {"Булка имя",105.6f, "Ингредиент имя", 12f, IngredientType.FILLING, 259.2f},
                {"Булка имя",123f, "Ингредиент имя", 1.001f, IngredientType.FILLING, 250.00403f}
        };
    }

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
        burger.setBuns(mockBun);
        burger.ingredients.add(mockIngredient);
        burger.ingredients.add(mockIngredient);
        burger.ingredients.add(mockIngredient);
        burger.ingredients.add(mockIngredient);
        Mockito.when(mockBun.getPrice()).thenReturn(bunPrice);
        Mockito.when(mockIngredient.getPrice()).thenReturn(oneIngredientPrice);
        Mockito.when(mockIngredient.getName()).thenReturn("Ингредиент имя");
        Mockito.when(mockBun.getName()).thenReturn("Булка имя");
        Mockito.when(mockIngredient.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void getPriceShouldReturnFullPriceTest(){
        System.out.println(burger.getPrice());
        Assert.assertEquals(expectedFullPrice, burger.getPrice(), 0.0001f);
    }

    @Test
    public void getReceiptShouldReturnReceiptTest(){
        
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bunName));
        for (Ingredient ingredient : burger.ingredients) {
            receipt.append(String.format("= %s %s =%n", ingredientType.toString().toLowerCase(), oneIngredientName));
        }
        receipt.append(String.format("(==== %s ====)%n", bunName));
        receipt.append(String.format("%nPrice: %f%n", expectedFullPrice));
        String expectedReceipt = receipt.toString();
        Assert.assertEquals(expectedReceipt, burger.getReceipt());

    }

}
