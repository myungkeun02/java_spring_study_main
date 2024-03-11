package org.myungkeun.blog_study.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")

public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_id")
    private int profile_id;

    @Column
    private int age;

    @Column
    private String sex;

    @Column
    private String job;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL)
    private User user;
}
