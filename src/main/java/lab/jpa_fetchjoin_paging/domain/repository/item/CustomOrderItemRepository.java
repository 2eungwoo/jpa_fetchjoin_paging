package lab.jpa_fetchjoin_paging.domain.repository.item;

import lab.jpa_fetchjoin_paging.domain.entity.OrderItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomOrderItemRepository {
    Page<OrderItemEntity> findAllWithOrder(Pageable pageable);
}
