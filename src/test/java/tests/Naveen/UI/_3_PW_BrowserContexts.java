package tests.Naveen.UI;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class _3_PW_BrowserContexts {

    @DisplayName("Open 3 links in same window -sequentially")
    @Test
    public void browserContextsTest(){
        System.out.println("Browser Contexts Test - Open 3 links in same window");
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext();

        Page tab1 = browserContext.newPage();
        tab1.navigate("https://www.google.com");
        System.out.println(tab1.title());

        Page tab2 = browserContext.newPage();
        tab2.navigate("https://naveenautomationlabs.com/");
        System.out.println(tab2.title());

        Page tab3 = browserContext.newPage();
        tab3.navigate("https://rahulshettyacademy.com/");
        System.out.println(tab3.title());

        browserContext.close();
        browser.close();
        playwright.close();
    }

    @DisplayName("Open 3 links in different window")
    @Test
    public void browserContextsTest1(){
        System.out.println("Browser Contexts Test - Open 3 links in different window");
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        //BrowserContext browserContext = browser.newContext();

        Page tab1 = browser.newPage();
        tab1.navigate("https://www.google.com");
        System.out.println(tab1.title());

        Page tab2 = browser.newPage();
        tab2.navigate("https://naveenautomationlabs.com/");
        System.out.println(tab2.title());

        Page tab3 = browser.newPage();
        tab3.navigate("https://rahulshettyacademy.com/");
        System.out.println(tab3.title());

        browser.close();
        playwright.close();
    }



}
