package gerenciador.viagens.org.tests;

import gerenciador.viagens.org.core.BaseTest;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ConsultaViagens extends BaseTest {

    @BeforeClass
    public static void authenticate() {
        String TOKEN = given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"usuario@email.com\", \"senha\": \"123456\"}")
        .when()
                .post("/auth")
        .then()
                .statusCode(200)
                .extract().path("data.token")
                ;

        requestSpecification.header("Authorization", TOKEN);
    }

    @Test
    public void retornaListaDeViagens() {
        given()
        .when()
                .get("/viagens")
        .then()
                .statusCode(200)
                .log().all()
                .body("data.id", hasSize(1))
                .body("data.acompanhante", hasItem("Giovanna"))
        ;
    }

    @Test
    public void retornaUmaViagemEspecifica() {
        given()
        .when()
                .get("/viagens/1")
        .then()
                .statusCode(200)
                .log().all()
                .body("data.acompanhante", is("Giovanna"))
                .body("data.localDeDestino", is("Manaus"))
                .body("data.regiao", is("Norte"))
        ;
    }

}
