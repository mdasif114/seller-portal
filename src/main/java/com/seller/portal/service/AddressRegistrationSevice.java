package com.seller.portal.service;

import com.seller.portal.entities.Address;
import com.seller.portal.entities.User;
import com.seller.portal.repositories.AddressRepository;
import com.seller.portal.validators.AddressRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.seller.portal.utils.Constants.ADDRESS;
import static com.seller.portal.utils.Constants.USERID;

@Slf4j
@Service
public class AddressRegistrationSevice {

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    HttpSession session;

    /**
     * This method is to fetch Address details of user from database.
     * and returns the data in transfer object.
     *
     * @return address details DTO object
     */
    public AddressRegistrationDto getUserAddress() {
        log.info("from getUserAddresss");
        AddressRegistrationDto addDto = new AddressRegistrationDto();
        Address address = getUserAddressData();
        if (address != null) {
            addDto.setCity(address.getCity());
            addDto.setCountry(address.getCountry());
            addDto.setLineOne(address.getLineOne());
            addDto.setLineTwo(address.getLineTwo());
            addDto.setPostCode(address.getPostCode());
            session.setAttribute(ADDRESS, createSessionObj(addDto));
        }
        return addDto;
    }

    private Address getUserAddressData() {
        Long userId = (Long) session.getAttribute(USERID);
        Address address = (Address) session.getAttribute(ADDRESS);
        if (address == null) {
            Optional<Address> addressDataFromDb = addressRepo.findById(userId);
            if (addressDataFromDb.isPresent()) {
                log.info("Retrieve data from database. ");
                address = addressDataFromDb.get();
            }
        }
        return address;
    }

    /**
     * This method is to store Bank account details of user in database.
     *
     * @param addressRegistrationDto this object contains user address details
     */
    public void saveUserAddress(AddressRegistrationDto addressRegistrationDto) {
        Address address = createAddressEntityData(addressRegistrationDto);
        Address addObjForSession = createSessionObj(addressRegistrationDto);
        if (!addObjForSession.equals(session.getAttribute(ADDRESS))) {
            addressRepo.save(address);
            log.info("Address saved successfully in database: " + address);
            session.setAttribute(ADDRESS, addObjForSession);
        }
    }

    private Address createAddressEntityData(AddressRegistrationDto addDto) {
        Long userId = (Long) session.getAttribute(USERID);
        Address address = new Address();
        address.setLineOne(addDto.getLineOne());
        address.setLineTwo(addDto.getLineTwo());
        address.setCity(addDto.getCity());
        address.setCountry(addDto.getCountry());
        address.setPostCode(addDto.getPostCode());
        address.setAddressId(userId);
        address.setUser(new User(userId));
        return address;
    }

    private Address createSessionObj(AddressRegistrationDto addDto) {
        return new Address(addDto.getCity(), addDto.getCountry(), addDto.getLineOne(),
                addDto.getLineTwo(), addDto.getPostCode());
    }
}
