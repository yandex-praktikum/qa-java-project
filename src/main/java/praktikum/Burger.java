package praktikum;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Burger {

    public Bun bun;
    public List<Ingredient> ingredients = new ArrayList<>();

    public void setBuns(Bun bun) {
        this.bun = bun;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public void removeIngredient(int index) {
        ingredients.remove(index);
    }

    public void moveIngredient(int fromIndex, int toIndex) {
        Ingredient ingredient = ingredients.remove(fromIndex);
        ingredients.add(toIndex, ingredient);
    }

    public float getPrice() {
        float total = 0;
        if (bun != null) {
            total += bun.getPrice() * 2; // верхняя и нижняя булка
        }
        for (Ingredient ingredient : ingredients) {
            total += ingredient.getPrice();
        }
        return total;
    }

    public String getReceipt() {
        StringBuilder receipt = new StringBuilder();

        receipt.append(String.format("(==== %s ====)%n", bun.getName()));

        for (Ingredient ingredient : ingredients) {
            receipt.append(String.format("= %s %s =%n",
                    ingredient.getType().toString().toLowerCase(),
                    ingredient.getName()));
        }

        receipt.append(String.format("(==== %s ====)%n%n", bun.getName()));

        // Старательно форматируем цену: 2 знака после точки, локаль US
        receipt.append(String.format(Locale.US, "Price: %.2f%n", getPrice()));

        return receipt.toString();
    }
}