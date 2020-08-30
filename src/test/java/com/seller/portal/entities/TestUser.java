package com.seller.portal.entities;

import com.seller.portal.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@AutoConfigureTestEntityManager
public class TestUser {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    @WithMockUser(username = "abcdefg@seller.com")
    public void testExample() throws Exception {
        this.entityManager.persist(new User("Corporate", "Singapore", "asif@yahoo.com", "+1234567890", "Asif1234" ));
        User user = this.repository.findByEmail("asif.1@yahoo.com");
        assertThat(user.getEmail()).isEqualTo("asif@yahoo.com");
        assertThat(user.getMobileNumber()).isEqualTo("+1234567890");
        assertThat(user.getAccountType()).isEqualTo("Corporate");
    }
}

