package steps;

import pages.MoviesPage;

import pages.FilterPage;

public class FilterSteps {
    private final FilterPage filterPage = new FilterPage();

    public void applyFilters() {
        filterPage.selectLocationSPB();      // Выбираем SPB
        filterPage.selectGenreMilitary();    // Выбираем "Военный"
    }

    public boolean isMovie689Visible() {
        return filterPage.isMovie689Visible();
    }

    public String getMovie689Title() {
        return filterPage.getMovie689Title();
    }
}