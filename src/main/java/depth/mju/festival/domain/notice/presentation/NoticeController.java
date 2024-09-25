package depth.mju.festival.domain.notice.presentation;

import depth.mju.festival.domain.notice.application.NoticeService;
import depth.mju.festival.domain.notice.domain.Notice;
import depth.mju.festival.domain.notice.dto.request.NoticeReq;
import depth.mju.festival.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notices")
public class NoticeController implements NoticeApi {

    private final NoticeService noticeService;

    @Override
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

    @Override
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

    @Override
    @PostMapping()
    public ResponseEntity<Void> registerNotice(
            @Valid @RequestBody NoticeReq noticeReq
    ) {
        Notice notice = noticeService.createNotice(noticeReq);
        return ResponseEntity.created(URI.create("/api/v1/notices" + notice.getId())).build();
    }

    @Override
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<Void> deleteNotice(
            @PathVariable Long noticeId
    ) {
        noticeService.deleteNotice(noticeId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{noticeId}")
    public ResponseEntity<Void> modifyNotice(
            @PathVariable Long noticeId,
            @Valid @RequestBody NoticeReq noticeReq
    ) {
        noticeService.updateNotice(noticeId, noticeReq);
        return ResponseEntity.noContent().build();
    }
}
