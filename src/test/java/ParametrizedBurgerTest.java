import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;

    @RunWith(Parameterized.class)
        public class ParametrizedBurgerTest {

        private Burger burger;
        private final String name;
        private final float price;


        public ParametrizedBurgerTest(String name, float price) {
            this.name = name;
            this.price = price;
        }

        @Before

            public void setUp() {
                burger = new Burger();
    }

    @Parameterized.Parameters(name = "{index} : bun = {0}, price = {1}")
    public static Object[][] getBunData() {
        return new Object[][] {
                {"бесплатная булочка", 0.0f},
                {"оригинальная булочка", 200.0f}
        };
    }

    @Test
    public void getBurgerPrice() {

        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "chili", 20.0f);
        Ingredient filling = new Ingredient(IngredientType.FILLING, "cheese", 15.5f);

        Bun bun = new Bun(name, price);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float expectedBurgerPrice = bun.getPrice() * 2 + sauce.getPrice() + filling.getPrice();
        float actualBurgerPrice = burger.getPrice();

        assertEquals("Неверная цена бургера", expectedBurgerPrice, actualBurgerPrice, 0);
    }
}

