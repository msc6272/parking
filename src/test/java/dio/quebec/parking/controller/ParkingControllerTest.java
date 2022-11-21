package dio.quebec.parking.controller;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;

import dio.quebec.parking.controller.dto.ParkingCreateDTO;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ParkingControllerTest extends AbstractContainerBase {
    @LocalServerPort
    private int randomPort;
    
    @BeforeEach
    public void setUpTest() {
        //System.out.println("Porta: " + randomPort);
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
            .header("Authorization", "Basic dXNlcjoxMjM0NQ==")
            //As duas linhas abaixo foram comentadas depois de reconfigurar o arquivo 'SwaggerConfig.java'
            /* .auth()
            .basic("user", "12345") */
            .when()
            .get("/parking")
            .then()
            .statusCode(200)
            //.body("license[0]", Matchers.equalTo("DMS-1111")); // Esta linha não funciona direito porque a ordem dos registros é aleatória, por causa do UUID
            .extract().response().body().prettyPrint();
    }

    @Test
    void whenCreateThenCheckIsCreated() {
        var createDTO = new ParkingCreateDTO();
        createDTO.setColor("AMARELA");
        createDTO.setLicense("WRT-5555");
        createDTO.setModel("BRASILIA");
        createDTO.setState("SP");
        
        RestAssured.given()
            .header("Authorization", "Basic dXNlcjoxMjM0NQ==")
            //As duas linhas abaixo foram comentadas depois de reconfigurar o arquivo 'SwaggerConfig.java'
            /* .auth()
            .basic("user", "12345") */
            .when()
            .body(createDTO)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .post("/parking")
            .then()
            .statusCode(HttpStatus.SC_CREATED)
            .body("license", Matchers.equalTo("WRT-5555"))
            .body("color", Matchers.equalTo("AMARELA"));
    }
}
