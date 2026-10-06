package tests;

import org.testng.annotations.Test;

import pages.Cart;
import pages.Dashboard;
import pages.Login;
import pages.Orders;
import pages.TestBase;

public class RegistrationFrameworkTest extends TestBase {
    // This class can be used to write test cases for the registration framework
    @Test(groups = {"Smoke"})
    public void testMethod() {
        // Registration registrationTest = new Registration(page, BaseUrl);
        // registrationTest.navigateToRegistrationPage();
        Login loginTest = new Login(page, BaseUrl);
        loginTest.navigateToLoginPage();
        

        Dashboard dashboardTest = new Dashboard(page);
        String id = dashboardTest.ViewAndBook();

        Cart cartTest = new Cart(page);
        cartTest.viewCart(id);

        Orders ordersTest = new Orders(page);
        ordersTest.viewAllOrders();
        ordersTest.SpecificOrderView();
        ordersTest.deleteOrder();
    }

}
