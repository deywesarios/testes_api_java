package gerenciador.viagens.org.tests;

import gerenciador.viagens.org.core.BaseTest;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class DeletarViagens extends BaseTest {

    @BeforeClass
    public static void authenticate() {
        String TOKEN = given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"admin@email.com\", \"senha\": \"654321\"}")
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .extract().path("data.token")
                ;

        requestSpecification.header("Authorization", TOKEN);
    }

    @Test
    public void deveDeletarViagens () {
        given()
        .when()
                .delete("/viagens/1")
        .then()
                .statusCode(204)
        ;
    }
}
