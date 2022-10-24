package pageObjects;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HomePage extends InitialPage{

    private static final Logger logger = LogManager.getLogger(HomePage.class);
    public HomePage(Page page) {
        super(page);
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
        return page.locator("//*[@data-testid='playlist-context-menu-create-simple']");
    }

    private Locator getPlaylistNameLocator() {

        return page.locator("//form[@class='create']//input");
    }
    private Locator getRenameInput() {

        return page.locator("//input[@data-testid='inline-playlist-name-input']");
    }

    //create playlist
    public String createPlaylist(String playlistName) {
        getPlusIconLocator().click();
        getNewPlaylistLocator().click();
        getPlaylistNameLocator().fill(playlistName);
        getPlaylistNameLocator().press("Enter");
        page.locator(".success").waitFor();
        return page.url().split("/")[5];
    }

    private Locator getPlaylistLink(String playListID) {
        return page.locator("//*[@href='#!/playlist/" + playListID + "']");
    }

    //check if playlist is present
    public boolean isPresent(String playListID, String playListName) {
            Locator playlistID = getPlaylistLink(playListID);
            return playlistID.isVisible() && playlistID.innerText().equals(playListName);
    }

    public void renamePlaylist(String playlistId, String newPlaylistName) {
        logger.info("playlist has been successfully renamed");

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
}
