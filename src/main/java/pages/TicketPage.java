package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class TicketPage {
    // Кнопка "Купить" на странице фильма (предполагаем, что она есть)
    private SelenideElement buyTicketButton = $("[data-qa-id='buy_ticket_button']");

    public void clickBuyTicket() {
        buyTicketButton.click();
    }
}