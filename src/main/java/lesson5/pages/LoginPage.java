package lesson5.pages;

import lesson5.helpers.DriverDealer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(DriverDealer dealer,String browser) {
        PageFactory.initElements(dealer.createDriver(browser),this);
    }

    @FindBy(xpath = "//*[@name = \"username\"]")
    public WebElement loginField;

    @FindBy(xpath = "//*[@name = \"password\"]")
    public WebElement passwordField;

    @FindBy(xpath = "//button[@type=\"submit\"]//span[text()='Sign in']")
    public WebElement loginButton;
}
