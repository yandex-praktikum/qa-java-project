package praktikum;

/**
 * Модель булочки для бургера.
 * Булочке можно дать название и назначить цену.
 */
public class Bun implements BunInterface{

    public String name;
    public float price;

    public Bun(String name, float price) {
        this.name = name;
        this.price = price;
    }
@Override
    public String getName() {
        return name;
    }
@Override
    public float getPrice() {
        return price;
    }

}