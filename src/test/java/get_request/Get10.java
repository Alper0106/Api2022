package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;

public class Get10 extends GoRestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2986
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like

                 {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Udai Panicker Jr.",
        "email": "jr_udai_panicker@dickinson.info",
        "gender": "male",
        "status": "inactive"
    }
}
     */

    @Test
    public void get01(){

        //1. Step: Set the Url
        spec.pathParams("first","users","second",2965);

        //2. Step: Set the expected data
        GoRestTestData dataKey=new GoRestTestData();//Gerekli methodun çağrılması için obje oluşturuyorum.
        Map<String,String>dataKeyMap=dataKey.dataKeyMap("Udai Panicker Jr.", "jr_udai_panicker@dickinson.info", "male", "inactive");
                //İç Map'i oluşturdum.

        Map<String , Object> expectedData = dataKey.expectedDataMap(null,dataKeyMap);//Üst Map'i oluşturan method.
                //Dis Map'i oluşturdum.

        //3. Step: Send the request and Get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");

        Map<String,Object>actualData= response.as(HashMap.class);//De-Serialization==> Json formatından Java Objesine çevirme
        System.out.println(actualData);

        //4. Step: Do Assertion
        assertEquals(expectedData.get("meta"), actualData.get("meta") );
        assertEquals(dataKeyMap.get("name"), ((Map)actualData.get("data")).get("name") );//Önce "data" elementine ulaşıp buradan aldığım objeyi Map formatına cast ediyorum.
        assertEquals(dataKeyMap.get("email"), ((Map)actualData.get("data")).get("email") );
        assertEquals(dataKeyMap.get("gender"), ((Map)actualData.get("data")).get("gender") );
        assertEquals(dataKeyMap.get("status"), ((Map)actualData.get("data")).get("status") );




    }



}
