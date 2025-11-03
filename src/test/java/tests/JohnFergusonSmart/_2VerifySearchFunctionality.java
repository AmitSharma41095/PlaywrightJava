package tests.JohnFergusonSmart;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class _2VerifySearchFunctionality {

    @Test
    public void verifyPageTitle(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();

        page.navigate("http://practicesoftwaretesting.com/");
        System.out.println("Page Title : "+ page.title());
        Assertions.assertTrue(page.title().contains("Practice Software Testing"));

        browser.close();
        playwright.close();
    }

    @Test
    public void verifySearchFunction(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();

        page.navigate("http://practicesoftwaretesting.com/");
        page.locator("//input[@placeholder='Search']").fill("Pliers");
        page.locator("button:has-text('Search')").click();

        int resultCount = page.locator(".card-title").count();
        System.out.println(resultCount);
        Assertions.assertTrue(resultCount>0);

        browser.close();
        playwright.close();
    }
}
