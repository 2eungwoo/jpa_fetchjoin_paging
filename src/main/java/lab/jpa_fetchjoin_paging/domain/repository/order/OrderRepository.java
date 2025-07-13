package lab.jpa_fetchjoin_paging.domain.repository.order;

import lab.jpa_fetchjoin_paging.domain.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, CustomOrderRepository {
}