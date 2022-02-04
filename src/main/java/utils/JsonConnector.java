package utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import models.ResponseBody;
import models.Workspace;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Wrapper;

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

    public static <T> T readDataForArray(File file, Class<T> classOfT) {
        Gson parser = new Gson();
        ResponseBody[] data = parser.fromJson(String.valueOf(file), ResponseBody[].class);
        return (T) data;
    }

    public static <T> T fillTheData(File file, Class<T> classOfT) {
        try {
            Gson parser = new Gson();
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            T t = parser.fromJson(buffer, classOfT);
            log.info("Object class has been loaded properly: {}", t);
            return t;
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
