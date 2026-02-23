package praktikum;

public class Ingredient {

    public IngredientType type;
    public String name;
    public float price;

    public Ingredient(IngredientType type, String name, float price) {
        this.type = type != null ? type : IngredientType.FILLING;

        if (name == null || name.isEmpty()) {
            this.name = "Без названия";
        } else {
            this.name = name;
        }

        this.price = Math.max(0, price);
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public IngredientType getType() {
        return type;
    }

}