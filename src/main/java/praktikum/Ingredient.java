package praktikum;

/**
 * Модель ингредиента для бургера.
 * Ингредиентом может быть соус (SAUCE) или начинка (FILLING).
 * У каждого ингредиента есть тип, название и цена.
 */
public class Ingredient {

    // Поля объявлены как public для прямого доступа в учебных целях,
    // но доступ к ним также возможен через геттеры.
    public IngredientType type; // Тип ингредиента (перечисление: соус или начинка)
    public String name;         // Название ингредиента (например, "hot sauce")
    public float price;         // Стоимость ингредиента

    /**
     * Конструктор класса Ingredient.
     * Используется для создания объекта с заданными характеристиками.
     * @param type  Тип ингредиента (из перечисления IngredientType)
     * @param name  Название ингредиента
     * @param price Цена за единицу
     */
    public Ingredient(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    /**
     * Геттер для получения цены.
     * Используется в Burger.getPrice() для расчета общей стоимости.
     */
    public float getPrice() {
        return price;
    }

    /**
     * Геттер для получения названия.
     * Используется при формировании чека в Burger.getReceipt().
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер для получения типа.
     * Возвращает SAUCE или FILLING.
     */
    public IngredientType getType() {
        return type;
    }

}