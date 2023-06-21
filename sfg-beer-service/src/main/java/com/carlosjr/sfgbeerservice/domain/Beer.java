package com.carlosjr.sfgbeerservice.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "beer")
public class Beer {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(length = 36, updatable = false, nullable = false)
    private UUID id;
    @Version
    private Long version;
    @CreationTimestamp
    @Column(updatable = false, name = "created_date")
    private Timestamp createdDate;
    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;
    @Column(name = "beer_name")
    private String beerName;
    @Column(name = "beer_style")
    private String beerStyle;
    @Column(unique = true)
    private Long upc;
    private BigDecimal price;
    @Column(name = "min_on_hand")
    private Integer minOnHand;
    @Column(name = "quantity_to_brew")
    private Integer quantityToBrew;

}
