package lab.jpa_fetchjoin_paging.domain.repository.order;

import java.util.List;
import lab.jpa_fetchjoin_paging.domain.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomOrderRepository {
    Page<OrderEntity> doQueryBadCase(Pageable pageable);

    List<Long> findIds(Pageable pageable);

    List<OrderEntity> findAllWithItemsByIds(List<Long> ids);

    Long countAll();
}
