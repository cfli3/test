package com.example.demo.service;

import com.example.demo.DemoApplication;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository repository;

    @Test
    public void testGetUserMessage() {
        Integer userId = 1;
        String userLogin = "userLogin1";
        String firstName = "firstName1";
        String lastName = "lastName1";
        String email = "user1@email.com";
        String address = "addLine1";

        User user = new User(userId, userLogin, firstName, lastName, address, email);
        User newUser = userService.createUser(user);

        //User existingUser = repository.findUserByUserId(1);
        User userResult = userService.getUser(userId);

        Assert.assertEquals(userId, userResult.getUserId());
        Assert.assertEquals(userLogin, userResult.getLoginName());
        Assert.assertEquals(firstName, userResult.getFirstName());
        Assert.assertEquals(lastName, userResult.getLastName());
        Assert.assertEquals(email, userResult.getEmail());
        Assert.assertEquals(address, userResult.getAddress());
    }


    //@Ignore
    @Test
    public void testCreateUser() {
        Integer userId = 1;
        String userLogin = "userLogin1";
        String firstName = "firstName1";
        String lastName = "lastName1";
        String email = "user1@email.com";
        String address = "addLine1";

        String encryptedFirstName = userService.encryptMessage(firstName);
        String encryptedLastName = userService.encryptMessage(lastName);
        String encryptedEmail = userService.encryptMessage(email);
        String encryptedAddress = userService.encryptMessage(address);

        User user = new User(userId, userLogin, firstName, lastName, address, email);
        User newUser = userService.createUser(user);

        Assert.assertEquals(userId, newUser.getUserId());
        Assert.assertEquals(userLogin, newUser.getLoginName());
        Assert.assertEquals(encryptedFirstName, newUser.getFirstName());
        Assert.assertEquals(encryptedLastName, newUser.getLastName());
        Assert.assertEquals(encryptedAddress, newUser.getAddress());
        Assert.assertEquals(encryptedEmail, newUser.getEmail());

        List<User> userMsgList = repository.findAll();
        Assert.assertEquals(1, userMsgList.size());
    }

    @Test
    public void testEncryptAndDecryptMessageStringMatch() {
        String originalMessage = "Original message to be encrypted";
        String encryptedString = userService.encryptMessage(originalMessage);
        String decryptedString = userService.decryptMessage(encryptedString);

        Assert.assertEquals(originalMessage, decryptedString);
    }
}

