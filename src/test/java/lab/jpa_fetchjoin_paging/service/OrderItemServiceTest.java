package lab.jpa_fetchjoin_paging.service;

import lab.jpa_fetchjoin_paging.domain.entity.OrderEntity;
import lab.jpa_fetchjoin_paging.domain.entity.OrderItemEntity;
import lab.jpa_fetchjoin_paging.domain.repository.OrderItemRepository;
import lab.jpa_fetchjoin_paging.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderItemServiceTest {

    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        // 테스트 데이터를 설정하는 메서드
        createTestOrderItems();
    }

    private void createTestOrderItems() {
        for (int i = 1; i <= 5; i++) {
            OrderEntity order = new OrderEntity();
            order.setName("Order " + i);
            orderRepository.save(order);

            for (int j = 1; j <= 2; j++) {
                OrderItemEntity item = new OrderItemEntity();
                item.setItemName("Item " + j);
                item.setOrder(order);
                orderItemRepository.save(item);
            }
        }
    }

    @Test
    public void testToOneFetchJoinPaginationWorksCorrectly() {

        // given : 두 번째 페이지, 2개씩
        PageRequest pageable = PageRequest.of(1, 2); // 두 번째 페이지, 2개씩

        Page<OrderItemEntity> resultPage = orderItemService.getPagedOrderItems(pageable);

        assertThat(resultPage.getTotalElements()).isEqualTo(10); // 총 10개 (5 orders * 2 items)
        assertThat(resultPage.getTotalPages()).isEqualTo(5);     // 10개 / 2개 per page
        assertThat(resultPage.getNumberOfElements()).isEqualTo(2);

        // Fetch Join이라 LAZY여도 이미 로딩돼있음
        resultPage.getContent().forEach(orderItem ->
                assertThat(orderItem.getOrder()).isNotNull()
        );
    }
}
