package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.MouseButton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HomePage extends InitialPage{

    private static final Logger logger = LogManager.getLogger(HomePage.class);
    public HomePage(Page page) {
        super(page);
    }

    private Locator logOutBtn() {
        return page.locator("//*[@data-testid='btn-logout']");
    }

    //check if logout btn is displayed
    public boolean isHomePage() {
        page.locator("//*[@data-testid='btn-logout']").waitFor();
        return page.locator("//*[@data-testid='btn-logout']").isVisible();
    }

    //playlist elements
    private Locator getPlusIconLocator() {
        return page.locator("//*[@id='playlists']//i");
    }

    private Locator getNewPlaylistLocator() {
        return page.locator("//*[contains(text(), 'New Playlist')]");
    }

    private Locator getPlaylistNameLocator() {
        return page.locator("//form[@class='create']//input");
    }
    private Locator getRenameInput() {
        return page.locator("//input[@data-testid='inline-playlist-name-input']");
    }

    //delete playlist elements
    private Locator getDeleteBtn() {
        page.locator("//*[contains(text(), 'Delete')]").waitFor();
        return page.locator("//*[contains(text(), 'Delete')]");
    }

    //create playlist
    public String createPlaylist(String playlistName) {
        logger.info("playlist has been created");

        getPlusIconLocator().click();
        getNewPlaylistLocator().click();
        getPlaylistNameLocator().fill(playlistName);
        getPlaylistNameLocator().press("Enter");
        page.locator(".success").waitFor();
        return page.url().split("/")[5];
    }

    public void createEmptyPlaylist(String name) {
        getPlusIconLocator().click();
        getNewPlaylistLocator().click();
        getPlaylistNameLocator().fill(name);
        getPlaylistNameLocator().press("Enter");
    }

    private Locator getPlaylistLink(String playListID) {
        return page.locator("//*[@href='#!/playlist/" + playListID + "']");
    }

    public void renamePlaylist(String playlistId, String newPlaylistName) {
        logger.info("playlist has been renamed");

        Locator element = getPlaylistLink(playlistId);
        element.scrollIntoViewIfNeeded();
        element.dblclick();
        Locator renameInput = getRenameInput();
        renameInput.press("Control+A");
        renameInput.fill(newPlaylistName);
        renameInput.press("Enter");
        // Wait for the second green banner
        page.locator("//*[contains(text(), 'Updated playlist')]").waitFor();
    }

    public void deletePlaylist(String playlistID) {
        logger.info("playlist has been deleted");

        Locator elem = getPlaylistLink(playlistID);
        elem.scrollIntoViewIfNeeded();
        elem.click(new Locator.ClickOptions().setButton(MouseButton.RIGHT));
        getDeleteBtn().click();
        page.locator("//*[contains(text(), 'Deleted playlist')]").waitFor();
    }

    //check if playlist is present
    public boolean isPresent(String playListID, String playListName) {
        Locator playlistID = getPlaylistLink(playListID);
        return playlistID.isVisible() && playlistID.innerText().equals(playListName);
    }

    //check if playlist is not created
    public boolean isNotCreated() {
        getPlaylistNameLocator().waitFor();
        return getPlaylistNameLocator().isVisible();
    }

    public LoginPage logOut() {
        logOutBtn().click();
        return new LoginPage(page);
    }
}
