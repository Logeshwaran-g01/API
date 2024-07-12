package tests;

import Utilities.EndPoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GoRest extends Base {


    int userId;
    @Test
    public void getAllUsers(){

        Response response = get(EndPoint.USER_ENDPOINT);
        System.out.println(response.body().asString());
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test
    public void createAllUsers() throws JsonProcessingException {

        User user = new User();
        user.setName("Lokeshw");
        user.setEmail("Lokki19"+System.currentTimeMillis()+"904@gmail.com");
        user.setGender("male");
        user.setStatus("active");

        Response response =post (EndPoint.USER_ENDPOINT, user);
        User responseUser = objectMapper.readValue(response.body().asString(), User.class);
        userId = responseUser.getId();
        Assert.assertEquals(response.getStatusCode(),201);
        Assert.assertEquals(responseUser.getName(),user.getName());
    }

    @Test
    public void updateAllUsers() throws JsonProcessingException {
        User user = new User();
        user.setName("Lokeshwaran");
        user.setEmail("Lokki19"+System.currentTimeMillis()+"904@gmail.com");
        user.setGender("male");
        user.setStatus("active");
        Response response =patch (EndPoint.USER_ENDPOINT+"/"+ userId, user);
        User responseUser = objectMapper.readValue(response.body().asString(), User.class);
        Assert.assertEquals(response.getStatusCode(),404);
        Assert.assertEquals(responseUser.getName(),user.getName());
    }
}
