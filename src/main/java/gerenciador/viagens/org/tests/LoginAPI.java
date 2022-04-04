package gerenciador.viagens.org.tests;

import gerenciador.viagens.org.core.BaseTest;
import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LoginAPI extends BaseTest {

    @Test
    public void deveAutenticarNaAplicacao() {
        String TOKEN = given()
                .body("{\"email\": \"admin@email.com\", \"senha\": \"654321\"}")
                .contentType(ContentType.JSON)
        .when()
                .post("/auth")
        .then()
                .statusCode(200)
                .extract().path("data.token")
        ;
    }

    @Test
    public void deveRetornarUnauthorized() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"login_errado@email.com\", \"senha\": \"654321\"}")
        .when()
                .post("/auth")
        .then()
                .statusCode(401)
                .body("message", is("Acesso negado. Você deve estar autenticado no sistema para acessar a URL solicitada."))
        ;
    }

}
