package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.GetByRoleOptions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public class Login {
    Page page;
    String BaseUrl;

    public static final String EMAIL = "#userEmail";
    public static final String PASSWORD = "#userPassword";
    
    public Login(Page page, String BaseUrl) {
        this.page = page;
        this.BaseUrl = BaseUrl;
    }

    public void navigateToLoginPage() {
        page.navigate(BaseUrl);
        
        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.HEADING, new GetByRoleOptions().setName("Log in"))).isVisible();
        page.locator(EMAIL).fill("prabhatechi123@gmail.com");
        page.locator(PASSWORD).fill("P@ssword123");
        page.locator("#login").click();
        
        //Assert.assertTrue(page.getByRole(AriaRole.ALERT,new GetByRoleOptions().setName("Login Successfully")).isVisible());
    }

}
