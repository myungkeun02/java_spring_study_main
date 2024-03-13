package org.myungkeun.shop_study.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "name")
    private String name;

    @Column(name = "zipcode")
    private int zipcode;

    @Column(name = "addr")
    private String addr;

    @Column(name = "addrDetail")
    private String addrDetail;

    @Column(name = "phone")
    private String phone;

    @Column(name = "request")
    private String request;


}
