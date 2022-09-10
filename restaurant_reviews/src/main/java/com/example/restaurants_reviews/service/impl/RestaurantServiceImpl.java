package com.example.restaurants_reviews.service.impl;

import com.example.restaurants_reviews.controller.data.RestaurantSmall;
import com.example.restaurants_reviews.dao.RestaurantRepository;
import com.example.restaurants_reviews.dto.in.UpdateOwnerIdRestaurantOutDTO;
import com.example.restaurants_reviews.dto.out.RestaurantOutDTO;
import com.example.restaurants_reviews.dto.out.RestaurantSmallOutDTO;
import com.example.restaurants_reviews.dto.out.UpdateRestaurantOutDTO;
import com.example.restaurants_reviews.entity.RestaurantEntity;
import com.example.restaurants_reviews.exception.OwnerNotFoundException;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.mapper.RestaurantMapper;
import com.example.restaurants_reviews.service.RestaurantService;
import com.example.restaurants_reviews.util.PhoneUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    private final RestaurantMapper restaurantMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
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
    public void updateOwner(UpdateOwnerIdRestaurantOutDTO updateOwnerIdRestaurantOutDTO) throws OwnerNotFoundException {
        Optional<RestaurantEntity> restaurant = restaurantRepository
                .findById(updateOwnerIdRestaurantOutDTO.getOldId());
        if (restaurant.isEmpty()) {
            throw new OwnerNotFoundException();
        }
        restaurantRepository.updateUserSetStatusForName(updateOwnerIdRestaurantOutDTO.getNewId(),
                updateOwnerIdRestaurantOutDTO.getOldId());
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
    public void updateRestaurantById(Long id, UpdateRestaurantOutDTO restaurant) throws RestaurantNotFoundException {
        Optional<RestaurantEntity> restaurantOptional = restaurantRepository.findById(id);
        if (restaurantOptional.isEmpty()) {
            throw new RestaurantNotFoundException();
        }
        restaurantOptional.get().setDescription(restaurant.getDescription());
        restaurantOptional.get().setEmailAddress(restaurant.getEmailAddress());
        restaurantOptional.get().setPhoneNumber(restaurant.getPhoneNumber());
        restaurantOptional.get().setName(restaurant.getName());
    }
}
