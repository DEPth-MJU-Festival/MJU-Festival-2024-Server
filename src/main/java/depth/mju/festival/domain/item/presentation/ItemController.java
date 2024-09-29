package depth.mju.festival.domain.item.presentation;

import depth.mju.festival.domain.item.application.ItemService;
import depth.mju.festival.domain.item.domain.Category;
import depth.mju.festival.domain.item.domain.Item;
import depth.mju.festival.domain.item.dto.request.CreateItemReq;
import depth.mju.festival.global.exception.DefaultException;
import depth.mju.festival.global.exception.ErrorCode;
import depth.mju.festival.global.exception.ErrorResponse;
import depth.mju.festival.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/items")
public class ItemController implements ItemApi {

    private final ItemService itemService;

    @Override
    @GetMapping
    public ResponseEntity<ApiResponse> findLostItems(
            @RequestParam(defaultValue = "CLOTHES") Category category
    ){
        ApiResponse apiResponse = ApiResponse.builder()
                .check(true)
                .information(itemService.findLostItemsByCategory(category))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteLostItem(
            @PathVariable Long itemId
    ) {
        itemService.deleteLostItem(itemId);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping
    public ResponseEntity<?> registerLostItem(
            @RequestPart MultipartFile image,
            @RequestPart CreateItemReq createItemReq
    ) {
        try {
            Item item = itemService.createLostItem(image, createItemReq);
            URI location = UriComponentsBuilder.fromPath("/api/v1/items")
                    .queryParam("category", item.getCategory())
                    .build().toUri();
            return ResponseEntity.created(location).build();
        } catch (DefaultException e) {
            ErrorResponse errorResponse = ErrorResponse.of(
                    ErrorCode.NOT_VALID_ERROR,
                    e.getMessage()
            );
            return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
        }
    }


}
