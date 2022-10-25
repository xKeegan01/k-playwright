package testing;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class LoginPageTest extends InitialTestData {

    @Test
    public void loginValidCredentials() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.openURL(url);
        HomePage homePageObject = loginPage.loginCredentials(username, pass);
        Assert.assertTrue(homePageObject.isHomePage());
    }

    @Test
    public void loginInvalidCredentials() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.openURL(url);
        loginPage.loginCredentials("wrongEmail@email.com", "wrongPassword");
        Assert.assertTrue(loginPage.isError());
    }

    @Test
    public void logOut() {
        LoginPage loginPage = new LoginPage(page);
        loginPage.openURL(url);
        HomePage homePageObject = loginPage.loginCredentials(username, pass);
        LoginPage logOut = homePageObject.logOut();
        Assert.assertTrue(logOut.isLoggedOut());
    }
}
