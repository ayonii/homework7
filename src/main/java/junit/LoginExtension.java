package junit;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import steps.AuthSteps;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginExtension implements BeforeEachCallback {

    private final AuthSteps authSteps = new AuthSteps();

    @Override
    public void beforeEach(ExtensionContext context) {
        // 1. Открываем главную страницу
        Selenide.open("https://cinescope.t-qa.ru/");
        System.out.println("🌐 Открыта главная страница.");
        Selenide.sleep(5000); // Ждём 5 секунд

        // Проверяем, отображается ли кнопка "Войти"
        SelenideElement loginButton = $("[data-qa-id='login_page_button']");
        SelenideElement profileButton = $("[data-qa-id='profile_page_button']");

        // Цикл: если кнопка "Профиль" не видна, пытаемся авторизоваться
        while (!profileButton.is(visible)) {
            System.out.println("🔍 Кнопка 'Профиль' не отображается. Проверяем кнопку 'Войти'...");

            // Проверяем, отображается ли кнопка "Войти"
            if (loginButton.is(visible)) {
                System.out.println("🖱️ Кнопка 'Войти' видна. Ждём 5 секунд перед кликом...");
                Selenide.sleep(5000); // Ждём 5 секунд перед кликом

                System.out.println("🖱️ Нажимаем кнопку 'Войти'...");
                loginButton.click();

                System.out.println("⏳ Ждём 5 секунд после клика...");
                Selenide.sleep(5000); // Ждём 5 секунд после клика

                // Ждём появления формы логина
                System.out.println("📧 Ждём появления формы логина...");
                $("#email").shouldBe(visible, java.time.Duration.ofSeconds(10));

                // Выполняем логин через AuthSteps
                System.out.println("📧 Вводим креды...");
                authSteps.login("waitan123@tavorot.ru", "qwerty123_OOO");

                System.out.println("✅ Логин введён. Ждём 5 секунд перед нажатием 'Войти'...");
                Selenide.sleep(5000); // Ждём 5 секунд перед отправкой

                // Ждём появления кнопки "Профиль" — подтверждение успешного входа
                System.out.println("⏳ Ждём появления кнопки 'Профиль'...");
                profileButton.shouldBe(visible, java.time.Duration.ofSeconds(15));

                System.out.println("✅ Авторизация успешна. Кнопка 'Профиль' отображается.");
            } else {
                // Если кнопка "Войти" не видна, но и "Профиль" тоже не видна — ошибка
                throw new IllegalStateException("Не удалось определить статус авторизации: ни кнопка 'Войти', ни 'Профиль' не отображаются.");
            }
        }

        // Если цикл завершился, значит кнопка "Профиль" уже отображалась, или мы вошли
        System.out.println("✅ Пользователь авторизован (кнопка 'Профиль' видна).");
    }
}