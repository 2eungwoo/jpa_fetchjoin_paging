package lab.jpa_fetchjoin_paging.service;

import lab.jpa_fetchjoin_paging.domain.entity.OrderItemEntity;
import lab.jpa_fetchjoin_paging.domain.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public Page<OrderItemEntity> getPagedOrderItems(Pageable pageable) {
        List<OrderItemEntity> orderItems = orderItemRepository.findAllWithOrder(pageable);
        // Page<OrderItemEntity> orderItems = orderItemRepository.findAllWithOrder(pageable);

        long total = orderItemRepository.count(); // JpaRepository.count()

        return new PageImpl<>(orderItems, pageable, total);
        // return new PageImpl<>(orderItems.getContent(), pageable, total);
    }
}