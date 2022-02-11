package configuration.yamlReader;

import configuration.PropertyConfig;
import configuration.forAsana.Config;
import configuration.forAsana.PropertyForAsana;
import lombok.extern.slf4j.Slf4j;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class YamlReader {
    private Map<String, String> properties;
    private static final String BROWSER_CONFIG = "src/main/resources/config.yaml";
    private static final String ASANA_CONFIG = "src/main/resources/newConfig.yaml";

    public YamlReader() {
        try {
            Yaml yaml = new Yaml(new Constructor(PropertyConfig.class));
            PropertyConfig config = yaml.load(new FileInputStream(BROWSER_CONFIG));
            this.properties = config.getProperties();

            Yaml yamlAsana = new Yaml(new Constructor(Config.class));
            Config configForAsana = yamlAsana.load(new FileInputStream(ASANA_CONFIG));
            this.properties = configForAsana.getProperties();

            assertNotNull(configForAsana);

        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("Environment configuration loaded from the yaml file");
    }
}