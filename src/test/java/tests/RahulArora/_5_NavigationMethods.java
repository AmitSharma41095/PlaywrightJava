package tests.RahulArora;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class _5_NavigationMethods {

    @DisplayName("Navigation Methods - goBack, goForward, reload")
    @Test
    public void navigationMethods() throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();

        page.navigate("https://www.way2automation.com/");
        System.out.println("Page title: " + page.title());
        Assertions.assertEquals(page.title(),"Get Online Selenium Certification Course | Way2Automation");

        page.navigate("https://www.google.com/");
        System.out.println("Page title: " + page.title());

        page.goBack();
        System.out.println("After going back, Page title: " + page.title());

        page.goForward();
        System.out.println("After going forward, Page title: " + page.title());

        page.reload();
        System.out.println("After reloading, Page title: " + page.title());

        page.goBack(new Page.GoBackOptions().setTimeout(500));
        System.out.println("After going back with timeout, Page title: " + page.title());

        browser.close();
        playwright.close();
    }
}
