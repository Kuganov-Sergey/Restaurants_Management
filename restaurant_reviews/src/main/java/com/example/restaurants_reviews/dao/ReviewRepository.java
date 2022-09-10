package com.example.restaurants_reviews.dao;

import com.example.restaurants_reviews.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

    @Query(value = "SELECT rev.id, rev.restaurant_id, rev.review, rev.rating FROM reviews as rev " +
            "WHERE rev.restaurant_id = :id", nativeQuery = true)
    List<ReviewEntity> getReviewsById(@Param("id") Long id);

    @Query(value = "SELECT avg(rating) from reviews " +
            "join restaurants as re where re.id = :id", nativeQuery = true)
    double getRatingByName(@Param("id") Long id);


}
