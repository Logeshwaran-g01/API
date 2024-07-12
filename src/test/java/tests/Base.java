package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class Base {
    static RequestSpecification requestSpecification;
    static ResponseSpecification responseSpecification;
    ObjectMapper objectMapper = new ObjectMapper();

    @BeforeTest
    public void setUp(){
        RestAssured.baseURI ="https://gorest.co.in/public/v2/";
        requestSpecification = given().
                header(new Header("Accept","application/json")).
                header(new Header("Content-Type","application/json")).
                header(new Header("Authorization","Bearer b071eb57b5ddda410371d9f61f085c05334003dde4925a4eaf36ded0651300cc")).
                log().all();
        responseSpecification = new ResponseSpecBuilder().log(LogDetail.ALL).build();
    }

    public static Response get(String endPoint){
        return given(requestSpecification).
                when().
                get(endPoint).
                then().spec(responseSpecification).
                extract().response();
    }
    public static Response post(String endPoint, Object reqBody){
        return given(requestSpecification).
                body(reqBody).
                when().
                post(endPoint).
                then().spec(responseSpecification).
                extract().response();
    }

    public static Response patch(String endPoint, Object reqBody){
        return given(requestSpecification).
                body(reqBody).
                when().
                patch(endPoint).
                then().spec(responseSpecification).
                extract().response();
    }
}
