package com.example.practicestart.service;

import com.example.practicestart.domain.Item;
import com.example.practicestart.domain.Order;
import com.example.practicestart.domain.OrderItem;
import com.example.practicestart.repository.ItemRepository;
import com.example.practicestart.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long itemId, int count){
        // 엔티티 조회
        Item findItem = itemRepository.findById(itemId).get();
        // 주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getPrice(), count);
        // 주문 생성
        Order order = Order.createOrder(orderItem);
        // 주문 저장
        orderRepository.save(order);
        // 반환
        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId){
        // 주문 엔티티 조회
        Order findOrder = orderRepository.findById(orderId).get();
        // 주문 취소
        findOrder.cancel();
    }

    public List<Order> findOrders(){
        // 모든 주문 상품 조회 후 반환
        return orderRepository.findAll();
    }
}
