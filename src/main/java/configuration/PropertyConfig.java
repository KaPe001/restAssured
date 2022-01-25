package configuration;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyConfig {

    Map<String, String> properties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonAnySetter
    public void setProperties(String key, String value) {
        properties.put(key, value);
    }
}
