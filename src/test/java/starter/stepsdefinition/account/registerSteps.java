package starter.stepsdefinition.account;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import starter.BaseTest;

public class registerSteps extends BaseTest {
    @Given("I am set body request valid username {string} and password {string}")
    public void iAmSetBodyRequestValidUsernameAndPassword(String username, String password) {
        register.setBodyRequestPayload(username,password);
    }

    @Given("I am set body request valid username {string} and invalid password {string}")
    public void iAmSetBodyRequestValidUsernameAndInvalidPassword(String username, String password) {
        register.setBodyRequestPayload(username,password);
    }

    @When("I am hit endpoint register")
    public void iAmHitEndpointRegister() {
        register.hitEndpointRegister();
    }

    @Then("I am get status code register is {int}")
    public void iAmGetStatusCodeRegisterIs(int statuscode) {
        register.validateStatusCode(statuscode);
    }

    @And("validate response body success")
    public void validateResponseBodySuccess() {
        register.validateJSONSchemaSuccess();
    }

    @And("validate response body failed")
    public void validateResponseBodyFailed() {
        register.validateJSONSchemaFailed();
    }

    @And("validate object username {string}")
    public void validateObjectUsername(String username) {
        register.validateObjectRegisterSuccess(username);
    }

    @And("validate object code {string} and message {string}")
    public void validateObjectCodeAndMessage(String code, String message) {
        register.validateObjectRegisterFailed(code,message);
    }

}
