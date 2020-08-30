package com.seller.portal.service;

import com.seller.portal.entities.Address;
import com.seller.portal.repositories.UserRepository;
import com.seller.portal.validators.AddressRegistrationDto;
import com.seller.portal.validators.BankRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddressRegistrationTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private AddressRegistrationSevice addRegService;

    private MockMvc mockMvc;

    MockHttpSession session;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        session = new MockHttpSession();
    }

    @Test
    public void testAddRegistrationDetails() {
        AddressRegistrationDto addDto = new AddressRegistrationDto();
        addDto.setLineOne("1234");
        addDto.setLineTwo("4567");
        addDto.setPostCode("01233");
        addDto.setCountry("China Mainland");
        addDto.setCity("Shanghai");
        addRegService.saveUserAddress(addDto);
    }

    @Test
    @WithMockUser(username = "abcdefgh@seller.com")
    public void testGetRegistrationDetails() throws Exception {
        session.setAttribute("userId", 123L);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/addressRegistration").session(session);
        this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("addressRegistration"))
                .andExpect(model().attributeExists("address"));
    }
}
