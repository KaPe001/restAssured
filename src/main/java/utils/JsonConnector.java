package utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import models.Workspace;
import org.apache.commons.io.FileUtils;
import testBase.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Slf4j
public class JsonConnector {

    public static <T> T readData(File file, Class<T> classOfT) {
        try {
            Gson parser = new Gson();
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            return parser.fromJson(buffer, classOfT);
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
            log.info("Workspace has been loaded properly: {}", workspace);
            return workspace;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File updateJsonFile(File file, String value, String replacement) throws IOException {
        String jsonString = FileUtils.readFileToString(file);

        String updatedJsonString = jsonString.replace(value, replacement);

        JsonElement jelement = new JsonParser().parse(updatedJsonString);

        Gson gson = new Gson();
        String resultingJson = gson.toJson(jelement);
        FileUtils.writeStringToFile(file, resultingJson);
        log.info("File: {} updated", file);
        return file;
    }

    public static String jsonSerializerWorkspace(Workspace workspace) {
        Gson gson = new Gson();
        return gson.toJson(workspace);
    }
}
