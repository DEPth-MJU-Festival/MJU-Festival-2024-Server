package depth.mju.festival.domain.notice.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class NoticeRes {

    @Schema(type = "Long", example = "1", description = "공지의 id입니다.")
    private Long noticeId;

    @Schema(type = "String", example = "[공지사항] 2024 축제 안내", description = "공지의 내용입니다.")
    private String title;

    @Schema(type = "String", example = "축제 일정은 10월 7일 월요일부터 8일 화요일까지이며...", description = "공지의 내용입니다.")
    private String content;

    @Schema(type = "LocalDate", example = "2024-04-26", description = "공지의 작성일입니다.")
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate createdDate;

    @Builder
    public NoticeRes(Long noticeId, String title, String content, LocalDate createdDate) {
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
    }

}
