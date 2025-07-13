package lab.jpa_fetchjoin_paging.domain.repository.item;

import static lab.jpa_fetchjoin_paging.domain.entity.QOrderItemEntity.orderItemEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lab.jpa_fetchjoin_paging.domain.entity.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements CustomOrderItemRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<OrderItemEntity> findAllWithOrder(Pageable pageable) {
        List<OrderItemEntity> content = queryFactory
            .selectFrom(orderItemEntity)
            .join(orderItemEntity.order).fetchJoin()
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long total = queryFactory
            .select(orderItemEntity.count())
            .from(orderItemEntity)
            .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
