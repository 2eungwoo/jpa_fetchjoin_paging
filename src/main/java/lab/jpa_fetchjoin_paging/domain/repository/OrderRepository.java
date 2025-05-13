package lab.jpa_fetchjoin_paging.domain.repository;

import lab.jpa_fetchjoin_paging.domain.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(
            value = "SELECT DISTINCT o FROM OrderEntity o JOIN FETCH o.orderItems",
            countQuery = "SELECT COUNT(DISTINCT o) FROM OrderEntity o"
    )
    Page<OrderEntity> doQueryBadCase(Pageable pageable);

    @Query("SELECT o.id FROM OrderEntity o")
    List<Long> findIds(Pageable pageable);

    @Query("SELECT DISTINCT o FROM OrderEntity o " +
            "JOIN FETCH o.orderItems " +
            "WHERE o.id IN :ids")
    List<OrderEntity> findAllWithItemsByIds(List<Long> ids);

    @Query("SELECT COUNT(o) FROM OrderEntity o")
    long countAll();
}