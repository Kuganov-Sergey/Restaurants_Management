package com.example.restaurants_reviews.dao;

import com.example.restaurants_reviews.data.ReviewSmall;
import com.example.restaurants_reviews.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {

//    @Query(value = "SELECT rev.id, rev.restaurant_id, rev.review, rev.rating FROM reviews as rev " +
//            "WHERE rev.restaurant_id = :id", nativeQuery = true)
//    List<ReviewEntity> getReviewsById(@Param("id") Long id);

    @Query(value = "select rev.id, rev.restaurant_id, substring(rev.review, 1, 50) as review, rev.rating," +
            "case when length(review) < 50 then 'true' else 'false' end full " +
            "from reviews as rev where restaurant_id = :id", nativeQuery = true)
    List<ReviewSmall> getReviewsById(@Param("id") Long id);
}
