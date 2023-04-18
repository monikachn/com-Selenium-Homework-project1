package testsuite;

import browserfactory.BaseTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;

import java.util.List;

public class LoginTest extends BaseTest {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }
    @Test
    public void verifyUserShouldNavigateToLoginPageSuccessfully(){
        // find login link and click on login LINK
        WebElement loginLink =driver.findElement(By.linkText("Log in"));
        loginLink.click();
        String expectedMessage = "Welcome, Please Sign In!";

        WebElement actualTextElement = driver.findElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals("Redirected to login page", expectedMessage, actualMessage);

    }
    @Test
    public void verifyUserShouldLoginSuccessfullyWithValidCredentials(){
        //Find login link and click on login link
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();

        //Enter username
        WebElement emailField = driver.findElement(By.id("Email"));

        // Enter Password
        emailField.sendKeys("mariasmith@gmail.com");
        //Find the Password Field Element and send password on password field
        driver.findElement(By.name("Password")).sendKeys("654321");
        //Find the Login btn Element and click
        WebElement loginBtn = driver.findElement(By.xpath("//button[contains(text(),'Log in')]"));
        loginBtn.click();
    }
    @Test
    public void verifyTheErrorMessage(){
        WebElement loginLink = driver.findElement(By.linkText("Log in"));
        loginLink.click();

        //Find the Email Field Element
        WebElement emailField = driver.findElement(By.id("Email"));

        // Type the Email address to email field element
        emailField.sendKeys("mariasmith@gmail.com");

        //Find the Password Field Element and send password on password field
        driver.findElement(By.name("Password")).sendKeys("65432");
        driver.findElement((By.xpath("//button[text()='Log in']"))).click();
        String expectedMessage = "Login was unsuccessful. Please correct the errors and try again.\n" +
                "The credentials provided are incorrect";
        WebElement actualTextElement = driver.findElement(By.xpath("//div[@class='message-error validation-summary-errors']"));
        String actualMessage = actualTextElement.getText();
        Assert.assertEquals("Logout not valid", expectedMessage,actualMessage);

    }
    public void closeBrowser(){
        driver.quit();
    }


}