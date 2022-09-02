package com.example.restaurants_reviews.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant {

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

    @Column(name = "creation_date")
    private LocalDate date;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 116;
    }
}
