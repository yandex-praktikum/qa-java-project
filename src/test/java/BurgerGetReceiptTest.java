import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import java.util.List;

import static org.junit.Assert.assertThrows;


//Тестирование метода getReceipt класса Burger
@RunWith(Parameterized.class)
public class BurgerGetReceiptTest {

    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final String expectedReceipt;
    private Burger burger;


    public BurgerGetReceiptTest(Bun bun, List<Ingredient> ingredients, String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    //Создание массива параметров
    @Parameterized.Parameters(name = "Булка:{0}, Список ингредиентов:{1}, ожидаемый чек:{2}")
    public static Object[][] burgerContents() {
        return new Object[][]{
                {Mocks.getMockBun(),
                        List.of(Mocks.getMockSauce(), Mocks.getMockSalad(), Mocks.getMockMeat()),
                        "(==== Булочка ====)" + "\r\n" +
                                "= sauce Соус =" + "\r\n" +
                                "= filling Салат =" + "\r\n" +
                                "= filling Мясо =" + "\r\n" +
                                "(==== Булочка ====)" + "\r\n" +
                                "\r\n" + "Price: 500,000000" + "\r\n"},
                {Mocks.getMockBun(),
                        List.of(Mocks.getMockSauce(), Mocks.getMockMeat()),
                        "(==== Булочка ====)" + "\r\n" +
                                "= sauce Соус =" + "\r\n" +
                                "= filling Мясо =" + "\r\n" +
                                "(==== Булочка ====)" + "\r\n" +
                                "\r\n" + "Price: 430,000000" + "\r\n"},
        };
    }

    @Before
    //Создаем Бургер перед каждым тестом
    public void prepareBurger() {
        burger = new Burger();
    }

    @Test
    //Тестирование метода getReceipt
    //Тест проверяет правильность вывода чека
    public void getReceiptTest() {
        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);
        String receipt = burger.getReceipt();
        Assert.assertEquals(expectedReceipt, receipt);
    }

    @Test
    //Тестирование метода getReceipt
    //Тест проверяет выброс NullPointerException при отсутствующей булке
    public void getReceiptWithoutBunShouldNullPointEx() {
        assertThrows(NullPointerException.class, () -> {
            burger.getReceipt();
        });
    }

    @Test
    //Тестирование метода getReceipt
    //Тест проверяет выброс NullPointerException при отсутствующих ингредиентах
    public void getReceiptWithoutIngredientsShouldNullPointExTest() {
        burger.setBuns(bun);
        burger.ingredients = null; // если поле публичное или через сеттер
        assertThrows(NullPointerException.class, () -> {
            burger.getReceipt();
        });
    }

}
