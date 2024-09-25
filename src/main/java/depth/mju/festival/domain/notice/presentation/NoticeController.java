package depth.mju.festival.domain.notice.presentation;

import depth.mju.festival.domain.notice.application.NoticeService;
import depth.mju.festival.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notices")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity<ApiResponse> findNotices(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "6") int size
    ) {
        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(noticeService.findAllNotice(page, size))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{noticeId}")
    public ResponseEntity<ApiResponse> findNoticeDetail(
            @PathVariable Long noticeId
    ) {
        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(noticeService.findNoticeDetail(noticeId))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
