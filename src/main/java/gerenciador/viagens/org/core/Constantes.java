package gerenciador.viagens.org.core;

import io.restassured.http.ContentType;

public interface Constantes {

    String APP_BASE_URL = "http://localhost:8089";
    Integer APP_PORT = 8089;
    String APP_BASE_PATH = "/api/v1";

    Long MAX_TIMEOUT = 5000L;
}
