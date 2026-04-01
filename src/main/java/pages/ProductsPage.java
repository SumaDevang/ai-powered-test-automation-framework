package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle        = By.className("title");
    private final By productItems     = By.className("inventory_item");
    private final By addToCartButtons = By.cssSelector("button[data-test^='add-to-cart']");
    private final By cartIcon         = By.className("shopping_cart_link");
    private final By cartBadge        = By.className("shopping_cart_badge");
    private final By menuButton       = By.id("react-burger-menu-btn");
    private final By logoutLink       = By.id("logout_sidebar_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return wait.until(
            ExpectedConditions.visibilityOfElementLocated(pageTitle)).getText();
    }

    public boolean isProductsPageDisplayed() {
        return getPageTitle().equalsIgnoreCase("Products");
    }

    public int getProductCount() {
        return driver.findElements(productItems).size();
    }

    public void addFirstProductToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButtons)).click();
    }

    public void addProductToCartByIndex(int index) {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (index < buttons.size()) {
            buttons.get(index).click();
        }
    }

    public String getCartBadgeCount() {
        try {
            return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)).getText();
        } catch (Exception e) {
            return "0";
        }
    }

    public void clickCartIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
    }

    public void clickMenuButton() {
        wait.until(ExpectedConditions.elementToBeClickable(menuButton)).click();
    }

    public void clickLogout() {
        clickMenuButton();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}