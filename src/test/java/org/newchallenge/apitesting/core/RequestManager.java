package org.newchallenge.apitesting.core;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public final class RequestManager {
    /**
     * Build the Post method request with body request
     *
     * @return the response body
     */
    public static Response post(RequestSpecification requestSpecification) {
        return given().spec(requestSpecification).log().method()
                .when().log().method()
                .post();
    }

    /**
     * Build the Get method request
     *
     * @param requestSpecification
     * @return
     */
    public static Response get(RequestSpecification requestSpecification, String endpoint) {
        return given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint);
    }

    /**
     * Build the Get method request
     *
     * @param requestSpecification
     * @return
     */
    public static Response get(RequestSpecification requestSpecification) {
        return given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .when()
                .get();
    }
}
