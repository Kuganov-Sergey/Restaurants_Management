package com.example.restaurants_reviews.dao;

import com.example.restaurants_reviews.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query(value = "SELECT review FROM reviews " +
            "JOIN restaurants AS re WHERE re.name = :name", nativeQuery = true)
    List<String> getReviewsByName(@Param("name") String name);

    @Query(value = "SELECT avg(rating) from reviews " +
            "join restaurants as re where re.name = :name", nativeQuery = true)
    double getRatingByName(@Param("name") String name);
}
