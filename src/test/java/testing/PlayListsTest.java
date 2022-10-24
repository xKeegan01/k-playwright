package testing;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class PlayListsTest extends InitialTestData {

    @Test
    public void createNewPlaylist() {
        String randomPlaylistName = faker.aviation().aircraft();
        LoginPage loginKoel = new LoginPage(page);
        loginKoel.openURL(url);
        HomePage homePageObject = loginKoel.loginCredentials(username, pass );
        String playListID = homePageObject.createPlaylist(randomPlaylistName);
        Assert.assertTrue(homePageObject.isPresent(playListID, randomPlaylistName));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void renamePlayList() {
        String randomPlaylistName = faker.hobbit().location();
        LoginPage loginKoel = new LoginPage(page);
        loginKoel.openURL(url);
        HomePage homePageObject = loginKoel.loginCredentials(username, pass);
        String playListID = homePageObject.createPlaylist(randomPlaylistName);

        String newPlaylistName = faker.harryPotter().character();
        homePageObject.renamePlaylist(playListID, newPlaylistName);
        Assert.assertTrue(homePageObject.isPresent(playListID, newPlaylistName));
    }


}
