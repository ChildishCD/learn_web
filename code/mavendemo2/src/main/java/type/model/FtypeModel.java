package type.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class FtypeModel {
    private Integer typeId;
    private String typeName;
    private Integer attribution;
}
