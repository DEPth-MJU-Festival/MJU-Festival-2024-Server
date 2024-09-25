package depth.mju.festival.domain.item.domain;

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
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private School school;

    private String image;

    private String title;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Builder
    public Item(String image, String title, Category category, School school) {
        this.image = image;
        this.title = title;
        this.category = category;
        this.school = school;
    }
}
