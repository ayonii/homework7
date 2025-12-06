package tests;

import io.qameta.allure.Step;
import junit.UITest;
import org.junit.jupiter.api.Test;
import steps.TicketSteps;

import static org.assertj.core.api.Assertions.assertThat;

@UITest
public class NegativePaymentTest {

    private final TicketSteps ticketSteps = new TicketSteps();

    @Test
    @Step("Попытка оплаты с неверным CVC")
    public void shouldShowErrorWhenCVCIsInvalid() {
        // Тестовые данные с неверным CVC
        String testCardNumber = "4242424242424242"; // Валидный номер
        String cardHolder = "John Doe";
        String month = "12"; // Валидный месяц
        String year = "25";  // Валидный год
        String invalidCvc = "999"; // Неверный CVC

        // Действие: попытка оплаты
        ticketSteps.purchaseTicketWithCard(testCardNumber, cardHolder, month, year, invalidCvc);

        // Проверка: оплата НЕ прошла успешно
        assertThat(ticketSteps.checkPaymentSuccess())
                .as("Оплата с неверным CVC не должна быть успешной")
                .isFalse();

    }
}
