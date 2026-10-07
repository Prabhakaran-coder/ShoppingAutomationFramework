package pages;

import org.testng.Assert;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public class Cart {

    Page page;
    public final String CART_FIELD="Cart";
    public final String ITEMID_FIELD=".itemNumber";
    public final String CART_SECTION=".removeWrap .btn-primary";
    public static final String SELECTCOUNTRY_FIELD="Select Country";
    public static final String COUNTRY_VALUE="India";
    public static final String PLACEORDER_FIELD=".btnn.action__submit.ng-star-inserted";
    public static final String PLACEORDER_BUTTON="Place Order";
    public static final String ORDERID_FIELD="label.ng-star-inserted";
    public static String OrderId;

    public Cart(Page page) {
        this.page = page;
    }

    public void viewCart(String ItemId) {

        System.out.println("CART PAGE: ");
        
        page.locator(".btn-custom", new Page.LocatorOptions().setHasText(CART_FIELD)).click();
        
        PlaywrightAssertions.assertThat(page.getByText("My Cart")).isVisible();
        
        Locator itemIdLocator = page.locator(this.ITEMID_FIELD, new Page.LocatorOptions().setHasText(ItemId));
        PlaywrightAssertions.assertThat(itemIdLocator).isVisible();
        
        int noOfItemsInCart = page.locator(this.CART_SECTION).count();
        for(int i = 0; i < noOfItemsInCart; i++) {
            String itemIdInCart = page.locator(this.ITEMID_FIELD).nth(i).textContent().substring(1);
            if (itemIdInCart.equals(ItemId)) {
                Assert.assertTrue(page.locator(this.ITEMID_FIELD, new Page.LocatorOptions().setHasText(ItemId)).isVisible());
               page.locator(CART_SECTION).nth(i).click();
            }
        }
        //page.waitForTimeout(5000);
        //page.locator(this.ITEMID_FIELD, new Page.LocatorOptions().setHasText(ItemId)).filter( new Locator.FilterOptions().setHas(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Buy Now")))).click();
        PlaywrightAssertions.assertThat(page.getByText("Payment Method")).isVisible();
        page.getByPlaceholder(SELECTCOUNTRY_FIELD).press("I");
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(COUNTRY_VALUE)).nth(1).click();
        //page.getByPlaceholder("Select Country").evaluate("element => element.scrollTop+=100");
        //page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Place Order ")).click();
        page.locator(PLACEORDER_FIELD, new Page.LocatorOptions().setHasText(PLACEORDER_BUTTON)).click();
        PlaywrightAssertions.assertThat(page.getByText("Order Placed Successfully")).isVisible();
        System.out.println(page.locator(ORDERID_FIELD).textContent());
        OrderId = page.locator(ORDERID_FIELD).textContent().substring(2,26);
        System.out.println("Order Placed Successfully");
    }

}
