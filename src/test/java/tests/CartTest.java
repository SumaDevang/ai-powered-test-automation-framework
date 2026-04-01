package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;

public class CartTest extends BaseTest {

    private static final String BASE_URL       = "https://www.saucedemo.com";
    private static final String VALID_USER     = "standard_user";
    private static final String VALID_PASSWORD = "secret_sauce";

    @BeforeMethod
    public void loginAndAddToCart() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USER, VALID_PASSWORD);

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.clickCartIcon();
    }

    @Test(description = "Verify cart page is displayed")
    public void testCartPageIsDisplayed() {
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isCartPageDisplayed(),
            "Cart page should be displayed");
    }

    @Test(description = "Verify added item appears in cart")
    public void testItemAppearsInCart() {
        CartPage cartPage = new CartPage(driver);
        Assert.assertEquals(cartPage.getCartItemCount(), 1,
            "Cart should contain 1 item");
    }

    @Test(description = "Verify item can be removed from cart")
    public void testRemoveItemFromCart() {
        CartPage cartPage = new CartPage(driver);
        cartPage.removeFirstItem();
        Assert.assertEquals(cartPage.getCartItemCount(), 0,
            "Cart should be empty after removing item");
    }

    @Test(description = "Verify continue shopping navigates back to products")
    public void testContinueShopping() {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickContinueShopping();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
            "User should be back on products page");
    }

    @Test(description = "Verify checkout button navigates to checkout page")
    public void testProceedToCheckout() {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"),
            "User should be on checkout step 1 page");
    }
}