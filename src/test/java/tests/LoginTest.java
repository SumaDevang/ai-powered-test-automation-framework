package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {

    private final String BASE_URL = "https://www.saucedemo.com";

    @Test
    public void testValidLogin() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "User should land on inventory page");
    }

    @Test
    public void testInvalidUsernameAndPassword() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrong_user", "wrong_password");
        Assert.assertTrue(driver.getPageSource().contains("Username and password do not match"),
                "Error message should be displayed");
    }

    @Test
    public void testValidUsernameInvalidPassword() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "wrong_password");
        Assert.assertTrue(driver.getPageSource().contains("Username and password do not match"),
                "Error message should be displayed");
    }

    @Test
    public void testInvalidUsernameValidPassword() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("wrong_user", "secret_sauce");
        Assert.assertTrue(driver.getPageSource().contains("Username and password do not match"),
                "Error message should be displayed");
    }

    @Test
    public void testEmptyUsername() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "secret_sauce");
        Assert.assertTrue(driver.getPageSource().contains("Username is required"),
                "Username required error should be displayed");
    }

    @Test
    public void testEmptyPassword() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "");
        Assert.assertTrue(driver.getPageSource().contains("Password is required"),
                "Password required error should be displayed");
    }

    @Test
    public void testEmptyCredentials() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");
        Assert.assertTrue(driver.getPageSource().contains("Username is required"),
                "Error should be displayed for empty fields");
    }

    @Test
    public void testLockedOutUserLogin() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(driver.getPageSource().contains("locked out"),
                "Locked out error should be displayed");
    }

    @Test
    public void testUsernameWithSpaces() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(" standard_user ", "secret_sauce");
        Assert.assertTrue(driver.getPageSource().contains("Username and password do not match"),
                "Login should fail due to spaces");
    }

    @Test
    public void testPasswordWithSpaces() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", " secret_sauce ");
        Assert.assertTrue(driver.getPageSource().contains("Username and password do not match"),
                "Login should fail due to spaces");
    }
}