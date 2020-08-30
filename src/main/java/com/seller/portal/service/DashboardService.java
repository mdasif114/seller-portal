package com.seller.portal.service;

import com.seller.portal.repositories.AddressRepository;
import com.seller.portal.repositories.BankAccountRepository;
import com.seller.portal.repositories.IdentityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class DashboardService {

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private IdentityRepository identityRepo;

    @Autowired
    private BankAccountRepository bankRepo;

    @Autowired
    private HttpSession session;

    private Long userId;

    /**
     * This function is used to store the registration status of user to display
     */
    public void checkRegistrationStatus() {
        getUserIdFromSession();
        if (!registrationCompleted() && isAddressRegistered() && isIdentityRegistered() && isBankAccountRegistered()) {
            session.setAttribute("dashboard", true);
        }
    }

    private boolean isBankAccountRegistered() {
        Optional bankSessionObj = Optional.ofNullable(session.getAttribute("bank"));
        return bankSessionObj.isPresent() || bankRepo.existsById(userId);
    }

    private boolean isIdentityRegistered() {
        Optional identitySessionObj = Optional.ofNullable(session.getAttribute("identity"));
        return identitySessionObj.isPresent() || identityRepo.existsById(userId);
    }

    private boolean isAddressRegistered() {
        Optional addressSessionObj = Optional.ofNullable(session.getAttribute("address"));
        return addressSessionObj.isPresent() || addressRepo.existsById(userId);
    }

    private boolean registrationCompleted() {
        return Optional.ofNullable(session.getAttribute("dashboard")).isPresent();
    }

    private void getUserIdFromSession() {
        Optional userIdSessionObj = Optional.ofNullable(session.getAttribute("userId"));
        if (userIdSessionObj.isPresent()) {
            this.userId = (Long) userIdSessionObj.get();
        }
    }
}
