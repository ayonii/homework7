package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class MoviePage {

    // Кнопка "Купить билет" на странице фильма
    public SelenideElement buyTicketButton = $("[data-qa-id='shopping_cart_button']"); // Нет точного data-qa-id, используем текст или другой селектор
    // Но на основе HTML, у кнопки нет уникального data-qa-id, поэтому используем:
    // Найдем по тексту "Купить билет"
    // Однако, для отзывов нам это не нужно.

    // Поле ввода отзыва
    public SelenideElement reviewTextarea = $("[data-qa-id='movie_review_input']");

    // Кнопка отправки отзыва
    public SelenideElement submitReviewButton = $("[data-qa-id='movie_review_submit_button']");

    // Селектор рейтинга (выпадающий список с рейтингом)
    // На странице review.txt: <select> внутри кнопки, но Selenide может работать с кнопкой
    // Лучше найти сам select или использовать кнопку и выбирать через неё
    // Для простоты, предположим, что мы можем установить значение напрямую в скрытый select
    // Но в HTML он скрыт! Поэтому будем кликать на кнопку и выбирать опцию.
    // Пока оставим простой способ: найти кнопку рейтинга.
    public SelenideElement ratingSelectButton = $("[data-qa-id='movie_rating_select']").parent();

    // Успешное сообщение об отзыве (по классу из acceptedreview.txt)
    public SelenideElement successMessage = $(".go3958317564");

    // Заголовок фильма (для проверки)
    public SelenideElement movieTitle = $("h2"); // Первый h2 — это название фильма

    /**
     * Публикует отзыв
     *
     * @param reviewText текст отзыва
     * @param rating     оценка от 1 до 5
     */
    public void publishReview(String reviewText, int rating) {
        reviewTextarea.setValue(reviewText);

        // Открываем выпадающий список рейтинга и выбираем нужную опцию
        ratingSelectButton.click();
        $("[role='listbox'] option[value='" + rating + "']").click(); // Упрощённо, может не сработать
        // Альтернатива: используем селектор по select
        // Но select скрыт! Поэтому лучший способ — найти все кнопки-опции
        // Исходя из HTML, опции — это <option> внутри скрытого <select>
        // Selenide может взаимодействовать со скрытыми элементами через JS
        $("#movie_rating_select ~ select").selectOptionByValue(String.valueOf(rating));

        submitReviewButton.click();
    }

    public boolean isReviewSuccessMessageDisplayed() {
        return successMessage.isDisplayed();
    }

    public String getSuccessMessageText() {
        return successMessage.getText();
    }
}
