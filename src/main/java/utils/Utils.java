package utils;

import com.github.javafaker.Faker;

import java.util.Random;

public class Utils {

    public static String randomName() {
        Faker faker = new Faker();
        String randomName = faker.leagueOfLegends().summonerSpell();
        return randomName;
    }

    public static float randomPrice() {
        Random random = new Random();
        Faker faker = new Faker();
        int min = 1;
        int max = 27;
        float randomPrice = (float)faker.number().numberBetween(min, max) + random.nextFloat();
        return randomPrice;
    }

}
