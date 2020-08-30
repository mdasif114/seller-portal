package com.seller.portal.service;

import com.seller.portal.entities.Bank;
import com.seller.portal.entities.User;
import com.seller.portal.repositories.BankAccountRepository;
import com.seller.portal.validators.BankRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class BankRegistrationService {

    @Autowired
    private BankAccountRepository bankRepo;

    @Autowired
    HttpSession session;

    /**
     * This method is to fetch Bank account details of user from database.
     * and returns the data in transfer object
     *
     * @return user bank details DTO object
     */
    public BankRegistrationDto getBankDetails() {
        Long userId = (Long) session.getAttribute("userId");
        Optional bankDataObject = bankRepo.findById(userId);
        BankRegistrationDto bankDto = new BankRegistrationDto();
        if (bankDataObject.isPresent()) {
            Bank bank = (Bank) bankDataObject.get();
            bankDto.setAccountName(bank.getAccountName());
            bankDto.setAccountNumber(bank.getAccountNumber());
            bankDto.setBankName(bank.getBankName());
            bankDto.setSwiftCode(bank.getSwiftCode());
        }
        return bankDto;
    }

    /**
     * This method is to store Bank account details of user in database
     *
     * @param bankDto it contains the bank data of user filled in
     *                bank details GUI
     */
    public void saveBankDetails(BankRegistrationDto bankDto) {
        Long userId = (Long) session.getAttribute("userId");
        Bank bank = new Bank();
        bank.setAccountName(bankDto.getAccountName());
        bank.setAccountNumber(bankDto.getAccountNumber());
        bank.setBankName(bankDto.getBankName());
        bank.setSwiftCode(bankDto.getSwiftCode());
        bank.setBankId(userId);
        bank.setUser(new User(userId));
        bankRepo.save(bank);
    }

}