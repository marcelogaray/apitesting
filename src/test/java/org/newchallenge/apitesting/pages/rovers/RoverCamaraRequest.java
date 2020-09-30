package org.newchallenge.apitesting.pages.rovers;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.newchallenge.apitesting.core.RequestManager;
import org.newchallenge.handleproperties.ApiEndPoint;

import java.util.List;
import java.util.stream.Collectors;

public class RoverCamaraRequest {
    private static RoverCamaraRequest RoverCamaraRequest = null;
    protected static final ApiEndPoint API_ENDPOINT = ApiEndPoint.getInstance();
    private RequestSpecification requestSpecification;
    private Response resp;

    /**
     * Instance for the Endpoint 'Rover Camara EndPoint' just as an example
     * how can add multiple endpoints
     * @return RoverCamaraEndPoint
     */
    public static RoverCamaraRequest getInstance(){
        if(RoverCamaraRequest == null){
            RoverCamaraRequest = new RoverCamaraRequest();
        }
        return RoverCamaraRequest;
    }

    /**
     * This method would be a method to build a request specification for APIKEY
     * By the moment the NASA already generate the APIKEY
     *
     * @param pages number of pages filtering
     * @param roverCamara would be Curiosity,Opportunity or Spirit
     * @param amount Sol info data
     * @return
     */
    private RequestSpecification getRequestSpecByMartianSol(int pages, String roverCamara, int amount){
        String uriRoverCamara = API_ENDPOINT.getMarsRoverPhotosBaseUri().concat(roverCamara.toLowerCase())
                .concat("/photos");

        return new RequestSpecBuilder().setBaseUri(uriRoverCamara)
                .addQueryParam("sol", amount)
                .addQueryParam("page", pages)
                .addQueryParam("api_key", API_ENDPOINT.getApiKey())
                .build();
    }

    /**
     *
     * @param pages number of pages filtering
     * @param roverCamara would be Curiosity,Opportunity or Spirit
     * @param dates Earth date info
     * @return
     */
    private RequestSpecification getRequestSpecByEarthDate(int pages, String roverCamara, String dates, int amount) {
        String uriRoverCamara = API_ENDPOINT.getMarsRoverPhotosBaseUri().concat(roverCamara.toLowerCase())
                .concat("/photos");

        return new RequestSpecBuilder().setBaseUri(uriRoverCamara)
                .addQueryParam("earth_date", dates)
                .addQueryParam("page", pages)
                .addQueryParam("sol", amount)
                .addQueryParam("api_key", API_ENDPOINT.getApiKey())
                .build();
    }

    public List getRoverCamaraNumberPhotosRoverCamaraAndAmount(int numberPhotos, String roverCamara, int amount) {
        int pages = getNumberOfPagesByNumberPhotos(numberPhotos);
        requestSpecification = getRequestSpecByMartianSol(pages, roverCamara, amount);
        resp = RequestManager.get(requestSpecification);
        return resp.jsonPath().getList("photos").stream().limit(numberPhotos).collect(Collectors.toList());
    }

    public List getRoverCamaraNumberPhotosRoverCamaraDateAndAmount(int numberPhotos, String roverCamara, String dates, int amount) {
        int pages = getNumberOfPagesByNumberPhotos(numberPhotos);
        requestSpecification = getRequestSpecByEarthDate(pages, roverCamara, dates, amount);
        resp = RequestManager.get(requestSpecification);
        return resp.jsonPath().getList("photos").stream().limit(numberPhotos).collect(Collectors.toList());
    }

    private int getNumberOfPagesByNumberPhotos(int numberPhotos) {
        int pages;
        int photosByPage = 25;

        if (numberPhotos <= photosByPage) {
            pages = 1;
        } else {
            pages = (int) Math.floor(numberPhotos / photosByPage);
        }
        return pages;
    }
}
