package get_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.BookingPojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;

public class Get15ObjectMapper extends HerOkuAppBaseUrl {
    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {
    "firstname": "Trang",
    "lastname": "Bui",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2022-01-01",
        "checkout": "2023-01-01"
    },
    "additionalneeds": "Breakfast"
}
     */
    @Test
    public void get01(){
        //1. Step: Set the Url
        spec.pathParams("first","booking","second", 22);

        //2. Step: Set the expected Data
        String expectedData = "{\n" +
                "    \"firstname\": \"Trang\",\n" +
                "    \"lastname\": \"Bui\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2022-01-01\",\n" +
                "        \"checkout\": \"2023-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
        BookingPojo expectedDataPojo = JsonUtil.convertJsonToJavaObject(expectedData, BookingPojo.class);

        //3. Step: Send the Get Request get the Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //4. Step: Do Assertion
        BookingPojo actualDataPojo = JsonUtil.convertJsonToJavaObject(response.asString(), BookingPojo.class);

        //Hard Assertion
//        assertEquals(200, response.getStatusCode());
//        assertEquals(expectedDataPojo.getFirstname(),actualDataPojo.getFirstname());
//        assertEquals(expectedDataPojo.getLastname(),actualDataPojo.getLastname());
//        assertEquals(expectedDataPojo.getTotalprice(),actualDataPojo.getTotalprice());
//        assertEquals(expectedDataPojo.getDepositpaid(),actualDataPojo.getDepositpaid());
//        assertEquals(expectedDataPojo.getAdditionalneeds(),actualDataPojo.getAdditionalneeds());
//        assertEquals(expectedDataPojo.getBookingdates().getCheckin(),actualDataPojo.getBookingdates().getCheckin());
//        assertEquals(expectedDataPojo.getBookingdates().getCheckout(),actualDataPojo.getBookingdates().getCheckout());

        //Soft Assertion
        //1. Adım: SoftAssert objesi oluştur
        SoftAssert softAssert = new SoftAssert();

        //2. Adım: Assertion Yap
        softAssert.assertEquals(actualDataPojo.getFirstname(), expectedDataPojo.getFirstname(),"Firstname uyuşmadı");
        softAssert.assertEquals(actualDataPojo.getLastname(), expectedDataPojo.getLastname(),"Lastname uyuşmadı");
        softAssert.assertEquals(actualDataPojo.getTotalprice(), expectedDataPojo.getTotalprice(),"Total Price uyuşmadı");
        softAssert.assertEquals(actualDataPojo.getDepositpaid(), expectedDataPojo.getDepositpaid(),"Depositpaid uyuşmadı");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckin(), expectedDataPojo.getBookingdates().getCheckin(),"Checkin uyuşmadı");
        softAssert.assertEquals(actualDataPojo.getBookingdates().getCheckout(), expectedDataPojo.getBookingdates().getCheckout(),"Checkout uyuşmadı");

        //3. Adım: assertAll() methodunu çalıştır.
        softAssert.assertAll();


    }

}
