package get_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get4Deneme extends JsonPlaceHolderBaseUrl {

    @Test
     public void get01(){
        spec.pathParams("first","todos");
        Response response= given().spec(spec).accept(ContentType.JSON).when().get("/{first}");
        response.prettyPrint();

        response.then().assertThat().statusCode(200).contentType("application/json").body("id",hasSize(200))
                .body("id", hasSize(200),
                        "title", hasItem("quis eius est sint explicabo"),
                        "userId",hasItems(2, 7, 9));
    }

}
