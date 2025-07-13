package lab.jpa_fetchjoin_paging.service;

import lab.jpa_fetchjoin_paging.domain.entity.OrderEntity;
import lab.jpa_fetchjoin_paging.domain.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Page<OrderEntity> getPagedOrders(Pageable pageable) {

        // ID만 먼저 페이징 사이즈에 맞게 쿼리
        List<Long> pageIds = orderRepository.findIds(pageable);

        // 연관 엔티티 fetch join (in쿼리)
        List<OrderEntity> orders = orderRepository.findAllWithItemsByIds(pageIds);

        return PageableExecutionUtils.getPage(
            orders,
            pageable,
            () -> orderRepository.countAll()
        );
    }

    public Page<OrderEntity> getPagedOrdersBadCase(Pageable pageable) {
        return orderRepository.doQueryBadCase(pageable);
    }
}
