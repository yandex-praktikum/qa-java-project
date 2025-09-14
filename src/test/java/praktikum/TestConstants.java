package praktikum;

public class TestConstants {

    // Названия булок
    public static final String BUN_NAME_FLUOR = "Флюоресцентная булка R2-D3";
    public static final String BUN_NAME_CRATER = "Краторная булка N-200i";
    public static final String BUN_NAME_TEST = "Тестовая булка";
    public static final String BUN_NAME_GENERIC = "Test Bun";

    // Названия ингредиентов
    public static final String SAUCE_SPICY = "Соус Spicy-X";
    public static final String SAUCE_TEST = "Test Sauce";

    public static final String FILLING_PROTOSTOMIA = "Мясо бессмертных моллюсков Protostomia";

    // Цены булок
    public static final float BUN_PRICE_100 = 100.0f;
    public static final float BUN_PRICE_150 = 150.0f;
    public static final float BUN_PRICE_50 = 50.0f;
    public static final float BUN_PRICE_75 = 75.0f;
    public static final float BUN_PRICE_200 = 200.0f;
    public static final float BUN_PRICE_33_33 = 33.33f;
    public static final float BUN_PRICE_ZERO = 0.0f;

    // Цены ингредиентов
    public static final float INGREDIENT_PRICE_25 = 25.0f;
    public static final float INGREDIENT_PRICE_30 = 30.0f;
    public static final float INGREDIENT_PRICE_20 = 20.0f;
    public static final float INGREDIENT_PRICE_50 = 50.0f;
    public static final float INGREDIENT_PRICE_75 = 75.0f;
    public static final float INGREDIENT_PRICE_ZERO = 0.0f;
    public static final float INGREDIENT_PRICE_1 = 1.0f;
    public static final float INGREDIENT_PRICE_11_11 = 11.11f;
    public static final float INGREDIENT_PRICE_22_22 = 22.22f;
    public static final float INGREDIENT_PRICE_33_33 = 33.33f;
    public static final float INGREDIENT_PRICE_25_5 = 25.5f;
    public static final float INGREDIENT_PRICE_44 = 44.0f;

    // Ожидаемые цены для параметризованного теста (float)
    public static final float EXPECTED_PRICE_200 = 200.0f;
    public static final float EXPECTED_PRICE_125 = 125.0f;
    public static final float EXPECTED_PRICE_280 = 280.0f;
    public static final float EXPECTED_PRICE_300 = 300.0f;
    public static final float EXPECTED_PRICE_202 = 202.0f;
    public static final float EXPECTED_PRICE_400_5 = 400.5f;
    public static final float EXPECTED_PRICE_500 = 500.0f;
    public static final float EXPECTED_PRICE_133_32 = 133.32f;
    public static final float EXPECTED_PRICE_ZERO = 0.0f;

    // Ожидаемые цены для тестов getReceipt (String с локалью)
    public static final String EXPECTED_PRICE_200_STR = "200";

    // Дельта для сравнения float
    public static final float DELTA = 0.01f;

    // Строки для проверки чека
    public static final String RECEIPT_BUN_PREFIX = "(==== ";
    public static final String RECEIPT_BUN_SUFFIX = " ====)";
    public static final String RECEIPT_SAUCE_PREFIX = "= sauce ";
    public static final String RECEIPT_FILLING_PREFIX = "= filling ";
    public static final String RECEIPT_INGREDIENT_SUFFIX = " =";
    public static final String RECEIPT_PRICE_PREFIX = "Price: ";

    // Индексы для тестов
    public static final int INDEX_ZERO = 0;
    public static final int INDEX_ONE = 1;

    // Размеры коллекций
    public static final int SIZE_ZERO = 0;
    public static final int SIZE_ONE = 1;
    public static final int SIZE_TWO = 2;
}