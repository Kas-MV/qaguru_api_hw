package qa.guru.state;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseState {

    @BeforeAll
    static public void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }
}
