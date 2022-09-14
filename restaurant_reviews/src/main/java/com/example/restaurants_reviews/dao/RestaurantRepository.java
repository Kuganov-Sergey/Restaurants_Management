package com.example.restaurants_reviews.dao;

import com.example.restaurants_reviews.data.RestaurantSmall;
import com.example.restaurants_reviews.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    RestaurantEntity findRestaurantByName(String name);

    @Modifying
    @Query("update RestaurantEntity as r set r.ownerId = :newOwnerId where r.ownerId = :oldOwnerId")
    void updateUserSetStatusForName(@Param("newOwnerId") Long newOwnerId, @Param("oldOwnerId") Long oldOwnerId);

    @Query(value =
            "select restaurants.id as id , max(restaurants.name) as name, avg(reviews.rating) as avg " +
                    "from restaurants " +
                    "join reviews on (restaurants.id = reviews.restaurant_id) " +
                    "group by restaurants.id",
            nativeQuery = true)
    Page<RestaurantSmall> findSmallRestaurants(Pageable pageable);
}
