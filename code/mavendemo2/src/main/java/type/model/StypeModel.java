package type.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StypeModel {
    private Integer typeId;
    private String typeName;
    private Integer attribution;
    private Integer ftypeId;
}
