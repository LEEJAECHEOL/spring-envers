package com.example.envers.common.entity;

import lombok.*;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * revison은 오라클 디비의 id(PK) 와 비슷하다
 * 모든 *_history의 rev가 여기서 모인다.
 * 기본 rev는 int 이기때문에 long 으로 변경
 */

@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@RevisionEntity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "rev_info")
public class CustomRevisionEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @RevisionNumber
  @EqualsAndHashCode.Include
  @Column(name = "REV")
  private Long id;

  @RevisionTimestamp
  @EqualsAndHashCode.Include
  @Column(name = "REVTSTMP")
  private Long timestamp;

  private String entityName;

  @Transient
  public Date getRevisionDate() {
    return new Date(this.timestamp);
  }

}
