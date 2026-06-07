package praktikum;

import java.util.Arrays;
import java.util.List;

//Тестовые данные
public class TestData {

    // Цены
    public static final float PRICE_BUN_BASIC = 100f;
    public static final float PRICE_BUN_DELUXE = 200f;
    public static final float PRICE_ING_SAUCY = 50f;
    public static final float PRICE_ING_HEARTY = 150f;
    public static final float PRICE_ING_EXOTIC = 300f;

    //Компонентоы
    public static final String NAME_BUN_OAT = "oat bran bun";
    public static final String NAME_SAUCE_ZESTY = "zesty lime drizzle";
    public static final String NAME_FILL_BISON = "bison patty";
    public static final String NAME_FILL_MANGO = "caramelized mango slice";

    //Ингредиенты
    public static final IngredientType TYPE_SAUCE = IngredientType.SAUCE;
    public static final IngredientType TYPE_FILLING = IngredientType.FILLING;

    //Настройки
    public static final float FLOAT_DELTA = 0.01f; // Допустимая погрешность для сравнения float

    //Методы для булок
    public static Bun createBasicBun() {
        return new Bun(NAME_BUN_OAT, PRICE_BUN_BASIC);
    }

    public static Bun createBun(String name, float price) {
        return new Bun(name, price);
    }

    //Ьетоды
    public static Ingredient createZestySauce() {
        return new Ingredient(TYPE_SAUCE, NAME_SAUCE_ZESTY, PRICE_ING_SAUCY);
    }

    public static Ingredient createBisonPatty() {
        return new Ingredient(TYPE_FILLING, NAME_FILL_BISON, PRICE_ING_HEARTY);
    }

    public static Ingredient createMangoSlice() {
        return new Ingredient(TYPE_FILLING, NAME_FILL_MANGO, PRICE_ING_EXOTIC);
    }

    public static Ingredient createIngredient(IngredientType type, String name, float price) {
        return new Ingredient(type, name, price);
    }

    //Объекты для перемещения
    public static final Ingredient MOVE_INGREDIENT_A = createBisonPatty();
    public static final Ingredient MOVE_INGREDIENT_B = createZestySauce();
    public static final Ingredient MOVE_INGREDIENT_C = createMangoSlice();

    //Списки перестановки
    public static final List<Ingredient> LIST_ABC = Arrays.asList(MOVE_INGREDIENT_A, MOVE_INGREDIENT_B, MOVE_INGREDIENT_C);
    public static final List<Ingredient> LIST_ACB = Arrays.asList(MOVE_INGREDIENT_A, MOVE_INGREDIENT_C, MOVE_INGREDIENT_B);
    public static final List<Ingredient> LIST_BAC = Arrays.asList(MOVE_INGREDIENT_B, MOVE_INGREDIENT_A, MOVE_INGREDIENT_C);
    public static final List<Ingredient> LIST_BCA = Arrays.asList(MOVE_INGREDIENT_B, MOVE_INGREDIENT_C, MOVE_INGREDIENT_A);
    public static final List<Ingredient> LIST_CAB = Arrays.asList(MOVE_INGREDIENT_C, MOVE_INGREDIENT_A, MOVE_INGREDIENT_B);

    //Сборка бургера
    public static Burger createBurgerWithIngredients(Bun bun, List<Ingredient> ingredients) {
        Burger burger = new Burger();
        burger.setBuns(bun);
        for (Ingredient ing : ingredients) {
            burger.addIngredient(ing);
        }
        return burger;
    }
}