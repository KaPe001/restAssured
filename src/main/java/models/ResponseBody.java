package models;

import io.cucumber.java.eo.Se;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseBody {
    private List<Data> data;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public class Data {
        private String gid;
        private String name;
        private String resource_type;
    }
}
