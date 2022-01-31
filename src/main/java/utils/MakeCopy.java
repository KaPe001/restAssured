package utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class MakeCopy {

    public static File getCopyOfProjectFile() throws IOException {

        File original = new File("src/main/java/testData/Project.json");
        File copy = new File("src/main/java/testData/CopyProject" + System.currentTimeMillis() + ".json");

        FileUtils.copyFile(original, copy);

        return copy;
    }
}
