package steps;

import com.codeborne.selenide.Condition;
import pages.FilterPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FilterSteps {
    private final FilterPage filterPage = new FilterPage();

    public void applyFilters() {
        // --- ШАГ 1: Проверка, видны ли кнопки фильтров ---
        System.out.println("🔍 Проверяем, отображаются ли кнопки фильтров...");

        // Проверим, например, видна ли кнопка фильтра локации
        // Используем `isDisplayed()` для проверки видимости элемента
        boolean isLocationFilterVisible = $("[data-qa-id='movies_filter_location_select']").is(Condition.visible);

        if (!isLocationFilterVisible) {
            System.out.println("❌ Кнопки фильтров не видны. Переходим по ссылке 'Все фильмы'...");
            // Клик по ссылке "Все фильмы" в навигации
            $("nav a[href='/movies?page=1']").click();
            // Или $("a:contains('Все фильмы')").click(); // менее надёжно
            // Или $("a[href='/movies?page=1']").click(); // если href уникален

            // Ждём загрузки страницы с фильтрами
            System.out.println("⏳ Ждём появления кнопок фильтров...");
            $("[data-qa-id='movies_filter_location_select']").shouldBe(Condition.visible);

        } else {
            System.out.println("✅ Кнопки фильтров уже видны.");
        }

        // --- ШАГ 2: Применение фильтров ---
        System.out.println("⚙️ Применяем фильтры: SPB, Военный...");
        filterPage.selectLocationSPB();
        filterPage.selectGenreMilitary();
    }

    public boolean isMovie689Visible() {
        return filterPage.isMovie689Visible();
    }

    public String getMovie689Title() {
        return filterPage.getMovie689Title();
    }
}