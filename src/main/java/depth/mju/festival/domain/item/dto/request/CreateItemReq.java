package depth.mju.festival.domain.item.dto.request;

import depth.mju.festival.domain.item.domain.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateItemReq {

    @Schema(type = "String", example = "이어폰", description = "분실물 게시글의 제목입니다.")
    @NotBlank
    @Size(max = 9, message = "제목은 9자 이하까지 가능합니다.")
    private String title;

    @Schema(type = "Category", example = "ELECTRONICS", description = "분실물의 카테고리입니다. CLOTHES(의류), ELECTRONICS(전자기기), ACCESSORY(소지품), OTHER(기타)가 존재합니다.")
    @NotNull
    private Category category;
}
