package stepsDefinitions;

import configuration.forAsana.Config;
import configuration.forAsana.PropertyForAsana;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks {
    Config config;

    @Before(order = 1)
    public void getTitle(Scenario scenario) {
        System.out.println("Start scenario " + scenario.getName());
        log.info("Scenario started properly");
    }

    @Before(order = 2)
    public void getData() {
        RestAssured.baseURI= config.getProperties().get("baseUri");
        RestAssured.basePath= config.getProperties().get("basePath");
        log.info("Commons taken from the configuration file properly");
    }
}
