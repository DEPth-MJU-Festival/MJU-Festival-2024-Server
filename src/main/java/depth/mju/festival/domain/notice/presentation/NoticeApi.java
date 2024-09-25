package depth.mju.festival.domain.notice.presentation;

import depth.mju.festival.domain.item.domain.Category;
import depth.mju.festival.domain.item.dto.response.ItemRes;
import depth.mju.festival.domain.notice.dto.request.CreateNoticeReq;
import depth.mju.festival.domain.notice.dto.response.NoticeRes;
import depth.mju.festival.global.exception.ErrorResponse;
import depth.mju.festival.global.response.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "공지사항 API", description = "공지사항과 관련한 API입니다.")
public interface NoticeApi {

    @Operation(summary = "공지사항 목록 조회", description = "공지사항을 목록으로 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "0", description = "공지사항 목록 조회 성공 - dataList 구성",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = NoticeRes.class)))}
            ),
            @ApiResponse(
                    responseCode = "200", description = "공지사항 목록 조회 성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PageResponse.class))}
            ),
            @ApiResponse(
                    responseCode = "400", description = "조회 실패",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @GetMapping
    ResponseEntity<depth.mju.festival.global.response.ApiResponse> findNotices(
            @Parameter(description = "페이지의 번호를 입력해주세요. 기본값은 1이며 페이지는 1부터 시작합니다.", required = true) @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "페이지 내 요소의 개수입니다. 기본값은 6입니다.") @RequestParam(defaultValue = "6") int size
    );

    @Operation(summary = "공지사항 상세 조회", description = "공지사항을 상세 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "공지사항 상세 조회 성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NoticeRes.class))}
            ),
            @ApiResponse(
                    responseCode = "400", description = "조회 실패",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @GetMapping("/{noticeId}")
    ResponseEntity<depth.mju.festival.global.response.ApiResponse> findNoticeDetail(
            @Parameter(description = "공지사항의 id를 입력해주세요.", required = true) @PathVariable Long noticeId
    );

    @Operation(summary = "공지사항 등록", description = "공지사항을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "등록 성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))}
            ),
            @ApiResponse(
                    responseCode = "400", description = "등록 실패",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @PostMapping
    ResponseEntity<Void> registerNotice(
            @Parameter(description = "Schemas의 CreateNoticeReq를 참고해주세요.", required = true) @Valid @RequestBody CreateNoticeReq createNoticeReq
    );

    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204", description = "삭제 성공",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))}
            ),
            @ApiResponse(
                    responseCode = "400", description = "삭제 실패",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @GetMapping("/{noticeId}")
    ResponseEntity<Void> deleteNotice(
            @Parameter(description = "공지사항의 id를 입력해주세요.", required = true) @PathVariable Long noticeId
    );
}
