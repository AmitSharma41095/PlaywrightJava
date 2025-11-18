package tests.Naveen.UI;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

public class _11_PW_DownloadFile {

    @DisplayName("Download File Test")
    @Test
    public void downloadFileTest() {
        System.out.println("Download File Test............");
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        page.navigate("https://chromedriver.storage.googleapis.com/index.html?path=114.0.5735.90/");
        Download download = page.waitForDownload(() -> {
            page.click("a:text('chromedriver_win32.zip')");
        });

//      download.cancel(); //to cancel download
        System.out.println("Download URL: " + download.url());
        System.out.println("Suggested File Name: " + download.suggestedFilename());
        System.out.println("Download Path: " + download.path().toString());
        System.out.println("Download Failure: " + download.failure());
        System.out.println("Downlaod page title: " + download.page().title());

        //downlaod at specific location
        download.saveAs(Paths.get("src/test/resources/ChromeExe.zip"));

        browser.close();
        playwright.close();
    }

}
