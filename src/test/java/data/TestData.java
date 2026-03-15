package data;

import com.github.javafaker.Faker;
import java.util.Random;

public class TestData {
    private final Faker faker = new Faker();
    private final Random random = new Random();

    public String getName() {
        return faker.food().ingredient();
    }

    public float getPrice() {
        // Используем Random для генерации float
        return 0.01f + random.nextFloat() * (100.00f - 0.01f);
    }
}