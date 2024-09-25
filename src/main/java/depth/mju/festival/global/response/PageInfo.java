package depth.mju.festival.global.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ToString
@Getter
public class PageInfo {

    @Schema(type = "Integer", example = "0", description = "현재 페이지. 0부터 시작. (요청한 페이지)")
    private Integer currentPage;

    @Schema(type = "Integer", example = "5", description = "전체 페이지 개수")
    private Integer totalPage;

    @Schema(type = "Integer", example = "13", description = "한 페이지의 사이즈")
    private Integer pageSize;

    @Schema(type = "Long", example = "33", description = "전체 요소 개수")
    private Long totalElements;

    @Schema(type = "boolean", example = "true", description = "다음 페이지의 존재 여부")
    private boolean hasNext;

    @Schema(type = "boolean", example = "true", description = "시작페이지 여부")
    private boolean isFirst;

    public PageInfo(){};

    @Builder
    public PageInfo(Integer currentPage, Integer totalPage, Integer pageSize, Long totalElements, boolean hasNext, boolean isFirst) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.hasNext = hasNext;
        this.isFirst = isFirst;
    }

    public static PageInfo toPageInfo(Pageable pageable, Page<?> pageContent) {
        return PageInfo.builder()
                .currentPage(pageable.getPageNumber() + 1)
                .totalPage(pageContent.getTotalPages())
                .pageSize(pageable.getPageSize())
                .totalElements(pageContent.getTotalElements())
                .hasNext(pageContent.hasNext())
                .isFirst(pageContent.isFirst())
                .build();
    }
}
