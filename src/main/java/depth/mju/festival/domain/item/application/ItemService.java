package depth.mju.festival.domain.item.application;

import depth.mju.festival.domain.common.Status;
import depth.mju.festival.domain.item.domain.Category;
import depth.mju.festival.domain.item.domain.Item;
import depth.mju.festival.domain.item.domain.repository.ItemRepository;
import depth.mju.festival.domain.item.dto.response.ItemRes;
import depth.mju.festival.global.exception.DefaultException;
import depth.mju.festival.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    // 카테고리별 분실물 조회
    public List<ItemRes> findLostItemsByCategory(Category category) {
        List<Item> items = itemRepository.findByCategoryAndStatusOrderByCreatedDateDesc(category, Status.ACTIVE);
        return items.stream()
                .map(item -> ItemRes.builder()
                        .id(item.getId())
                        .title(item.getTitle())
                        .imageUrl(item.getImage())
                        .build())
                .collect(Collectors.toList());
    }

    // Description: 관리자 기능
    // 분실물 게시(제목 9자 이하)
    // public void createLostItem(MultipartFile image, CreateItemReq itemReq) { }


    // 분실물 삭제
    @Transactional
    public void deleteLostItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new DefaultException(ErrorCode.NOT_FOUND_ERROR, "유효한 값이 아닙니다."));
        // SOFT DELETE
        item.updateStatus(Status.DELETE);

    }

}
