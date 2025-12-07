package steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.NoSuchElementException;
import pages.LoginPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class AuthSteps {
    private final LoginPage loginPage = new LoginPage();

    public void login(String email, String password) {
        // Ждём появления поля email на странице логина (с учетом таймаута из Configuration)
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }

    public boolean isUserLoggedIn() {
        try {
            // Ждём кнопку "Профиль" с глобальным таймаутом
            $("[data-qa-id='profile_page_button']").shouldBe(visible);
            return true;
        } catch (NoSuchElementException | AssertionError e) {
            return false;
        }
    }

    public void ensureLoggedIn(String email, String password) {
        if (!isUserLoggedIn()) {
            System.out.println("🔒 Сессия потеряна. Выполняем повторный вход...");
            // Открываем страницу логина
            Selenide.open("https://cinescope.t-qa.ru/login");

            // Ждём, пока форма логина (например, поле email) станет видимой
            System.out.println("⏳ Ждём загрузки формы логина...");
            $("#email").shouldBe(visible); // <- Ждёт с таймаутом из Configuration

            // Теперь выполняем логин
            login(email, password);

            // Проверим, что авторизация прошла и кнопка "Профиль" появилась
            System.out.println("⏳ Ждём появления кнопки 'Профиль' после повторного входа...");
            $("[data-qa-id='profile_page_button']").shouldBe(visible); // <- Ждёт с таймаутом

            System.out.println("✅ Повторная авторизация успешна.");
        } else {
            System.out.println("✅ Пользователь уже авторизован.");
        }
    }
}