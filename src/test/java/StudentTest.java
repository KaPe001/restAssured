import io.restassured.http.ContentType;
import models.Student;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class StudentTest {

    HashMap<String, String> studentMap = new HashMap<>();
    Student student;

    @Test
    public void postStudentUsingString() {
        String requestBody =
                "{\n" +
                        "    \"first_name\": \"Angelina\",\n" +
                        "    \"middle_name\": \"Jolie\",\n" +
                        "    \"last_name\": \"Camila\",\n" +
                        "    \"date_of_birth\": \"01/03/1977\"\n" +
                        "}";

        given()
                .baseUri("http://www.thetestingworldapi.com/")
                .basePath("api/studentsDetails")
                .contentType(ContentType.JSON)
                .log()
                .body().
        when()
                .body(requestBody)
                .post().
        then()
                .log()
                .all()
                .statusCode(201);
    }

    @Test
    public void postStudentByPuttingToMap() {

        studentMap.put("first_name","Brad");
        studentMap.put("middle_name","Janusz");
        studentMap.put("last_name","Pitt");
        studentMap.put("date_of_birth","01/03/1977");

        given()
                .baseUri("http://www.thetestingworldapi.com/")
                .basePath("api/studentsDetails")
                .contentType(ContentType.JSON)
                .log()
                .body().
        when()
                .body(studentMap)
                .post().
        then()
                .log()
                .all()
                .statusCode(201);
    }

    @Test
    public void postStudentFromConstructor() {
        student = new Student("Salma", "Joanna", "Hayek", "01/03/1977");

        given()
                .baseUri("http://www.thetestingworldapi.com/")
                .basePath("api/studentsDetails")
                .contentType(ContentType.JSON)
                .log()
                .body().
        when()
                .body(student)
                .post().
        then()
                .log()
                .all()
                .statusCode(201);
    }

    @Test
    public void postStudentFromFile() {

        given()
                .baseUri("http://www.thetestingworldapi.com/")
                .basePath("api/studentsDetails")
                .contentType(ContentType.JSON)
                .log()
                .body().
        when()
                .body(new File("src/test/resources/student.json"))
                .post().
        then()
                .log()
                .all()
                .statusCode(201);
    }
}
