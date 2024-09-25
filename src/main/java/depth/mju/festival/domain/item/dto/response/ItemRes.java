package depth.mju.festival.domain.item.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRes {

    @Schema(type = "Long", example = "1", description = "분실물의 id입니다.")
    private Long id;

    @Schema(type = "String", example = "이어폰", description = "분실물 게시글의 제목입니다.")
    private String title;

    @Schema(type = "String", example = "https://mju-festival-bucket.s3.amazonaws.com/014edc6a-48d0-4f11-8379-a4e48ba61402.jpg", description= "분실물 이미지 URL입니다.")
    private String imageUrl;


    @Builder
    public ItemRes(Long id, String title, String imageUrl) {
        this.id = id;
        this.title = title;
        this.imageUrl = imageUrl;
    }
}
