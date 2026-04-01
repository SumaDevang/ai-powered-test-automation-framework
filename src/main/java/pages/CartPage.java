package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle              = By.className("title");
    private final By cartItems              = By.className("cart_item");
    private final By cartItemNames          = By.className("inventory_item_name");
    private final By removeButtons          = By.cssSelector("button[data-test^='remove']");
    private final By checkoutButton         = By.id("checkout");
    private final By continueShoppingButton = By.id("continue-shopping");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public boolean isCartPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("Your Cart");
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public List<WebElement> getCartItemNames() {
        return driver.findElements(cartItemNames);
    }

    public boolean isItemInCart(String itemName) {
        return getCartItemNames().stream()
            .anyMatch(item -> item.getText().equalsIgnoreCase(itemName));
    }

    public void removeFirstItem() {
        wait.until(ExpectedConditions.elementToBeClickable(removeButtons)).click();
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void clickContinueShopping() {
        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton)).click();
    }
}