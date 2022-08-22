package get_request;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {
     /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Dron Varma", "Ganesh Sinha", "Prof. Lai Devar" are among the users
        And
            The female users are more than or equals to male users
     */
    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParam("first", "users");

        //2. Set the Expected Data

        //3. Step: Send the Request get the Response
        Response response= given().spec(spec).when().get("/{first}");
        //response.prettyPrint();

        //4. Step: Do Assertion
        response.then().assertThat().
                statusCode(200).
                body("meta.pagination.limit", equalTo(10),
                "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id", hasSize(10),
                        "data.status", hasItem("active"),// contains gibi icerip icermedigini kontrol eder.
                        "data.name", hasItems("Ms. Agnimitra Trivedi", "Dr. Bhima Malik", "Chapal Pothuvaal LLD"));
        // "data.id", hasSize(10)= bu komut [ ] list halinde oldugunu bildigimiz body  deki id nin boyunu listteki hasSize metodu ile bulduk.
        // "data[0].id", hasSize(10) bu komutla index i 1 olan id yi verir.

        //Bayan ve erkek sayısını karşılaştıralım
        //1. Yol: Tüm cinisyetli çekip bayan sayısı ile karşılaştıralım.

        JsonPath json= response.jsonPath();
        List<String>genders= json.getList("data.gender");
        System.out.println(genders);

        int numOfFemales=0;

        for (String w: genders) {
            if(w.equals("female")){
                numOfFemales++;
            }
        }
        System.out.println(numOfFemales);

        assertTrue(numOfFemales>genders.size()-numOfFemales);

        //2. Yol:  Tüm bayan ve bayları ayrı ayrı Groovy ile çekelim.
        List<String> femaleList = json.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println(femaleList);

        List<String> maleList = json.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(maleList);

        assertTrue(femaleList.size()>maleList.size());


    }

}


