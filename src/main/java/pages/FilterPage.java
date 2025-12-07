// src/main/java/pages/FilterPage.java
package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;
import org.openqa.selenium.By;

public class FilterPage {

    // --- ИСПОЛЬЗУЕМ РАБОЧИЙ СЕЛЕКТОР ИЗ КОДА №2 ---
    // Кнопка фильтра локации (SPB)
    private final SelenideElement locationFilterButton = $("[data-qa-id='movies_filter_location_select']").parent();
    // --- ИСПРАВЛЕНО: Кнопка фильтра жанра ---
    private final SelenideElement genreFilterButton = $$(".w-36 button[role='combobox']").get(1);

    // Карточка фильма (ID 689)
    private final SelenideElement movieCard689 = $("[data-qa-id='movie_more_689']");

    public void selectLocationSPB() {
        System.out.println("🖱️ Кликаем по кнопке фильтра локации...");
        // Убираем проверку shouldBe(visible) из PageObject, так как FilterSteps уже обеспечил её
        locationFilterButton.click();

        // --- ИСПОЛЬЗУЕМ РАБОЧУЮ ЛОГИКУ ИЗ КОДА №1 ---
        System.out.println("⏳ Ждём появления списка опций для локации...");
        SelenideElement listBox = $(By.xpath("//div[@role='listbox']")).shouldBe(visible);

        // Ищем опцию "SPB" *внутри* найденного списка
        System.out.println("🔍 Ищем и кликаем по опции 'SPB' внутри списка...");

        SelenideElement spbOption = listBox.$(By.xpath(".//div[@role='option']//span[text()='SPB']"));
        spbOption.shouldBe(visible).click(); // Явно ждём видимости и кликаем

        Selenide.sleep(5000); // Задержка после выбора
    }

    public void selectGenreMilitary() {
        System.out.println("🖱️ Кликаем по кнопке фильтра жанра...");

        // --- ИСПОЛЬЗУЕМ ИСПРАВЛЕННЫЙ СЕЛЕКТОР КНОПКИ ---
        genreFilterButton.shouldBe(visible);
        genreFilterButton.click();

        // --- ИСПОЛЬЗУЕМ РАБОЧУЮ ЛОГИКУ ИЗ КОДА №1 ---
        System.out.println("⏳ Ждём появления списка опций для жанра...");
        SelenideElement listBox = $(By.xpath("//div[@role='listbox']")).shouldBe(visible);

        // Ищем опцию "Военный" *внутри* найденного списка
        System.out.println("🔍 Ищем и кликаем по опции 'Военный' внутри списка...");

        SelenideElement militaryOption = listBox.$(By.xpath(".//div[@role='option']//span[text()='Военный']"));
        militaryOption.shouldBe(visible).click(); // Явно ждём видимости и кликаем

        Selenide.sleep(5000); // Задержка после выбора
    }

    public boolean isMovie689Visible() {
        return movieCard689.isDisplayed();
    }

    public String getMovie689Title() {
        return movieCard689.$("h3").text();
    }
}