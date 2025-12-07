// src/main/java/pages/PaymentPage.java
package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import org.openqa.selenium.By;

public class PaymentPage {

    // --- Поля формы оплаты ---
    private final SelenideElement amountInput = $("#amount");
    private final SelenideElement cardNumberInput = $("#card\\.cardNumber"); // Экранирование точки
    private final SelenideElement cardHolderInput = $("#card\\.cardholderName");
    private final SelenideElement monthSelectButton = $("#month"); // Кнопка для выбора месяца
    private final SelenideElement yearSelectButton = $("#year");   // Кнопка для выбора года
    private final SelenideElement cvcInput = $("#cvc");

    // --- Кнопка "Оплатить" ---
    private final SelenideElement submitButton = $("[data-qa-id='payment_submit_button']");

    // --- ИСПРАВЛЕНО: Сообщение об успешной оплате ---
    private final SelenideElement successMessage = $(".go3958317564"); // <- НОВЫЙ СЕЛЕКТОР

    // --- Или, альтернативно, можно искать по тексту: ---
    // private SelenideElement successMessage = $(By.xpath("//*[text()='Оплата прошла успешно']"));

    public void setAmount(int amount) {
        amountInput.setValue(String.valueOf(amount));
    }

    public void enterCardNumber(String cardNumber) {
        System.out.println("⏳ Ждём, пока поле 'Номер карты' станет доступным для ввода...");
        cardNumberInput.shouldBe(visible).shouldBe(Condition.editable);
        cardNumberInput.setValue(cardNumber);
    }

    public void enterCardHolder(String cardHolder) {
        System.out.println("⏳ Ждём, пока поле 'Владелец карты' станет доступным для ввода...");
        cardHolderInput.shouldBe(visible).shouldBe(Condition.editable);
        cardHolderInput.setValue(cardHolder);
    }

    public void selectMonth(String monthValue) {
        System.out.println("🖱️ Кликаем по кнопке выбора месяца...");
        monthSelectButton.shouldBe(visible).click();

        System.out.println("⏳ Ждём появления списка опций для месяца...");
        SelenideElement listBox = $(By.xpath("//div[@role='listbox']")).shouldBe(visible);

        String monthText;
        switch (monthValue) {
            case "01": monthText = "Январь"; break;
            case "02": monthText = "Февраль"; break;
            case "03": monthText = "Март"; break;
            case "04": monthText = "Апрель"; break;
            case "05": monthText = "Май"; break;
            case "06": monthText = "Июнь"; break;
            case "07": monthText = "Июль"; break;
            case "08": monthText = "Август"; break;
            case "09": monthText = "Сентябрь"; break;
            case "10": monthText = "Октябрь"; break;
            case "11": monthText = "Ноябрь"; break;
            case "12": monthText = "Декабрь"; break;
            default: throw new IllegalArgumentException("Неизвестный месяц: " + monthValue);
        }

        System.out.println("🔍 Ищем и кликаем по опции '" + monthText + "' внутри списка месяцев...");
        SelenideElement optionElement = listBox.$(By.xpath(".//div[@role='option']//span[text()='" + monthText + "']/.."));
        optionElement.shouldBe(visible).click();
    }

    public void selectYear(String yearValue) {
        System.out.println("🖱️ Кликаем по кнопке выбора года...");
        yearSelectButton.shouldBe(visible).click();

        System.out.println("⏳ Ждём появления списка опций для года...");
        SelenideElement listBox = $(By.xpath("//div[@role='listbox']")).shouldBe(visible);

        String yearText = "20" + yearValue; // "25" -> "2025"

        System.out.println("🔍 Ищем и кликаем по опции '" + yearText + "' внутри списка годов...");
        SelenideElement optionElement = listBox.$(By.xpath(".//div[@role='option']//span[text()='" + yearText + "']/.."));
        optionElement.shouldBe(visible).click();
    }

    public void enterCvc(String cvc) {
        System.out.println("⏳ Ждём, пока поле 'CVC' станет доступным для ввода...");
        cvcInput.shouldBe(visible).shouldBe(Condition.editable);
        cvcInput.setValue(cvc);
    }

    public void clickPay() {
        System.out.println("🖱️ Нажимаем кнопку 'Оплатить'...");
        submitButton.click();
    }

    // --- ИСПРАВЛЕНО: Проверка успешной оплаты ---
    public boolean isPaymentSuccessful() {
        System.out.println("✅ Проверяем, отобразилось ли сообщение об успешной оплате...");
        // Используем новый селектор
        return successMessage.shouldBe(visible).isDisplayed();
    }

    // --- ИСПРАВЛЕНО: Получение текста сообщения ---
    public String getSuccessMessageText() {
        // Используем новый селектор
        return successMessage.getText();
    }
}