package depth.mju.festival.domain.item.presentation;

import depth.mju.festival.domain.item.domain.Category;
import depth.mju.festival.domain.item.dto.response.ItemRes;
import depth.mju.festival.global.error.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "분실물 API", description = "분실물과 관련한 API입니다.")
public interface ItemApi {

    @Operation(summary = "분실물 조회", description = "카테고리별 분실물 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "조회 성공",
                    content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ItemRes.class)))}),
            @ApiResponse(
                    responseCode = "400", description = "조회 실패",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @GetMapping
    ResponseEntity<depth.mju.festival.global.response.ApiResponse> findLostItems(
            @Parameter(description = "카테고리를 입력해주세요. 기본 값은 CLOTHES(의류)이며, ELECTRONICS(전자기기), ACCESSORY(소지품), OTHER(기타)가 존재합니다.", required = true) @RequestParam(defaultValue = "CLOTHES") Category category
    );

}
