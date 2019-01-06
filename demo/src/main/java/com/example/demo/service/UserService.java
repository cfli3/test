package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Encryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    public UserRepository userRepository;

    @Transactional
    public User getUser(Integer userId) {

        User existingUser = userRepository.findUserByUserId(userId);

        if (existingUser == null) {
            throw new ResourceNotFoundException(String.format("No UserMessage found for user with userId [%d]", userId));
        }

        existingUser.setFirstName(decryptMessage(existingUser.getFirstName()));
        existingUser.setLastName(decryptMessage(existingUser.getLastName()));
        existingUser.setEmail(decryptMessage(existingUser.getEmail()));
        existingUser.setAddress(decryptMessage(existingUser.getAddress()));

        return existingUser;
    }


    //create user message
    @Transactional
    public User createUser(User user) {

        Integer userId = user.getUserId();
        String userLogin = user.getLoginName();
        String encFirstName = encryptMessage(user.getFirstName());
        String encLastName = encryptMessage(user.getLastName());
        String encEmail = encryptMessage(user.getEmail());
        String encAddress = encryptMessage(user.getAddress());

        User userEntity = new User(userId, userLogin, encFirstName, encLastName, encAddress, encEmail);
        User existingUser = userRepository.save(userEntity);
        return existingUser;
    }

    //encrypt message
    public String encryptMessage(String message) {
        Encryptor encryptor = new Encryptor();
        String encMessage = null;

        try {
            encMessage = encryptor.encryptMessage(message);
        } catch (Exception exc) {
            System.out.println("Exception occurred while encrypting message");
            exc.printStackTrace();
        }

        return encMessage;
    }

    //decrypt message
    public String decryptMessage(String encMessage) {
        Encryptor encryptor = new Encryptor();
        String decryptedString = null;

        try {
            decryptedString = encryptor.decryptMessage(encMessage);
        } catch (Exception exc) {
            System.out.println("Exception occurred while decrypting message");
            exc.printStackTrace();
        }
        return decryptedString;
    }
}