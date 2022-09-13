package com.example.restaurants_reviews.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Getter
@Setter
@Table(name = "restaurants")
public class RestaurantEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;

    @Basic
    @Column(name = "email_address")
    private String emailAddress;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDate date;

    @UpdateTimestamp
    @Column(name = "updated_datetime")
    private LocalDateTime updatedDatetime;

    @Column(name = "owner_id")
    private Long ownerId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantEntity that = (RestaurantEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 116;
    }
}
