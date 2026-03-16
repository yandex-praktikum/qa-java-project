package praktikum;

/**
 * Модель булочки для бургера.
 * Булочке можно дать название и назначить цену.
 */
public class Bun {

    // Поля объявлены как public для упрощения доступа в учебном проекте,
    // но инкапсуляция сохранена через наличие геттеров.
    public String name; // Название булочки (например, "Флюоресцентная булка")
    public float price; // Стоимость булочки

    /**
     * Конструктор класса Bun.
     * @param name Название, которое мы хотим присвоить.
     * @param price Цена за одну единицу товара.
     */
    public Bun(String name, float price) {
        this.name = name;
        this.price = price;
    }
    /**
     * Геттер для получения названия.
     * В JUnit тестах мы будем использовать его для assertEquals.
     */
    public String getName() {
        return name;
    }
    /**
     * Геттер для получения цены.
     * Надо учесть нюанс: в бургерах Stellar Burgers цена булки при расчете стоимости обычно удваивается.
     */
    public float getPrice() {
        return price;
    }

}