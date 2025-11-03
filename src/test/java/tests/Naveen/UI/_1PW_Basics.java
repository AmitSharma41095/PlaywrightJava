package tests.Naveen.UI;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class _1PW_Basics {

    @DisplayName("Headless mode - test execution")
    @Test
    public void headless(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(); //headless mode
//        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); //headed mode
        Page page = browser.newPage();

        page.navigate("https://www.google.com");
        System.out.println("Page Title : "+ page.title());
        System.out.println("Page URL : "+ page.url());

        browser.close();
        playwright.close();
    }

    @DisplayName("Headed mode - test execution")
    @Test
    public void headed(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); //headed mode
        Page page = browser.newPage();

        page.navigate("https://www.google.com");
        System.out.println("Page Title : "+ page.title());
        System.out.println("Page URL : "+ page.url());

        browser.close();
        playwright.close();
    }

    @DisplayName("Headed mode - chrome execution")
    @Test
    public void headed_chrome(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome")); //headed mode
        Page page = browser.newPage();

        page.navigate("https://www.google.com");
        System.out.println("Page Title : "+ page.title());
        System.out.println("Page URL : "+ page.url());

        browser.close();
        playwright.close();
    }

    @DisplayName("Headed mode - msedge execution")
    @Test
    public void headed_msedge(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setChannel("msedge")); //headed mode
        Page page = browser.newPage();

        page.navigate("https://www.google.com");
        System.out.println("Page Title : "+ page.title());
        System.out.println("Page URL : "+ page.url());

        browser.close();
        playwright.close();
    }

    @DisplayName("Headed mode - firefox execution")
    @Test
    public void headed_firefox(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)); //headed mode
        Page page = browser.newPage();

        page.navigate("https://www.google.com");
        System.out.println("Page Title : "+ page.title());
        System.out.println("Page URL : "+ page.url());

        browser.close();
        playwright.close();
    }

    @DisplayName("Headed mode - webkit execution")
    @Test
    public void headed_webkit(){
        Playwright playwright = Playwright.create();
        Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)); //headed mode
        Page page = browser.newPage();

        page.navigate("https://www.google.com");
        System.out.println("Page Title : "+ page.title());
        System.out.println("Page URL : "+ page.url());

        browser.close();
        playwright.close();
    }



}
