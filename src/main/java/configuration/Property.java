package configuration;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Property {
    private String baseUri;
    private String basePath;
    private String appid;
    private Long expectedRespTime;
    private int expectedSuccessCode;

}
