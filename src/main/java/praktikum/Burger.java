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
        if (index >= 0 && index < ingredients.size()) {
            ingredients.remove(index);
        }
    }

    public void moveIngredient(int index, int newIndex) {
        if (index < 0 || index >= ingredients.size()) {
            return;
        }

        // если newIndex больше допустимого — помещаем в конец
        if (newIndex < 0) {
            newIndex = 0;
        } else if (newIndex > ingredients.size()) {
            newIndex = ingredients.size();
        }

        Ingredient ingredient = ingredients.remove(index);

        // при добавлении в конец size уже уменьшился на 1 — нужно пересчитать
        if (newIndex > ingredients.size()) {
            ingredients.add(ingredient);
        } else {
            ingredients.add(newIndex, ingredient);
        }
    }

    public float getPrice() {
        float total = 0f;
        if (bun != null) {
            total += bun.getPrice() * 2;
        }
        for (Ingredient ingredient : ingredients) {
            if (ingredient != null) {
                total += ingredient.getPrice();
            }
        }
        return total;
    }

    public String getReceipt() {
        StringBuilder sb = new StringBuilder();

        String bunName = (bun != null && bun.getName() != null) ? bun.getName().trim() : "";

        sb.append("(==== ").append(bunName).append(" ====)").append("\n");

        for (Ingredient ingredient : ingredients) {
            if (ingredient != null) {
                String type = ingredient.getType() == null ? "" : ingredient.getType().toString().toLowerCase();
                String name = ingredient.getName() == null ? "" : ingredient.getName().trim();
                sb.append("= ").append(type).append(" ").append(name).append(" =").append("\n");
            } else {
                sb.append("=  =").append("\n");
            }
        }

        sb.append("(==== ").append(bunName).append(" ====)").append("\n");
        sb.append("\n");
        sb.append(String.format(Locale.US, "Price: %.1f", getPrice())).append("\n");

        return sb.toString();
    }
}