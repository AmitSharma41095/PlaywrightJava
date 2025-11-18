package tests.Naveen.UI;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class _15_PW_HandleWindowPopUp {

    @DisplayName("Handle Single Window Pop Up")
    @Test
    public void handleSingleWindowPopUpTest(){
        System.out.println("Handle Single Window Pop Up Test");

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext();

        Page page = browserContext.newPage();
        page.navigate("https://www.hyrtutorials.com/p/window-handles-practice.html");

        Page newWindow = page.waitForPopup(() -> {
            page.locator("#newWindowBtn").click();
        });
        System.out.println("New window URL: " + newWindow.url());
        System.out.println("New window title: " + newWindow.title());
        newWindow.close();
        System.out.println("Back to main window title: " + page.title());
        System.out.println("Back to main window URL: " + page.url());

        page.close();
        browserContext.close();
        playwright.close();
    }

    @DisplayName("Handle Single Tab Pop Up")
    @Test
    public void handleSingleTabPopUpTest(){
        System.out.println("Handle Single Tab Pop Up Test");

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext();

        Page page = browserContext.newPage();
        page.navigate("https://www.hyrtutorials.com/p/window-handles-practice.html");

        Page newWindow = page.waitForPopup(() -> {
            page.locator("#newTabBtn").click();
        });
        System.out.println("New window URL: " + newWindow.url());
        System.out.println("New window title: " + newWindow.title());
        newWindow.close();
        System.out.println("Back to main window title: " + page.title());
        System.out.println("Back to main window URL: " + page.url());

        page.close();
        browserContext.close();
        playwright.close();
    }

    @DisplayName("Open a new blank tab and enter google")
    @Test
    public void handleBlankTabPopUpTest(){
        System.out.println("Open a new blank tab and enter google Test");

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext browserContext = browser.newContext();

        Page page = browserContext.newPage();
        page.navigate("https://www.hyrtutorials.com/p/window-handles-practice.html");

        Page newWindow = browserContext.newPage();
        newWindow.navigate("https://www.google.com");
        System.out.println("New window URL: " + newWindow.url());
        System.out.println("New window title: " + newWindow.title());
        newWindow.close();
        System.out.println("Back to main window title: " + page.title());
        System.out.println("Back to main window URL: " + page.url());

        page.close();
        browserContext.close();
        playwright.close();
    }

}
