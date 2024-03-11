package org.myungkeun.blog_study.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String nickName;

    @Column
    private String email;

    @Column
    private String password;

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    // repository와 crud 생성
}
