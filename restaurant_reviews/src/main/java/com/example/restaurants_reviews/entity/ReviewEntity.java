package com.example.restaurants_reviews.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "reviews")
public class ReviewEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurantId;

    @Column(name = "review")
    private String review;

    @Column(name = "rating")
    private Integer rating;

    public ReviewEntity(RestaurantEntity restaurantId, String review, Integer rating) {
        this.restaurantId = restaurantId;
        this.review = review;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewEntity reviewEntity1 = (ReviewEntity) o;
        return Objects.equals(id, reviewEntity1.id);
    }

    @Override
    public int hashCode() {
        return 16;
    }
}
