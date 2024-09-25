package depth.mju.festival.domain.item.presentation;

import depth.mju.festival.domain.item.application.ItemService;
import depth.mju.festival.domain.item.domain.Category;
import depth.mju.festival.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

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


}
