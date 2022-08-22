package get_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;

public class Get07 extends JsonPlaceHolderBaseUrl {
    /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200

			 2)Print all ids greater than 190 on the console(id si 190 dan buyuk olanlari yazdir)
			   Assert that there are 10 ids greater than 190(190 dan buyuk 10 tane id oldugunu bana goster)
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4(5 ten kucuk 4 tane oldugunu ispatla)
			 4)Print all titles whose ids are less than 5(id leri 5 ten kucuk olanlarin basliklarini yazdir)
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */
    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParam("first","todos");

        //2. Step: Set the expected data


        //3. Step: Send the request and get the response

        Response response = given().spec(spec).when().get("/{first}");
        // response.prettyPrint();

        //4. Step: Do Assertion

        response.then().assertThat().statusCode(200);

        //2)Print all ids greater than 190 on the console
        JsonPath json= response.jsonPath();
       List<Integer>ids= json.get("findAll{it.id>190}.id");//Groovy Language = Java temelli bir proglamlama dili
        System.out.println(ids);

        //Assert that there are 10 ids greater than 190
        assertEquals(10,ids.size());

        // Print all titles whose ids are less than 5(id leri 5 ten kucuk olanlarin basliklarini yazdir)
        List<String>sdl= json.get("findAll{it.id<5}.title");
        System.out.println(sdl);
        assertEquals(4,sdl.size());

        // Assert that "delectus aut autem" is one of the titles whose id is less than 5
        assertTrue(sdl.contains("delectus aut autem"));



    }

}
