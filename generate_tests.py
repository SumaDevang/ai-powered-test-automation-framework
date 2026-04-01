def generate_test_case(user_story):
    return f"""
// AI GENERATED TEST CASE

package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class GeneratedLoginTest extends BaseTest {{

    @Test
    public void testLogin() {{
        driver.get("https://www.saucedemo.com");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }}
}}
"""

if __name__ == "__main__":
    story = "As a user, I want to login with valid credentials"
    result = generate_test_case(story)

    with open("GeneratedLoginTest.java", "w") as f:
        f.write(result)

    print("✅ AI test generated and saved successfully!")