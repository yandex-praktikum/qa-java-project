package praktikum;

public class Bun {

    public String name;
    public float price;

    public Bun(String name, float price) {
        if (name == null || name.isEmpty()) {
            this.name = "Без названия";
        } else {
            this.name = name;
        }
        this.price = Math.max(0, price);
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

}