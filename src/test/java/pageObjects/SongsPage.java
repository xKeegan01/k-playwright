package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.MouseButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class SongsPage extends InitialPage {
    public SongsPage(Page page) {
        super(page);
    }

    private Locator getRandomSong() {
        int rows = page.locator("table tr").count();
        List<Locator> locators = new ArrayList<>();
        int index = 0;
        while (locators.size() < rows - 1) {
            locators.add(page.locator("table tr").nth(index++));
            }
        int listSize = locators.size();
        Random random = new Random();
        int randomIndex = random.nextInt(listSize);
        Locator locator = locators.get(randomIndex);
        return locator;
        }


    private Locator getPlaybackBtn() {
        page.locator("//*[@class='playback']").waitFor();
        return page.locator("//*[@class='playback']");
    }

    private Locator getSoundbars() {
        page.locator("//*[@data-test='soundbars']").waitFor();
        return page.locator("//*[@data-test='soundbars']");
    }

    public void playRandomSong() {
        getRandomSong().scrollIntoViewIfNeeded();
        getRandomSong().click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
        getPlaybackBtn().click();
    }

    public boolean isPlaying() {
        return getSoundbars().isVisible();
    }


    public boolean isAllSongsPage() {
        page.locator("//h1[contains(text(), 'All Songs')]").waitFor();
        return page.locator("//h1[contains(text(), 'All Songs')]").isVisible();
    }
}
