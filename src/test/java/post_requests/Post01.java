package post_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Post01 extends JsonPlaceHolderBaseUrl {
     /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)
        When
            I send POST Request to the Url
        Then
            Status code is 201 {
                 "userId": 1,
                        "id": 3,
                        "title": "fugiat veniam minus",
                        "completed": false
              }
        And
            response body is like {
                                    "userId": 1,
                        "id": 3,
                        "title": "fugiat veniam minus",
                        "completed": false
                                    }
     */



    @Test
    public void Post01(){
        //1. Step: Set the Url
        spec.pathParam("first", "todos");

        //2. Step: Set the expected data
        JsonPlaceHolderTestData expectedData= new JsonPlaceHolderTestData();
        Map<String,Object>expectedDataMap= expectedData.expectedDataWithAllKeys(1,"Tidy your room",false);

        //3. Step: Send Post Request and get Response
        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedDataMap).when().post("/{first}");
        response.prettyPrint();

        //4. Step: Do Assertion
        Map<String,Object>actualDataMap= response.as(HashMap.class);
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));




    }
}
