package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @ParameterizedTest
    @CsvFileSource(
            resources = "/test.csv", // Путь к файлу
            numLinesToSkip =1,            // Пропуск заголовка
            delimiter = ','                // Разделитель
    )

    void getPrice(String type, String name, float price) {
        Ingredient ig = new Ingredient(IngredientType.valueOf(type), name, price);
        assertEquals(price, ig.getPrice());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/test.csv", // Путь к файлу
            numLinesToSkip =1,            // Пропуск заголовка
            delimiter = ','                // Разделитель
    )
    void getName(String type, String name, float price) {
        Ingredient ig = new Ingredient(IngredientType.valueOf(type), name, price);
        assertEquals(name, ig.getName());
    }

    @ParameterizedTest
    @CsvFileSource(
            resources = "/test.csv", // Путь к файлу
            numLinesToSkip =1,            // Пропуск заголовка
            delimiter = ','                // Разделитель
    )
    void getType(String type, String name, float price) {
        Ingredient ig = new Ingredient(IngredientType.valueOf(type), name, price);
        assertEquals(IngredientType.valueOf(type), ig.getType());
    }
}