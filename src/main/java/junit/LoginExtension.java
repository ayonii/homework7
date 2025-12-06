package junit;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import steps.AuthSteps;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginExtension implements BeforeEachCallback {

    private final AuthSteps authSteps = new AuthSteps();

    @Override
    public void beforeEach(ExtensionContext context) {
        // 1. Открываем страницу логина
        Selenide.open("https://cinescope.t-qa.ru/login");

        // 2. Ждём, пока появится поле email (гарантирует, что форма загрузилась)
        $("#email").shouldBe(visible);

        // 3. Выполняем логин
        authSteps.login("waitan123@tavorot.ru", "qwerty123_OOO");

        // 🔥 КРИТИЧЕСКИ ВАЖНО: ждём появления элемента, подтверждающего вход
        $("[data-qa-id='profile_page_button']").shouldBe(visible, Duration.ofSeconds(10));

        // 4. Ждём появления элемента, подтверждающего успешный вход
        // На главной странице после логина появляется кнопка "Профиль"
        SelenideElement profileButton = $("[data-qa-id='profile_page_button']");
        profileButton.shouldBe(visible);
    }
}