package org.newchallenge.handleproperties;

public final class ApiEndPoint {
    public EnvironmentConfiguration config = EnvironmentConfiguration.getInstance();
    private final static String API_KEY = "api_key";
    private final static String BASE_API_URI = "base_api_uri";
    private static final String MARS_ROVER_PHOTOS = "Mars_Rover_Photos";

    private static ApiEndPoint ApiEndPoint = null;

    public static ApiEndPoint getInstance(){
        if(ApiEndPoint == null){
            ApiEndPoint = new ApiEndPoint();
        }
        return ApiEndPoint;
    }

    private ApiEndPoint(){ }

    public String getApiKey() {
        return config.getPropertyByName(API_KEY);
    }

    private String getBaseUri() {
        return config.getPropertyByName(BASE_API_URI);
    }

    public String getMarsRoverPhotos(){
        return config.getPropertyByName(MARS_ROVER_PHOTOS);
    }

    public String getMarsRoverPhotosBaseUri(){
        return getBaseUri().concat(getMarsRoverPhotos());
    }
}
