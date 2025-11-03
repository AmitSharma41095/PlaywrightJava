package tests.JohnFergusonSmart;

import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.Options;
import com.microsoft.playwright.junit.OptionsFactory;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

@UsePlaywright(_6BrowserOptionsDemo_UsePlaywrightAnnotation.MyOptions.class)
public class _6BrowserOptionsDemo_UsePlaywrightAnnotation {

    public static class MyOptions implements OptionsFactory {

        @Override
        public Options getOptions() {
            return new Options()
                    .setHeadless(false)
                    .setLaunchOptions(new BrowserType.LaunchOptions()
                            .setArgs(Arrays.asList("--no-sandbox","--disable-gpu"))
                    );
        }
    }

    @Test
    public void verifyPageTitle(Page page){
        page.navigate("http://practicesoftwaretesting.com/");
        System.out.println("Page Title : "+ page.title());
        Assertions.assertTrue(page.title().contains("Practice Software Testing"));
    }

    @Test
    public void verifySearchFunction(Page page){
        page.navigate("http://practicesoftwaretesting.com/");
        page.locator("//input[@placeholder='Search']").fill("Pliers");
        page.locator("button:has-text('Search')").click();

        int resultCount = page.locator(".card-title").count();
        System.out.println(resultCount);
        Assertions.assertTrue(resultCount>0);
    }

}
