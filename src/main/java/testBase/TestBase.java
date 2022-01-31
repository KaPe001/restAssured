package testBase;

import io.restassured.response.Response;
import models.ProjectData;
import models.ProjectUpdateName;
import models.Workspace;
import utils.JsonConnector;
import utils.RequestBuilder;

import java.io.File;

public class TestBase {
    protected final static String DATA_PATH = "src/main/java/testData/Data.json";
    protected final static String WORKSPACE_PATH = "src/main/java/testData/Workspace.json";
    protected static final String PROJECT_PATH = "src/main/java/testData/Project.json";
    protected static final String PROJECT_DATA_PATH = "src/main/java/testData/ProjectData.json";
    protected static final String PROJECT_UPDATE_PATH = "src/main/java/testData/ProjectUpdateName.json";

    protected RequestBuilder requestBuilder;
    protected static Data data = JsonConnector.readData(new File(DATA_PATH), Data.class);
    protected static Workspace workspace = JsonConnector.readData(new File(WORKSPACE_PATH), Workspace.class);
    protected static ProjectData projectData = JsonConnector.readData(new File(PROJECT_DATA_PATH), ProjectData.class);
    protected Response response;
    protected ProjectUpdateName projectUpdateName = JsonConnector.readData(new File(PROJECT_UPDATE_PATH), ProjectUpdateName.class);
}
