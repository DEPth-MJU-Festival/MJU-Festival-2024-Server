package depth.mju.festival.domain.notice.application;

import depth.mju.festival.domain.common.Status;
import depth.mju.festival.domain.notice.domain.Notice;
import depth.mju.festival.domain.notice.domain.repository.NoticeRepository;
import depth.mju.festival.domain.notice.dto.response.NoticeRes;
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

    // 공지 등록

    // 공지 삭제

    // 공지 수정
}
