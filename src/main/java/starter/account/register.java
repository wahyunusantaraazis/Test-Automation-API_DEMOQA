package starter.account;

import net.serenitybdd.rest.SerenityRest;
import org.json.simple.JSONObject;
import starter.BaseEndpoint;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class register {
    BaseEndpoint endpoint = new BaseEndpoint();
    JSONObject requestpayload;

    // set request payload username dan password
    public void setBodyRequestPayload(String username, String password){
        requestpayload = new JSONObject();
        requestpayload.put("userName",username);
        requestpayload.put("password",password);
        SerenityRest
                .given()
                .header("Content-Type","application/json")
                .body(requestpayload.toString());
    }

    // hit endpoint register
    public void hitEndpointRegister(){
        SerenityRest
                .when()
                .post(endpoint.REGISTER);
    }

    //validasi status code
    public void validateStatusCode(int statuscode){
        SerenityRest
                .then()
                .statusCode(statuscode);
    }

    //validasi jsonschema/response body success
    public void validateJSONSchemaSuccess(){
        SerenityRest
                .then()
                .body(matchesJsonSchemaInClasspath("JSONSchema/account/register/registersuccess.json"));
    }

    //validasi jsonschema/response body failed
    public void validateJSONSchemaFailed(){
        SerenityRest
                .then()
                .body(matchesJsonSchemaInClasspath("JSONSchema/account/register/registerfailed.json"));
    }

    //validasi username dengan username di response body
    public void validateObjectRegisterSuccess(String username){
        SerenityRest
                .then()
                .body("username",equalTo(username));
    }

    //validasi isi object code dan message
    public void validateObjectRegisterFailed(String code, String message){
        SerenityRest
                .then()
                .body("code",equalTo(code))
                .body("message",equalTo(message));
    }

}
