package depth.mju.festival.domain.item.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRes {

    private Long id;

    private String title;

    private String imageUrl;


    @Builder
    public ItemRes(Long id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }
}
