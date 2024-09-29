package depth.mju.festival.domain.item.application;

import depth.mju.festival.domain.common.Status;
import depth.mju.festival.domain.item.domain.Category;
import depth.mju.festival.domain.item.domain.Item;
import depth.mju.festival.domain.item.domain.repository.ItemRepository;
import depth.mju.festival.domain.item.dto.request.CreateItemReq;
import depth.mju.festival.domain.item.dto.response.ItemRes;
import depth.mju.festival.domain.school.domain.School;
import depth.mju.festival.domain.school.domain.repository.SchoolRepository;
import depth.mju.festival.global.exception.DefaultException;
import depth.mju.festival.global.exception.ErrorCode;
import depth.mju.festival.infrastructure.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;
    private final SchoolRepository schoolRepository;
    private final S3Service s3Service;

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
    // 분실물 게시
    @Transactional
    public Item createLostItem(MultipartFile image, CreateItemReq itemReq) {
        // @RequestPart CreateItemReq 유효성 검증
        validateCreateItemReq(itemReq);

        String fileName = s3Service.uploadImage(image);
        Item item = Item.builder()
                .school(findDefaultSchool())
                .title(itemReq.getTitle())
                .category(itemReq.getCategory())
                .image(fileName)
                .build();
        itemRepository.save(item);
        return item;
    }

    private void validateCreateItemReq(CreateItemReq itemReq) {
        // 제목 유효성 검증
        String title = itemReq.getTitle();
        if (title == null || title.isEmpty() || title.length() > 9) {
            throw new DefaultException(ErrorCode.NOT_VALID_ERROR, "제목은 9자 이하만 가능합니다.");
        }
        // 카테고리 유효성 검증
        if (itemReq.getCategory() == null) {
            throw new DefaultException(ErrorCode.NOT_VALID_ERROR, "카테고리가 유효하지 않습니다.");
        }
    }

    private School findDefaultSchool() {
        return schoolRepository.findById(1L)
                .orElseThrow(() -> new DefaultException(ErrorCode.NOT_FOUND_ERROR, "학교를 찾을 수 없습니다."));
    }


    // 분실물 삭제
    @Transactional
    public void deleteLostItem(Long itemId) {
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new DefaultException(ErrorCode.NOT_FOUND_ERROR, "유효한 값이 아닙니다."));
        // SOFT DELETE
        item.updateStatus(Status.DELETE);

    }

}
