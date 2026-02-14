import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class BurgerTests {

    static final String BUN_NAME = "Name";
    final float BUN_PRICE = 1;
    static final IngredientType INGREDIENT_TYPE = IngredientType.FILLING;
    static final String INGREDIENT_NAME = "Ingredient name";
    final float INGREDIENT_PRICE = 2;

    Burger burger;


    Bun bun;

    @Mock
    Ingredient ingredient;

    @BeforeEach
    @DisplayName("Создание объекта класса Burger")
    void setUp(){
        burger = new Burger();
    }

    @Test
    @DisplayName("Метод setBuns() должен устанавливать булку для бургера")
    void setBunsTest(){
        createAndSetBun();
        Assertions.assertEquals(bun, burger.bun);
    }

    @Test
    @DisplayName("Метод addIngredient() должен добавлять ингредиент в список")
    void addIngredientAddsNewElementInIngredientList(){
        createAndSetIngredient();
        Assertions.assertEquals(ingredient, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Метод removeIngredient() должен удалять ингредиент по индексу")
    void removeIngredientRemovesElementFromIngredientListByIndex(){
        createAndSetIngredient();
        burger.removeIngredient(0);
        Assertions.assertTrue(burger.ingredients.isEmpty());

    }

    @Test
    @DisplayName("Удаление по несуществующему индексу должно вызывать исключение IndexOutOfBoundsException")
    void removeIngredientByNonExistingIndexShouldInvokeIndexOutOfBoundsException(){
        createAndSetIngredient();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(3));
    }

    @Test
    @DisplayName("Метод moveIngredient() должен изменять порядок ингредиентов в списке")
    void moveIngredientShouldChangeListOrderAndChangeIndexOfElementToNewIndex(){
        Ingredient ingredient1 = new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE);
        Ingredient ingredient2 = new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE+1);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(1, 0);
        Assertions.assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    @DisplayName("Перемещение ингредиента на несуществующий индекс должно вызывать исключение IndexOutOfBoundsException")
    void moveIngredientToIndexOutOfBoundsShouldInvokeIndexOutOfBoundsException(){
        Ingredient ingredient1 = new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE);
        Ingredient ingredient2 = new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE+1);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(1, 3));
    }

    @Test
    @DisplayName("Метод getPrice() должен возвращать сумму цен двух булок и всех ингредиентов")
    void getPriceReturnsTotalPriceOfTwoBunsAndIngredients(){
        createAndSetBun();
        createAndSetIngredient();
        Assertions.assertEquals(BUN_PRICE*2+INGREDIENT_PRICE, burger.getPrice());
    }

    @Test
    @DisplayName("Метод getPrice() должен вызывать метод getPrice() булки ровно 1 раз")
    void getPriceShouldCallBunGetPriceOnce(){
        Bun bunSpy = Mockito.spy(createBun());
        burger.setBuns(bunSpy);
        burger.getPrice();
        Mockito.verify(bunSpy, Mockito.times(1)).getPrice();
    }

    @DisplayName("Параметризованный тест проверки корректности составления чека")
    @ParameterizedTest
    @MethodSource("burgerDataProvider")
    void getReceiptShouldReturnCorrectlyFormattedReceipt(String testName, Bun bun, List<Ingredient> ingredients, String expectedReceipt){
        burger.setBuns(bun);
        for (Ingredient ingredient : ingredients){
            burger.addIngredient(ingredient);
        }

        String actualReceipt = burger.getReceipt();
        Assertions.assertEquals(expectedReceipt, actualReceipt, "Неверно составленый чек" + testName);
    }

    @Test
    @DisplayName("Метод getReceipt() должен вызывать метод getName() булочки дважды")
    void getReceiptCallsBunGetNameTwice(){
        Bun bunSpy = Mockito.spy(createBun());
        burger.setBuns(bunSpy);
        burger.getReceipt();
        Mockito.verify(bunSpy, Mockito.times(2)).getName();
    }

    void createAndSetBun(){
        bun = new Bun(BUN_NAME, BUN_PRICE);
        burger.setBuns(bun);
    }

    void createAndSetIngredient(){
        ingredient = new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE);
        burger.addIngredient(ingredient);
    }

    Bun createBun(){
        return new Bun(BUN_NAME, BUN_PRICE);
    }

    Ingredient createIngredient(){
        return new Ingredient(INGREDIENT_TYPE, INGREDIENT_NAME, INGREDIENT_PRICE);
    }


    static Stream<Arguments> burgerDataProvider(){
        return Stream.of(
          Arguments.of(
                  "Бургер без ингредиентов",
                  new Bun("black bun", 100),
                  Arrays.asList(),
                  String.format("(==== black bun ====)%n" +
                                "(==== black bun ====)%n%n" +
                                "Price: 200,000000%n")
          ),
          Arguments.of(
                  "Бургер с одним соусом",
                  new Bun("white bun", 150),
                  Arrays.asList(new Ingredient(IngredientType.SAUCE, "hot sauce", 75)),
                  String.format("(==== white bun ====)%n" +
                                "= sauce hot sauce =%n" +
                                "(==== white bun ====)%n%n" +
                                "Price: 375,000000%n")
          ),
          Arguments.of(
                  "Бургер с одной начинкой",
                  new Bun("red bun", 200),
                  Arrays.asList(new Ingredient(IngredientType.FILLING, "cutlet", 120)),
                  String.format("(==== red bun ====)%n" +
                                "= filling cutlet =%n" +
                                "(==== red bun ====)%n%n" +
                                "Price: 520,000000%n")
                ),
          Arguments.of(
                  "Бургер с несколькими ингредиентами",
                  new Bun("black bun", 100),
                  Arrays.asList(
                          new Ingredient(IngredientType.SAUCE, "hot sauce", 100),
                          new Ingredient(IngredientType.FILLING, "cutlet", 150),
                          new Ingredient(IngredientType.SAUCE, "sour cream", 50),
                          new Ingredient(IngredientType.FILLING, "cheese", 80)
                  ),
                  String.format("(==== black bun ====)%n" +
                                "= sauce hot sauce =%n" +
                                "= filling cutlet =%n" +
                                "= sauce sour cream =%n" +
                                "= filling cheese =%n" +
                                "(==== black bun ====)%n%n" +
                                "Price: 580,000000%n")
          )
        );
    }


}
