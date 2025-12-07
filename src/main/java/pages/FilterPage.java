package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;
import org.openqa.selenium.By;

public class FilterPage {

    // Кнопка фильтра локации (SPB)
    private final SelenideElement locationFilterButton = $("[data-qa-id='movies_filter_location_select']").parent();

    // Карточка фильма (ID 689)
    private final SelenideElement movieCard689 = $("[data-qa-id='movie_more_689']");

    public void selectLocationSPB() {
        System.out.println("🖱️ Кликаем по кнопке фильтра локации...");
        locationFilterButton.click();

        // Ждём, пока выпадающий список появится
        System.out.println("⏳ Ждём появления выпадающего списка для локации...");
        SelenideElement listBox = $(By.xpath("//div[@role='listbox']")).shouldBe(visible);

        // Ищем опцию "SPB" *внутри* найденного списка
        System.out.println("🖱️ Ищем и кликаем по опции 'SPB' внутри списка...");
        listBox.$(By.xpath(".//div[@role='option' and contains(., 'SPB')]")).click();

        Selenide.sleep(5000); // Задержка после выбора
    }

    public void selectGenreMilitary() {
        // 🆕 Уточнённый XPath для поиска кнопки фильтра жанра
        // Ищем вторую кнопку role="combobox" внутри контейнера с фильтрами
        System.out.println("🔍 Ищем кнопку фильтра жанра (ожидаем, что это вторая кнопка в группе фильтров)...");
        SelenideElement genreFilterButton = $(By.xpath("//main[@class='py-10']//div[@class='flex h-full gap-5 items-center']//div[@class='w-36'][2]//button[@role='combobox']"));

        // Если и это не сработает, можно попробовать найти *все* кнопки combobox и взять вторую:
        // SelenideElement genreFilterButton = $$(By.xpath("//button[@role='combobox']")).get(1);

        System.out.println("🖱️ Кликаем по кнопке фильтра жанра...");
        genreFilterButton.click(); // Кнопка фильтра жанра

        // Ждём, пока выпадающий список появится
        System.out.println("⏳ Ждём появления выпадающего списка для жанра...");
        SelenideElement listBox = $(By.xpath("//div[@role='listbox']")).shouldBe(visible);

        // Ищем опцию "Военный" *внутри* найденного списка
        // В HTML 'открытый военный.txt' текст "Военный" находится внутри <span>
        System.out.println("🖱️ Ищем и кликаем по опции 'Военный' внутри списка...");
        // listBox.$(By.xpath(".//div[@role='option']//span[contains(., 'Военный')]")).click();
        // Попробуем более простой селектор, если span не обязателен:
        listBox.$(By.xpath(".//div[@role='option' and contains(., 'Военный')]")).click();

        Selenide.sleep(5000); // Задержка после выбора
    }

    public boolean isMovie689Visible() {
        return movieCard689.isDisplayed();
    }

    public String getMovie689Title() {
        return movieCard689.$("h3").text();
    }
}