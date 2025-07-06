import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;

    @Test
    public void setBunsTest() {
        // Создаем объект бургера
        Burger newBurger = new Burger();
        // Вызываем метод с замоканным объектом
        newBurger.setBuns(bun);
        // Проверяем, что значение успешно сохранено и совпадает
        assertEquals(newBurger.bun, bun);
    }

    @Test
    public void addIngredientTest() {
        // Создаем объект бургера
        Burger newBurger = new Burger();
        // Добавляем мок-объект ингредиента
        newBurger.addIngredient(ingredient);
        // Проверяем, что список ингредиентов бургера содержит наш добавленный
        assertTrue(newBurger.ingredients.contains(ingredient));
    }

    @Test
    public void removeIngredientTest() {
        // Создаем объект бургера
        Burger newBurger = new Burger();
        // Добавляем мок-объект ингредиента
        newBurger.addIngredient(ingredient);
        // Удаляем ингредиент из списка
        newBurger.removeIngredient(0);
        // Проверяем, что теперь список ингредиентов снова пуст
        assertEquals(0, newBurger.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {
        // Создаем объект бургера
        Burger newBurger = new Burger();
        // Создаем моки ингредиентов для добавления
        // Создаем два мока ингредиентов
        Ingredient mockIngredient1 = Mockito.mock(Ingredient.class);
        Ingredient mockIngredient2 = Mockito.mock(Ingredient.class);
        // Создаем список с ожидаемым порядком для проверки после перемещения
        List<Ingredient> ingredientsInExpectedOrder = List.of(mockIngredient1, mockIngredient2);
        // Добавляем ингредиенты в обратном порядке, чтобы потом поменять
        newBurger.addIngredient(mockIngredient2);
        newBurger.addIngredient(mockIngredient1);
        // Меняем местами
        newBurger.moveIngredient(0, 1);
        // Проверяем с нашим объектом списка
        assertEquals(newBurger.ingredients, ingredientsInExpectedOrder);
    }

    @Test
    public void getPriceTest() {
        // Мокаем вызов функций цены для булочки и инредиента
        Mockito.when(bun.getPrice()).thenReturn(111.0F);
        Mockito.when(ingredient.getPrice()).thenReturn(222.0F);
        // Булочки две, поэтому в ожидаемой сумме - цена содержимого + (цена булочки * 2)
        float expectedSum = 444.0f;
        // Создаем объект бургера
        Burger newBurger = new Burger();
        // Добавляем булочку и ингредиент
        newBurger.addIngredient(ingredient);
        newBurger.setBuns(bun);
        // Проверяем, что сумма совпала (дельта добавлена из-за чисел с плавающей точкой, иначе падает)
        assertEquals(expectedSum, newBurger.getPrice(), 0.0F);
    }

    @Test
    public void getReceiptTest() {
        // Создаем объект бургера и заполняем
        Burger newBurger = new Burger();
        newBurger.addIngredient(ingredient);
        newBurger.setBuns(bun);

        // Создаем список ингредиентов для добавления в ожидаемый чек
        List<Ingredient> ingredientsExpectedInReceipt = List.of(ingredient);

        // Мокаем методы объектов, которые используются при составлении чека
        // Сначала для булочки
        Mockito.when(bun.getName()).thenReturn("Какая-то булочка");
        Mockito.when(bun.getPrice()).thenReturn(111.0F);
        // Теперь для ингредиента
        Mockito.when(ingredient.getName()).thenReturn("Какая-то котлета");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient.getPrice()).thenReturn(2222.0F);
        // Сразу сохраняем сумму, которую ожидаем получить в итоге
        float expectedReceiptSum = 2444.0F;
        // Создаем и заполняем объект чека, который ожидаем в итоге получить
        StringBuilder expectedReceipt = new StringBuilder(String.format("(==== %s ====)%n", bun.getName()));

        for (Ingredient ingredient: ingredientsExpectedInReceipt) {
            expectedReceipt.append(
                    String.format(
                            "= %s %s =%n",
                            ingredient.getType()
                                    .toString()
                                    .toLowerCase(),
                            ingredient.getName()
                    )
            );
        }

        // Добавляем название булочки и сумму чека
        expectedReceipt.append(String.format("(==== %s ====)%n", bun.getName()));
        expectedReceipt.append(String.format("%nPrice: %f%n", expectedReceiptSum));

        // Проверяем, что наши рецепты идентичны
        assertEquals(expectedReceipt.toString(), newBurger.getReceipt());
    }
}
