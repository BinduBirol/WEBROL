package com.birol.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.birol.persistence.model.NewLocationToken;
import com.birol.persistence.model.PasswordResetToken;
import com.birol.persistence.model.User;
import com.birol.persistence.model.VerificationToken;
import com.birol.web.dto.UserDto;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void deleteUser(User user);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

    void createPasswordResetTokenForUser(User user, String token);

    User findUserByEmail(String email);

    PasswordResetToken getPasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    Optional<User> getUserByID(long id);
    

    void changeUserPassword(User user, String password);

    boolean checkIfValidOldPassword(User user, String password);

    String validateVerificationToken(String token);

    String generateQRUrl(User user) throws UnsupportedEncodingException;

    User updateUser2FA(boolean use2FA);

    List<String> getUsersFromSessionRegistry();

    NewLocationToken isNewLoginLocation(String username, String ip);

    String isValidNewLocationToken(String token);

    void addUserLocation(User user, String ip);

	List<User> findAllUser();

	void updateUserInfo(User user);
}
