package depth.mju.festival.domain.notice.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateNoticeReq {

    @NotBlank
    @Schema(type = "String", example = "[공지사항] 2024 축제 안내", description = "공지의 내용입니다.")
    private String title;

    @NotBlank
    @Schema(type = "String", example = "축제 일정은 10월 7일 월요일부터 8일 화요일까지이며...", description = "공지의 내용입니다.")
    private String content;

}
