package gerenciador.viagens.org.tests;

import gerenciador.viagens.org.core.BaseTest;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CadastroViagens extends BaseTest {

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
    public void deveCadastrarUmaViagem() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"acompanhante\": \"Giovanna\",\n" +
                        "  \"dataPartida\": \"2023-07-08\",\n" +
                        "  \"dataRetorno\": \"2023-07-15\",\n" +
                        "  \"localDeDestino\": \"Manaus\",\n" +
                        "  \"regiao\": \"Norte\"\n" +
                        "}")
        .when()
                .post("/viagens")
        .then()
                .statusCode(201)
                .body("data.acompanhante", is("Giovanna"))
                .body("data.localDeDestino", is("Manaus"))
                .body("data.regiao", is("Norte"))
                .body("data.dataPartida", is("2023-07-08"))
                .body("data.dataRetorno", is("2023-07-15"))
        ;
    }
}
