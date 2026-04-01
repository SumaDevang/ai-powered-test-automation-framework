package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;

public class CheckoutTest extends BaseTest {

    private static final String BASE_URL       = "https://www.saucedemo.com";
    private static final String VALID_USER     = "standard_user";
    private static final String VALID_PASSWORD = "secret_sauce";

    @BeforeMethod
    public void loginAddToCartAndGoToCheckout() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USER, VALID_PASSWORD);

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.clickCartIcon();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckout();
    }

    @Test(description = "Verify checkout step 1 page is displayed")
    public void testCheckoutStep1IsDisplayed() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        Assert.assertEquals(checkoutPage.getPageTitle(), "Checkout: Your Information",
            "Checkout step 1 page should be displayed");
    }

    @Test(description = "Verify user can proceed with valid customer info")
    public void testValidCheckoutInfo() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCustomerInfo("Suma", "Shekar", "67202");
        checkoutPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two"),
            "User should proceed to order summary page");
    }

    @Test(description = "Verify error when first name is missing")
    public void testCheckoutWithoutFirstName() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCustomerInfo("", "Shekar", "67202");
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.isErrorDisplayed(),
            "Error should be displayed for missing first name");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("First Name is required"),
            "Error message should mention First Name");
    }

    @Test(description = "Verify error when last name is missing")
    public void testCheckoutWithoutLastName() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCustomerInfo("Suma", "", "67202");
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.isErrorDisplayed(),
            "Error should be displayed for missing last name");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Last Name is required"),
            "Error message should mention Last Name");
    }

    @Test(description = "Verify error when postal code is missing")
    public void testCheckoutWithoutPostalCode() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCustomerInfo("Suma", "Shekar", "");
        checkoutPage.clickContinue();
        Assert.assertTrue(checkoutPage.isErrorDisplayed(),
            "Error should be displayed for missing postal code");
        Assert.assertTrue(checkoutPage.getErrorMessage().contains("Postal Code is required"),
            "Error message should mention Postal Code");
    }

    @Test(description = "Verify order summary page shows total amount")
    public void testOrderSummaryDisplaysTotal() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCustomerInfo("Suma", "Shekar", "67202");
        checkoutPage.clickContinue();
        Assert.assertFalse(checkoutPage.getTotalAmount().isEmpty(),
            "Total amount should be displayed on order summary page");
    }

    @Test(description = "Verify complete order flow end to end")
    public void testCompleteOrderFlow() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCustomerInfo("Suma", "Shekar", "67202");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        Assert.assertEquals(checkoutPage.getConfirmationHeader(), "Thank you for your order!",
            "Order confirmation message should be displayed");
    }

    @Test(description = "Verify back home button after order completion")
    public void testBackHomeAfterOrder() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.fillCustomerInfo("Suma", "Shekar", "67202");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();
        checkoutPage.clickBackHome();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),
        	    "User should be back on products page after clicking Back Home");

    }
}