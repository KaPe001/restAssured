package utils;

import com.google.gson.Gson;
import models.Workspace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testBase.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JsonConnector {
    static Logger logger = LoggerFactory.getLogger(JsonConnector.class);

    public static Data readData(File file) {
        try {
            Gson parser = new Gson();
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            Data data = parser.fromJson(buffer, Data.class);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Workspace fillWorkspaceData(File file) {
        try {
            Gson parser = new Gson();
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            Workspace workspace = parser.fromJson(buffer, Workspace.class);
            logger.info("Workspace has been loaded properly: {}", workspace);
            return workspace;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String jsonSerializerWorkspace(Workspace workspace) {
        Gson gson = new Gson();
        return gson.toJson(workspace);
    }
}
