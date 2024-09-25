package depth.mju.festival.domain.notice.application;

import depth.mju.festival.domain.common.Status;
import depth.mju.festival.domain.notice.domain.Notice;
import depth.mju.festival.domain.notice.domain.repository.NoticeRepository;
import depth.mju.festival.domain.notice.dto.request.CreateNoticeReq;
import depth.mju.festival.domain.notice.dto.response.NoticeRes;
import depth.mju.festival.domain.school.domain.School;
import depth.mju.festival.domain.school.domain.repository.SchoolRepository;
import depth.mju.festival.global.exception.DefaultException;
import depth.mju.festival.global.exception.ErrorCode;
import depth.mju.festival.global.response.PageInfo;
import depth.mju.festival.global.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final SchoolRepository schoolRepository;

    // 공지 조회
    public PageResponse findAllNotice(int page, int size) {
        String sortBy = "createdDate";
        Sort.Direction direction = Sort.Direction.DESC;

        Pageable pageable = PageRequest.of(page - 1, size, direction, sortBy);
        Page<Notice> noticePage = noticeRepository.findByStatus(Status.ACTIVE, pageable);

        List<NoticeRes> noticeRes = noticePage.stream()
                .map(notice -> NoticeRes.builder()
                        .noticeId(notice.getId())
                        .title(notice.getTitle())
                        .content(notice.getContent())
                        .createdDate(notice.getCreatedDate())
                        .build())
                .collect(Collectors.toList());

        PageInfo pageInfo = PageInfo.toPageInfo(pageable, noticePage);
        return PageResponse.toPageResponse(pageInfo, noticeRes);
    }

    // 공지 상세 조회
    public NoticeRes findNoticeDetail(Long noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new DefaultException(ErrorCode.NOT_FOUND_ERROR, "유효한 값이 아닙니다."));
        return NoticeRes.builder()
                .noticeId(notice.getId())
                .title(notice.getTitle())
                .content(notice.getContent())
                .createdDate(notice.getCreatedDate())
                .build();
    }

    // 공지 등록
    @Transactional
    public Notice createNotice(CreateNoticeReq noticeReq) {
        return Notice.builder()
                .title(noticeReq.getTitle())
                .content(noticeReq.getContent())
                .school(findDefaultSchool())
                .build();
    }

    private School findDefaultSchool() {
        return schoolRepository.findById(1L)
                .orElseThrow(() -> new DefaultException(ErrorCode.NOT_FOUND_ERROR, "학교를 찾을 수 없습니다."));
    }


    // 공지 삭제

    // 공지 수정
}
