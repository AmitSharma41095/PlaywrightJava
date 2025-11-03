package tests.Naveen.API.GET;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.HttpHeader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class _3GetHeadersTest {

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

    @DisplayName("Response Headers with maps - .headers()")
    @Test
    public void responseHeadersTest(){
        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users");

        Map<String, String> headersMap = apiResponse.headers();
        headersMap.forEach((k,v)-> System.out.println(k + " : "+ v));
        System.out.println("Total response headers :: "+ headersMap.size());
        System.out.println("Response headers :: "+ headersMap);
        System.out.println("get a particular header - content-type: "+ headersMap.get("content-type"));
    }

    @DisplayName("Response Headers with maps - .headersArray()")
    @Test
    public void responseHeadersListTest(){
        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users");

        List<HttpHeader> httpHeadersList = apiResponse.headersArray();
        for(HttpHeader e : httpHeadersList){
            System.out.println(e.name + " : "+ e.value);
        }
        System.out.println("Total response headers :: "+ httpHeadersList.size());
        System.out.println("Response headers :: "+ httpHeadersList);
        System.out.println("get a particular header : "+ httpHeadersList.get(0));
    }
}
