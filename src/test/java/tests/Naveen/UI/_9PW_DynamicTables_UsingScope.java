package tests.Naveen.UI;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class _9PW_DynamicTables_UsingScope {
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

    @DisplayName("Handle dynamic web tables using scope")
    @Test
    public void handleDynamicWebTables(){
        page.navigate("https://datatables.net/examples/basic_init/zero_configuration.html");
        Locator rows = page.locator("table#example tbody tr");
        System.out.println("Total rows: "+ rows.count());
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Print specific table contents:");
        rows.locator(":scope", new Locator.LocatorOptions().setHasText("Bradley Greer"))
                .allInnerTexts().forEach(e-> System.out.println(e));
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Print all table contents:");
        rows.locator(":scope").allInnerTexts().forEach(e-> System.out.println(e));
    }

    @DisplayName("Handle dynamic web tables using scope - checkbox selection")
    @Test
    public void handleDynamicWebTables1(){
        page.navigate("https://selectorshub.com/xpath-practice-page/");
        Locator rows = page.locator("table#resultTable tbody tr");
        System.out.println("Total rows: "+ rows.count());
        rows.locator(":scope", new Locator.LocatorOptions().setHasText("John.Smith"))
                        .locator("input[type='checkbox']").click();
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Print specific table contents:");
        rows.locator(":scope", new Locator.LocatorOptions().setHasText("John.Smith"))
                .allInnerTexts().forEach(e-> System.out.println(e));
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Print all table contents:");
        rows.locator(":scope").allInnerTexts().forEach(e-> System.out.println(e));
    }


}
