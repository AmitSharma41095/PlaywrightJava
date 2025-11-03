package tests.Naveen.API.GET;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class _2GetDisposeTest {

    Playwright playwright;
    APIRequest request;
    APIRequestContext apiRequestContext;

    @BeforeEach
    public void setup(){
        playwright = Playwright.create();
        request = playwright.request();
        apiRequestContext = request.newContext();
    }

    @AfterEach
    public void tearDown(){
        playwright.close();
    }

    @DisplayName("Response Dispose test")
    @Test
    public void responseDisposeTest(){
        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users");

        System.out.println("************ Before Dispose *******************");
        System.out.println("Response status code :: "+ apiResponse.status());
        System.out.println("Response status Text :: "+ apiResponse.statusText());
        System.out.println("Response URL :: "+ apiResponse.url());
        System.out.println("Response text :: "+apiResponse.text());

        apiResponse.dispose();
        System.out.println("************ After Dispose *******************");
        System.out.println("Response status code :: "+ apiResponse.status());
        System.out.println("Response status Text :: "+ apiResponse.statusText());
        System.out.println("Response URL :: "+ apiResponse.url());
        try{
            System.out.println("Response text :: "+apiResponse.text());
        }catch (PlaywrightException e){
            System.out.println("Exception encounted - Response has been disposed");
        }
    }

    @DisplayName("Response Context Dispose test")
    @Test
    public void responseContextDisposeTest(){
        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users");
        APIResponse apiResponse1 = apiRequestContext.get("https://gorest.co.in/public/v2/comments");

        apiRequestContext.dispose();

        try{
            System.out.println("Response text :: "+apiResponse.text());
        }catch (PlaywrightException e){
            System.out.println("Exception encounted - Response has been disposed for Response1");
        }

        try{
            System.out.println("Response text :: "+apiResponse1.text());
        }catch (PlaywrightException e){
            System.out.println("Exception encounted - Response has been disposed for Response2");
        }
    }
}
