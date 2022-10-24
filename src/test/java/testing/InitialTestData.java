package testing;


import com.github.javafaker.Faker;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class InitialTestData {

    protected Page page;
    protected Browser browser;
    protected String url;
    protected String username;
    protected String pass;

    protected Faker faker;
    @Parameters({"username", "pass", "url"})
    @BeforeMethod
    public void initialData(String username, String pass, String url) {
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(true));

        page = browser.newPage();

        this.username = username;
        this.pass = pass;
        this.url = url;
        faker = new Faker();
    }

    @AfterMethod
    public void finalStep(ITestResult iTestResult) {
        page.close();
        browser.close();
    }
}
