package lab.jpa_fetchjoin_paging.domain.repository.item;

import lab.jpa_fetchjoin_paging.domain.entity.OrderItemEntity;
import lab.jpa_fetchjoin_paging.domain.repository.order.CustomOrderRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long>, CustomOrderRepository {
}