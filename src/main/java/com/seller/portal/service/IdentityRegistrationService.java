package com.seller.portal.service;

import com.seller.portal.entities.Identity;
import com.seller.portal.entities.User;
import com.seller.portal.repositories.IdentityRepository;
import com.seller.portal.validators.IdentityRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@Service
public class IdentityRegistrationService {

    @Autowired
    private IdentityRepository identityRepo;

    @Autowired
    HttpSession session;

    /**
     * This method is to fetch Identity details of user from database.
     * and returns the data in transfer object
     *
     * @return user identity details DTO object
     * @throws Exception
     */
    public IdentityRegistrationDto getIdentityDetails() {
        Long userId = (Long) session.getAttribute("userId");
        Optional identityObject = identityRepo.findById(userId);
        IdentityRegistrationDto identityDto = new IdentityRegistrationDto();
        if (identityObject.isPresent()) {
            Identity identity = (Identity) identityObject.get();
            identityDto.setDocumentNumber(identity.getDocumentNumber());
            identityDto.setDocumentType(identity.getDocumentType());
        }
        return identityDto;
    }

    /**
     * This method is to store Identity details of user in database
     *
     * @param identityRegistrationDto it contains the identity details of user.
     */
    public void saveIdentityDetails(IdentityRegistrationDto identityRegistrationDto) {
        Long userId = (Long) session.getAttribute("userId");
        Identity identity = new Identity();
        identity.setDocumentType(identityRegistrationDto.getDocumentType());
        identity.setDocumentNumber(identityRegistrationDto.getDocumentNumber());
        identity.setIdentityId(userId);
        identity.setUser(new User(userId));
        identityRepo.save(identity);
    }
}
