package com.seller.portal.service;

import com.seller.portal.SellerPortalApplicationTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

/*@RunWith(SpringRunner.class)
@WebMvcTest(AddressRegistrationController.class)*/
public class TestAddressRegistrationService extends SellerPortalApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;


    //private MockMvc mockMvc;

/*    @Before()
    public void setup() {
        MockMvcWebClientBuilder.webAppContextSetup(webApplicationContext).build();
        HtmlPage createMsgFormPage = webClient.getPage("http://localhost:8080/identityRegistration");
        //mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @WithMockUser(username = "asif.shaik97@gmail.com")
    public void testEmployee() throws Exception {
        Identity identity;
        mockMvc.perform(post("/addressRegistration")).andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(status().isOk()).andExpect(content().string("China Mainland"));
    }*/
}
