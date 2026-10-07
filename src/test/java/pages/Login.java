package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public class Login {
    Page page;
    String BaseUrl;

    public static final String email = "#userEmail";
    public static final String password = "#userPassword";
    
    public Login(Page page, String BaseUrl) {
        this.page = page;
        this.BaseUrl = BaseUrl;
    }

    public void navigateToLoginPage() {
        page.navigate(BaseUrl);
        
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.HEADING, new GetByRoleOptions().setName("Log in"))).isVisible();
        page.locator(email).fill("prabhatechi123@gmail.com");
        page.locator(password).fill("P@ssword123");
        page.locator("#login").click();
        
        //Assert.assertTrue(page.getByRole(AriaRole.ALERT,new GetByRoleOptions().setName("Login Successfully")).isVisible());
    }

}
