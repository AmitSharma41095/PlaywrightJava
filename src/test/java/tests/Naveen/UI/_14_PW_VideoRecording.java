package tests.Naveen.UI;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class _14_PW_VideoRecording {

    @DisplayName("Video Recording Test")
    @Test
    public void videoRecordingTest() throws IOException {
        System.out.println("Video Recording Test");
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false));

        String projectPath = System.getProperty("user.dir");
        String videoDirPath = projectPath + "/src/test/java/tests/Naveen/UI/videos/Run_" + System.currentTimeMillis();
        Path videoPath = Paths.get(videoDirPath);
        Files.createDirectories(videoPath);

        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
        .setRecordVideoDir(videoPath)
                .setRecordVideoSize(1280, 585));

        Page page = context.newPage();
        page.navigate("https://academy.naveenautomationlabs.com/");
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Login")).click();
        page.locator("#microfe-popup-login").contentFrame().getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Name")).click();
        page.locator("#microfe-popup-login").contentFrame().getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Name")).fill("TestingAutomationInspector");
        page.locator("#microfe-popup-login").contentFrame().getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Email address")).click();
        page.locator("#microfe-popup-login").contentFrame().getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Email address")).fill("InspectorTesting@test.com");
        page.locator("#microfe-popup-login").contentFrame().getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Password")).click();
        page.locator("#microfe-popup-login").contentFrame().getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Password")).fill("Testing");
        page.locator("#microfe-popup-login").contentFrame().getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Enter your number")).click();
        page.locator("#microfe-popup-login").contentFrame().getByRole(AriaRole.BUTTON, new FrameLocator.GetByRoleOptions().setName("India: +")).click();
        page.locator("#microfe-popup-login").contentFrame().getByText("India", new FrameLocator.GetByTextOptions().setExact(true)).click();
        page.locator("#microfe-popup-login").contentFrame().getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Enter your number")).click();
        page.locator("#microfe-popup-login").contentFrame().getByRole(AriaRole.TEXTBOX, new FrameLocator.GetByRoleOptions().setName("Enter your number")).fill("+91 98765-43210");
        page.locator("#microfe-popup-login").contentFrame().locator("#loginPopupCloseBtn svg").click();

        page.close();
        context.close();
        playwright.close();
    }

}
