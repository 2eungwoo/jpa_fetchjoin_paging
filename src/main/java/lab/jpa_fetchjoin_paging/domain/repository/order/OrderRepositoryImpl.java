package lab.jpa_fetchjoin_paging.domain.repository.order;

import static lab.jpa_fetchjoin_paging.domain.entity.QOrderEntity.orderEntity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lab.jpa_fetchjoin_paging.domain.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements CustomOrderRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> findIds(Pageable pageable) {
        return queryFactory
            .select(orderEntity.id)
            .from(orderEntity)
            .orderBy(orderEntity.id.asc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
    }

    @Override
    public List<OrderEntity> findAllWithItemsByIds(List<Long> ids) {
        return queryFactory
            .selectFrom(orderEntity)
            .distinct()
            .join(orderEntity.orderItems).fetchJoin()
            .where(orderEntity.id.in(ids))
            .fetch();
    }

    @Override
    public Long countAll() {
        return queryFactory
            .select(orderEntity.count())
            .from(orderEntity)
            .fetchOne();
    }

    @Override
    public Page<OrderEntity> doQueryBadCase(Pageable pageable) {
        List<OrderEntity> content = queryFactory
            .selectFrom(orderEntity)
            .distinct()
            .join(orderEntity.orderItems).fetchJoin()
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long total = queryFactory
            .select(orderEntity.countDistinct())
            .from(orderEntity)
            .fetchOne();

        return new PageImpl<>(content, pageable, total);
    }
}
