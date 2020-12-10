package lesson5.pages;

import lesson5.helpers.DriverDealer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomersPage {

    public CustomersPage(DriverDealer dealer,String browser) {
        PageFactory.initElements(dealer.createDriver(browser),this);
    }

    @FindBy(xpath = "//tr[1][@resource=\"customers\"]")
    public WebElement customerInfo;

    @FindBy(xpath = "//*[@id=\"address\"]")
    public WebElement address;

    @FindBy(xpath = "//*[@type=\"submit\"]")
    public WebElement buttonSave;

    @FindBy(xpath = "//textarea[text()]")
    public WebElement addressText;
}
