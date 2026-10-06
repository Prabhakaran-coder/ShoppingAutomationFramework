package pages;

import java.util.HashMap;

import org.testng.Assert;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.microsoft.playwright.options.AriaRole;

public class Registration{
    Page page;
    String BaseUrl;
    private static final String firstName = "First Name";
    private static final String lastName = "Last Name";
    private static final String email = "email@example.com";
    private static final String password = "#userPassword";
    private static final String confirmPassword = "Confirm Password";
    private static final String phone = "enter your number";
    private static final String occupation = ".custom-select";
    // private static final String gender = "gender";

    HashMap<String, String> userData = new HashMap<>();
    
    
    public Registration(Page page, String BaseUrl) {
        this.page = page;
        this.BaseUrl = BaseUrl;
        dataSetup();
    }

    
    public final  void dataSetup() {
        userData.put("firstName", "prabha");
        userData.put("lastName", "mani");
        userData.put("email", "prabhatechi123@gmail.com");
        userData.put("password", "P@ssword123");
        userData.put("confirmPassword", "P@ssword123");
        userData.put("phone", "9090909090");
        userData.put("occupation", "Engineer");
        userData.put("gender", "Male");
    }

    public void navigateToRegistrationPage() {
        page.navigate(BaseUrl);
        page.waitForTimeout(3000);
        page.locator(".text-reset", new Page.LocatorOptions().setHasText("Register here")).click();
         page.waitForTimeout(3000);
        Assert.assertTrue(page.locator(".login-title", new Page.LocatorOptions().setHasText("Register")).isVisible());
        page.getByLabel(firstName).fill(userData.get("firstName"));
        page.getByLabel(lastName).fill(userData.get("lastName"));
        page.getByPlaceholder(email).fill(userData.get("email"));
        page.getByPlaceholder(phone).fill(userData.get("phone"));
        page.locator(occupation).selectOption(userData.get("occupation"));
       
        page.locator("input[type='radio'][value='" + userData.get("gender") + "']").check();
        page.locator(password).fill(userData.get("password"));
        page.getByLabel(confirmPassword).fill(userData.get("confirmPassword"));
        page.getByRole(AriaRole.CHECKBOX).check();
        
        page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Register")).click();
        page.waitForTimeout(3000);
        Assert.assertTrue(page.getByRole(AriaRole.HEADING, new GetByRoleOptions().setName("Account Created Successfully")).isVisible());
        //page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Login")).click();
        page.getByRole(AriaRole.BUTTON, new GetByRoleOptions().setName("Login")).click();
        page.waitForTimeout(5000);
        Assert.assertTrue(page.getByRole(AriaRole.HEADING, new GetByRoleOptions().setName("Log in")).isVisible());

    }
}
