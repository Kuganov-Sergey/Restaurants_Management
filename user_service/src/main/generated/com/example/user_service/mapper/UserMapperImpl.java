package com.example.user_service.mapper;

import com.example.user_service.DTO.in.RoleInDTO;
import com.example.user_service.DTO.in.UserInDTO;
import com.example.user_service.DTO.out.UserOutDTO;
import com.example.user_service.entity.RoleEntity;
import com.example.user_service.entity.UserEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.processing.Generated;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-29T10:32:16+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    private final DatatypeFactory datatypeFactory;

    public UserMapperImpl() {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
        }
        catch ( DatatypeConfigurationException ex ) {
            throw new RuntimeException( ex );
        }
    }

    @Override
    public UserOutDTO userEntityToUserOutDTO(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        UserOutDTO.UserOutDTOBuilder userOutDTO = UserOutDTO.builder();

        userOutDTO.id( userEntity.getId() );
        userOutDTO.name( userEntity.getName() );
        userOutDTO.surname( userEntity.getSurname() );
        userOutDTO.lastname( userEntity.getLastname() );
        userOutDTO.email( userEntity.getEmail() );
        userOutDTO.registrationDate( xmlGregorianCalendarToLocalDate( localDateTimeToXmlGregorianCalendar( userEntity.getRegistrationDate() ) ) );
        userOutDTO.phoneNumber( userEntity.getPhoneNumber() );
        Collection<RoleEntity> collection = userEntity.getRoles();
        if ( collection != null ) {
            userOutDTO.roles( new ArrayList<RoleEntity>( collection ) );
        }

        return userOutDTO.build();
    }

    @Override
    public UserEntity UserInDTOToUserEntity(UserInDTO userInDTO) {
        if ( userInDTO == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setName( userInDTO.getName() );
        userEntity.setSurname( userInDTO.getSurname() );
        userEntity.setLastname( userInDTO.getLastname() );
        userEntity.setEmail( userInDTO.getEmail() );
        userEntity.setPassword( userInDTO.getPassword() );
        userEntity.setPhoneNumber( userInDTO.getPhoneNumber() );

        return userEntity;
    }

    @Override
    public RoleEntity roleInDTOToRoleEntity(RoleInDTO roleInDTO) {
        if ( roleInDTO == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setRole( roleInDTO.getRole() );

        return roleEntity;
    }

    private XMLGregorianCalendar localDateTimeToXmlGregorianCalendar( LocalDateTime localDateTime ) {
        if ( localDateTime == null ) {
            return null;
        }

        return datatypeFactory.newXMLGregorianCalendar(
            localDateTime.getYear(),
            localDateTime.getMonthValue(),
            localDateTime.getDayOfMonth(),
            localDateTime.getHour(),
            localDateTime.getMinute(),
            localDateTime.getSecond(),
            localDateTime.get( ChronoField.MILLI_OF_SECOND ),
            DatatypeConstants.FIELD_UNDEFINED );
    }

    private static LocalDate xmlGregorianCalendarToLocalDate( XMLGregorianCalendar xcal ) {
        if ( xcal == null ) {
            return null;
        }

        return LocalDate.of( xcal.getYear(), xcal.getMonth(), xcal.getDay() );
    }
}
