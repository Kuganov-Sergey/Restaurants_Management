package com.example.user_service.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "roles")
public class RoleEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role")
    private String role;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_roles",
//            joinColumns = { @JoinColumn(name = "user_id") },
//            inverseJoinColumns = { @JoinColumn(name = "role_id") }
//    )
//    private Collection<UserEntity> entities;
}
