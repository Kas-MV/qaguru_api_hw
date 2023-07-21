package qa.guru.reqres;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class TestReqresApi {

    @Test
    void testPostRegistration() {
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .body("id", is(4));
    }

    @Test
    void testPostCreate() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .statusCode(201)
                .body("name", is("morpheus"),
                        "job", is("leader"));
    }

    @Test
    void testPostMissingPassword() {
        String data = "{ \"email\": \"peter@klaven\", \"error\": \"Missing password\"}";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void testPutUpdate() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(data)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .statusCode(200)
                .body("name", is("morpheus"),
                        "job", is("zion resident"));
    }

    @Test
    void testPatchUpdate() {
        String data = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(data)
                .when()
                .patch("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .statusCode(200)
                .body("name", is("morpheus"),
                        "job", is("zion resident"));
    }

    @Test
    void testDeleteUser() {

        given()
                .log().all()
                .param("page", 1)
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    void testGetListUser() {

        given()
                .log().all()
                .param("page", 2)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .log().all()
                .statusCode(200)
                .body("total_pages", is(2));
    }
}
