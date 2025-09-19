package practicum;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestReport {

    public static void main(String[] args) {
        System.out.println("=== ОТЧЕТ О ТЕСТИРОВАНИИ ПЕРЕЧИСЛЕНИЯ IngredientType ===\n");

        // Запуск параметризованных тестов
        System.out.println("ЗАПУСК ПАРАМЕТРИЗОВАННЫХ ТЕСТОВ:");
        Result paramResult = JUnitCore.runClasses(IngredientTypeParameterizedTest.class);
        System.out.println("Выполнено тестов: " + paramResult.getRunCount());
        System.out.println("Провалено тестов: " + paramResult.getFailureCount());
        System.out.println("Время выполнения: " + paramResult.getRunTime() + " мс");

        if (paramResult.getFailureCount() > 0) {
            System.out.println("\nОШИБКИ В ПАРАМЕТРИЗОВАННЫХ ТЕСТАХ:");
            for (Failure failure : paramResult.getFailures()) {
                System.out.println("- " + failure.getMessage());
            }
        }

        // Запуск непараметризованных тестов
        System.out.println("\nЗАПУСК НЕПАРАМЕТРИЗОВАННЫХ ТЕСТОВ:");
        Result nonParamResult = JUnitCore.runClasses(IngredientTypeTest.class);
        System.out.println("Выполнено тестов: " + nonParamResult.getRunCount());
        System.out.println("Провалено тестов: " + nonParamResult.getFailureCount());
        System.out.println("Время выполнения: " + nonParamResult.getRunTime() + " мс");

        if (nonParamResult.getFailureCount() > 0) {
            System.out.println("\nОШИБКИ В НЕПАРАМЕТРИЗОВАННЫХ ТЕСТАХ:");
            for (Failure failure : nonParamResult.getFailures()) {
                System.out.println("- " + failure.getMessage());
            }
        }

        // Итоговый отчет
        System.out.println("\n=== ИТОГОВЫЙ ОТЧЕТ ===");
        int totalTests = paramResult.getRunCount() + nonParamResult.getRunCount();
        int totalFailures = paramResult.getFailureCount() + nonParamResult.getFailureCount();

        System.out.println("Всего тестов: " + totalTests);
        System.out.println("Успешных: " + (totalTests - totalFailures));
        System.out.println("Проваленных: " + totalFailures);

        if (totalFailures == 0) {
            System.out.println("\n✅ ВСЕ ТЕСТЫ ПРОЙДЕНЫ УСПЕШНО!");
            System.out.println("Перечисление IngredientType работает корректно.");
        } else {
            System.out.println("\n❌ ОБНАРУЖЕНЫ ПРОБЛЕМЫ В ТЕСТАХ");
            System.out.println("Требуется дополнительная проверка реализации.");
        }

        System.out.println("\n=== РЕКОМЕНДАЦИИ ===");
        System.out.println("1. Для более полного покрытия можно добавить тесты на методы compareTo()");
        System.out.println("2. Можно добавить тесты на сериализацию/десериализацию значений перечисления");
        System.out.println("3. Рассмотрите возможность использования инструментов Allure или JaCoCo для более детальных отчетов");
    }
}