package com.example.envers.order.entity;

import com.example.envers.common.entity.BaseEntity;
import com.example.envers.order.domain.product.entity.OrderedProduct;
import com.example.envers.order.enums.OrderStatus;
import com.example.envers.user.entity.User;
import lombok.*;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Audited
@AuditOverride(forClass = BaseEntity.class)
public class Orders extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_id")
  private Long id;

  // User 클래스를 이력 대상에서 제외
  @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private OrderStatus status;

  @NotAudited
  @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
  private List<OrderedProduct> orderedProducts = new ArrayList<>();

  @NotAudited // 이력 대상 제외
  private String text;

  public void updateStatus(OrderStatus status) {
    this.status = status;
  }

}
