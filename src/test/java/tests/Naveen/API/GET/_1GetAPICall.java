package tests.Naveen.API.GET;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

public class _1GetAPICall {

    @DisplayName("Get API Test - all methods of Response")
    @Test
    public void getUsersAPITest() throws IOException {
        Playwright playwright = Playwright.create();
        APIRequest request = playwright.request();
        APIRequestContext apiRequestContext = request.newContext();
        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users");

        System.out.println("Response status code :: "+ apiResponse.status());
        System.out.println("Response status Text :: "+ apiResponse.statusText());
        System.out.println("Response URL :: "+ apiResponse.url());
        System.out.println("Response text :: "+apiResponse.text());

        Map<String, String> headersMap = apiResponse.headers();
        System.out.println("Response headers :: "+ headersMap);
        System.out.println("get a particular header - content-type: "+ headersMap.get("content-type"));

        System.out.println("Response body :: "+ apiResponse.body());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(apiResponse.body());
        String jsonPrettyResponse = jsonNode.toPrettyString();
        System.out.println("Response body in readable form : "+ jsonPrettyResponse);
    }

    @DisplayName("Get API Test - using query parameter")
    @Test
    public void getSpecificUsersAPITest() throws IOException {
        Playwright playwright = Playwright.create();
        APIRequest request = playwright.request();
        APIRequestContext apiRequestContext = request.newContext();

        APIResponse apiResponse = apiRequestContext.get("https://gorest.co.in/public/v2/users",
                RequestOptions.create()
                        .setQueryParam("id", "8208182")
                        .setQueryParam("status", "inactive")
        );

        System.out.println("Response status code :: "+ apiResponse.status());
        System.out.println("Response status Text :: "+ apiResponse.statusText());
        System.out.println("Response URL :: "+ apiResponse.url());
        System.out.println("Response text :: "+apiResponse.text());
    }

}
