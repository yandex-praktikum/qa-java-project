README.md
Про проект
Автоматизированный набор API-тестов на Java (RestAssured + JUnit4) с интеграцией Allure для отчётности и DataFaker для генерации тестовых данных. Тесты покрывают сценарии работы с пользователями и заказами (создание, авторизация, удаление и т.д.).

Требования
Java 11
Maven 3.6+
(опционально) Allure CLI для локального просмотра отчётов (allure serve / allure open)
Примечание: проект использует AspectJ (aspectjweaver) через argLine в Surefire — убедитесь, что зависимости скачаны в локальный Maven-репозиторий, если запускаете в изолированной среде.

Установка / Быстрый старт
 Клонируйте репозиторий:
git clone
 Перейдите в папку проекта:
cd
 Соберите проект и запустите все тесты:
mvn clean test
Генерация и просмотр Allure-отчётов
 После выполнения тестов результаты попадут в:
target/allure-results (или ${project.build.directory}/allure-results)
 Если установлен Allure CLI, можно запустить:
mvn test
allure serve target/allure-results
 Генерация отчёта через Maven-плагин:
mvn allure:report
mvn allure:open
Полезные команды
Запустить все тесты:
mvn clean test
Запустить конкретный тестовый класс:
mvn -Dtest=CreateOrderTests test
Запустить тесты и не прерывать сборку при падении одного теста:
mvn -DskipTests=false test
Очистить проект:
mvn clean
Сгенерировать Allure-отчёт:
mvn allure:report
Открыть сгенерированный отчет (Allure CLI):
allure open target/site/allure-maven-plugin (или allure serve target/allure-results)
