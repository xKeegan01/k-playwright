package pageObjects;

import com.microsoft.playwright.Page;

public class InitialPage {
    protected Page page;

    public InitialPage(Page page) {
        this.page = page;
    }
}
