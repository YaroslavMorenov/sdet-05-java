package lesson5;

import lesson5.helpers.AppProperties;
import lesson5.helpers.DriverDealer;
import lesson5.helpers.PageActions;
import lesson5.pages.*;
import org.junit.*;
import org.openqa.selenium.Keys;

public class TestWithChrome {
    public static LoginPage loginPage;
    public static MainPage mainPage;
    public static OrdersPage ordersPage;
    public static CustomersPage customersPage;
    public static InvoicesPage invoicesPage;

    @Before
    public void setup() {
        loginPage = new LoginPage(DriverDealer.getInstance());
        mainPage = new MainPage(DriverDealer.getInstance());
        ordersPage = new OrdersPage(DriverDealer.getInstance());
        customersPage = new CustomersPage(DriverDealer.getInstance());
        invoicesPage = new InvoicesPage(DriverDealer.getInstance());
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
        PageActions.click(mainPage.orders);
        PageActions.click(ordersPage.checkBox1);
        PageActions.click(ordersPage.checkBox2);
        PageActions.click(ordersPage.checkBox3);
        Assert.assertEquals("3 items selected",PageActions.getText(ordersPage.textItems));
    }

    @Test
    public void invoiceTest() {
        PageActions.click(mainPage.invoices);
        PageActions.input(invoicesPage.dateFrom,"19.10.2020");
        PageActions.input(invoicesPage.dateTo,"29.10.2020");
        PageActions.waitForElement(invoicesPage.firstExpand);
        PageActions.click(invoicesPage.firstExpand);
        Assert.assertNotEquals("",PageActions.getText(invoicesPage.customerInfoOnInvoice));
    }

    @Test
    public void customerTest() {
        String text = "volga22";
        PageActions.click(mainPage.customers);
        PageActions.click(customersPage.customerInfo);
        PageActions.clear(customersPage.address);
        PageActions.input(customersPage.address,text);
        PageActions.click(customersPage.buttonSave);
        PageActions.movePage(Keys.PAGE_UP);
        PageActions.click(customersPage.customerInfo);
        Assert.assertEquals(text,PageActions.getText(customersPage.addressText));
    }
}
