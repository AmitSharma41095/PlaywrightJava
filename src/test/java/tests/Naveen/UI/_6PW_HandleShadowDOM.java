package tests.Naveen.UI;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class _6PW_HandleShadowDOM {
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

    @DisplayName("Handling Shadow DOM elements")
    @Test
    public void shadowDOMHandleTest(){
        page.navigate("https://books-pwakit.appspot.com/");

        //Accessing shadow DOM element
        page.locator("book-app[apptitle='BOOKS'] input#input").fill("testing notes");
        String text = page.locator("book-app[apptitle='BOOKS'] .books-desc").textContent();
        System.out.println("Shadow text is : "+ text);
    }

    @DisplayName("Handling Shadow DOM elements - text and enter")
    @Test
    public void shadowDOMHandleTest1(){
        page.navigate("https://selectorshub.com/xpath-practice-page/");

        //Accessing shadow DOM element
        page.locator(".jackPart #kils").fill("Amit Sharma");
        page.locator(".jackPart #pizza").fill("Cheese Pizza");
        //page.locator(".jackPart #training").fill("Concept test"); //this has shadow root (closed) hence cannot be accessed
    }
}
