package tests.RahulArora;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class _1_FirstTest {

    @DisplayName("First Playwright Test - Headless mode")
    @Test
    public void FirstTest_Headless(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();
        page.navigate("https://www.way2automation.com/");
        System.out.println("Page title: " + page.title());
        Assertions.assertEquals(page.title(),"Get Online Selenium Certification Course | Way2Automation");
        browser.close();
        playwright.close();
    }

    @DisplayName("First Playwright Test - Headed mode")
    @Test
    public void FirstTest_Headed(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.way2automation.com/");
        System.out.println("Page title: " + page.title());
        Assertions.assertEquals(page.title(),"Get Online Selenium Certification Course | Way2Automation");
        browser.close();
        playwright.close();
    }


}
