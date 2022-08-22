package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GoRestDataPojo;
import pojos.GoRestResponseBodyPojo;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
    /*
        Given

        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And https://gorest.co.in/public/v1/users/2507
            Response body should be like
           {
                "meta": null,
                "data": {
        "id": 2507,
        "name": "Arnesh Mahajan VM",
        "email": "arnesh_mahajan_vm@witting.io",
        "gender": "female",
        "status": "active"
                }
            }
    */
    @Test
    public void getPojo01(){
        //1. Step: Set the Url
        spec.pathParams("first", "users", "second", 2507);


        //2. Step: Set the Expected Data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(2507,"Arnesh Mahajan VM","arnesh_mahajan_vm@witting.io","female","active");
        GoRestResponseBodyPojo goRestResponseBodyPojo = new GoRestResponseBodyPojo(null,goRestDataPojo);

        //3. Step: Send the Get Request get the Response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion
        GoRestResponseBodyPojo actualPojo = response.as(GoRestResponseBodyPojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(goRestResponseBodyPojo.getMeta(),actualPojo.getMeta());
        assertEquals(goRestResponseBodyPojo.getData().getId(), actualPojo.getData().getId());
        assertEquals(goRestResponseBodyPojo.getData().getName(), actualPojo.getData().getName());
        assertEquals(goRestResponseBodyPojo.getData().getEmail(), actualPojo.getData().getEmail());
        assertEquals(goRestResponseBodyPojo.getData().getGender(), actualPojo.getData().getGender());
        assertEquals(goRestResponseBodyPojo.getData().getStatus(), actualPojo.getData().getStatus());

    }

}