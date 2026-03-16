package praktikum;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Database имитирует работу с базой данных.
 * Он содержит в себе списки доступных булочек и ингредиентов (соусов и начинок),
 * которые можно использовать для сборки бургера.
 */
public class Database {

    // Список для хранения доступных булочек
    private final List<Bun> buns = new ArrayList<>();

    // Список для хранения доступных ингредиентов (начинки и соусы)
    private final List<Ingredient> ingredients = new ArrayList<>();

    /**
     * Конструктор класса Database.
     * При создании объекта базы данных списки сразу заполняются тестовыми данными.
     */
    public Database() {
        // Добавление булочек в "базу данных"
        buns.add(new Bun("black bun", 100)); // Черная булка за 100
        buns.add(new Bun("white bun", 200)); // Белая булка за 200
        buns.add(new Bun("red bun", 300));   // Красная булка за 300

        // Добавление соусов (тип SAUCE)
        ingredients.add(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "sour cream", 200));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "chili sauce", 300));

        // Добавление начинок (тип FILLING)
        ingredients.add(new Ingredient(IngredientType.FILLING, "cutlet", 100));
        ingredients.add(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        ingredients.add(new Ingredient(IngredientType.FILLING, "sausage", 300));
    }

    /**
     * Метод возвращает список всех доступных в базе булочек.
     * @return Список объектов Bun.
     */
    public List<Bun> availableBuns() {
        return buns;
    }

    /**
     * Метод возвращает список всех доступных в базе ингредиентов.
     * @return Список объектов Ingredient (соусы и начинки).
     */
    public List<Ingredient> availableIngredients() {
        return ingredients;
    }

}