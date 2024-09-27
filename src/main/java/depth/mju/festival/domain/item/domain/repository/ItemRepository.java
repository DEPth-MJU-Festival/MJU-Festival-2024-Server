package depth.mju.festival.domain.item.domain.repository;

import depth.mju.festival.domain.common.Status;
import depth.mju.festival.domain.item.domain.Category;
import depth.mju.festival.domain.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value =
            "SELECT i " +
                    "FROM Item i " +
                    "WHERE i.category = :category AND i.status = :status " +
                    "ORDER BY i.createdDate DESC")
    List<Item> findByCategoryAndStatusOrderByCreatedDateDesc(Category category, Status status);
}
