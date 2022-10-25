package testing;

import helpers.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class PlayListsTest extends InitialTestData {

    @Test
    public void createNewPlaylist() {
        String randomPlaylistName = faker.aviation().aircraft();
        LoginPage login = new LoginPage(page);
        login.openURL(url);
        HomePage homePageObject = login.loginCredentials(username, pass );
        String playListID = homePageObject.createPlaylist(randomPlaylistName);
        Assert.assertTrue(homePageObject.isPresent(playListID, randomPlaylistName));
    }

    @Test
    public void createEmptyPlaylist() {
        LoginPage login = new LoginPage(page);
        login.openURL(url);
        HomePage homePageObject = login.loginCredentials(username, pass );
        homePageObject.createEmptyPlaylist("");
        Assert.assertTrue(homePageObject.isNotCreated());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void renamePlayList() {
        String randomPlaylistName = faker.hobbit().location();
        LoginPage login = new LoginPage(page);
        login.openURL(url);
        HomePage homePageObject = login.loginCredentials(username, pass);
        String playListID = homePageObject.createPlaylist(randomPlaylistName);

        String newPlaylistName = faker.harryPotter().character();
        homePageObject.renamePlaylist(playListID, newPlaylistName);
        Assert.assertTrue(homePageObject.isPresent(playListID, newPlaylistName));
    }

    @Test
    public void deletePlaylist() {
        String randomPlaylistName = faker.hobbit().location();
        LoginPage login = new LoginPage(page);
        login.openURL(url);
        HomePage homePageObject = login.loginCredentials(username, pass);
        String playListID = homePageObject.createPlaylist(randomPlaylistName);

        homePageObject.deletePlaylist(playListID);
        Assert.assertFalse(homePageObject.isPresent(playListID, randomPlaylistName));
    }


}
