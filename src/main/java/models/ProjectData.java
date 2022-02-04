package models;

import io.cucumber.java.mk_latn.No;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectData {

    private String gid;
    private String name;
    private String resource_type;

}
