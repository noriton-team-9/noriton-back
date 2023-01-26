package com.noriton.team9.service;

import com.noriton.team9.domain.Item;
import com.noriton.team9.domain.Member;
import com.noriton.team9.domain.Orders;
import com.noriton.team9.repository.ItemRepository;
import com.noriton.team9.repository.MemberRepository;
import com.noriton.team9.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    /**
     * 주문 생성
     * */
    @Transactional
    public Orders saveOrder(String address, String size, int count, String phoneNumber, Long memberId, Long itemId){
        // 엔티티 조회
        Optional<Item> optionalItem = itemRepository.findById(itemId);
        Member member = memberRepository.findOne(memberId);
        // Todo : itemId를 pk로 가지는 item이 없는 경우 exception터트리기
        Item item = optionalItem.get();

        Orders order = Orders.createOrder(count, address, size, phoneNumber, member, item);
        return orderRepository.save(order);
    }

    /**
     * 주문 전체 조회
     * */
    public List<Orders> findOrders() {
        return orderRepository.findAll();
    }

    /**
     * 주문 삭제 -> 주문상품에 대해 발송처리 한 경우
     * */
    @Transactional
    public void deleteOrder(Long orderId){
        orderRepository.delete(orderId);
    }
}
