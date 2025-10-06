package praktikum;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class BunTest {

    @Test
    public void getNameIsSuccess() {
        String bunName = "bunName";
        Bun bun = new Bun(bunName, 100);

        MatcherAssert.assertThat(
                "Получено некорректное наименование булочки",
                bun.getName(),
                equalTo(bunName)
        );
    }

    @Test
    public void getPriceIsSuccess() {
        float bunPrice = 100;
        Bun bun = new Bun("bunName", bunPrice);

        MatcherAssert.assertThat(
                "Получена некорректная стоимость булочки",
                bun.getPrice(),
                equalTo(bunPrice)
        );
    }
}
