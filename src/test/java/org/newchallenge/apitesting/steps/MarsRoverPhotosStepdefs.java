package org.newchallenge.apitesting.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.newchallenge.apitesting.pages.rovers.RoverCamaraRequest;
import org.newchallenge.managedata.InfoShare;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarsRoverPhotosStepdefs {
    RoverCamaraRequest request;

    @And("^I Get the first (\\d+) Mars photos made by ([^\"]*) Rover Cameras on (\\d+) Martian sol$")
    public void iGetTheFirstMarsPhotosMadeByRoverCamerasOnMartianSol(int numberPhotos, String roverCamera, int attribute) throws Throwable {
        String apiKey = InfoShare.getInstance().getInfoFromShare("APIKEY", String.class);
        List roverCamaraNumberPhotosWithAttribute = RoverCamaraRequest.getInstance().getRoverCamaraNumberPhotosRoverCamaraAndAmount(numberPhotos, roverCamera, attribute);
        InfoShare.getInstance().putInfoToShare("roverCamaraNumberPhotosWithAttribute", roverCamaraNumberPhotosWithAttribute);
    }

    @And("^I Get the first (\\d+) Mars photos made by ([^\"]*) Rover Cameras on ([^\"]*) Earth date equal to (\\d+) Martian sol$")
    public void iGetTheFirstNumberPhotosMarsPhotosMadeByRoverCameraRoverCamerasOnEarthDateEarthDateEqualToAttributeMartianSol(int numberPhotos, String roverCamera, String dates, int attribute) {
        List roverCamaraNumberPhotosDatesWithAttribute = RoverCamaraRequest.getInstance().getRoverCamaraNumberPhotosRoverCamaraDateAndAmount(numberPhotos, roverCamera, dates, attribute);
        InfoShare.getInstance().putInfoToShare("roverCamaraNumberPhotosDatesWithAttribute", roverCamaraNumberPhotosDatesWithAttribute);
    }

    @Then("^I compare the first Martial sol list with Earth date list$")
    public void iCompareTheFirstMartialSolListWithEarthDateList() {
        List martialSolList = InfoShare.getInstance().getInfoFromShare("roverCamaraNumberPhotosWithAttribute", List.class);
        List earthDateList = InfoShare.getInstance().getInfoFromShare("roverCamaraNumberPhotosDatesWithAttribute", List.class);
        Assert.assertEquals(martialSolList, earthDateList);
    }

    @And("^verify the amounts of picture Mars Sol are not greater than (\\d+) times the amount taken by other cameras$")
    public void verifyTheAmountsOfPictureMarsSolAreNotGreaterThanTimesTheAmountTakenByOtherCameras(int times) {
        List martialSolList = InfoShare.getInstance().getInfoFromShare("roverCamaraNumberPhotosWithAttribute", List.class);

        HashMap<Integer, Integer> amountPicturesMartialSol = new HashMap<>();
        for (Object martialSol : martialSolList) {
            Map<String, Object> temp = (Map) martialSol;
            amountPicturesMartialSol.put((Integer)temp.get("id"), ((Map)temp.get("camera")).size());
        }

        List earthDateList = InfoShare.getInstance().getInfoFromShare("roverCamaraNumberPhotosDatesWithAttribute", List.class);
        HashMap<Integer, Integer> amountPicturesEarthDate = new HashMap<>();
        for (Object earthDate : earthDateList) {
            Map<String, Object> temp = (Map) earthDate;
            amountPicturesEarthDate.put((Integer)temp.get("id"), ((Map)temp.get("camera")).size());
        }

        Assert.assertEquals(amountPicturesMartialSol, amountPicturesEarthDate);
    }
}
