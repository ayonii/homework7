package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class MoviesPage {

    // Селектор фильтра по локации (SPB)
    private final SelenideElement locationFilterButton = $("[data-qa-id='movies_filter_location_select']").parent();

    // Селектор фильтра по жанру (Военный)
    private final SelenideElement genreFilterButton = $("button:contains('Военный')"); // Нет data-qa-id, используем текст

    // Название фильма в списке
    private final SelenideElement movieCard = $("[data-qa-id='movie_more_689'] h3");

    /**
     * Применяет фильтры: локация и жанр
     */
    public void applyFilters() {
        // Открываем фильтр локации
        locationFilterButton.click();
        // Выбираем "SPB"
        $("[role='listbox'] li:contains('SPB')").click(); // Упрощённо

        // Открываем фильтр жанра
        genreFilterButton.click();
        // Выбираем "Военный"
        $("[role='listbox'] li:contains('Военный')").click(); // Упрощённо
    }

    /**
     * Проверяет, отображается ли фильм с идентификатором 689
     */
    public boolean isMovie689Visible() {
        return movieCard.isDisplayed();
    }

    public String getMovie689Title() {
        return movieCard.getText();
    }

    public boolean isMovieInResults() {
        return false;
    }

    public int getFilteredMovieCount() {
        return 0;
    }

    public void applyGenreFilter() {
    }
}
