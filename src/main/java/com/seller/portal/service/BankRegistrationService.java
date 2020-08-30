package com.seller.portal.service;

import com.seller.portal.entities.Bank;
import com.seller.portal.entities.User;
import com.seller.portal.repositories.BankAccountRepository;
import com.seller.portal.validators.BankRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import java.util.Optional;

import static com.seller.portal.utils.Constants.BANK;
import static com.seller.portal.utils.Constants.USERID;

@Slf4j
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
        BankRegistrationDto bankDto = new BankRegistrationDto();
        Bank bankDataObject = getBankAccountData();
        if (bankDataObject != null) {
            bankDto.setAccountName(bankDataObject.getAccountName());
            bankDto.setAccountNumber(bankDataObject.getAccountNumber());
            bankDto.setBankName(bankDataObject.getBankName());
            bankDto.setSwiftCode(bankDataObject.getSwiftCode());
            session.setAttribute(BANK, createSessionObj(bankDto));
        }
        return bankDto;
    }

    private Bank getBankAccountData() {
        Long userId = (Long) session.getAttribute(USERID);
        Bank bank = (Bank) session.getAttribute(BANK);
        if (bank == null) {
            Optional<Bank> bankDataFromDb = bankRepo.findById(userId);
            if (bankDataFromDb.isPresent()) {
                log.info("Retrieve data from database ");
                bank = bankDataFromDb.get();
            }
        }
        return bank;
    }

    /**
     * This method is to store Bank account details of user in database
     */
    public void saveBankDetails(BankRegistrationDto bankRegistrationDto) {
        Bank bank = createBankEntityData(bankRegistrationDto);
        Bank bankObjForSession = createSessionObj(bankRegistrationDto);
        if (!bankObjForSession.equals(session.getAttribute(BANK))) {
            bankRepo.save(bank);
            log.info("Bank saved successfully in database: " + bank);
            session.setAttribute(BANK, bankObjForSession);
        }
    }

    private Bank createBankEntityData(BankRegistrationDto bankDto) {
        Long userId = (Long) session.getAttribute(USERID);
        Bank bank = new Bank();
        bank.setAccountName(bankDto.getAccountName());
        bank.setAccountNumber(bankDto.getAccountNumber());
        bank.setBankName(bankDto.getBankName());
        bank.setSwiftCode(bankDto.getSwiftCode());
        bank.setBankId(userId);
        bank.setUser(new User(userId));
        return bank;
    }

    private Bank createSessionObj(BankRegistrationDto bankDto) {
        return new Bank(bankDto.getAccountName(), bankDto.getAccountNumber(), bankDto.getBankName(), bankDto.getSwiftCode());
    }

}