package tests.JohnFergusonSmart;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class _5BrowserOptionsDemo {

    static Playwright playwright;
    static Browser browser;
    static Page page;

    @BeforeAll
    public static void setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(false)
                        .setArgs(Arrays.asList("--start-maximized","--no-sandbox","--disable-extensions","--disable-gpu"))
        );
        page = browser.newPage();
    }

    @AfterAll
    public static void tearDown(){
        browser.close();
        playwright.close();
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
