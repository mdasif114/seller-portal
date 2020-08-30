package com.seller.portal.service;

import com.seller.portal.SellerPortalApplicationTests;
import com.seller.portal.entities.Identity;
import com.seller.portal.entities.User;
import com.seller.portal.validators.UserRegistrationDto;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestUserRegistrationService extends SellerPortalApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRegistrationService userRegService;

    private MockMvc mockMvc;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TestUserRegistrationService.class);

    @Test
    public void testEmailAlreadyExists() {
        try {
            User user = userRegService.findByEmail("abcdefg@seller.com");
            Assert.assertEquals(user.getEmail(), "abcdefg@seller.com");
        } catch (UsernameNotFoundException unfe) {
            Assert.assertEquals("Invalid username or password.", unfe.getMessage());
        }
    }

    @Test (expected = AssertionError.class)
    public void testMobileNumberAlreadyExists() {
        try {
            userRegService.findByMobileNumber("+123456789");
        //  fail("Mobile Number already registered");
        } catch (ConstraintViolationException ce) {
        }
    }

    @RequestMapping(value = "/user_registration", method = RequestMethod.POST)
    public void test_user_validation() {
        UserRegistrationDto userRegDto = new UserRegistrationDto();
        userRegDto.setAccountType("Individual");
        userRegDto.setEmail("abcdefgh@seller.com");
        userRegDto.setShopBasedIn("France");
        userRegDto.setMobileNumber("+123456789");
        userRegDto.setPassword("Abcd!23456");
        userRegService.save(userRegDto);
    }
/*
    @Test
    public void test_register_user() {
        UserRegistrationDto userRegDto = new UserRegistrationDto();
        userRegDto.setAccountType("Individual");
        userRegDto.setEmail("abcdefgh@seller.com");
        userRegDto.setShopBasedIn("France");
        userRegDto.setMobileNumber("+123456789");
        userRegDto.setPassword("Abcd!23456");
        userRegService.save(userRegDto);
    }*/

    @Test
    @WithMockUser(username = "abcdefg@seller.com")
    public void testEmployee() throws Exception {
        Identity identity;
        mockMvc.perform(get("/identityRegistration")).andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(model().size(2));
    }
}
