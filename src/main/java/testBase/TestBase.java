package testBase;

import io.restassured.response.Response;
import utils.JsonConnector;
import utils.RequestBuilder;

import java.io.File;

public class TestBase {
    protected final static String DATA_PATH = "src/main/java/testData/Data.json";
    protected final static String WORKSPACE_PATH = "src/main/java/testData/Workspace.json";
    protected RequestBuilder requestBuilder;
    protected static Data data = JsonConnector.readData(new File(DATA_PATH));
    protected Response response;
}
