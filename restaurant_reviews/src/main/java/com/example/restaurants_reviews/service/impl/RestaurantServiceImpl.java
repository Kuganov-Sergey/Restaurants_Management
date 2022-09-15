package com.example.restaurants_reviews.service.impl;

import com.example.restaurants_reviews.feign_clients.UserServiceClient;
import com.example.restaurants_reviews.data.RestaurantSmall;
import com.example.restaurants_reviews.data.ReviewSmall;
import com.example.restaurants_reviews.dao.RestaurantRepository;
import com.example.restaurants_reviews.dao.ReviewRepository;
import com.example.restaurants_reviews.dto.in.UpdateOwnerIdRestaurantInDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.dto.out.RestaurantSmallOutDTO;
import com.example.restaurants_reviews.dto.out.ReviewSmallOutDTO;
import com.example.restaurants_reviews.dto.in.UpdateRestaurantInDTO;
import com.example.restaurants_reviews.entity.RestaurantEntity;
import com.example.restaurants_reviews.exception.OwnerNotFoundException;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.mapper.RestaurantMapper;
import com.example.restaurants_reviews.mapper.ReviewMapper;
import com.example.restaurants_reviews.service.RestaurantService;
import com.example.restaurants_reviews.util.PhoneUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final UserServiceClient userServiceClient;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper,
                                 ReviewRepository reviewRepository, ReviewMapper reviewMapper, UserServiceClient userServiceClient) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
        this.userServiceClient = userServiceClient;
    }

    private RestaurantEntity restaurantNotFoundCheck(String name) throws RestaurantNotFoundException {
        Optional<RestaurantEntity> optionalRestaurant = Optional.ofNullable(restaurantRepository.findRestaurantByName(name));
        if (optionalRestaurant.isEmpty()) {
            throw new RestaurantNotFoundException();
        }
        return optionalRestaurant.get();
    }

    @Override
    @Transactional
    public Page<RestaurantEntity> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public void addRestaurant(RestaurantEntity restaurantEntity) {
        String phone = restaurantEntity.getPhoneNumber();
        if (phone == null || phone.equals("absent")) {
            restaurantEntity.setPhoneNumber("absent");
        } else {
            try {
                restaurantEntity.setPhoneNumber(PhoneUtil.reformatRuTelephone(phone));
            } catch (NumberParseException e) {
                e.printStackTrace();
            }
        }
        restaurantRepository.save(restaurantEntity);
    }

    @Override
    @Transactional
    public void updateOwner(UpdateOwnerIdRestaurantInDTO updateOwnerIdRestaurantInDTO) throws OwnerNotFoundException, RestaurantNotFoundException {
        Optional<RestaurantEntity> restaurant = restaurantRepository
                .findById(updateOwnerIdRestaurantInDTO.getOldId());
        if (restaurant.isEmpty()) {
            throw new RestaurantNotFoundException();
        }
        if (userServiceClient.getUser(updateOwnerIdRestaurantInDTO.getNewId())
                .getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new OwnerNotFoundException();
        }
        restaurantRepository.updateUserSetStatusForName(updateOwnerIdRestaurantInDTO.getNewId(),
                updateOwnerIdRestaurantInDTO.getOldId());
    }

    @Override
    @Transactional
    public Page<RestaurantSmallOutDTO> getSmallList(Pageable pageable) {
        Page<RestaurantSmall> smallRestaurants = restaurantRepository.findSmallRestaurants(pageable);
        return smallRestaurants.map(restaurantMapper::restaurantSmallToRestaurantSmallOutDTO);
    }

    @Override
    public RestaurantOutDTO getRestaurantById(Long id) throws RestaurantNotFoundException {
        Optional<RestaurantEntity> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isEmpty()) {
            throw new RestaurantNotFoundException();
        }
        return restaurantMapper.restaurantToRestaurantOutDTO(restaurantOptional.get());
    }

    @Override
    @Transactional
    public void updateRestaurantById(Long id, UpdateRestaurantInDTO restaurant) throws RestaurantNotFoundException, OwnerNotFoundException {
        Optional<RestaurantEntity> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isEmpty()) {
            throw new RestaurantNotFoundException();
        }
        if (userServiceClient.getUser(restaurant.getOwnerId())
                .getStatusCode().equals(HttpStatus.NOT_FOUND)) {
            throw new OwnerNotFoundException();
        }
        restaurantOptional.get().setDescription(restaurant.getDescription());
        restaurantOptional.get().setEmailAddress(restaurant.getEmailAddress());
        restaurantOptional.get().setPhoneNumber(restaurant.getPhoneNumber());
        restaurantOptional.get().setName(restaurant.getName());
        restaurantOptional.get().setOwnerId(restaurant.getOwnerId());
        restaurantOptional.get().setKitchenTypeE(restaurant.getKitchenTypeE());
    }

    @Override
    @Transactional
    public List<ReviewSmallOutDTO> getReviewsByRestaurantId(Long id) {
        List<ReviewSmall> reviewsById = reviewRepository.getReviewsById(id);
        return reviewsById.stream().map(reviewMapper::reviewSmallToReviewSmallOutDTO).toList();
    }

    @Override
    @Transactional
    public Long deleteRestaurantById(Long id) throws RestaurantNotFoundException {
        Optional<RestaurantEntity> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isEmpty()) {
            throw new RestaurantNotFoundException();
        }
        restaurantRepository.deleteById(id);
        return id;
    }
}
