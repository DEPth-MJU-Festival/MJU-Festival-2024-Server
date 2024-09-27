package depth.mju.festival.domain.notice.domain.repository;

import depth.mju.festival.domain.common.Status;
import depth.mju.festival.domain.notice.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Page<Notice> findByStatus(Status status, Pageable pageable);

    Optional<Notice> findByIdAndStatus(Long noticeId, Status status);
}
