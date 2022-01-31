package models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectUpdateName {
    private Data data;

    @Getter
    @Setter
    public class Data {
        private String name;

    }
}


