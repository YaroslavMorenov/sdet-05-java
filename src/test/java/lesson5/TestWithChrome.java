package lesson5;

import lesson5.helpers.AppProperties;
import lesson5.helpers.DriverDealer;
import lesson5.helpers.PageActions;
import lesson5.pages.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Keys;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestWithChrome {
    public static LoginPage loginPage;
    public static MainPage mainPage;
    public static OrdersPage ordersPage;
    public static CustomersPage customersPage;
    public static InvoicesPage invoicesPage;
    private String browser;

    public TestWithChrome(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Collection browsers() {
        return Arrays.asList(new Object[]{
                "Firefox",
                "Chrome"
        });
    }

    public void setup(String browser) {
        loginPage = new LoginPage(DriverDealer.getInstance(),browser);
        mainPage = new MainPage(DriverDealer.getInstance(),browser);
        ordersPage = new OrdersPage(DriverDealer.getInstance(),browser);
        customersPage = new CustomersPage(DriverDealer.getInstance(),browser);
        invoicesPage = new InvoicesPage(DriverDealer.getInstance(),browser);
        PageActions.input(loginPage.loginField,AppProperties.getProperty("login"));
        PageActions.input(loginPage.passwordField,AppProperties.getProperty("password"));
        PageActions.click(loginPage.loginButton);
    }

    @After
    public void logOut() {
        PageActions.click(mainPage.account);
        PageActions.click(mainPage.logOut);
    }

    @AfterClass
    public static void tearDown() {
        DriverDealer.getInstance().closeDriver();
    }


    @Test
    public void orderTest() {
        setup(browser);
        PageActions.click(mainPage.orders);
        PageActions.click(ordersPage.checkBox1);
        PageActions.click(ordersPage.checkBox2);
        PageActions.click(ordersPage.checkBox3);
        Assert.assertEquals("3 items selected",PageActions.getText(ordersPage.textItems));
    }


    @Test
    public void invoiceTest() {
        setup(browser);
        PageActions.click(mainPage.invoices);
        PageActions.input(invoicesPage.dateFrom,"19.10.2020");
        PageActions.input(invoicesPage.dateTo,"29.10.2020");
        PageActions.waitForElement(invoicesPage.firstExpand,browser);
        PageActions.click(invoicesPage.firstExpand);
        Assert.assertNotEquals("",PageActions.getText(invoicesPage.customerInfoOnInvoice));
    }

    @Test
    public void customerTest() {
        setup(browser);
        String text = "volga22";
        PageActions.click(mainPage.customers);
        PageActions.click(customersPage.customerInfo);
        PageActions.clear(customersPage.address);
        PageActions.input(customersPage.address,text);
        PageActions.click(customersPage.buttonSave);
        PageActions.movePage(Keys.PAGE_UP,browser);
        PageActions.click(customersPage.customerInfo);
        Assert.assertEquals(text,PageActions.getText(customersPage.addressText));
    }
}
