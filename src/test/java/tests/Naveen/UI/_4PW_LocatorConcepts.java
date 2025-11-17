package tests.Naveen.UI;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class _4PW_LocatorConcepts {
    Playwright playwright;
    Browser browser;
    Page page;

    @BeforeEach
    public void setup(){
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterEach
    public void tearDown(){
        browser.close();
        playwright.close();
    }

    @DisplayName("Single element locator test")
    @Test
    public void locatorTest(){
        page.navigate("https://orangehrm.com/");
        Locator FreeDemoLocator = page.locator("text=Book a Free Demo").last();
        FreeDemoLocator.click();
        System.out.println("page title = "+page.title());
        Assertions.assertThat(page.url()).isEqualTo("https://orangehrm.com/en/book-a-free-demo");
    }

    @DisplayName("Single element locator test - strict violation")
    @Test
    public void locatorTest1(){
        page.navigate("https://orangehrm.com/");
//        Locator policyLocator = page.locator("text=policy"); //message='Error: strict mode violation: locator("text=Policy") resolved to 5 elements:
        Locator policyLocator = page.locator("text=policy").first();
        policyLocator.click();
        System.out.println("page title = "+page.title());
    }

    @DisplayName("Multiple element locator test")
    @Test
    public void locatorTest2(){
        page.navigate("https://orangehrm.com/en/contact-sales");
        Locator countryDDLLocator = page.locator("select#Form_getForm_Country option");
        System.out.println("count of list : "+ countryDDLLocator.count());

        //Iterate through list
        System.out.println("---------Using nth() method ---------");
        for(int i=1; i<countryDDLLocator.count(); i++){
            System.out.println(countryDDLLocator.nth(i).textContent());
        }
        // Another way to get all the texts in a list
        System.out.println("---------Using allTextContents() method ---------");
        List<String> AlltextList = countryDDLLocator.allTextContents();
        for(String countryName : AlltextList){
            System.out.println(countryName);
        }
        //Another way - using forEach
        System.out.println("---------Using forEach() method ---------");
        AlltextList.forEach(e -> System.out.println(e));
    }



}
