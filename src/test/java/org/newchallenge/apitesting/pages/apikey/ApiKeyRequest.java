package org.newchallenge.apitesting.pages.apikey;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.newchallenge.apitesting.core.RequestManager;
import org.newchallenge.handleproperties.ApiEndPoint;

public class ApiKeyRequest {
    private static ApiKeyRequest ApiKeyRequest = null;
    protected static final ApiEndPoint API_ENDPOINT = ApiEndPoint.getInstance();
    private RequestSpecification requestSpecification;
    private Response resp;

    /**
     * Instance for the Endpoint 'Api Key' just as an example
     * how can add multiple endpoints
     * @return ApiKeyEndPoint
     */
    public static ApiKeyRequest getInstance(){
        if(ApiKeyRequest == null){
            ApiKeyRequest = new ApiKeyRequest();
        }
        return ApiKeyRequest;
    }
    /**
     * This method would be a method to build a request specification for APIKEY
     * By the moment the NASA already generate the APIKEY
     * @return
     */
    private RequestSpecification getRequestSpec(){
        return new RequestSpecBuilder()
//                .setBaseUri(API_ENDPOINT.getApiKeyTokenEndpoint())
//                .addFormParam("grant_type", "client_credentials")
//                .addHeader("Accept", "application/json, text/plain, */*")
                .build();
    }

    /**
     * Send Request Get for Apikey Token
     * this method as example
     * @return
     */
    public String getApiKeyToken() {
        requestSpecification = getRequestSpec();
        resp = RequestManager.get(requestSpecification, "test/testing");
        return resp.jsonPath().getString("user").concat(" ").concat(resp.jsonPath().getString("access_token"));
    }

    public String getApiKey(){
        return API_ENDPOINT.getApiKey();
    }
}
