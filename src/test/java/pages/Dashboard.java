package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public final class Dashboard {

    Page page;

    public static final String SEARCH_FIELD = "search";
    public static final String CARD_FIELD = ".card";
    public static final String VIEW_BUTTON_FIELD = "View";
    public static final String ADD_TO_CARD_FIELD = "Add to Cart";

    int maxPrice;
    int minPrice;
    
    String randomCardHeading;

    public Dashboard(Page page) {
        this.page = page;
        Filter();
    }

    public void Filter() {

        System.out.println("DASHBOARD PAGE: ");
        page.locator(".btn.btn-custom").getByText("Home").waitFor();
        int noOfCards = page.locator(CARD_FIELD).count();
        
        int randomNumber = (int) (Math.random() * (noOfCards));
        System.out.println(randomNumber);
        randomCardHeading = page.locator(".card").getByRole(AriaRole.HEADING).nth(randomNumber).textContent();
        page.locator(".card .text-muted").first().waitFor();
        this.maxPrice = Integer.parseInt(page.locator(".card .text-muted").nth(randomNumber).textContent().substring(2, 7));
        this.minPrice = (int) (Math.random() * this.maxPrice);
       
    }

   
    public String ViewAndBook() {
        page.getByPlaceholder(SEARCH_FIELD).nth(1).fill(randomCardHeading);
        System.out.println("Random card heading is: " + randomCardHeading);
        page.getByPlaceholder("Min Price").nth(1).fill(String.valueOf(minPrice));
        page.getByPlaceholder("Max Price").nth(1).fill(String.valueOf(maxPrice));
        page.keyboard().press("Enter");
        page.waitForTimeout(2000);
        page.locator(CARD_FIELD).first().waitFor();
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(VIEW_BUTTON_FIELD)).click();
        
        String ID = page.url().substring(page.url().indexOf("#"));
        ID=ID.substring(28, ID.length());
        System.out.println("ID is: " + ID);

        Locator randomCard = page.locator(".col-lg-6.rtl-text")
        .filter(new Locator.FilterOptions().setHasText(randomCardHeading));
        randomCard.getByRole(AriaRole.BUTTON, new Locator.GetByRoleOptions().setName(ADD_TO_CARD_FIELD)).click();
        PlaywrightAssertions.assertThat(page.getByText("Product Added To Cart")).isVisible();
        System.out.println("ID added to cart:"+ID);
        return ID;
    }
}
