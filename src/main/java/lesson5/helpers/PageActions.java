package lesson5.helpers;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageActions {
    private static final DriverDealer dealer = DriverDealer.getInstance();
    private static long EXPLICIT_TIMEOUT = Long.parseLong(AppProperties.getProperty("timeout"));

    public static void click(WebElement element) {
        element.click();
    }

    public static void input(WebElement element,String text) {
        element.sendKeys(text);
    }

    public static void movePage(Keys key,String browser) {
        Actions action = new Actions(dealer.createDriver(browser));
        action.sendKeys(key).build().perform();
    }

    public static String getText(WebElement element) {
        return element.getText();
    }

    public static void clear(WebElement element) {
        while(!element.getAttribute("value").equals("")) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public static void waitForElement(WebElement element,String browser) {
        new WebDriverWait(dealer.createDriver(browser),EXPLICIT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(element));

    }
}
