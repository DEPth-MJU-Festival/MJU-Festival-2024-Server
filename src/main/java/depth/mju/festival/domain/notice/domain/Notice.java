package depth.mju.festival.domain.notice.domain;

import depth.mju.festival.domain.common.BaseEntity;
import depth.mju.festival.domain.school.domain.School;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public Notice(String title, String content, School school) {
        this.title = title;
        this.content = content;
        this.school = school;
    }

}
