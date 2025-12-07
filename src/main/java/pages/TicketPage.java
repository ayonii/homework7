package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class TicketPage {

    // Ищем кнопку по тексту "Купить билет" внутри неё
    private final SelenideElement buyTicketButton = $(withText("Купить билет")); // ✅ ПРАВИЛЬНО

    public void clickBuyTicket() {
        buyTicketButton.click();
    }
}