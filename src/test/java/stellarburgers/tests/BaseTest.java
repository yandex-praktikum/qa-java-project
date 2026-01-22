package stellarburgers.tests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.example.clients.AuthClient;
import org.example.clients.OrderClient;
import org.example.clients.UserClient;
import org.example.config.UserData;
import org.example.models.User;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    protected UserClient userClient;
    protected AuthClient authClient;
    protected OrderClient orderClient;
    protected User testUser;
    protected String accessToken;

    @Before
    @Step("Настройка тестового окружения")
    public void setUp() {
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter(),
                new AllureRestAssured()
                        .setRequestTemplate("http-request.ftl")
                        .setResponseTemplate("http-response.ftl")
        );

        userClient = new UserClient();
        authClient = new AuthClient();
        orderClient = new OrderClient();

        // Создаем уникального пользователя для тестов
        testUser = new User(
                UserData.generateUniqueEmail(),
                UserData.DEFAULT_PASSWORD,
                UserData.generateUniqueName()
        );
    }

    @After
    @Step("Очистка тестовых данных")
    public void tearDown() {
        // Удаляем тестового пользователя если он был создан и у нас есть токен
        if (accessToken != null && !accessToken.isEmpty()) {
            try {
                userClient.deleteUser(accessToken);
            } catch (Exception e) {
                // Игнорируем ошибки при удалении
            }
        }
    }
}