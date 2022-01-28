package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class MakeCopy {

    public static File getCopyOfProjectFile() {
        File file = new File("src/main/java/testData/Project.json");
        File copy = new File("src/main/java/testData/CopyProject" + System.currentTimeMillis() + ".json");

        try {
            FileUtils.copyFile(file, copy);
            return copy;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return copy;
    }
}
