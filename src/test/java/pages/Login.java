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
    String username = System.getenv("APP_USERNAME");
    String password = System.getenv("APP_PASSWORD");
    
    public Login(Page page, String BaseUrl) {
        this.page = page;
        this.BaseUrl = BaseUrl;
    }


    public void navigateToLoginPage() {
        page.navigate(BaseUrl);
        
        if (username == null || username.isBlank()) {
        throw new IllegalStateException("APP_USERNAME is missing");
            }

            if (password == null || password.isBlank()) {
                throw new IllegalStateException("APP_PASSWORD is missing");
            }

        PlaywrightAssertions.assertThat(page.getByRole(AriaRole.HEADING, new GetByRoleOptions().setName("Log in"))).isVisible();
        page.locator(EMAIL).fill(username);
        page.locator(PASSWORD).fill(password);
        page.locator("#login").click();
        
        //Assert.assertTrue(page.getByRole(AriaRole.ALERT,new GetByRoleOptions().setName("Login Successfully")).isVisible());
    }

}
