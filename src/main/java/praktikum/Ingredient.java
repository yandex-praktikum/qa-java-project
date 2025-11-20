package praktikum;

public class Ingredient {

    public IngredientType type;
    public String name;
    public float price;

    public Ingredient(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
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