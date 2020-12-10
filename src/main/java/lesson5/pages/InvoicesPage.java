package lesson5.pages;

import lesson5.helpers.DriverDealer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvoicesPage {
    public InvoicesPage(DriverDealer dealer,String browser) {
        PageFactory.initElements(dealer.createDriver(browser),this);
    }

    @FindBy(xpath = "//*[@name = 'date_gte']")
    public WebElement dateFrom;

    @FindBy(xpath = "//*[@name = 'date_lte']")
    public WebElement dateTo;

    @FindBy(xpath = "//tr[1]/td[1]//div[@aria-label= 'Expand']")
    public WebElement firstExpand;

    @FindBy(xpath = "//div[@class ='MuiGrid-root MuiGrid-container MuiGrid-item MuiGrid-align-content-xs-flex-end MuiGrid-grid-xs-12']//p[text()]")
    public WebElement customerInfoOnInvoice;
}
