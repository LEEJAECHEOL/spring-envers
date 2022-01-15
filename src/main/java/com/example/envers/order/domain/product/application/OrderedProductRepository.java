package com.example.envers.order.domain.product.application;

import com.example.envers.order.domain.product.entity.OrderedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedProductRepository extends JpaRepository<OrderedProduct, Long> {
}
