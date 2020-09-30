package org.newchallenge.apitesting.steps;

import cucumber.api.java.en.Given;
import org.newchallenge.apitesting.pages.apikey.ApiKeyRequest;
import org.newchallenge.managedata.InfoShare;

public class ConfigurationFileStepdefs {
    @Given("^I get ApiKey$")
    public void iGetApiKey() {
        String apiKey = ApiKeyRequest.getInstance().getApiKey();
        InfoShare.getInstance().putInfoToShare("APIKEY", apiKey);
    }
}
