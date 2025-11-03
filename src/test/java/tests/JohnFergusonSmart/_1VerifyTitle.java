package tests.JohnFergusonSmart;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class _1VerifyTitle {
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
}
