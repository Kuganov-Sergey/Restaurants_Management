package com.example.restaurants_reviews.service.impl;

import com.example.restaurants_reviews.dao.RestaurantRepository;
import com.example.restaurants_reviews.dto.in.DeleteOwnerInRestaurantOutDTO;
import com.example.restaurants_reviews.dto.in.UpdateOwnerIdRestaurantOutDTO;
import com.example.restaurants_reviews.dto.out.AddOwnerOutDTO;
import com.example.restaurants_reviews.entity.Restaurant;
import com.example.restaurants_reviews.exception.FoundationDateIsExpiredException;
import com.example.restaurants_reviews.exception.IncorrectEmailAddressException;
import com.example.restaurants_reviews.exception.OwnerNotFoundException;
import com.example.restaurants_reviews.exception.RestaurantNotFoundException;
import com.example.restaurants_reviews.service.RestaurantService;
import com.example.restaurants_reviews.util.EmailUtil;
import com.example.restaurants_reviews.util.PhoneUtil;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final EntityManager entityManager;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, EntityManager entityManager) {
        this.restaurantRepository = restaurantRepository;
        this.entityManager = entityManager;
    }

    private Restaurant restaurantNotFoundCheck(String name) throws RestaurantNotFoundException {
        Optional<Restaurant> optionalRestaurant = Optional.ofNullable(restaurantRepository.findRestaurantByName(name));
        if (optionalRestaurant.isEmpty()) {
            throw new RestaurantNotFoundException();
        }
        return optionalRestaurant.get();
    }

    @Override
    public String getDescriptionByName(String name) throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantNotFoundCheck(name);
        return restaurant.getDescription();
    }

    @Override
    @Transactional
    public Page<Restaurant> getAllRestaurants(Pageable pageable) {
        return restaurantRepository.findAll(pageable);
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        String phone = restaurant.getPhoneNumber();
        if (phone == null || phone.equals("absent")) {
            restaurant.setPhoneNumber("absent");
        } else {
            try {
                restaurant.setPhoneNumber(PhoneUtil.reformatRuTelephone(phone));
            } catch (NumberParseException e) {
                e.printStackTrace();
            }
        }
        restaurantRepository.save(restaurant);
    }

    @Override
    @Transactional
    public void updateDescriptionByName(String name, String description) throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantNotFoundCheck(name);
        restaurant.setDescription(description);
        addRestaurant(restaurant);
    }

    @Override
    public Restaurant findRestaurantByName(String name) {
        return restaurantRepository.findRestaurantByName(name);
    }

    @Override
    public long addPhoneByRestaurantName(String name, String phone) throws RestaurantNotFoundException,
            NumberParseException {
        Restaurant restaurant = restaurantNotFoundCheck(name);
        restaurant.setPhoneNumber(PhoneUtil.reformatRuTelephone(phone));
        return restaurantRepository.save(restaurant).getId();

    }

    @Override
    public void addEmailAddressByName(String name, String emailAddress) throws IncorrectEmailAddressException,
            RestaurantNotFoundException {
        Restaurant restaurant = restaurantNotFoundCheck(name);
        if (EmailUtil.checkValid(emailAddress)) {
            restaurant.setEmailAddress(emailAddress);
            restaurantRepository.save(restaurant);
        } else {
            throw new IncorrectEmailAddressException("write correct Email Address");
        }
    }

    @Override
    public long addRestaurantByNameAndCreationDate(String name, LocalDate creationDate) throws FoundationDateIsExpiredException {
        if (creationDate == null || LocalDate.now().isBefore(creationDate)) {
            throw new FoundationDateIsExpiredException(name, creationDate);
        }
        Restaurant restaurant = new Restaurant();
        restaurant.setName(name);
        restaurant.setDescription("Absent");
        restaurant.setEmailAddress("Absent");
        restaurant.setPhoneNumber("Absent");
        restaurant.setDate(creationDate);
        restaurantRepository.save(restaurant);
        return restaurant.getId();
    }

    @Override
    public LocalDate getCreationDateByRestaurantName(String name) throws RestaurantNotFoundException {
        Restaurant restaurant = restaurantNotFoundCheck(name);
        return restaurant.getDate();
    }

    @Override
    public Page<Restaurant> getPaginatedAllRestaurants(int pageNum, int pageSize) {
        Pageable paging = PageRequest.of(pageNum, pageSize);
        return restaurantRepository.findAll(paging);
    }

    @Override
    public void addOwner(AddOwnerOutDTO addOwnerOutDTO) {
        //TODO реализовать!
    }

    @Override
    @Transactional
    public void deleteOwner(DeleteOwnerInRestaurantOutDTO deleteOwnerInRestaurantOutDTO) throws OwnerNotFoundException {
        List<Restaurant> restaurants = restaurantRepository
                .findRestaurantByOwnerId(deleteOwnerInRestaurantOutDTO.getOwnerId());
        if (restaurants.isEmpty()) {
            throw new OwnerNotFoundException();
        }
        for (Restaurant r : restaurants) {
            r.setOwnerId(null);
        }
    }

    @Override
    @Transactional
    public void updateOwner(UpdateOwnerIdRestaurantOutDTO updateOwnerIdRestaurantOutDTO) throws OwnerNotFoundException {
        List<Restaurant> restaurants = restaurantRepository
                .findRestaurantByOwnerId(updateOwnerIdRestaurantOutDTO.getOldId());
        if (restaurants.isEmpty()) {
            throw new OwnerNotFoundException();
        }
        for (Restaurant r : restaurants) {
            r.setOwnerId(updateOwnerIdRestaurantOutDTO.getNewId());
        }
    }
}
