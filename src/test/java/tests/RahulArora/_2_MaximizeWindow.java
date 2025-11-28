package tests.RahulArora;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;

public class _2_MaximizeWindow {

    @DisplayName("Maximize window - approach 1")
    @Test
    public void maximizeWindow_approach1() throws InterruptedException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight();

        System.out.println(width+ "X"+ height);
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width,height));
        Page page = browserContext.newPage();
        page.navigate("https://www.way2automation.com/");
        System.out.println("Page title: " + page.title());
        Assertions.assertEquals(page.title(),"Get Online Selenium Certification Course | Way2Automation");
        Thread.sleep(5000);
        browser.close();
        playwright.close();
    }

    @DisplayName("Maximize window - approach 2")
    @Test
    public void maximizeWindow_approach2() throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(Arrays.asList("--start-maximized")));
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
