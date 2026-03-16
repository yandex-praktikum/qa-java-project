package praktikum;

import java.util.ArrayList;
import java.util.List;

/**
 * Основной класс Burger, описывающий структуру бургера и действия с ним.
 * Модель бургера.
 * Бургер состоит из булочек и ингредиентов (начинка или соус).
 * Ингредиенты можно перемещать и удалять.
 * Можно распечатать чек с информацией о бургере.
 */
public class Burger {

    /**
     * Поле для хранения булочки.
     * В этой модели предполагается, что булка сверху и снизу одинаковая.
     */
    public Bun bun;

    /**
     * Список ингредиентов (начинки и соусы).
     * Используется ArrayList, так как нам важен порядок ингредиентов.
     */
    public List<Ingredient> ingredients = new ArrayList<>();

    /**
     * Устанавливает булки для бургера.
     * В JUnit тестах это будет один из первых шагов сборки (Arrange/Given).
     */
    public void setBuns(Bun bun) {
        this.bun = bun;
    }

    // Добавляет ингредиент в список
    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    /**
     * Удаляет ингредиент по его индексу в списке.
     * При тестировании важно проверять граничные значения индексов.
     */
    public void removeIngredient(int index) {
        ingredients.remove(index);
    }

    /**
     * Перемещает ингредиент в списке.
     * Сначала метод удаляет объект по старому индексу (remove возвращает удаленный объект),
     * а затем сразу вставляет его по новому индексу.
     */
    public void moveIngredient(int index, int newIndex) {
        ingredients.add(newIndex, ingredients.remove(index));
    }

    /**
     * Рассчитывает итоговую стоимость бургера.
     * Логика: (Цена булки * 2) + Сумма всех ингредиентов.
     * Это критически важный метод для Unit-тестирования.
     */
    public float getPrice() {
        float price = bun.getPrice() * 2;

        for (Ingredient ingredient : ingredients) {
            price += ingredient.getPrice();
        }

        return price;
    }

    /**
     * Генерирует текстовый чек.
     * Использует StringBuilder для эффективной работы со строками в цикле.
     * Форматирует вывод: название булки в скобках, тип ингредиента в нижнем регистре.
     */
    public String getReceipt() {
        StringBuilder receipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));

        for (Ingredient ingredient : ingredients) {
            // Приведение типа ингредиента к нижнему регистру (toLowerCase)
            receipt.append(String.format("= %s %s =%n", ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }

        // Закрываем чек названием нижней булки
        receipt.append(String.format("(==== %s ====)%n", bun.getName()));
        // Выводим итоговую цену. %f — формат для float (число с плавающей точкой)
        receipt.append(String.format("%nPrice: %f%n", getPrice()));

        // Превращаем накопленные данные в финальную строку
        return receipt.toString();
    }

}