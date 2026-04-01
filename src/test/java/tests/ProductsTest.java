package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import utils.BaseTest;

public class ProductsTest extends BaseTest {

    private static final String BASE_URL       = "https://www.saucedemo.com";
    private static final String VALID_USER     = "standard_user";
    private static final String VALID_PASSWORD = "secret_sauce";

    @BeforeMethod
    public void loginBeforeEachTest() {
        driver.get(BASE_URL);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USER, VALID_PASSWORD);
    }

    @Test(description = "Verify Products page is displayed after login")
    public void testProductsPageIsDisplayed() {
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isProductsPageDisplayed(),
            "Products page should be displayed after login");
    }

    @Test(description = "Verify 6 products are displayed on the page")
    public void testProductCountIsCorrect() {
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.getProductCount(), 6,
            "There should be 6 products displayed");
    }

    @Test(description = "Verify cart badge updates when item is added")
    public void testAddItemToCartUpdatesBadge() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        Assert.assertEquals(productsPage.getCartBadgeCount(), "1",
            "Cart badge should show 1 after adding an item");
    }

    @Test(description = "Verify cart badge updates when multiple items added")
    public void testAddMultipleItemsToCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addProductToCartByIndex(0);
        productsPage.addProductToCartByIndex(1);
        productsPage.addProductToCartByIndex(2);
        Assert.assertEquals(productsPage.getCartBadgeCount(), "3",
            "Cart badge should show 3 after adding 3 items");
    }

    @Test(description = "Verify user can navigate to cart page")
    public void testNavigateToCart() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addFirstProductToCart();
        productsPage.clickCartIcon();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"),
            "User should be navigated to cart page");
    }

    @Test(description = "Verify logout navigates back to login page")
    public void testLogout() {
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.clickLogout();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),
        	    "User should be redirected to login page after logout");

    }
}