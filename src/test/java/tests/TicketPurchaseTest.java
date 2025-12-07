package tests;

import io.qameta.allure.Step;
import junit.UITest;
import org.junit.jupiter.api.Test;
import steps.TicketSteps;
import com.codeborne.selenide.Selenide;
import static org.assertj.core.api.Assertions.assertThat;

@UITest
public class TicketPurchaseTest {

    private final TicketSteps ticketSteps = new TicketSteps();

    @Test
    @Step("Покупка билета с использованием тестовой карты")
    public void shouldPurchaseTicketSuccessfullyWithTestCard() {
        // 1. Открываем страницу фильма
        Selenide.open("https://cinescope.t-qa.ru/movies/689");

        // 2. Покупка билета
        String testCardNumber = "4242424242424242";
        String cardHolder = "John Doe";
        String month = "12";
        String year = "25";
        String cvc = "123";

        ticketSteps.purchaseTicketWithCard(testCardNumber, cardHolder, month, year, cvc);

        assertThat(ticketSteps.checkPaymentSuccess())
                .as("Оплата должна быть успешной")
                .isTrue();

        String confirmation = ticketSteps.getPaymentConfirmation();
        assertThat(confirmation)
                .as("Подтверждение оплаты должно содержать 'успешно'")
                .containsIgnoringCase("успешно");
    }
}