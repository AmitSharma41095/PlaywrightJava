package tests.Naveen.UI;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;

public class _13_PW_MaximizeScreen {
    
    @DisplayName("Maximize Screen Test")
    @Test
    public void maximizeScreenTest(){
        System.out.println("Maximize Screen Test");
        //java method to get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        System.out.println("Screen width: "+ width + " height: "+ height);

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(width,height));
        Page page = browserContext.newPage();
        page.navigate("https://orangehrm.com/");
        page.waitForTimeout(3000);

        page.close();
        playwright.close();
    }

    @DisplayName("Maximize Screen Test - using setArgs")
    @Test
    public void maximizeScreenTest1(){
        System.out.println("Maximize Screen Test - using set Args");

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(Arrays.asList("--start-maximized")));

        // Don’t set viewport — keep it null to match window size
        BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        Page page = browserContext.newPage();
        page.navigate("https://orangehrm.com/");    // just to visually confirm
        page.waitForTimeout(3000);

        page.close();
        playwright.close();
    }
}
