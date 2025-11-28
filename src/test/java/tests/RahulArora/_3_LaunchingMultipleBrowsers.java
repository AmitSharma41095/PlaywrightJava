package tests.RahulArora;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;

public class _3_LaunchingMultipleBrowsers {

    @DisplayName("Launching Browsers = chromium, chrome, firefox, webkit, msedge")
    @Test
    public void maximizeWindow_approach1() throws InterruptedException {
        Playwright playwright = Playwright.create();
//        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
//        Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
//        Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
//        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge").setArgs(Arrays.asList("--start-maximized")));
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome").setArgs(Arrays.asList("--start-maximized")));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("https://www.way2automation.com/");
        System.out.println("Page title: " + page.title());
        Assertions.assertEquals(page.title(),"Get Online Selenium Certification Course | Way2Automation");
        Thread.sleep(5000);
        browser.close();
        playwright.close();
    }


}
