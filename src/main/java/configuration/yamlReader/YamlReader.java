package configuration.yamlReader;

import configuration.Property;
import configuration.PropertyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class YamlReader {
    public Logger logger = LoggerFactory.getLogger(YamlReader.class);
    private List<Property> properties;

    public YamlReader() {
        try {
            Yaml yaml = new Yaml(new Constructor(PropertyConfig.class));
            PropertyConfig config = yaml.load(new FileInputStream("src/main/resources/config.yaml"));
            this.properties = config.getProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Environment configuration loaded from the yaml file");
    }
}