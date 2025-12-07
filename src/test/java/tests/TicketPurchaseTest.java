package tests;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import junit.UITest;
import org.junit.jupiter.api.Test;
import steps.AuthSteps;
import steps.FilterSteps;
import steps.TicketSteps;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;

@UITest
public class TicketPurchaseTest {

    private final TicketSteps ticketSteps = new TicketSteps();
    private final AuthSteps authSteps = new AuthSteps();
    private final FilterSteps filterSteps = new FilterSteps();

    @Test
    @Step("Покупка билета с использованием тестовой карты")
    public void shouldPurchaseTicketSuccessfullyWithTestCard() {
        // --- ШАГ 1: Открытие страницы со списком фильмов ---
        System.out.println("🔍 Открываем страницу со списком всех фильмов...");
        Selenide.open("https://cinescope.t-qa.ru/movies");

        // --- ШАГ 2: Проверка и восстановление сессии ---
        authSteps.ensureLoggedIn("waitan123@tavorot.ru", "qwerty123_OOO");

        // --- ШАГ 3: Применение фильтров ---
        System.out.println("⚙️ Применяем фильтры: SPB, Военный...");
        filterSteps.applyFilters();

        // --- ШАГ 4: Найти и кликнуть по карточке фильма ID 689 ---
        System.out.println("🔍 Ищем и кликаем по фильму с ID 689...");
        $("[data-qa-id='movie_more_689']").shouldBe(visible).click(); // <- Ждёт видимости и кликает

        // --- ШАГ 5: Проверка сессии после перехода на страницу фильма ---
        System.out.println("✅ Проверяем сессию после перехода на страницу фильма...");
        authSteps.ensureLoggedIn("waitan123@tavorot.ru", "qwerty123_OOO");

        // --- ШАГИ 6 & 7: Используем метод из TicketSteps, который вызывает TicketPage ---
        System.out.println("🖱️ Нажимаем кнопку 'Купить билет' через Page Object...");
        ticketSteps.clickBuyTicketButton(); // <- Этот метод внутри вызывает ticketPage.clickBuyTicket()

        // --- ШАГ 8: Проверка сессии после клика по кнопке покупки ---
        System.out.println("✅ Проверяем сессию после клика по 'Купить билет'...");
        authSteps.ensureLoggedIn("waitan123@tavorot.ru", "qwerty123_OOO");

        // --- ШАГ 9: Покупка билета ---
        System.out.println("💳 Начинаем процесс покупки билета...");
        String testCardNumber = "4242424242424242";
        String cardHolder = "John Doe";
        String month = "12";
        String year = "25";
        String cvc = "123";

        ticketSteps.purchaseTicketWithCard(testCardNumber, cardHolder, month, year, cvc);

        // --- ШАГ 10: Проверка успешной оплаты ---
        System.out.println("✅ Проверяем результат оплаты...");
        assertThat(ticketSteps.checkPaymentSuccess())
                .as("Оплата должна быть успешной")
                .isTrue();

        String confirmation = ticketSteps.getPaymentConfirmation();
        assertThat(confirmation)
                .as("Подтверждение оплаты должно содержать 'успешно'")
                .containsIgnoringCase("успешно");
    }
}