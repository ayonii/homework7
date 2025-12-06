package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class FilterPage {

    // Кнопка фильтра "Локация" (SPB)
    private final SelenideElement locationFilterButton = $("[data-qa-id='movies_filter_location_select']").parent();

    // Кнопка фильтра "Жанр" (текст "Военный")
    private final SelenideElement genreFilterButton = $("button").find(String.valueOf(Condition.text("Военный")));

    // Ссылка на фильм с ID 689
    private final SelenideElement movieCard689 = $("[data-qa-id='movie_more_689']");

    // Заголовок фильма внутри карточки
    public SelenideElement getMovieTitleElement() {
        return movieCard689.$("h3");
    }

    /**
     * Открывает фильтр локации и выбирает "SPB"
     */
    public void selectLocationSPB() {
        locationFilterButton.click();
        // Выбираем опцию "SPB" в выпадающем списке
        $("[role='listbox']").$(byText("SPB")).click();
    }

    /**
     * Выбирает жанр "Военный" (фильтр применяется мгновенно)
     */
    public void selectGenreMilitary() {
        genreFilterButton.click();
    }

    /**
     * Проверяет, отображается ли фильм с ID 689
     */
    public boolean isMovie689Visible() {
        return movieCard689.isDisplayed();
    }

    /**
     * Возвращает текст названия фильма
     */
    public String getMovie689Title() {
        return getMovieTitleElement().text();
    }
}