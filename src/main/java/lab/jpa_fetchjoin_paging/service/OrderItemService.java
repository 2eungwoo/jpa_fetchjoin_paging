package lab.jpa_fetchjoin_paging.service;

import lab.jpa_fetchjoin_paging.domain.entity.OrderItemEntity;
import lab.jpa_fetchjoin_paging.domain.repository.item.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public Page<OrderItemEntity> getPagedOrderItems(Pageable pageable) {
        return orderItemRepository.findAllWithOrder(pageable);
    }
}