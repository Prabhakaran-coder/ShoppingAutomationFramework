package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;

public final class Orders {

    Page page;
    public static final String ORDERS_BUTTON_FIELD="ORDERS";
    public static final String TABLE_BODY_ROW_FIELD="tbody tr";
    public static final String DELETE_BUTTON_FIELD=".btn.btn-danger";
    public static final String VIEW_BUTTON_FIELD=".btn.btn-primary";
     public static final String ORDERS_LINK_FIELD=".btn.-teal";
    public  int noOfOrders;
    public  int randomNumber;
    public String orderId;

    public Orders(Page page) {
        this.page = page;
        
    }

    public void viewAllOrders() {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(ORDERS_BUTTON_FIELD)).click();
        page.locator(TABLE_BODY_ROW_FIELD)
            .first()
            .waitFor();  
    randomNumberFromData();
    }
   
    public void randomNumberFromData(){
        
        noOfOrders = page.locator(TABLE_BODY_ROW_FIELD).count();
        System.out.println("Number of orders: " + noOfOrders);
        randomNumber = (int) (Math.random() * (noOfOrders-1));
        orderId = page.locator("tbody tr").nth(randomNumber).locator("th").textContent();
        System.out.println("Selected order ID: " + orderId);
    }
    
    
    public void SpecificOrderView(){
         System.out.println("randomNumber:"+randomNumber);
         page.locator(TABLE_BODY_ROW_FIELD).nth(randomNumber).locator(VIEW_BUTTON_FIELD).click();
         PlaywrightAssertions.assertThat(page.getByText(orderId)).isVisible();
         
         page.locator(ORDERS_LINK_FIELD).click();
        }
    
    public void deleteOrder(){
        
        page.locator(TABLE_BODY_ROW_FIELD).nth(randomNumber).locator(DELETE_BUTTON_FIELD).click();
        PlaywrightAssertions.assertThat(page.getByText("Orders Deleted Successfully")).isVisible();
        PlaywrightAssertions.assertThat(page.getByText(orderId)).isHidden();
    }

}
