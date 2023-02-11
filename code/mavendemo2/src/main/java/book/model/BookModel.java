package book.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookModel {
    private Long bookId;
    private String bookName;
    private String authorName;
    private Integer attribution;
    private Integer ftypeId;
    private Integer stypeId;
    private Integer status;
    private Integer isVip;
    private String description;
    private String coverUrl;
    private String keyword;
    private String wordCount;
    private Integer lastUpdateChapterId;
    private String lastUpdateChapterName;
    private Integer isRecommand;

}
