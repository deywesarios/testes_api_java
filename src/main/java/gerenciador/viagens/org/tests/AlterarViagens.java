package gerenciador.viagens.org.tests;

import gerenciador.viagens.org.core.BaseTest;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class AlterarViagens extends BaseTest {

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
    public void deveAlterarUmaViagem() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"acompanhante\": \"Lucieny\",\n" +
                        "  \"dataPartida\": \"2023-11-11\",\n" +
                        "  \"dataRetorno\": \"2023-12-12\",\n" +
                        "  \"localDeDestino\": \"São Paulo\",\n" +
                        "  \"regiao\": \"Suldeste\"\n" +
                        "}")
        .when()
                .put("/viagens/1")
        .then()
                .statusCode(204) // Há um erro no swagger da API onde informa que o status code deveria ser 200 e retornar o body da alteração,
                                // quando na verdade ele só altera a viagem e retorna um 204.
        ;
    }
}