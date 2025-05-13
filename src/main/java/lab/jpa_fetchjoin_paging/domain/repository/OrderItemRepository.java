package lab.jpa_fetchjoin_paging.domain.repository;

import lab.jpa_fetchjoin_paging.domain.entity.OrderItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    @Query(
            value = "SELECT oi FROM OrderItemEntity oi JOIN FETCH oi.order",
            countQuery = "SELECT COUNT(oi) FROM OrderItemEntity oi"
    )
    List<OrderItemEntity> findAllWithOrder(Pageable pageable);
    // Page<OrderItemEntity> findAllWithOrder(Pageable pageable);
}