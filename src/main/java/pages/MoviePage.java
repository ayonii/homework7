// src/main/java/pages/MoviePage.java
package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.visible;
import org.openqa.selenium.By;

public class MoviePage {

    // Кнопка "Купить билет" на странице фильма
    private final SelenideElement buyTicketButton = $("[data-qa-id='buy-ticket-button']");

    // Поле ввода отзыва
    private final SelenideElement reviewTextarea = $("[data-qa-id='movie_review_input']");

    // Кнопка отправки отзыва
    private final SelenideElement submitReviewButton = $("[data-qa-id='movie_review_submit_button']");

    // Кнопка выбора рейтинга (всплывающее меню)
    private final SelenideElement ratingSelectButton = $("[data-qa-id='movie_rating_select']").parent();

    // Успешное сообщение об отзыве (по классу из acceptedreview.txt)
    private final SelenideElement successMessage = $(".go3958317564");

    // Заголовок фильма (для проверки)
    private SelenideElement movieTitle = $("h2");

    public void publishReview(String reviewText, int rating) {
        System.out.println("📝 Вводим текст отзыва...");
        reviewTextarea.setValue(reviewText);

        System.out.println("⭐ Выбираем оценку '" + rating + "'...");
        ratingSelectButton.click();

        // --- ИСПРАВЛЕНО: Используем XPath для поиска опции с текстом ---
        String ratingText = String.valueOf(rating);
        // Ищем div с role='listbox', затем внутри него div с role='option', внутри которого span с нужным текстом
        SelenideElement ratingOption = $(By.xpath("//div[@role='listbox']//div[@role='option']//span[text()='" + ratingText + "']/.."));
        ratingOption.shouldBe(visible).click(); // Явно ждём видимости и кликаем

        System.out.println("📤 Нажимаем кнопку 'Отправить отзыв'...");
        submitReviewButton.click();
    }

    public boolean isReviewPublishedSuccessfully() {
        System.out.println("✅ Проверяем, отобразилось ли сообщение об успешной публикации отзыва...");
        return successMessage.shouldBe(visible).isDisplayed();
    }

    public String getReviewConfirmationMessage() {
        return successMessage.getText();
    }
}