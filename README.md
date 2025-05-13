[JPA Fetch Join과 Pagination을 함께 쓸 때 발생하는 문제](https://nonstop-snapper-a75.notion.site/JPA-Fetch-Join-Paging-1f21f679d4cb80f49a11d9c7c5e7c8e4?pvs=73)

### 프로젝트 폴더 구조
```
├─main
│  ├─java
│  │  └─lab
│  │      └─jpa_fetchjoin_paging
│  │          │  JpaFetchjoinPagingApplication.java
│  │          │
│  │          ├─domain
│  │          │  ├─entity
│  │          │  │      OrderEntity.java
│  │          │  │      OrderItemEntity.java
│  │          │  │
│  │          │  └─repository
│  │          │          OrderItemRepository.java
│  │          │          OrderRepository.java
│  │          │
│  │          └─service
│  │                  OrderItemService.java
│  │                  OrderService.java
│  │
│  └─resources
│          application.yml
│
└─test
    └─java
        └─lab
            └─jpa_fetchjoin_paging
                │  JpaFetchjoinPagingApplicationTests.java
                │
                └─service
                        OrderItemServiceTest.java
                        OrderServiceTest.java

```
