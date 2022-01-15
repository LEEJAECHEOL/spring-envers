package com.example.envers;

import com.example.envers.order.application.OrderRepository;
import com.example.envers.order.domain.product.application.OrderedProductRepository;
import com.example.envers.order.domain.product.entity.OrderedProduct;
import com.example.envers.order.entity.Orders;
import com.example.envers.order.enums.OrderStatus;
import com.example.envers.product.application.ProductRepository;
import com.example.envers.product.entity.Product;
import com.example.envers.user.application.UserRepository;
import com.example.envers.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class EnversTest {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private OrderedProductRepository orderedProductRepository;

  @Test
  void OrderUpdateTest() {
    Orders orders = orderRepository.findById(1L).get();
    orders.updateStatus(OrderStatus.REFUND);
    orderRepository.save(orders);
  }

  @Test
  void OrderTest() {
    Orders orders = orderRepository.save(Orders.builder()
        .status(OrderStatus.ORDERED)
        .text("주문 ㅎㅎ")
        .user(User.builder().id(1L).build())
      .build());

    orderedProductRepository.save(OrderedProduct.builder()
        .orders(orders)
        .product(Product.builder().id(1L).build())
        .orderCount(1)
      .build());

  }

  @Test
  void saveProduct(){
    productRepository.save(Product.builder().productName("상품1").price(100).build());
    productRepository.save(Product.builder().productName("상품2").price(200).build());
    productRepository.save(Product.builder().productName("상품3").price(300).build());
  }

  @Test
  void saveUserTest() {
    userRepository.save(User.builder().name("이름").build());

  }

}
