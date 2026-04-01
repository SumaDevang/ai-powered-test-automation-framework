package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle          = By.className("title");
    private final By firstNameField     = By.id("first-name");
    private final By lastNameField      = By.id("last-name");
    private final By postalCodeField    = By.id("postal-code");
    private final By continueButton     = By.id("continue");
    private final By cancelButton       = By.id("cancel");
    private final By errorMessage       = By.cssSelector("[data-test='error']");
    private final By finishButton       = By.id("finish");
    private final By totalAmount        = By.cssSelector(".summary_total_label");
    private final By confirmationHeader = By.className("complete-header");
    private final By backHomeButton     = By.id("back-to-products");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public void enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).clear();
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode) {
        driver.findElement(postalCodeField).clear();
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void fillCustomerInfo(String firstName, String lastName, String postalCode) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void clickCancel() {
        wait.until(ExpectedConditions.elementToBeClickable(cancelButton)).click();
    }

    public void clickFinish() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public boolean isErrorDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    public String getTotalAmount() {
        try {
            Thread.sleep(1000); // wait for page to load
            return driver.findElement(By.cssSelector(".summary_total_label")).getText();
        } catch (Exception e) {
            return "";
        }
    }

    public String getConfirmationHeader() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(confirmationHeader)).getText();
    }

    public void clickBackHome() {
        wait.until(ExpectedConditions.elementToBeClickable(backHomeButton)).click();
    }
}