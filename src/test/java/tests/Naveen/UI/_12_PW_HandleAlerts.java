package tests.Naveen.UI;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class _12_PW_HandleAlerts {

    @DisplayName("Auto Handling of Alerts.")
    @Test
    public void handleAlertsTest(){
        System.out.println("Handle Alerts Test");
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

        page.getByText("Click for JS Alert").click();
        System.out.println(page.locator("#result").textContent());
        page.getByText("Click for JS Confirm").click();
        System.out.println(page.locator("#result").textContent());
        page.getByText("Click for JS Prompt").click();
        System.out.println(page.locator("#result").textContent());
    }

    @DisplayName("Manually Handling of Alerts.")
    @Test
    public void handleAlertsManuallyTest() throws InterruptedException {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://the-internet.herokuapp.com/javascript_alerts");

        // ------------------ JS ALERT ---------------------
        page.onceDialog(dialog -> {
            System.out.println("Alert message: " + dialog.message());
            dialog.accept();
        });
        page.getByText("Click for JS Alert").click();
        System.out.println(page.locator("#result").textContent());

        // ------------------ JS CONFIRM (Accept) ---------------------
        page.onceDialog(dialog -> {
            System.out.println("Confirm message: " + dialog.message());
            dialog.accept();
        });
        page.getByText("Click for JS Confirm").click();
        System.out.println(page.locator("#result").textContent());

        // ------------------ JS CONFIRM (Dismiss) ---------------------
        Thread.sleep(2000);
        page.onceDialog(dialog -> {
            System.out.println("Confirm message: " + dialog.message());
            dialog.dismiss();
        });
        page.getByText("Click for JS Confirm").click();
        System.out.println(page.locator("#result").textContent());

        // ------------------ JS PROMPT (Accept with Input) ---------------------
        page.onceDialog(dialog -> {
            System.out.println("Prompt message: " + dialog.message());
            dialog.accept("Hello from Playwright");
        });
        page.getByText("Click for JS Prompt").click();
        System.out.println(page.locator("#result").textContent());

        // ------------------ JS PROMPT (Dismiss) ---------------------
        page.onceDialog(dialog -> {
            System.out.println("Prompt message: " + dialog.message());
            dialog.dismiss();
        });
        page.getByText("Click for JS Prompt").click();
        System.out.println(page.locator("#result").textContent());

        browser.close();
        playwright.close();
    }
}
