package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends InitialPage {


    public LoginPage(Page page) {

        super(page);
    }

    private Locator getEmailLocator() {

        return page.locator("//*[@type='email']");
    }

    private Locator getPasswordLocator() {

        return page.locator("//*[@type='password']");
    }

    private Locator getLoginButton() {

        return page.locator("//*[text()='Log In']");
    }

    public void openURL(String url) {

        page.navigate(url);
    }

    //log into account
    public HomePage loginCredentials(String email, String pass) {
        getEmailLocator().fill(email);
        getPasswordLocator().fill(pass);
        getLoginButton().click();
        return new HomePage(page);
    }

    public boolean isError() {
        page.locator(".error").waitFor();
        return page.locator(".error").isVisible();
    }
}
