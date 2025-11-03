package tests.Naveen.API.Resources;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HandleBasicAuthTokens {

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
    
    @Test
    public void handleBasicAuthToken(){

    }

    public void getTokenID(){

        apiRequestContext.post("https://restful-booker.herokuapp.com/auth",
                RequestOptions.create()
                        .setData("")
                );
    }
    
}
