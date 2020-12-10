package lesson5.pages;

import lesson5.helpers.DriverDealer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    public MainPage(DriverDealer dealer,String browser) {
        PageFactory.initElements(dealer.createDriver(browser),this);
    }

    @FindBy(xpath = "//*[@class = \"MuiButton-label\"]")
    public WebElement account;

    @FindBy(xpath = "//ul[@role = \"menu\"]//li[text()='Logout']")
    public WebElement logOut;

    @FindBy(xpath = "//a[text()='Orders']")
    public WebElement orders;

    @FindBy(xpath = "//a[text()='Invoices']")
    public WebElement invoices;

    @FindBy(xpath = "//a[text()='Customers']")
    public WebElement customers;
}
