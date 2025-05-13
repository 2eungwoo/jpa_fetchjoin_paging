package lab.jpa_fetchjoin_paging.service;

import jakarta.transaction.Transactional;
import lab.jpa_fetchjoin_paging.domain.entity.OrderEntity;
import lab.jpa_fetchjoin_paging.domain.entity.OrderItemEntity;
import lab.jpa_fetchjoin_paging.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        createTestOrders();
    }

    private void createTestOrders() {
        for (int i = 1; i <= 10; i++) {
            OrderEntity order = new OrderEntity();
            order.setName("Order " + i);

            for (int j = 1; j <= 4; j++) {
                OrderItemEntity item = new OrderItemEntity();
                item.setItemName("Item " + j);
                item.setOrder(order);
                order.getOrderItems().add(item);
            }

            orderRepository.save(order);
        }
    }

    @Test
    @Transactional
    public void testGetPagedOrders() {
        // given : 두번째 페이지, 4개 아이템
        PageRequest pageable = PageRequest.of(1, 4);

        // when
        Page<OrderEntity> resultPage = orderService.getPagedOrders(pageable);

        // then
        assertThat(resultPage.getTotalElements()).isEqualTo(10);
        assertThat(resultPage.getTotalPages()).isEqualTo(3);
        resultPage.getContent().forEach(order ->
                assertThat(order.getOrderItems()).isNotEmpty()
        );
    }

    @Test
    @Transactional
    public void testGetPagedOrdersBadCase() {
        // given : 두번째 페이지, 4개 아이템
        PageRequest pageable = PageRequest.of(1, 4);

        // when
        Page<OrderEntity> resultPage = orderService.getPagedOrdersBadCase(pageable);

        // then
        assertThat(resultPage.getSize()).isEqualTo(4);  // 전체 페이지 수
        resultPage.getContent().forEach(order ->
                assertThat(order.getOrderItems()).isNotEmpty()
        );
    }
}