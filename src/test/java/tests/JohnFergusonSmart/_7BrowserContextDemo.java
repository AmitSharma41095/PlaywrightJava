package tests.JohnFergusonSmart;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;

public class _7BrowserContextDemo {
    static Playwright playwright;
    static Browser browser;
    static Page page;
    static BrowserContext browserContext;

    @BeforeAll
    public static void setupBrowser(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(Arrays.asList("--start-maximized","--no-sandbox","--disable-extensions","--disable-gpu"))
        );
        browserContext = browser.newContext();
    }

    @AfterAll
    public static void tearDown(){
        browser.close();
        playwright.close();
    }

    @BeforeEach
    public void setup(){
        page = browserContext.newPage();
    }

    @Test
    public void verifyPageTitle(){
        page.navigate("http://practicesoftwaretesting.com/");
        System.out.println("Page Title : "+ page.title());
        Assertions.assertTrue(page.title().contains("Practice Software Testing"));
    }

    @Test
    public void verifySearchFunction(){
        page.navigate("http://practicesoftwaretesting.com/");
        page.locator("//input[@placeholder='Search']").fill("Pliers");
        page.locator("button:has-text('Search')").click();

        int resultCount = page.locator(".card-title").count();
        System.out.println(resultCount);
        Assertions.assertTrue(resultCount>0);
    }


}
