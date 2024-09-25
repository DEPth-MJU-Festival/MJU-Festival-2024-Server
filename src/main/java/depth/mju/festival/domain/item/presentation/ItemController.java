package depth.mju.festival.domain.item.presentation;

import depth.mju.festival.domain.item.application.ItemService;
import depth.mju.festival.domain.item.domain.Category;
import depth.mju.festival.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
