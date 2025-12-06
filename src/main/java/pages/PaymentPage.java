package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {

    // Элементы формы оплаты
    private final SelenideElement amountInput = $("#amount"); // Количество билетов
    private final SelenideElement cardNumberInput = $("#card.cardNumber"); // Номер карты
    private final SelenideElement cardHolderInput = $("#card.cardholderName"); // Владелец карты
    private final SelenideElement monthSelect = $("#month"); // Месяц
    private final SelenideElement yearSelect = $("#year"); // Год
    private final SelenideElement cvcInput = $("#cvc"); // CVC
    private final SelenideElement submitButton = $("[data-qa-id='payment_submit_button']"); // Кнопка "Оплатить"

    // Элемент для проверки успешной оплаты
    private final SelenideElement successMessage = $(".toast-success"); // Пример, нужно уточнить на сайте

    public void setAmount(int amount) {
        amountInput.setValue(String.valueOf(amount));
    }

    public void enterCardNumber(String cardNumber) {
        cardNumberInput.setValue(cardNumber);
    }

    public void enterCardHolder(String holderName) {
        cardHolderInput.setValue(holderName);
    }

    public void selectMonth(String monthValue) {
        monthSelect.selectOptionByValue(monthValue); // Например, "12" для Декабря
    }

    public void selectYear(String yearValue) {
        yearSelect.selectOptionByValue(yearValue); // Например, "25" для 2025 года
    }

    public void enterCvc(String cvc) {
        cvcInput.setValue(cvc);
    }

    public void clickPay() {
        submitButton.click();
    }

    public boolean isPaymentSuccessful() {
        return successMessage.exists(); // Проверяем, появилось ли сообщение об успехе
    }

    public String getSuccessMessageText() {
        return successMessage.getText();
    }
}
