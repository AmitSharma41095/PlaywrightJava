package tests.Naveen.UI;

import com.microsoft.playwright.*;
import net.datafaker.idnumbers.SouthAfricanIdNumber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class _8_PW_RelativeLocators {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    public void setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterEach
    public void tearDown(){
        browser.close();
        playwright.close();
    }

    @DisplayName("Relative Locators - left of, right of, below, above, near")
    @Test
    public void relativeLocators(){
        page.navigate("https://selectorshub.com/xpath-practice-page/");

        //Scenario 1: check checkbox left of Joe Root
        page.locator("input[type='checkbox']:left-of(:text('Joe Root'))").first().click();

        //Scenario 2: get user role right of Joe Root
        String userRole = page.locator("td:right-of(:text('Joe.Root'))").first().textContent();
        System.out.println("User role of Joe Root is : "+ userRole);

        //Scenario 3: Get the user name above Joe Root
        String userNameAbove = page.locator("a:above(:text('Joe.Root'))").first().textContent();
        System.out.println("User name above Joe Root is : "+ userNameAbove);

        //Scenario 4: Get the user name below Joe Root
        String userNameBelow = page.locator("a:below(:text('Joe.Root'))").first().textContent();
        System.out.println("User name above Joe Root is : "+ userNameBelow);

        //Scenario 5: Get the td near to Joe Root
        Locator nearElements = page.locator("td:near(:text('Joe.Root'))");
        System.out.println("Total near elements count: "+ nearElements.count());
        for(int i=0; i<nearElements.count(); i++){
            System.out.println("Near td "+ (i+1) +" : "+ nearElements.nth(i).textContent());
        }

        //Scenario 6: Get the td near to Joe Root for 120 pixels
        Locator nearElements120Px = page.locator("td:near(:text('Joe.Root'),120)");
        System.out.println("Total near elements count for 120 px: "+nearElements120Px);
        nearElements120Px.count();
        for(int i=0; i<nearElements120Px.count(); i++){
            System.out.println("Near td "+ (i+1) +" : "+ nearElements120Px.nth(i).textContent());
        }

    }


}
